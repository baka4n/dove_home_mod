package io.github.dovehome.dovehomemod.forge.core.blocks.entities;

import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SandSculptureBlockEntity extends BlockEntity {
    public SandSculptureBlockEntity(BlockPos pos, BlockState blockState) {
        super(DoveBlocks.sandSculptureEntity, pos, blockState);
    }
}
