package io.github.dovehometeam.dovedb;

import io.github.dovehometeam.dovedb.config.BaseConfig;
import io.github.dovehometeam.dovedb.db.base.DoveSQL;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(DoveDB.MODID)
public class DoveDB {
    public static final String MODID = "dovedb";

    public DoveDB() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BaseConfig.SPEC);
        IEventBus forge = MinecraftForge.EVENT_BUS;
        forge.register(new DoveSQL());
    }
}
