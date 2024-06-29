package io.github.dovehometeam.dovegod;

import io.github.dovehometeam.dovegod.infrastructure.registry.RegistryBlock;
import io.github.dovehometeam.dovegod.infrastructure.registry.RegistryBlockEntity;
import io.github.dovehometeam.dovegod.infrastructure.registry.RegistryItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AllRegistry {
    public static final DeferredRegister<Item> items;

    public static final DeferredRegister<Fluid> fluids;

    public static final DeferredRegister<Block> blocks;

    public static final DeferredRegister<BlockEntityType<?>> tiles;

    static {
        fluids = DeferredRegister.create(ForgeRegistries.FLUIDS, DoveGod.MODID);
        blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, DoveGod.MODID);
        items = DeferredRegister.create(ForgeRegistries.ITEMS, DoveGod.MODID);
        tiles = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DoveGod.MODID);
    }

    public static void registerAll(IEventBus mod) {
        RegistryBlock.init();
        RegistryBlockEntity.init();
        RegistryItem.init();


        blocks.register(mod);
        tiles.register(mod);
        items.register(mod);
    }
}
