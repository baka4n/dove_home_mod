package io.github.dovehome.dovehomemod.forge.core.registry;

import io.github.dovehome.bakalib.forge.annotations.RegistryItem;
import io.github.dovehome.dovehomemod.forge.core.items.KnifeItem;
import io.github.dovehome.dovehomemod.forge.core.tab.Step1Tab;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class DoveItems {

    @RegistryItem("sandstone_pickaxe")
    public static final DiggerItem sandStonePickaxe;
    @RegistryItem("sandstone_axe")
    public static final AxeItem sandStoneAxe;

    @RegistryItem("sandstone_sword")
    public static final SwordItem sandStoneSword;

    @RegistryItem("sandstone_shovel")
    public static final ShovelItem sandStoneShovel;
    @RegistryItem("sandstone_hoe")
    public static final HoeItem sandStoneHoe;

    @RegistryItem("sandstone_knife")
    public static final KnifeItem sandstoneKnife;

    static {
        sandStonePickaxe = new DiggerItem(0.5F, -3.2F, Tiers.WOOD, BlockTags.MINEABLE_WITH_PICKAXE, new Item.Properties().tab(Step1Tab.getInstance())) {
            public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
                return ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
            }
        };
        sandStoneAxe = new AxeItem(Tiers.WOOD, 4.5F, -3.2F, new Item.Properties().tab(Step1Tab.getInstance()));
        sandStoneSword = new SwordItem(Tiers.WOOD, 2, -2.4F, (new Item.Properties()).tab(Step1Tab.getInstance()));
        sandStoneShovel = new ShovelItem(Tiers.WOOD, 1.3F, -3.0F, (new Item.Properties()).tab(Step1Tab.getInstance()));
        sandStoneHoe = new HoeItem(Tiers.WOOD, 0, -3.0F, new Item.Properties().tab(Step1Tab.getInstance()));
        sandstoneKnife = new KnifeItem(Tiers.WOOD, 0, -4F, new Item.Properties().tab(Step1Tab.getInstance()));
    }
}
