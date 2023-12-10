package io.github.dovehome.dovehomemod.forge.events;

import io.github.dovehome.dovehomemod.forge.core.registry.DoveDimensions;
import io.github.dovehome.dovehomemod.forge.persistence.DoveSavedData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DovePlayerEvents {

    @SubscribeEvent
    public static void firstJoinServer(PlayerEvent.PlayerLoggedInEvent event) {
        Player entity = event.getEntity();


        Level level = entity.getLevel();
        if (level.isClientSide()) {
            return;
        }

        MinecraftServer server = entity.getServer();
        if (server != null) {
            ServerLevel level1 = server.getLevel(DoveDimensions.DESERT_KEY);
            ServerPlayer player = (ServerPlayer) entity;
            DoveSavedData doveSavedData = new DoveSavedData(player.getPersistentData());
            if (level1 != null) {
                if (!doveSavedData.isSecondJoinInServer()) {
                    player.teleportTo(level1, entity.getX(), entity.getY(), entity.getZ(), player.getYRot(), player.getXRot());
                    player.setRespawnPosition(DoveDimensions.DESERT_KEY, player.blockPosition(), player.getRespawnAngle(), true, false);
                    doveSavedData.setSecondJoinInServer(true, player.getPersistentData());
                }
            }
        }
    }
}
