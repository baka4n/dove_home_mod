package io.github.dovehometeam.dovehomemod.infrastructure.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static io.github.dovehometeam.dovehomemod.AllRegistry.items;

public enum RegisterBlockItem implements Supplier<Item> {
    abrasive_sand_stone(() -> new BlockItem(RegisterBlock.abrasive_sand_stone.get(), new Item.Properties())),
    ;
    public final RegistryObject<Item> t;

    RegisterBlockItem(Supplier<Item> supplier) {
        this.t = items.register(name(), supplier);
    }

    @Override
    public Item get() {
        return t.get();
    }

    public static void init() {

    }
}
