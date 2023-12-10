package io.github.dovehome.dovehomemod.forge.events;

import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DoveBlockEvents {
    @SubscribeEvent
    public static void rightClientBlock(PlayerInteractEvent.RightClickBlock event) {
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
