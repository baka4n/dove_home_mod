package io.github.dovehometeam.dovehomemod.infrastructure.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

import static io.github.dovehometeam.dovehomemod.AllRegistry.blocks;
import static io.github.dovehometeam.dovehomemod.AllRegistry.items;

public enum RegisterBlock implements Supplier<Block> {
    ;

    public final RegistryObject<Block> tBlock;

    RegisterBlock(Supplier<Block> block) {
        this.tBlock = blocks.register(name().toLowerCase(Locale.ROOT), block);
    }

    public static void init() {}

    @Override
    public Block get() {
        return tBlock.get();
    }

    public enum RegisterItem implements Supplier<Item> {
        ;
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


}
