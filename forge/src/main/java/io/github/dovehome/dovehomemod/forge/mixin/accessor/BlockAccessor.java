package io.github.dovehome.dovehomemod.forge.mixin.accessor;

import io.github.dovehome.dovehomemod.forge.core.blocks.properties.UUIDProperty;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Block.class)
public interface BlockAccessor {
    @Unique
    UUIDProperty dove_home_mod$GetMultiUuid();
}
