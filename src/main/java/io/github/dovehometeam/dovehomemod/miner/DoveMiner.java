package io.github.dovehometeam.dovehomemod.miner;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DoveMiner {
    @SubscribeEvent
    public static void breakMiner(BlockEvent.BreakEvent event) {
        int playerCycleMax = MinerConfig.playerCycleMax;
        BlockPos pos = event.getPos();
        
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

    }
}
