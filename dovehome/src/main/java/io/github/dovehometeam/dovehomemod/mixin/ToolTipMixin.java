package io.github.dovehometeam.dovehomemod.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
@Debug(export = true)
public class ToolTipMixin {
    @Inject(method = "appendHoverText", at = @At("RETURN"))
    private void appendHoverText(ItemStack pStack,
                                 Level pLevel,
                                 List<Component> pTooltipComponents,
                                 TooltipFlag pIsAdvanced,
                                 CallbackInfo ci) {
        CompoundTag blockEntityData = BlockItem.getBlockEntityData(pStack);
        if (blockEntityData != null) {
            pTooltipComponents.add(Component.empty().append(Component.translatable("dovehomemod.abrasive.value")).append(String.valueOf(blockEntityData.getInt("done"))));
        }
    }
}
