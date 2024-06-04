package io.github.dovehometeam.dovehomemod;

import io.github.dovehometeam.dovehomemod.item.RegisterItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AllRegistry {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, Dovehomemod.MODID);


    public static void init(IEventBus modEventBus) {
        RegisterItem.init();
        items.register(modEventBus);
    }
}
