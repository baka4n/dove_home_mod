package io.github.dovehometeam.dovedb.db.base;

import io.github.dovehometeam.dovedb.db.BaseSQL;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class DoveSQL extends BaseSQL {

    @Override
    public void autoSave(Path... paths) {

    }

    @Override
    public void serverStarting(ServerStartingEvent event) {
        directory.set(createDir(event.getServer(),"dove", "base"));
        entities.set(new ConcurrentHashMap<>());
    }


    @Override
    public void playerJoinServer(PlayerEvent.PlayerLoggedInEvent event) {

    }

    @Override
    public void tick(TickEvent.ServerTickEvent event) {

    }

    @Override
    public void olayerLevelServer(PlayerEvent.PlayerLoggedInEvent event) {

    }
}
