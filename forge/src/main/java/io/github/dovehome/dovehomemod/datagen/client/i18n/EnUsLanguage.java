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
        add(DoveBlocks.resurrectedSapling, "Rebel saplings");
        add(DoveBlocks.sandstoneFurnace, "sandstone furnace");
        add(DoveItems.sandStonePickaxe, "Sandstone Pickaxe");
        add(DoveItems.sandStoneAxe, "Sandstone Axe");
        add(DoveItems.sandStoneSword, "Sandstone Sword");
        add(DoveItems.sandStoneShovel, "Sandstone Shovel");
        add(DoveItems.sandStoneHoe, "Sandstone Hoe");
        add(DoveItems.sandstoneKnife, "sandstone knife");

        add("blood.count", "blood staining amount is ");
        add("sandstone.furnace.tooltip", "It's very slow");
        add("container.sandstone.furnace", "sandstone furnace");
        add("dovehomemod.root.advancement.title", "The starting point of everything");
        add("dovehomemod.root.advancement.tips", "change dimension to desert");
        add("dovehomemod.sand.advancement.title", "It used to be full of gold, but now it's full of \"gold\"");
        add("dovehomemod.sandstone.advancement.title", "very hard");
        add("dovehomemod.sandstone.advancement.tips", "This is so hard");
        add("dovehomemod.stick.advancement.title", "dirty");
        add("dovehomemod.stick.advancement.tips", "dirty stick");
        add("dovehomemod.sandstone.pickaxe.advancement.title", "Worse than a wooden pickaxe.");
        add("dovehomemod.sandstone.axe.advancement.title", "Worse than a wooden axe.");
        add("dovehomemod.sandstone.sword.advancement.title", "Worse than a wooden sword.");
        add("dovehomemod.sandstone.shovel.advancement.title", "Worse than a wooden shovel.");
        add("dovehomemod.sandstone.hoe.advancement.title", "Worse than a wooden hoe.");
        add("dovehomemod.sandstone.tools.advancement.tips", "Use it reluctantly.");
        add("dovehomemod.sandstone.knife.advancement.title", "Backhand carving knife");
        add("dovehomemod.sandstone.knife.advancement.tips", "How come there is such a weird design?");
        add("dovehomemod.cheaters.advancement.title", "Cheaters");
        add("dovehomemod.cheaters.advancement.tips", "You watch the world with the eyes of an administrator");
        add("dovehomemod.voyeurs.advancement.title", "Voyeurs");
        add("dovehomemod.voyeurs.advancement.tips", "You're a voyeur and love to watch the man next door take a shower");
        add("dovehomemod.adventurer.advancement.title", "Adventurer");
        add("dovehomemod.adventurer.advancement.tips", "You are a man of strength");
        add("dovehomemod.cactus.thorns.advancement.title", "114514");
        add("dovehomemod.cactus.thorns.advancement.tips", "Hmm Hmm Hmm AAA");
        add("dovehomemod.garbled.advancement.title", "烫烫烫");
        add("dovehomemod.garbled.advancement.tips", "锟斤拷");

        add(((TranslatableContents) CactusThornsBlockItem.bloody.getContents()).getKey(), "bloody ");
    }
}
