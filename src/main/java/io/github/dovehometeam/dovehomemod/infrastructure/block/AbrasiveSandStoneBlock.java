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
import net.minecraft.world.level.storage.loot.LootParams;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

import static io.github.dovehometeam.dovehomemod.Dovehomemod.ran;

public class AbrasiveSandStoneBlock extends BaseEntityBlock {
    public AbrasiveSandStoneBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos,
                                      @NotNull BlockState blockState) {
        return RegisterBlock.RegisterType.abrasive_sand_stone.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel,
                                                                  @NotNull BlockState pState,
                                                                  @NotNull BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide() ? null : createTickerHelper(pBlockEntityType, RegisterBlock.RegisterType.abrasive_sand_stone.get(), AbrasiveSandStoneBlockEntity::tick);
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

    @Override
    public void appendHoverText(@NotNull ItemStack pStack,
                                @Nullable BlockGetter pLevel,
                                @NotNull List<Component> pTooltip,
                                @NotNull TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        CompoundTag blockEntityData = BlockItem.getBlockEntityData(pStack);
        if (blockEntityData != null) {
            pTooltip.add(Component.empty().append(Component.translatable("dovehomemod.abrasive.value")).append(String.valueOf(blockEntityData.getInt("done"))));
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void attack(@NotNull BlockState pState,
                       @NotNull Level pLevel,
                       @NotNull BlockPos pPos,
                       @NotNull Player pPlayer) {
        super.attack(pState, pLevel, pPos, pPlayer);
        ItemStack mainHand = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
        if (mainHand.is(RegisterBlock.RegisterItem.abrasive_sand_stone.get())) {
            if (pState.hasBlockEntity()) {
                BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
                if (!mainHand.hasTag())
                    mainHand.setTag(new CompoundTag());
                if (blockEntity instanceof AbrasiveSandStoneBlockEntity entity) {
                    if (ran.nextInt(0, 30) <= 2) {
                        CompoundTag blockEntityData = BlockItem.getBlockEntityData(mainHand);
                        if (blockEntityData == null) {
                            entity.saveToItem(mainHand);
                        } else {
                            blockEntityData.putInt("done",  blockEntityData.getInt("done")+entity.getDone());
                            mainHand.removeTagKey("BlockEntityTag");
                            mainHand.addTagElement("BlockEntityTag", blockEntityData);
                        }

                    }
                }

            }
        }
    }
}
