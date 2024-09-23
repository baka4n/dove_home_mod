package io.github.dovehometeam.dovedatagen;

import io.github.dovehometeam.dovedatagen.lang.DefaultLangGen;
import io.github.dovehometeam.dovedatagen.lang.ZhCnLangGen;
import io.github.dovehometeam.dovehomemod.Dovehomemod;

import io.github.dovehometeam.dovehomemod.infrastructure.registry.RegisterBlock;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Dovehomemod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DoveDatagen {

    @SubscribeEvent
    public static void datagen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        DefaultLangGen en_us = new DefaultLangGen(generator.getPackOutput());
        ZhCnLangGen zh_cn = new ZhCnLangGen(generator.getPackOutput());

        en_us
                .put(RegisterBlock.abrasive_sand_stone.get(), "abrasive sand stone")
                .put("dovehomemod.abrasive.value", "Abrasive value: ");
        zh_cn
                .put(RegisterBlock.abrasive_sand_stone.get(), "磨砂的砂岩")
                .put("dovehomemod.abrasive.value", "磨砂值: ");



        generator.addProvider(event.includeClient(), en_us);
        generator.addProvider(event.includeClient(), zh_cn);

    }
}
