package io.github.dovehome.dovehomemod.forge.core.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BaseEntityImpl extends EntityBlock {
    @Nullable
    @Override
    default <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return (level.isClientSide() || blockEntityType != getTickerImpl()) ? null : this::dove$tick;
    }

    BlockEntityType<?> getTickerImpl();

    <T extends BlockEntity> void dove$tick(Level world, BlockPos pos, BlockState state, T blockEntity);
}
