package io.github.dovehome.dovehomemod.base;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class AnimationBlockRenderBase<T extends AnimationBlockEntityBase> extends GeoBlockRenderer<T> {
    public AnimationBlockRenderBase(BlockEntityRendererProvider.Context rendererProvider, AnimatedGeoModel<T> modelProvider) {
        super(rendererProvider, modelProvider);
    }
}
