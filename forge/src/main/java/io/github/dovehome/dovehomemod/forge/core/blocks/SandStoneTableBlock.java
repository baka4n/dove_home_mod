package io.github.dovehome.dovehomemod.forge.core.blocks;

import io.github.dovehome.dovehomemod.forge.core.blocks.entities.SandstoneTableBlockEntity;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import io.github.dovehome.dovehomemod.forge.core.util.BaseEntityImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SandStoneTableBlock extends HorizontalDirectionalBlock implements BaseEntityImpl {
    public SandStoneTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<?> getTickerImpl() {
        return DoveBlocks.sandstoneTableEntity;
    }

    @Override
    public <T extends BlockEntity> void dove$tick(Level world, BlockPos pos, BlockState state, T blockEntity) {
        SandstoneTableBlockEntity sandstoneTableEntity = (SandstoneTableBlockEntity) blockEntity;
        if (!sandstoneTableEntity.completed) {
            String completeJudgment = sandstoneTableEntity.complete_judgment;
            if (
                    completeJudgment.contains("A")
                    && completeJudgment.contains("S")
                    && completeJudgment.contains("K")
                    && completeJudgment.contains("P")
            ) {
                sandstoneTableEntity.completed = true;
            }
            return;
        }

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new SandstoneTableBlockEntity(pos, state);
    }
}
