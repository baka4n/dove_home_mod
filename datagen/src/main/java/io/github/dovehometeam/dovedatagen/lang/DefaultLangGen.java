package io.github.dovehometeam.dovedatagen.lang;

import io.github.dovehometeam.dovehomemod.Dovehomemod;
import io.github.dovehometeam.dovehomemod.auxiliary.IGenPut;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;

public class DefaultLangGen extends LanguageProvider implements IGenPut<DefaultLangGen> {

    public DefaultLangGen(PackOutput output, String locale) {
        super(output, Dovehomemod.MODID, locale);
    }

    public DefaultLangGen(PackOutput output) {
       this(output, "en_us");
    }

    @Override
    protected void addTranslations() {
    }

    @Override
    public DefaultLangGen put(String key, String translate) {
        add(key, translate);
        return this;
    }

    @Override
    public DefaultLangGen put(Block key, String translate) {
        add(key, translate);
        return this;
    }

    @Override
    public DefaultLangGen put(Item key, String translate) {
        add(key, translate);
        return this;
    }
}
