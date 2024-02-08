package io.github.dovehome.dovehomemod.datagen.client;

import io.github.dovehome.dovehomemod.forge.DovehomemodForge;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.data.LanguageProvider;

public abstract class DoveLanguageProvider extends LanguageProvider {

    public DoveLanguageProvider(DataGenerator gen, String locale) {
        super(gen, DovehomemodForge.modid, locale);
    }

    public void add(CreativeModeTab tab, String name) {
        add(((TranslatableContents) tab.getDisplayName().getContents()).getKey(), name);
    }
}
