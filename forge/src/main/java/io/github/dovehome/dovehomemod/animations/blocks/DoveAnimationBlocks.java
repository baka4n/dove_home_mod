package io.github.dovehome.dovehomemod.animations.blocks;

import com.mojang.datafixers.DSL;
import io.github.dovehome.bakalib.forge.annotations.RegistryBlock;
import io.github.dovehome.bakalib.forge.annotations.RegistryBlockEntity;
import io.github.dovehome.dovehomemod.animations.blocks.entity.MultiTransformBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class DoveAnimationBlocks {
    @RegistryBlock("multi_transform")
    public static final MultiTransformBlock multiTransform = new MultiTransformBlock(BlockBehaviour.Properties.of(Material.SAND));
    @RegistryBlockEntity("multi_transform")
    public static final BlockEntityType<MultiTransformBlockEntity> multiTransformEntity =  BlockEntityType.Builder.of(MultiTransformBlockEntity::new, multiTransform).build(DSL.remainderType());
}
