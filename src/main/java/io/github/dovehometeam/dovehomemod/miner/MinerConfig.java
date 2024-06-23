package io.github.dovehometeam.dovehomemod.miner;

import io.github.dovehometeam.dovehomemod.Dovehomemod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
@Mod.EventBusSubscriber(modid = Dovehomemod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MinerConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<Integer> maxPlayerCycle = BUILDER.comment("max player cycle").define("MaxPlayerCycle", 4);
    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int playerCycleMax;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        playerCycleMax = maxPlayerCycle.get();
    }
}
