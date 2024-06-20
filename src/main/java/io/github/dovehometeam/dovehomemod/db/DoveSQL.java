package io.github.dovehometeam.dovehomemod.db;

import io.github.dovehometeam.dovehomemod.auxiliary.IPelletSettings;
import io.github.dovehometeam.dovehomemod.db.team.DoveTeamEntity;
import io.github.dovehometeam.dovehomemod.infrastructure.Config;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static com.mojang.text2speech.Narrator.LOGGER;

public class DoveSQL {

    public static final ConcurrentHashMap<String,DoveEntity> entities = new ConcurrentHashMap<>();//uuid entry
    public static final ConcurrentHashMap<String, DoveTeamEntity> teamEntities = new ConcurrentHashMap<>();//队伍创建生成
    public static final LinkedList<DoveTeamEntity.TeamChunkPos> tcpList = new LinkedList<>();
    public static final AtomicReference<Path> pelletUseReference = new AtomicReference<>();//存储物品uuid是否使用过
    public static final AtomicInteger time = new AtomicInteger();

    @SubscribeEvent
    public static void serverStarting(ServerStartingEvent event) {

        Path pelletUse = createDir(event.getServer(), "pelletUse");
        pelletUseReference.set(pelletUse);
        for (Map.Entry<ResourceKey<Item>, Item> entry : ForgeRegistries.ITEMS.getEntries()) {
            if (entry.getValue() instanceof IPelletSettings) {
                createDir(event.getServer(), "pelletUse", entry.getKey().location().toString());
            }
        }

        load(event.getServer(), "doveSql", entities);
        load(event.getServer(), "teamSql", teamEntities);
        for (Map.Entry<String, DoveTeamEntity> entry : teamEntities.entrySet()) {
            DoveTeamEntity value = entry.getValue();
            tcpList.addAll(value.claimChunk);
            for (String player : value.players) DoveEntity.playerTeamClaimTcp.put(player, value.claimChunk);
        }
//        Path dSqlPath = createDir(event.getServer(), "doveSql");
//        try(Stream<Path> list = Files.list(dSqlPath)) {
//            list.forEach(path -> {
//                try(ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))) {
//                    entities.put(path.toFile().getName(), (DoveEntity) objectInputStream.readObject());
//                } catch (IOException | ClassNotFoundException ignored) {}
//            });
//        } catch (IOException ignored) {}
//        Path teamSqlPath = createDir(event.getServer(), "teamSql");
//        try(Stream<Path> list = Files.list(teamSqlPath)) {
//            list.forEach(path -> {
//                try(ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))) {
//                    teamEntities.put(path.toFile().getName(), (DoveTeamEntity) objectInputStream.readObject());
//                } catch (IOException | ClassNotFoundException ignored) {}
//            });
//        } catch (IOException ignored) {}
    }


    @SuppressWarnings("unchecked")
    public static <T> void load(MinecraftServer server, String pathName, ConcurrentHashMap<String, T> entities) {
        Path path = createDir(server, pathName);
        try(Stream<Path> list = Files.list(path)) {
            list.forEach(path1 -> {
                try(ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path1))) {
                    entities.put(path1.toFile().getName(), (T) objectInputStream.readObject());
                } catch (IOException | ClassNotFoundException ignored) {}
            });
        } catch (IOException ignored) {}
    }

    @SubscribeEvent
    public static void serverStopped(ServerStoppedEvent event) {
        autoSave(createDir(event.getServer(),"doveSql"), createDir(event.getServer(), "teamSql"));
    }

    @SubscribeEvent
    public static void playerJoinServer(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        DoveEntity.getAndCreate(player.getStringUUID());
    }

    @SubscribeEvent
    public static void tick(TickEvent.ServerTickEvent event) {
        time.getAndAdd(1);
        if (time.get() > Config.autoSaveSQL) {
            autoSave(createDir(event.getServer(),"doveSql"), createDir(event.getServer(), "teamSql"));
            LOGGER.info("save to dove data");
            time.set(0);
        }
    }

    public static void autoSave(Path dSqlPath, Path teamSqlPath) {
        entities.forEach((uuid, doveEntity) -> {
            Path resolve = dSqlPath.resolve(uuid);
            try(ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(resolve))) {
                out.writeObject(doveEntity);
            } catch (IOException ignored) {}
        });
        teamEntities.forEach((uuid, doveTeamEntity) -> {
            Path resolve = teamSqlPath.resolve(uuid);
            try(ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(resolve))) {
                out.writeObject(doveTeamEntity);
            } catch (IOException ignored) {}
        });
    }

    public static Path createDir(MinecraftServer server, String... pathNames) {
        Path path = server.getWorldPath(LevelResource.ROOT);
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
}
