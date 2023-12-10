package dev.galacticraft.forge;

import net.minecraft.core.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

@Mod(Galacticraft.modid)
public class Galacticraft {
    public static final String modid = "galacticraft";
    public Galacticraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}
