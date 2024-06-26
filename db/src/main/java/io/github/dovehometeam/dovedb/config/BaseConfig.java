package io.github.dovehometeam.dovedb.config;

import io.github.dovehometeam.dovedb.DoveDB;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.Arrays;

@Mod.EventBusSubscriber(modid = DoveDB.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BaseConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<Integer> sqlAutoSave = BUILDER.comment("auto save sql").define("AutoSaveSQL", 3000);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
    public static int autoSaveSQL;
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        autoSaveSQL = sqlAutoSave.get();
    }
}
