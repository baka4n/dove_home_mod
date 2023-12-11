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
        add("dovehomemod.root.advancement.title", "一切的起点");
        add("dovehomemod.root.advancement.tips", "将维度修改为荒漠");
        add("dovehomemod.sand.advancement.title", "以前这里遍地是黄金，现在这里遍地是\"黄金\"");
        add("dovehomemod.sandstone.advancement.title", "硬邦邦");
        add("dovehomemod.sandstone.advancement.tips", "这个好硬");
        add("dovehomemod.stick.advancement.title", "肮脏的");
        add("dovehomemod.stick.advancement.tips", "污秽的木棍");
        add("dovehomemod.sandstone.pickaxe.advancement.title", "比木镐更鸡肋");
        add("dovehomemod.sandstone.axe.advancement.title", "比木斧更鸡肋");
        add("dovehomemod.sandstone.sword.advancement.title", "比木剑更鸡肋");
        add("dovehomemod.sandstone.shovel.advancement.title", "比木锹更鸡肋");
        add("dovehomemod.sandstone.hoe.advancement.title", "比木锄更鸡肋");
        add("dovehomemod.sandstone.tools.advancement.tips", "勉强用用");
        add("dovehomemod.sandstone.knife.advancement.title", "反手握的雕刻刀");
        add("dovehomemod.sandstone.knife.advancement.tips", "怎么会有这种奇葩设计？");
        add(((TranslatableContents) CactusThornsBlockItem.bloody.getContents()).getKey(), "染血的");
    }
}
