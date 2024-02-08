package io.github.dovehome.dovehomemod.forge.core.tab;

import io.github.dovehome.dovehomemod.base.TabBase;
import io.github.dovehome.dovehomemod.forge.DovehomemodForge;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Step1Tab extends TabBase {

    private static Step1Tab step1;

    public Step1Tab(ResourceLocation label) {
        super(label);
    }

    public static Step1Tab getInstance() {
        if (step1 == null) {
            step1 = new Step1Tab(new ResourceLocation(DovehomemodForge.modid, "step1"));
        }
        return step1;
    }
    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(DoveBlocks.resurrectedSaplingItem);
    }
}
