package io.github.dovehometeam.dovehomemod.infrastructure.block;

import com.mojang.datafixers.DSL;
import io.github.dovehometeam.dovehomemod.infrastructure.block.tile.AbrasiveSandStoneBlockEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

import static io.github.dovehometeam.dovehomemod.AllRegistry.*;

public enum RegisterBlock implements Supplier<Block> {
    abrasive_sand_stone(() -> new Block(BlockBehaviour.Properties.of())),//磨砂de砂岩
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

    public enum RegisterType implements Supplier<BlockEntityType<? extends BlockEntity>> {
        abrasive_sand_stone(AbrasiveSandStoneBlockEntity::new, RegisterBlock.abrasive_sand_stone.get()),
        ;

        public final RegistryObject<BlockEntityType<? extends BlockEntity>> t;

        RegisterType(BlockEntityType.BlockEntitySupplier<? extends BlockEntity> pFactory, Block... pValidBlocks) {
            this.t = entityTypes.register(name().toLowerCase(Locale.ROOT), () -> BlockEntityType.Builder.of(pFactory, pValidBlocks).build(DSL.remainderType()));
        }

        @Override
        public BlockEntityType<?> get() {
            return t.get();
        }

        public static void init() {}
    }

    public enum RegisterItem implements Supplier<Item> {
        abrasive_sand_stone(() -> new BlockItem(RegisterBlock.abrasive_sand_stone.get(), new Item.Properties())),
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
