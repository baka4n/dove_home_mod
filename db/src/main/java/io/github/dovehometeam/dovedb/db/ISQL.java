package io.github.dovehometeam.dovedb.db;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public interface ISQL {
    AtomicReference<ConcurrentHashMap<String,? extends IEntity<?>>> value();
    AtomicReference<Path> directory();
    default Path createDir(MinecraftServer server, String... pathNames) {
        if (directory().get() != null) {
            return directory().get();
        }
        Path path = server.getWorldPath(LevelResource.ROOT);
        directory().set(path);
        for (String pathName : pathNames) {
            path = path.resolve(pathName);
        }

        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            try {
                Files.delete(path);
                Files.createDirectories(path);
            } catch (IOException ignored) {}
        }
        return path;
    }

    static <T extends IEntity<T>> void autoSave(Path path, ConcurrentHashMap<String,T> entries) {
        entries.forEach((uuid, t) -> {
            Path resolve = path.resolve(uuid);
            try(ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(resolve))) {
                out.writeObject(t);
            } catch (IOException ignored) {}
        });
    }

    @SuppressWarnings("unchecked")
    default <T> void load(MinecraftServer server, String pathName, ConcurrentHashMap<String, T> entities) {
        Path path = createDir(server, pathName);
        try(Stream<Path> list = Files.list(path)) {
            list.forEach(path1 -> {
                try(ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path1))) {
                    entities.put(path1.toFile().getName(), (T) objectInputStream.readObject());
                } catch (IOException | ClassNotFoundException ignored) {}
            });
        } catch (IOException ignored) {}
    }

    void autoSave(Path... paths);
    default void autoSave(MinecraftServer server,String... paths) {
        autoSave(Arrays.stream(paths).map(s -> createDir(server, s)).toArray(value -> new Path[0]));
    }

    @SubscribeEvent
    void serverStarting(ServerStartingEvent event);

    @SubscribeEvent
    void playerJoinServer(PlayerEvent.PlayerLoggedInEvent event);

    @SubscribeEvent
    void tick(TickEvent.ServerTickEvent event);

    @SubscribeEvent
    void olayerLevelServer(PlayerEvent.PlayerLoggedInEvent event);
}
