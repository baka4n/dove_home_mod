package io.github.dovehome.bakalib.forge.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BaseCapabilityProvider<T extends BaseCapability> implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    private final Capability<T> capability;
    private final Class<T> tClass;
    private T t = null;
    private final LazyOptional<T> optional = LazyOptional.of(this::create);

    private T create() {
        if (t == null) {
            try {
                tClass.getDeclaredConstructor().newInstance();
            } catch (Exception ignored) {}
        }
        return t;
    }

    public BaseCapabilityProvider(Capability<T> capability, Class<T> tClass) {
        this.capability = capability;
        this.tClass = tClass;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        if (capability == this.capability) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        create().save(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        create().load(nbt);
    }
}
