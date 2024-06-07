package io.github.dovehometeam.dovehomemod.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PickaxeItem.class)
public abstract class PickaxeMixin extends DiggerItem {


    public PickaxeMixin(float pAttackDamageModifier, float pAttackSpeedModifier, Tier pTier, TagKey<Block> pBlocks, Properties pProperties) {
        super(pAttackDamageModifier, pAttackSpeedModifier, pTier, pBlocks, pProperties);
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack pStack,
                                 @NotNull BlockState pState) {
        CompoundTag blockEntityData = BlockItem.getBlockEntityData(pStack);
        float destroySpeed = super.getDestroySpeed(pStack, pState);
        if (blockEntityData != null) {
            destroySpeed += (float) blockEntityData.getInt("done") / 10;
        }
        return destroySpeed;
    }
}
