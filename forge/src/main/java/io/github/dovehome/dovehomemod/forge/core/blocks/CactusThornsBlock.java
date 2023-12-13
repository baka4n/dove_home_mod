package io.github.dovehome.dovehomemod.forge.core.blocks;

import io.github.dovehome.dovehomemod.forge.core.blocks.abstracts.HorizontalDirectionalEntitySaveNbtBlock;
import io.github.dovehome.dovehomemod.forge.core.blocks.entities.CactusThornsBlockEntity;
import io.github.dovehome.dovehomemod.forge.core.items.CactusThornsBlockItem;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CactusThornsBlock extends HorizontalDirectionalEntitySaveNbtBlock {

    public CactusThornsBlock() {
        super(BlockBehaviour.Properties.of(Material.CACTUS).noOcclusion().noCollission());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
        super.entityInside(state, level, pos, entity);
        entity.hurt(DamageSource.CACTUS, 1F);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof CactusThornsBlockEntity block) {
            if (block.getBloodCount() >= CactusThornsBlockItem.MAX) {
                return;
            }
            block.addBloodCount(1);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state,
                                          @NotNull Level level,
                                          @NotNull BlockPos pos,
                                          @NotNull Player player,
                                          @NotNull InteractionHand hand,
                                          @NotNull BlockHitResult hit) {
        return super.use(state, level, pos, player, hand, hit);
    }

    @Nullable
    @Override
    public CactusThornsBlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CactusThornsBlockEntity(pos, state);
    }

    @Override
    public BlockEntityType<CactusThornsBlockEntity> getTickerImpl() {
        return DoveBlocks.cactusThornsEntity;
    }

    @Override
    public <T extends BlockEntity> void dove$tick(Level world, BlockPos pos, BlockState state, T blockEntity) {

    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return super.rotate(state, level, pos, direction);
    }

    @Override
    public @NotNull BlockState mirror(@NotNull BlockState state, @NotNull Mirror mirror) {
        return super.mirror(state, mirror);
    }
}
