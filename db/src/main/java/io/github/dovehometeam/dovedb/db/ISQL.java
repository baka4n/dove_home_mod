package io.github.dovehometeam.dovedb.db;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public interface ISQL<T extends IEntity<T>> {
    AtomicReference<ConcurrentHashMap<String,T>> value();
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

    static <T extends IEntity<T>> void autoSave(Player player, Path path, ConcurrentHashMap<String, T> entries) {
        String uuid = player.getStringUUID();
        T t = entries.get(uuid);
        if (t != null) {
            try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path.resolve(uuid)))) {
                oos.writeObject(t);
            } catch (IOException ignored) {}
            entries.remove(uuid);
        }
    }

    @SubscribeEvent
    void serverStarting(ServerStartingEvent event);

    @SubscribeEvent
    void playerJoinServer(PlayerEvent.PlayerLoggedInEvent event);

    @SubscribeEvent
    void tick(TickEvent.ServerTickEvent event);

    @SubscribeEvent
    void playerLevelServer(PlayerEvent.PlayerLoggedInEvent event);

    @SubscribeEvent
    void serverStopped(ServerStoppedEvent event);

    default void registry(IEventBus eventBus) {
        eventBus.register(this);
    }
}
