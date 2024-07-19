package io.github.dovehometeam.dovedb.db.base;

import io.github.dovehometeam.dovedb.config.BaseConfig;
import io.github.dovehometeam.dovedb.db.BaseSQL;
import io.github.dovehometeam.dovedb.db.ISQL;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.ConcurrentHashMap;

@OnlyIn(value = Dist.DEDICATED_SERVER)
public class DoveSQL extends BaseSQL<DoveEntity> {

    public int time;


    @Override
    @SubscribeEvent
    public void serverStarting(ServerStartingEvent event) {
        directory.set(createDir(event.getServer(),"dove", "base"));
        entities.set(new ConcurrentHashMap<>());
    }


    @SuppressWarnings("resource")
    @Override
    @SubscribeEvent
    public void playerJoinServer(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        Level level = player.level();
        if (level.isClientSide())
            return;
        DoveEntity.getOrCreate(player, directory.get(), value().get());
    }

    @Override
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void tick(TickEvent.ServerTickEvent event) {
        time++;
        if (time >= BaseConfig.autoSaveSQL) {
            ISQL.autoSave(directory.get(), entities.get());
            time = 0;
        }
    }

    @Override
    @SubscribeEvent
    public void playerLevelServer(PlayerEvent.PlayerLoggedInEvent event) {
        ISQL.autoSave(event.getEntity(), directory.get(), entities.get());
    }

    @Override
    @SubscribeEvent
    public void serverStopped(ServerStoppedEvent event) {
        ISQL.autoSave(directory.get(), entities.get());
    }
}
