package io.github.dovehometeam.dovehomemod.auxiliary;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface IGenPut<T extends IGenPut<T>> {
    IGenPut<T> put(String key, String translate);
    IGenPut<T> put(Block key, String translate);
    IGenPut<T> put(Item key, String translate);
}
