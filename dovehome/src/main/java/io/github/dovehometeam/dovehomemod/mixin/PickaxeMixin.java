package io.github.dovehometeam.dovehomemod.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(DiggerItem.class)
public abstract class PickaxeMixin {
    @Inject(method = "getDestroySpeed", at=@At("RETURN"), cancellable = true)
    protected void getDestroySpeed(ItemStack pStack, BlockState pState, CallbackInfoReturnable<Float> cir) {
        CompoundTag blockEntityData = BlockItem.getBlockEntityData(pStack);
        float destroySpeed = cir.getReturnValue();
        if (blockEntityData != null) {
            destroySpeed += (float) blockEntityData.getInt("done") / 20;
        }
        cir.setReturnValue(destroySpeed);
    }
}
