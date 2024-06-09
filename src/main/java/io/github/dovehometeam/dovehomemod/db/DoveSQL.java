package io.github.dovehometeam.dovehomemod.db;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static com.mojang.text2speech.Narrator.LOGGER;

public class DoveSQL {

    static final Map<String,DoveEntity> entities = new HashMap<>();//uuid entry
    static final AtomicInteger time = new AtomicInteger();
    @SubscribeEvent
    public static void serverStarting(ServerStartingEvent event) {
        Path dSqlPath = createDir(event.getServer());
        try(Stream<Path> list = Files.list(dSqlPath)) {
            list.forEach(path -> {
                try(ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))) {
                    DoveEntity doveEntity = (DoveEntity) objectInputStream.readObject();
                    entities.put(path.toFile().getName(), doveEntity);
                } catch (IOException | ClassNotFoundException ignored) {}
            });
        } catch (IOException ignored) {}

    }

    @SubscribeEvent
    public static void serverStopped(ServerStoppedEvent event) {
        Path dSqlPath = createDir(event.getServer());
        autoSave(dSqlPath);
    }

    @SubscribeEvent
    public static void playerJoinServer(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        DoveEntity.getAndCreate(player.getStringUUID());
    }

    @SubscribeEvent
    public static void tick(TickEvent.ServerTickEvent event) {
        time.getAndAdd(1);
        if (time.get() > 3000) {
            autoSave(createDir(event.getServer()));
            LOGGER.info("save to dove data");
            time.set(0);
        }
    }

    public static void autoSave(Path dSqlPath) {
        entities.forEach((uuid, doveEntity) -> {
            Path resolve = dSqlPath.resolve(uuid);
            try(ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(resolve))) {
                out.writeObject(doveEntity);
            } catch (IOException ignored) {}
        });
    }

    public static Path createDir(MinecraftServer server) {
        Path dSqlPath = server.getWorldPath(LevelResource.ROOT).resolve("doveSql");
        try {
            Files.createDirectories(dSqlPath);
        } catch (IOException e) {
            try {
                Files.delete(dSqlPath);
                Files.createDirectories(dSqlPath);
            } catch (IOException ignored) {}
        }
        return dSqlPath;
    }
}
