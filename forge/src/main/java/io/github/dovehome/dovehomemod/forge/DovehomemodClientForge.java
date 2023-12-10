package io.github.dovehome.dovehomemod.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = DovehomemodForge.modid)
public class DovehomemodClientForge {


    public static void registryRender(final EntityRenderersEvent.RegisterRenderers event) {

    }
}
