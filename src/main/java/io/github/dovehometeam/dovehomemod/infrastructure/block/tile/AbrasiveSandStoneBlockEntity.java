package io.github.dovehometeam.dovehomemod.infrastructure.block.tile;

import io.github.dovehometeam.dovehomemod.auxiliary.IBlockEntity;
import io.github.dovehometeam.dovehomemod.infrastructure.block.RegisterTile;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class AbrasiveSandStoneBlockEntity extends BlockEntity implements IBlockEntity {
    private int done;

    public int getDone() {
        return done;
    }

    public int addDone() {
        this.done++;
        return done;
    }

    public int addDone(int done) {
        this.done+=done;
        return done;
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("done", done);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        done = pTag.getInt("done");
    }


    public AbrasiveSandStoneBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(RegisterTile.abrasive_sand_stone.get(), pPos, pBlockState);
    }

    public static <T extends AbrasiveSandStoneBlockEntity> void tick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        if (!(blockEntity instanceof AbrasiveSandStoneBlockEntity entity)) return;

    }


}
