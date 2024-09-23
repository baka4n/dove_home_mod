package io.github.dovehometeam.doveviafix;

import io.github.dovehometeam.doveviafix.config.ViaFixConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import static io.github.dovehometeam.doveviafix.DoveViaFix.MODID;

@Mod(MODID)
public class DoveViaFix {
    public static final String MODID = "doveviafix";

    public DoveViaFix() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ViaFixConfig.SPEC);
    }
}
