package io.github.dovehome.dovehomemod.forge.persistence;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class DoveSavedData extends SavedData {
    private boolean second_join_in_server;

    public DoveSavedData(CompoundTag nbt) {
        second_join_in_server = Objects.requireNonNullElse(nbt.getCompound("dovehomemod"), new CompoundTag()).getBoolean("second_join_in_server");
    }

    public void setSecondJoinInServer(boolean second_join_in_server, CompoundTag nbt) {
        this.second_join_in_server = second_join_in_server;
        save(nbt);
    }

    public boolean isSecondJoinInServer() {
        return second_join_in_server;
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag nbt) {
        CompoundTag dovehomemod = new CompoundTag();
        dovehomemod.putBoolean("second_join_in_server", second_join_in_server);
        nbt.put("dovehomemod", dovehomemod);
        return nbt;
    }
}
