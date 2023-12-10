package io.github.dovehome.dovehomemod.forge.core.items.staff;

import io.github.dovehome.dovehomemod.forge.core.items.DoveStaffBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.mana.ManaPool;

public class SandstoneStaffItem extends DoveStaffBase {
    public SandstoneStaffItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Level world = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();//获取右击的方块pos
        Direction clickedFace = context.getClickedFace();
        if (world.getBlockState(clickedPos).is(Blocks.ACACIA_LOG)) {

            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }

    @Override
    public void onDissolveTick(ManaPool pool, ItemEntity item) {
        super.onDissolveTick(pool, item);
    }
}
