package io.github.dovehome.dovehomemod.datagen.client.i18n;

import io.github.dovehome.dovehomemod.datagen.client.DoveLanguageProvider;
import io.github.dovehome.dovehomemod.forge.core.items.CactusThornsBlockItem;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveItems;
import io.github.dovehome.dovehomemod.forge.core.tab.Step1Tab;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.contents.TranslatableContents;

public class ZhCnLanguage extends DoveLanguageProvider {
    public ZhCnLanguage(DataGenerator gen) {
        super(gen, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(Step1Tab.getInstance(), "一阶段");
        add(DoveBlocks.cactusThorns, "仙人掌刺");
        add(DoveBlocks.resurrectedSapling, "复活的树苗");
        add(DoveBlocks.sandstoneTable, "砂石台");
        add(DoveBlocks.sandstoneFurnace, "砂石炉");
        add(DoveItems.sandStonePickaxe, "砂石稿");
        add(DoveItems.sandStoneAxe, "砂石斧");
        add(DoveItems.sandStoneSword, "砂石剑");
        add(DoveItems.sandStoneShovel, "砂石锹");
        add(DoveItems.sandStoneHoe, "砂石锄");
        add("blood.count", "染血量：");
        add("sandstone.furnace.tooltip", "速度非常缓慢");
        add("container.sandstone.furnace", "砂石炉");
        add(((TranslatableContents) CactusThornsBlockItem.bloody.getContents()).getKey(), "染血的");
    }
}
