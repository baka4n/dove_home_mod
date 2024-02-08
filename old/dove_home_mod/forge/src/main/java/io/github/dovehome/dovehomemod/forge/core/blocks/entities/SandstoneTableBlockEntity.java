package io.github.dovehome.dovehomemod.forge.core.blocks.entities;

import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SandstoneTableBlockEntity extends BlockEntity {

    public String complete_judgment;//完成判断机制 为基本判断是 Pickaxe -> P, Axe -> A, Shovel -> S Knife -> K
    public boolean completed;//是否已经是成品

    public ItemStack u1, u2, u3;//存储上方3个格子物品
    public ItemStack m1, m2, m3;//存储中间3个格子物品
    public ItemStack d1, d2, d3;//存储下方3个格子物品
    //使用这种方式是为了更方便的注册有序合成锁
    public int process;

    public SandstoneTableBlockEntity(BlockPos pos, BlockState blockState) {
        super(DoveBlocks.sandstoneTableEntity, pos, blockState);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        complete_judgment = Objects.requireNonNullElse(tag.getString("complete_judgment"), "");
        completed = tag.getBoolean("completed");
        CompoundTag defaultObj = ItemStack.EMPTY.serializeNBT();
        {

            u1 = ItemStack.of(Objects.requireNonNullElse(tag.getCompound("u1"), defaultObj));
            u2 = ItemStack.of(Objects.requireNonNullElse(tag.getCompound("u2"), defaultObj));
            u3 = ItemStack.of(Objects.requireNonNullElse(tag.getCompound("u3"), defaultObj));
            m1 = ItemStack.of(Objects.requireNonNullElse(tag.getCompound("m1"), defaultObj));
            m2 = ItemStack.of(Objects.requireNonNullElse(tag.getCompound("m2"), defaultObj));
            m3 = ItemStack.of(Objects.requireNonNullElse(tag.getCompound("m3"), defaultObj));
            d1 = ItemStack.of(Objects.requireNonNullElse(tag.getCompound("d1"), defaultObj));
            d2 = ItemStack.of(Objects.requireNonNullElse(tag.getCompound("d2"), defaultObj));
            d3 = ItemStack.of(Objects.requireNonNullElse(tag.getCompound("d3"), defaultObj));
        }
        process = tag.getInt("process");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString("complete_judgment", complete_judgment);
        tag.putBoolean("completed", completed);
        {
            tag.put("u1", u1.serializeNBT());
            tag.put("u2", u2.serializeNBT());
            tag.put("u3", u3.serializeNBT());
            tag.put("m1", m1.serializeNBT());
            tag.put("m2", m2.serializeNBT());
            tag.put("m3", m3.serializeNBT());
            tag.put("d1", d1.serializeNBT());
            tag.put("d2", d2.serializeNBT());
            tag.put("d3", d3.serializeNBT());
        }
        tag.putInt("process", process);
    }

    @Override
    public void saveToItem(ItemStack stack) {
        super.saveToItem(stack);
    }
}
