package io.github.dovehome.creative.generation.mixins;

import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity.FuelType;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Debug(export = true)
@Mixin(value = BlazeBurnerBlockEntity.class, remap = false)
public abstract class BlazeBurnerBlockEntityMixin extends SmartBlockEntity {
    @Shadow protected FuelType activeFuel;

    @Unique
    public String creative$generation$fuelTypeImpl;


    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(BlockEntityType<?> type, BlockPos pos, BlockState state, CallbackInfo ci) {
        creative$generation$fuelTypeImpl = activeFuel.name();
    }

    public BlazeBurnerBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }



    @Inject(method = "tryUpdateFuel", at= @At(value = "INVOKE", target = "Lcom/simibubi/create/AllTags$AllItemTags;matches(Lnet/minecraft/world/item/ItemStack;)Z", ordinal = 0), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void tryUpdateFuel(ItemStack itemStack,
                               boolean forceOverflow,
                               boolean simulate,
                               CallbackInfoReturnable<Boolean> cir,
                               FuelType newFuel,
                               int newBurnTime) {

    }

    @Inject(method = "getHeatLevel", at = @At("HEAD"))
    private void getHeatLevel(CallbackInfoReturnable<BlazeBurnerBlock.HeatLevel> cir) {


    }


}
