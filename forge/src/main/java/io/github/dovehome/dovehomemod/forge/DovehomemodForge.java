package io.github.dovehome.dovehomemod.forge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.architectury.platform.forge.EventBuses;
import io.github.dovehome.bakalib.forge.registry.ForgeEventRegistry;
import io.github.dovehome.dovehomemod.events.*;
import io.github.dovehome.dovehomemod.forge.core.blocks.properties.Properties;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveDimensions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;
import vazkii.botania.common.item.BotaniaItems;

@Mod(DovehomemodForge.modid)
public class DovehomemodForge {
    public static final String modid = "dovehomemod";

    public static final Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();

    public static final int DEFAULT_AURA = 300000;

    public static ResourceLocation id(String name) {
        return new ResourceLocation(modid, name);
    }

    public static String advancementMain(String name) {
        return id("main/" + name).toString();
    }

    public ForgeEventRegistry registry;

    public DovehomemodForge() {

        DoveDimensions.registry();
        GeckoLib.initialize();
        DoveCriteriaTriggers.init();
        Properties.init();
        registry = new ForgeEventRegistry(FMLJavaModLoadingContext.get().getModEventBus());
        registry
                .setType(ForgeEventRegistry.Type.MOD)
                .listener(DoveRegistryEvent::registry)
                .registry(new DoveModLoaderEvent())
                .listener(DovehomemodClientForge::registryRender)
                .setType(ForgeEventRegistry.Type.FORGE)
                .registry(new DoveBlockEvents())
                .registry(new DovePlayerEvents());
        registry = null;
        System.gc();
    }





}