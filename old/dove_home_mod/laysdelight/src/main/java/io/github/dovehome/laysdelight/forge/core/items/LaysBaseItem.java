package io.github.dovehome.laysdelight.forge.core.items;

import io.github.dovehome.laysdelight.forge.registry.LaysItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public abstract class LaysBaseItem extends Item {
    public LaysBaseItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        return defaultStack(super.getDefaultInstance());
    }

    public @NotNull ItemStack defaultStack(ItemStack superStack) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("lays_count", defaultRan());
        superStack.setTag(tag);
        return superStack;
    }


    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        rightClick(level, player, usedHand);
        return super.use(level, player, usedHand);
    }

    public void rightClick(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (level.isClientSide()) return;
        if (usedHand == InteractionHand.OFF_HAND) {
            ItemStack itemInHand = player.getItemInHand(InteractionHand.MAIN_HAND);
            if (itemInHand.isEmpty()) {
                ItemStack hand = player.getItemInHand(usedHand);
                if (countSub(hand)) {
                    player.setItemSlot(EquipmentSlot.MAINHAND, LaysItems.potato_chip.getDefaultInstance(getDescriptionId()));
                }
            } else if ((isItem(itemInHand) && itemInHand.getCount() < 64)) {
                ItemStack hand = player.getItemInHand(usedHand);
                if (itemInHand.hasTag() && itemInHand.getTag() != null) {
                    CompoundTag tag = itemInHand.getTag();
                    if (tag.contains("lays_name")) {
                        if (tag.getString("lays_name").equals(itemInHand.getDescriptionId())) {
                            if (countSub(hand)) {
                                itemInHand.setCount(itemInHand.getCount() + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public abstract boolean isItem(@NotNull ItemStack stack);

    public static boolean countSub(ItemStack hand) {
        if (hand.hasTag() && hand.getTag() != null) {
            CompoundTag tag = hand.getTag();
            if (tag.contains("lays_count") && tag.getInt("lays_count") > 0) {
                int laysCount = tag.getInt("lays_count");
                laysCount--;
                tag.putInt("lays_count", laysCount);
                hand.setTag(tag);
                return true;
            }
        }
        return false;
    }

    public abstract int defaultRan();
}
