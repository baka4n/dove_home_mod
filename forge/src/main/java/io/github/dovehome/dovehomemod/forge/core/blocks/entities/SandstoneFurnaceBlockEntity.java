package io.github.dovehome.dovehomemod.forge.core.blocks.entities;

import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import io.github.dovehome.dovehomemod.forge.menu.SandstoneFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * {@link FurnaceBlockEntity}
 */
public class SandstoneFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    private int otherCookingTime;
    public SandstoneFurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        super(DoveBlocks.sandstoneFurnaceEntity, pos, blockState, RecipeType.SMELTING);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        otherCookingTime = tag.getInt("otherCookingTime");

    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("otherCookingTime", otherCookingTime);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.sandstone.furnace");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new SandstoneFurnaceMenu(containerId, inventory, this, this.dataAccess);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SandstoneFurnaceBlockEntity blockEntity) {
        boolean flag = blockEntity.isLit();
        boolean flag1 = false;
        if (blockEntity.isLit()) {
            --blockEntity.litTime;
        }

        ItemStack itemstack = blockEntity.items.get(1);
        boolean flag2 = !blockEntity.items.get(0).isEmpty();
        boolean flag3 = !itemstack.isEmpty();
        if (!blockEntity.isLit() && (!flag3 || !flag2)) {
            if (blockEntity.cookingProgress > 0) {
                blockEntity.cookingProgress = Mth.clamp(blockEntity.cookingProgress - 2, 0, blockEntity.cookingTotalTime);
            }
        } else {
            AbstractCookingRecipe recipe;
            if (flag2) {
                recipe = blockEntity.quickCheck.getRecipeFor(blockEntity, level).orElse(null);
            } else {
                recipe = null;
            }

            int i = blockEntity.getMaxStackSize();
            if (!blockEntity.isLit() && blockEntity.canBurn(recipe, blockEntity.items, i)) {
                blockEntity.litTime = blockEntity.getBurnDuration(itemstack);
                blockEntity.litDuration = blockEntity.litTime;
                if (blockEntity.isLit()) {
                    flag1 = true;
                    if (itemstack.hasCraftingRemainingItem()) {
                        blockEntity.items.set(1, itemstack.getCraftingRemainingItem());
                    } else if (flag3) {
                        Item item = itemstack.getItem();
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            blockEntity.items.set(1, itemstack.getCraftingRemainingItem());
                        }
                    }
                }
            }

            if (blockEntity.isLit() && blockEntity.canBurn(recipe, blockEntity.items, i)) {
                blockEntity.otherCookingTime++;
                if (blockEntity.otherCookingTime > 9) {
                    ++blockEntity.cookingProgress;
                    blockEntity.otherCookingTime = 0;
                }

                if (blockEntity.cookingProgress == blockEntity.cookingTotalTime) {
                    blockEntity.cookingProgress = 0;
                    blockEntity.cookingTotalTime = getTotalCookTime(level, blockEntity);
                    if (blockEntity.burn(recipe, blockEntity.items, i)) {
                        blockEntity.setRecipeUsed(recipe);
                    }

                    flag1 = true;
                }
            } else {
                blockEntity.cookingProgress = 0;
            }
        }

        if (flag != blockEntity.isLit()) {
            flag1 = true;
            state = state.setValue(AbstractFurnaceBlock.LIT, blockEntity.isLit());
            level.setBlock(pos, state, 3);
        }

        if (flag1) {
            setChanged(level, pos, state);
        }
    }

}
