package io.github.dovehome.dovehomemod.forge.mixin;

import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterFluid.class)
@Debug(export = true)
public class NoInfiniteWaterMixin {// no infinite water
    @Inject(method = "canConvertToSource", at = @At("HEAD"), cancellable = true)
    private void canConvertToSource(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}