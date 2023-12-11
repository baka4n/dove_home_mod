package io.github.dovehome.dovehomemod.forge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveDimensions;
import io.github.dovehome.dovehomemod.forge.events.DoveBlockEvents;
import io.github.dovehome.dovehomemod.forge.events.DoveModLoaderEvent;
import io.github.dovehome.dovehomemod.forge.events.DovePlayerEvents;
import io.github.dovehome.dovehomemod.forge.events.DoveRegistryEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(DovehomemodForge.modid)
public class DovehomemodForge {
    public static final String modid = "dovehomemod";

    public static final Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();

    public static final int DEFAULT_AURA = 300000;

    public static ResourceLocation id(String name) {
        return new ResourceLocation(modid, name);
    }

    public static String idStr(String name) {
        return id(name).toString();
    }

    public DovehomemodForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(DoveRegistryEvent::registry);


        modEventBus.addListener(DoveModLoaderEvent::init);
        modEventBus.addListener(DoveModLoaderEvent::commonSetup);
        DoveDimensions.registry();
        GeckoLib.initialize();
        modEventBus.addListener(DovehomemodClientForge::registryRender);
//        modEventBus.addListener(DovehomemodForge::commonSetup);
        IEventBus eventBus = MinecraftForge.EVENT_BUS;
//        eventBus.register(this);
        eventBus.addListener(DoveBlockEvents::rightClientBlock);
        //cap registry
        eventBus.addListener(DovePlayerEvents::firstJoinServer);

    }





}