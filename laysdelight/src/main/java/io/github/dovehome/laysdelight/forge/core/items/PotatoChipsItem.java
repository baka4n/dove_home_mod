package io.github.dovehome.laysdelight.forge.core.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PotatoChipsItem extends Item {
    /**
     * @param properties p
     */
    @Deprecated(forRemoval = true, since = "use other <init>")
    public PotatoChipsItem(Properties properties) {
        super(properties.food(new FoodProperties.Builder().nutrition(1).fast().saturationMod(0.2F).build()));
    }

    public PotatoChipsItem(Properties properties, int nutrition, float saturationMod) {
        super(properties.food(new FoodProperties.Builder().nutrition(nutrition).fast().saturationMod(saturationMod).build()));
    }

    public @NotNull ItemStack getDefaultInstance(String translate) {
        ItemStack defaultInstance = super.getDefaultInstance();
        CompoundTag tag = new CompoundTag();
        tag.putString("lays_name", translate);
        return defaultInstance;
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack defaultInstance = super.getDefaultInstance();
        CompoundTag tag = new CompoundTag();
        tag.putString("lays_name", "");
        return defaultInstance;
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        MutableComponent empty = Component.empty();
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("lays_name") && !tag.getString("lays_name").isEmpty()) {
            empty.append(Component.translatable("lays." + tag.getString("lays_name")));
        }
        return empty.append(super.getName(stack));
    }
}
