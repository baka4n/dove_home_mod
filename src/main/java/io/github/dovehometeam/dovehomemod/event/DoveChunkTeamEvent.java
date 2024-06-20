package io.github.dovehometeam.dovehomemod.event;

import io.github.dovehometeam.dovehomemod.db.DoveEntity;
import io.github.dovehometeam.dovehomemod.db.DoveSQL;
import io.github.dovehometeam.dovehomemod.db.team.DoveTeamEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;

public class DoveChunkTeamEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void breakBlock(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermissions(4)) {
            return;
        }
        BlockPos pos = event.getPos();
        LevelAccessor level = event.getLevel();
        ChunkPos pos1 = level.getChunk(pos).getPos();
        for (Map.Entry<String, DoveTeamEntity> entry : DoveSQL.teamEntities.entrySet()) {
            DoveTeamEntity doveTeamEntity = entry.getValue();
            for (DoveTeamEntity.TeamChunkPos teamChunkPos : doveTeamEntity.claimChunk) {
                if (teamChunkPos.is(pos1) && !doveTeamEntity.players.contains(player.getUUID().toString())) {
                    event.setCanceled(true);
                }
            }
        }
    }

    public static void placeBlock(BlockEvent.EntityPlaceEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            if (player.hasPermissions(4)) {
                return;
            }
            BlockPos pos = event.getPos();
            LevelAccessor level = event.getLevel();
            ChunkPos pos1 = level.getChunk(pos).getPos();
            final DoveTeamEntity.TeamChunkPos tcp = new DoveTeamEntity.TeamChunkPos(pos1.x, pos1.z);
            DoveEntity doveEntity = DoveSQL.entities.get(player.getStringUUID());
            if (!doveEntity.hasPlayerTeamClaimTcp(player, tcp)) {
                event.setCanceled(true);
            }
        }
    }
}
