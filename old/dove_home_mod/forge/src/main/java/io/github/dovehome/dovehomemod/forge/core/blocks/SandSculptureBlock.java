package io.github.dovehome.dovehomemod.forge.core.blocks;

import io.github.dovehome.dovehomemod.forge.core.blocks.abstracts.HorizontalDirectionalEntitySaveNbtBlock;
import io.github.dovehome.dovehomemod.forge.core.blocks.entities.SandSculptureBlockEntity;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SandSculptureBlock extends HorizontalDirectionalEntitySaveNbtBlock {
    public SandSculptureBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<SandSculptureBlockEntity> getTickerImpl() {
        return DoveBlocks.sandSculptureEntity;
    }

    @Override
    public void dove$tick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new SandSculptureBlockEntity(pos, state);
    }
}
