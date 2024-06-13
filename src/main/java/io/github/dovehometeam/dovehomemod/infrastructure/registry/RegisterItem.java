package io.github.dovehometeam.dovehomemod.infrastructure.registry;

import io.github.dovehometeam.dovehomemod.infrastructure.ModTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static io.github.dovehometeam.dovehomemod.AllRegistry.items;

public enum RegisterItem implements Supplier<Item> {
    sand_pickaxe(() -> new PickaxeItem(ModTiers.SAND, 1, -2.9F, new Item.Properties()));

    public final RegistryObject<Item> t;

    RegisterItem(Supplier<Item> supplier) {
        this.t = items.register(name(), supplier);
    }

    @Override
    public Item get() {
        return t.get();
    }

    public static void init() {}
}
