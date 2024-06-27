package io.github.dovehometeam.dovegod;

import io.github.dovehometeam.dovegod.infrastructure.registry.RegistryBlock;
import io.github.dovehometeam.dovegod.infrastructure.registry.RegistryItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AllRegistry {
    public static final DeferredRegister<Item> items;
    public static final DeferredRegister<Block> blocks;


    static {
        blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, DoveGod.MODID);
        items = DeferredRegister.create(ForgeRegistries.ITEMS, DoveGod.MODID);
    }

    public static void registerAll(IEventBus mod) {
        RegistryBlock.init();
        RegistryItem.init();

        blocks.register(mod);
        items.register(mod);
    }
}
