package io.github.dovehometeam.dovehomemod;

import io.github.dovehometeam.dovehomemod.infrastructure.block.RegisterBlock;
import io.github.dovehometeam.dovehomemod.infrastructure.block.RegisterBlockItem;
import io.github.dovehometeam.dovehomemod.infrastructure.block.RegisterTile;
import io.github.dovehometeam.dovehomemod.infrastructure.item.RegisterItem;
import io.github.dovehometeam.dovehomemod.infrastructure.tag.RegisterTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AllRegistry {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, Dovehomemod.MODID);
    public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, Dovehomemod.MODID);

    public static final DeferredRegister<BlockEntityType<?>> entityTypes = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Dovehomemod.MODID);

    public static void init(IEventBus modEventBus) {
        RegisterTag.init();
        RegisterBlock.init();
        RegisterTile.init();
        RegisterBlockItem.init();
        RegisterItem.init();
        blocks.register(modEventBus);
        entityTypes.register(modEventBus);
        items.register(modEventBus);

    }
}
