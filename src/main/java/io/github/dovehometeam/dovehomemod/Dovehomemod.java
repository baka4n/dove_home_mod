package io.github.dovehometeam.dovehomemod;

import com.mojang.logging.LogUtils;
import io.github.dovehometeam.dovehomemod.db.DoveSQL;
import io.github.dovehometeam.dovehomemod.event.DoveChunkTeamEvent;
import io.github.dovehometeam.dovehomemod.infrastructure.Config;
import io.github.dovehometeam.dovehomemod.miner.MinerConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

@Mod(Dovehomemod.MODID)
public class Dovehomemod {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "dovehomemod";
    public static final Random ran = new Random();
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LoggerFactory.getLogger("[%s]: ".formatted(MODID));

    public Dovehomemod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        AllRegistry.init(modEventBus);
        // Register ourselves for server and other game events we are interested in
        IEventBus eventBus = MinecraftForge.EVENT_BUS;
        eventBus.register(this);
        eventBus.addListener(DoveSQL::serverStarting);
        eventBus.addListener(DoveSQL::serverStopped);
        eventBus.addListener(DoveSQL::playerJoinServer);
        eventBus.addListener(DoveSQL::tick);

        eventBus.addListener(DoveChunkTeamEvent::breakBlock);
        eventBus.addListener(DoveChunkTeamEvent::placeBlock);
        eventBus.addListener(DoveChunkTeamEvent::trampleFarmland);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MinerConfig.SPEC, "dovehomemod-miner-common.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
