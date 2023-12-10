package io.github.dovehome.dovehomemod.datagen;

import io.github.dovehome.dovehomemod.datagen.client.i18n.EnUsLanguage;
import io.github.dovehome.dovehomemod.datagen.client.i18n.ZhCnLanguage;
import io.github.dovehome.dovehomemod.datagen.server.DoveRecipeProvider;
import io.github.dovehome.dovehomemod.datagen.server.DoveTableLootProvider;
import io.github.dovehome.dovehomemod.forge.DovehomemodForge;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DovehomemodForge.modid, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DovehomemodDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        System.out.println("dove home mod has starting datagen....");
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        generator.addProvider(event.includeClient(), new EnUsLanguage(generator));
        generator.addProvider(event.includeClient(), new ZhCnLanguage(generator));
        generator.addProvider(event.includeServer(), new DoveTableLootProvider(generator, DovehomemodForge.modid));
        generator.addProvider(event.includeServer(), new DoveRecipeProvider(generator));
    }
}
