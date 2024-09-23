package io.github.dovehometeam.dovegod.infrastructure.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.dovehometeam.dovegod.AllRegistry.items;

public enum RegistryItem implements Supplier<Item> {
    ;

    private final RegistryObject<Item> item;
    RegistryItem(Function<Item.Properties, Item> function) {
        item = items.register(name().toLowerCase(Locale.ROOT), () -> function.apply(new Item.Properties()));
    }

    RegistryItem(RegistryBlock block, Item.Properties props) {
        item = items.register(name().toLowerCase(Locale.ROOT), () -> new BlockItem(block.get(), props));
    }

    public static void init() {}

    @Override
    public Item get() {
        return item.get();
    }
}
