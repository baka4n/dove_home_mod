package io.github.dovehome.dovehomemod.base;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public abstract class TabBase extends CreativeModeTab {

    public TabBase(ResourceLocation label) {
        super(label.toString().replace(":", "."));
    }
}
