package io.github.dovehometeam.dovehomemod.mixin;

import io.github.dovehometeam.dovehomemod.infrastructure.Config;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterFluid.class)
public class WaterInfinityMixin {
    @Inject(method = "canConvertToSource", at = @At("HEAD"), cancellable = true)
    private void canConvertToSource(Level pLevel, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!Config.noInfiniteWaterBoolean);
    }
}
