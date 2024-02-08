package io.github.dovehome.dovehomemod.events;

import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;
import java.util.Random;

import static io.github.dovehome.dovehomemod.events.DoveCriteriaTriggers.right_click_block;

public class DoveBlockEvents {
    @SubscribeEvent
    public void rightClickBlockAdvancement(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            right_click_block.trigger(serverPlayer, serverPlayer.getLevel(), event.getPos());
        }
    }

    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (player instanceof ServerPlayer serverPlayer) {
            ServerLevel level = serverPlayer.getLevel();
            if (level.getBlockState(event.getPos()).is(Blocks.STONE)) {
                if (new Random().nextDouble(serverPlayer.experienceLevel * 0.1 + 0.01) >= 0.05) {
                    level.addFreshEntityWithPassengers(Objects.requireNonNull(EntityType.SILVERFISH.create(level, null, null, event.getPlayer(), event.getPos(), MobSpawnType.SPAWNER, true, false)));
                }

            }
        }
    }
    @SubscribeEvent
    public void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player entity = event.getEntity();

        if (!entity.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
            return;
        }
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState blockState = level.getBlockState(pos);
        if (blockState.is(Blocks.CACTUS) && entity.isShiftKeyDown()) {
            if (Math.random() <= 0.1) {
                entity.addItem(new ItemStack(DoveBlocks.cactusThronsItem));
            } else {
                entity.hurt(DamageSource.CACTUS, 0.3F);
                entity.hurtTime = 1;
            }
        }
    }
}
