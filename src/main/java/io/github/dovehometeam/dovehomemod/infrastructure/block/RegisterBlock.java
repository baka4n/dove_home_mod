package io.github.dovehometeam.dovehomemod.infrastructure.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

import static io.github.dovehometeam.dovehomemod.AllRegistry.*;

public enum RegisterBlock implements Supplier<Block> {
    abrasive_sand_stone(() -> new AbrasiveSandStoneBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE))),//磨砂de砂岩
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




}
