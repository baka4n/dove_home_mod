package io.github.dovehome.dovehomemod.datagen.client.i18n;

import io.github.dovehome.dovehomemod.datagen.client.DoveLanguageProvider;
import io.github.dovehome.dovehomemod.forge.core.items.CactusThornsBlockItem;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveItems;
import io.github.dovehome.dovehomemod.forge.core.tab.Step1Tab;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.contents.TranslatableContents;

public class EnUsLanguage extends DoveLanguageProvider {
    public EnUsLanguage(DataGenerator gen) {
        super(gen, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(Step1Tab.getInstance(), "Step:1");
        add(DoveBlocks.cactusThorns, "cactus thorns");
        add(DoveBlocks.sandstoneTable, "Sandstone Table");
        add(DoveBlocks.resurrectedSapling, "resurrected Sapling");
        add(DoveBlocks.sandstoneFurnace, "sandstone furnace");
        add(DoveItems.sandStonePickaxe, "Sandstone Pickaxe");
        add(DoveItems.sandStoneAxe, "Sandstone Axe");
        add(DoveItems.sandStoneSword, "Sandstone Sword");
        add(DoveItems.sandStoneShovel, "Sandstone Shovel");
        add(DoveItems.sandStoneHoe, "Sandstone Hoe");

        add("blood.count", "blood staining amount is ");
        add("sandstone.furnace.tooltip", "It's very slow");
        add("container.sandstone.furnace", "sandstone furnace");
        add(((TranslatableContents) CactusThornsBlockItem.bloody.getContents()).getKey(), "bloody ");
    }
}
