package io.github.dovehome.laysdelight.forge;


import io.github.dovehome.laysdelight.forge.registry.LaysItems;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Mod(LaysDelight.modid)//乐事乐事
public class LaysDelight {
    public static final String modid = "laysdelight";
    public static final Map<String, Item> items = new HashMap<>();
    public LaysDelight() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(LaysDelight::registry);
    }

    @SubscribeEvent
    public static void registry(RegisterEvent event) {
        LaysItems.registry();// init
        register(event, Registry.ITEM_REGISTRY, items);
        items.clear();
        System.gc();
    }

    public static <T> void register(@NotNull RegisterEvent event, ResourceKey<Registry<T>> registry, Map<String, T> map) {
        for (Map.Entry<String, T> entry : map.entrySet()) {
            event.register(registry, new ResourceLocation(modid, entry.getKey()), entry::getValue);
        }
    }
}
