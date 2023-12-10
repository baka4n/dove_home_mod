package io.github.dovehome.bakalib.forge.capabilities;

import net.minecraft.nbt.CompoundTag;

public abstract class BaseCapability {
    private final boolean death_retention;

    protected BaseCapability(boolean deathRetention) {
        death_retention = deathRetention;
    }

    public abstract void save(CompoundTag tag);
    public abstract void load(CompoundTag tag);
    public abstract <T extends BaseCapability> void copyFrom(T capability);

    public boolean isDeathRetention() {
        return death_retention;
    }
}
