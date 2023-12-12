package io.github.dovehome.dovehomemod.events;

import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static io.github.dovehome.dovehomemod.events.DoveCriteriaTriggers.right_click_block;

public class DoveBlockEvents {
    @SubscribeEvent
    public  static void rightClickBlockAdvancement(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            right_click_block.trigger(serverPlayer, serverPlayer.getLevel(), event.getPos());
        }
    }
    @SubscribeEvent
    public static void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
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
