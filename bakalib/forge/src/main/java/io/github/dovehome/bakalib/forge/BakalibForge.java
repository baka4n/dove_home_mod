package io.github.dovehome.bakalib.forge;

import dev.architectury.platform.forge.EventBuses;
import io.github.dovehome.bakalib.Bakalib;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Bakalib.MOD_ID)
public class BakalibForge {
    public BakalibForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Bakalib.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Bakalib.init();
    }
}