package io.github.dovehome.laysdelight.forge.core.tabs;

import io.github.dovehome.laysdelight.forge.LaysDelight;
import io.github.dovehome.laysdelight.forge.registry.LaysItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class LaysCannedTab extends CreativeModeTab {
    public static LaysCannedTab instance;

    public static LaysCannedTab getInstance() {
        if (instance == null) instance = new LaysCannedTab(new ResourceLocation(LaysDelight.modid, "lays_tab"));
        return instance;
    }
    public LaysCannedTab(ResourceLocation label) {
        super(label.toString());
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(LaysItems.loyal_original_flavor_lays_canned);
    }
}
