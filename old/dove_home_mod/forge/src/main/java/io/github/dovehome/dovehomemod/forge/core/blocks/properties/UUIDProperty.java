package io.github.dovehome.dovehomemod.forge.core.blocks.properties;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class UUIDProperty extends Property<UUID> {

    private final ImmutableSet<UUID> values = ImmutableSet.of();
    protected UUIDProperty(String name) {
        super(name, UUID.class);
    }

    public static UUIDProperty create(String name) {
        return new UUIDProperty(name);
    }

    @Override
    public @NotNull Collection<UUID> getPossibleValues() {
        return values;
    }

    @Override
    public @NotNull String getName(UUID value) {
        return value.toString();
    }

    @Override
    public @NotNull Optional<UUID> getValue(@NotNull String value) {
        return Optional.of(UUID.fromString(value));
    }
}
