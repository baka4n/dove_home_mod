package io.github.dovehometeam.dovegod;

import io.github.dovehometeam.dovegod.db.DoveGodSQL;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Random;

@Mod(DoveGod.MODID)
public class DoveGod {
    public static final String MODID = "dovegod";
    public static final DoveGodSQL godSQL = new DoveGodSQL();

    public static final Random ran = new Random();
    public DoveGod() {

        IEventBus mod = FMLJavaModLoadingContext.get().getModEventBus();
        AllRegistry.registerAll(mod);

        IEventBus forge = MinecraftForge.EVENT_BUS;

        forge.register(godSQL);
    }
}
