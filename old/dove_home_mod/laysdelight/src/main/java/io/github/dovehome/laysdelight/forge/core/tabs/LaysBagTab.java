package io.github.dovehome.laysdelight.forge.core.tabs;

import io.github.dovehome.laysdelight.forge.LaysDelight;
import io.github.dovehome.laysdelight.forge.registry.LaysItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class LaysBagTab extends CreativeModeTab {
    public static LaysBagTab instance;

    public static LaysBagTab getInstance() {
        if (instance == null) instance = new LaysBagTab(new ResourceLocation(LaysDelight.modid, "lays_tab"));
        return instance;
    }
    public LaysBagTab(ResourceLocation label) {
        super(label.toString());
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(LaysItems.american_original_flavor_classic_lays_bag);
    }
}
