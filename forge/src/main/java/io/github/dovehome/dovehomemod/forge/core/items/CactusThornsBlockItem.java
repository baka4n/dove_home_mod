package io.github.dovehome.dovehomemod.forge.core.items;

import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class CactusThornsBlockItem extends BlockItem {
    public CactusThornsBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    public static final int MAX = Integer.MAX_VALUE / 2;

    public static final MutableComponent bloody = Component.translatable("cactus.bloody");

    public static final Random ran = new Random();
    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {

        if (entity instanceof Player player) {
            if (player.isCreative() || player.isSpectator()) {
                return;
            }
            Inventory inventory = player.getInventory();
            if ((slotId < 9 && slotId >= 0) || slotId == inventory.getContainerSize() - 1) {
                if (stack.is(DoveBlocks.cactusThronsItem)) {
                    if (Math.random() <= 0.04) {
                        tagEdit(stack, player);
                    }
                }
            }
            if (player.isSleeping() && Math.random() <= 0.02) {
                if (stack.is(DoveBlocks.cactusThronsItem)) {
                    tagEdit(stack, player);
                    player.stopSleeping();
                }
            }
        }

    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level level,
                                @NotNull List<Component> tooltipComponents,
                                @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        CompoundTag blockEntityData = getBlockEntityData(stack);
        if (blockEntityData != null) {
            tooltipComponents.add(Component.translatable("blood.count").append(String.valueOf(blockEntityData.getInt("blood_count"))));
        }

    }

    public void tagEdit(@NotNull ItemStack stack, Player player) {
        CompoundTag blockEntityData = Objects.requireNonNullElse(getBlockEntityData(stack), new CompoundTag());



        player.hurt(DamageSource.CACTUS, 1);
        player.hurtTime = 1;
        int bloodCount = blockEntityData.getInt("blood_count");

        if (bloodCount >= MAX) {
            return;
        }
        blockEntityData.putInt("blood_count", 1 + bloodCount);
        stack.addTagElement("BlockEntityTag", blockEntityData);

    }



    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        MutableComponent empty = Component.empty();
        CompoundTag blockEntityData = getBlockEntityData(stack);
        if (blockEntityData != null) {
            if (blockEntityData.getInt("blood_count") >= 20) {
                empty.append(bloody);
            }
        }
        return empty.append(Component.translatable(getDescriptionId()));
    }
}
