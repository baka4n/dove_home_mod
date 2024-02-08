package io.github.dovehome.dovehomemod.forge.mixin;

import io.github.dovehome.dovehomemod.events.DoveCriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.class)
public class EntityInsideBlockMixin {
    @Inject(method = "entityInside", at = @At("HEAD"))
    private void entityInside(BlockState state,
                              Level level,
                              BlockPos pos,
                              Entity entity,
                              CallbackInfo ci) {
        if (entity instanceof ServerPlayer serverPlayer) {
            DoveCriteriaTriggers.entity_inside_block.trigger(serverPlayer, serverPlayer.getLevel(), pos);
        }
    }
}
