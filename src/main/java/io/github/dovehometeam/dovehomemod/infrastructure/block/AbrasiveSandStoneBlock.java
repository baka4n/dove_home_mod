package io.github.dovehometeam.dovehomemod.infrastructure.block;

import io.github.dovehometeam.dovehomemod.infrastructure.block.tile.AbrasiveSandStoneBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static io.github.dovehometeam.dovehomemod.Dovehomemod.ran;

public class AbrasiveSandStoneBlock extends BaseEntityBlock {
    public AbrasiveSandStoneBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos,
                                      @NotNull BlockState blockState) {
        return RegisterTile.abrasive_sand_stone.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel,
                                                                  @NotNull BlockState pState,
                                                                  @NotNull BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide() ? null : createTickerHelper(pBlockEntityType, RegisterTile.abrasive_sand_stone.get(), AbrasiveSandStoneBlockEntity::tick);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void spawnAfterBreak(@NotNull BlockState pState,
                                @NotNull ServerLevel pLevel,
                                @NotNull BlockPos pPos,
                                @NotNull ItemStack pStack,
                                boolean pDropExperience) {
        super.spawnAfterBreak(pState, pLevel, pPos, pStack, pDropExperience);
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity != null) {
            blockEntity.saveToItem(pStack);
        }
    }

    @Override
    public void setPlacedBy(@NotNull Level pLevel,
                            @NotNull BlockPos pPos,
                            @NotNull BlockState pState,
                            @Nullable LivingEntity pPlacer,
                            @NotNull ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        BlockEntity blockEntity = newBlockEntity(pPos, pState);
        CompoundTag blockEntityData = BlockItem.getBlockEntityData(pStack);
        if (blockEntity != null && blockEntityData != null) {
            blockEntity.load(blockEntityData);
        }
    }

//    @Override
//    public void appendHoverText(@NotNull ItemStack pStack,
//                                @Nullable BlockGetter pLevel,
//                                @NotNull List<Component> pTooltip,
//                                @NotNull TooltipFlag pFlag) {
//        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
//        CompoundTag blockEntityData = BlockItem.getBlockEntityData(pStack);
//        if (blockEntityData != null) {
//            pTooltip.add(Component.empty().append(Component.translatable("dovehomemod.abrasive.value")).append(String.valueOf(blockEntityData.getInt("done"))));
//        }
//    }

    @SuppressWarnings("deprecation")
    @Override
    public void attack(@NotNull BlockState pState,
                       @NotNull Level pLevel,
                       @NotNull BlockPos pPos,
                       @NotNull Player pPlayer) {
        super.attack(pState, pLevel, pPos, pPlayer);
        ItemStack mainHand = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);

        if (ran.nextInt(0, 60) <= 2) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof AbrasiveSandStoneBlockEntity entity) {
                CompoundTag tag = mainHand.getOrCreateTag();
                CompoundTag blockEntityData = tag.contains("BlockEntityTag") ? tag.getCompound("BlockEntityTag") : new CompoundTag();
                if (!tag.contains("BlockEntityTag")) tag.put("BlockEntityTag", blockEntityData);
                blockEntityData.putInt("done", blockEntityData.getInt("done") + (entity.getDone()!=0 ? ((entity.getDone() / 5) + 1) : 1));
            }

        }
    }
}
