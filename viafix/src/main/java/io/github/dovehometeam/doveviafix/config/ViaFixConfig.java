package io.github.dovehometeam.doveviafix.config;

import io.github.dovehometeam.doveviafix.DoveViaFix;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.Arrays;

@EventBusSubscriber(modid = DoveViaFix.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ViaFixConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.BooleanValue noInfiniteWater = BUILDER.comment("No Infinite Water").define("NoInfiniteWater", true);
    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean noInfiniteWaterBoolean;
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        noInfiniteWaterBoolean = noInfiniteWater.get();
    }
}
