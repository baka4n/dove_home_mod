package io.github.dovehometeam.dovehomemod.infrastructure.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;

public class PelletBase extends Item {
    public PelletBase(Properties pProperties) {
        super(pProperties.food(Foods.APPLE).fireResistant());
    }

    @Override
    public @NotNull Rarity getRarity(@NotNull ItemStack pStack) {
        // quality 品质nbt
        CompoundTag tag = pStack.hasTag() ? pStack.getTag() : null;
        if (tag != null) {
            if (tag.contains("quality")) {
                switch (tag.getInt("quality")) {
                    case 7 -> {return DoveRarity.IMMORTALS;}
                    case 6 -> {return DoveRarity.SKY;}
                    case 5 -> {return DoveRarity.EARTH;}
                    case 4 -> {
                        return DoveRarity.POLE;
                    }
                    case 3 -> {
                        return DoveRarity.UPPER;
                    }
                    case 2 -> {
                        return DoveRarity.MID_LEVEL;
                    }
                    case 1 -> {
                        return DoveRarity.LOWER;
                    }
                }
            }
        }
        return DoveRarity.DEPRECATED;

    }
}
