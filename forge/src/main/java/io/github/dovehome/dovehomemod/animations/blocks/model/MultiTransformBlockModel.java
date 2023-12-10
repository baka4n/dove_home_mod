package io.github.dovehome.dovehomemod.animations.blocks.model;

import io.github.dovehome.dovehomemod.animations.blocks.entity.MultiTransformBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MultiTransformBlockModel extends AnimatedGeoModel<MultiTransformBlockEntity> {
    @Override
    public ResourceLocation getModelResource(MultiTransformBlockEntity object) {
        return new ResourceLocation("");
    }

    @Override
    public ResourceLocation getTextureResource(MultiTransformBlockEntity object) {
        return new ResourceLocation("");
    }

    @Override
    public ResourceLocation getAnimationResource(MultiTransformBlockEntity animatable) {
        return new ResourceLocation("");
    }
}
