package io.github.dovehometeam.dovegod.infrastructure.registry;

import com.mojang.datafixers.DSL;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Supplier;

import static io.github.dovehometeam.dovegod.AllRegistry.tiles;

public enum RegistryBlockEntity implements Supplier<BlockEntityType<? extends BlockEntity>> {
    ;

    public final RegistryObject<? extends BlockEntityType<? extends BlockEntity>> tile;

    @SafeVarargs
    RegistryBlockEntity(BlockEntityType.BlockEntitySupplier<? extends BlockEntity> pFactory, RegistryObject<Block>... blocks) {
        tile = tiles.register(name().toLowerCase(Locale.ROOT), () -> BlockEntityType.Builder.of(pFactory, Arrays.stream(blocks).map(RegistryObject::get).toArray(value -> new Block[0])).build(DSL.remainderType()));
    }

    public static void init() {}

    @Override
    public BlockEntityType<? extends BlockEntity> get() {
        return tile.get();
    }
}
