package io.github.dovehome.dovehomemod.forge.core.items;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import vazkii.botania.api.item.ManaDissolvable;
import vazkii.botania.api.mana.ManaPool;

public class DoveStaffBase extends Item implements ManaDissolvable {
    public DoveStaffBase(Properties properties) {
        super(properties);
    }

    // 充能解能
    // 可以给法杖充能(使用充能的法杖会消耗耐久度)
    @Override
    public void onDissolveTick(ManaPool pool, ItemEntity item) {

    }
}
