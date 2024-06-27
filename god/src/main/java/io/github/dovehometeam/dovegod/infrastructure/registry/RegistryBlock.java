package io.github.dovehometeam.dovegod.infrastructure.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.dovehometeam.dovegod.AllRegistry.blocks;

public enum RegistryBlock implements Supplier<Block> {
    ;

    private final RegistryObject<Block> block;

    RegistryBlock(Function<BlockBehaviour.Properties, Block> function) {
        block = blocks.register(name().toLowerCase(Locale.ROOT), () -> function.apply(BlockBehaviour.Properties.of()));
    }

    public static void init() {}

    @Override
    public Block get() {
        return block.get();
    }
}
