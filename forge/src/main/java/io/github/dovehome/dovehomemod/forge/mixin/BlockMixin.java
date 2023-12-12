package io.github.dovehome.dovehomemod.forge.mixin;

import io.github.dovehome.dovehomemod.forge.core.blocks.properties.UUIDProperty;
import io.github.dovehome.dovehomemod.forge.mixin.accessor.BlockAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(Block.class)
public abstract class BlockMixin extends BlockBehaviour implements BlockAccessor {
    @Unique
    private static final UUIDProperty dove_home_mod$uuid = io.github.dovehome.dovehomemod.forge.core.blocks.properties.Properties.multiBlockGen;

    @Override
    public UUIDProperty dove_home_mod$GetMultiUuid() {
        return dove_home_mod$uuid;
    }

    public BlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "registerDefaultState", at = @At("HEAD"))
    private void registerDefaultState(BlockState state, CallbackInfo ci) {
        state.setValue(dove_home_mod$uuid, UUID.randomUUID());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void updateIndirectNeighbourShapes(@NotNull BlockState state,
                                              @NotNull LevelAccessor level,
                                              @NotNull BlockPos pos,
                                              int flags,
                                              int recursionLeft) {
        super.updateIndirectNeighbourShapes(state, level, pos, flags, recursionLeft);
    }
}
