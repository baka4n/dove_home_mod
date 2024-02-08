package io.github.dovehome.dovehomemod.events;

import io.github.dovehome.bakalib.forge.registry.RegistryAll;
import io.github.dovehome.dovehomemod.animations.blocks.DoveAnimationBlocks;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveConfiguredFeatures;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveItems;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveRecipes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static io.github.dovehome.dovehomemod.forge.DovehomemodForge.modid;

public class DoveRegistryEvent {

    public static final Map<String, ConfiguredFeature<?, ?>> configuredFeatures = new HashMap<>();

    public static final Map<String, Biome> biomes = new HashMap<>();
    public static final Map<String, Level> dimensions = new HashMap<>();

    @SubscribeEvent
    public static void registry(RegisterEvent event) {

        RegistryAll registryAll = new RegistryAll(modid);
        registryAll.add(DoveBlocks.class);
        registryAll.add(DoveItems.class);
        registryAll.add(DoveConfiguredFeatures.class);
        registryAll.add(DoveRecipes.class);
        registryAll.add(DoveAnimationBlocks.class);
        registryAll.register(event);

        register(event, Registry.CONFIGURED_FEATURE_REGISTRY, configuredFeatures);
        register(event, Registry.BIOME_REGISTRY, biomes);
        register(event, Registry.DIMENSION_REGISTRY, dimensions);
        configuredFeatures.clear();
        biomes.clear();
        dimensions.clear();
        Runtime.getRuntime().gc();
    }

    public static <T> void register(@NotNull RegisterEvent event, ResourceKey<Registry<T>> registry, Map<String, T> map) {
        for (Map.Entry<String, T> entry : map.entrySet()) {
            event.register(registry, new ResourceLocation(modid, entry.getKey()), entry::getValue);
        }
    }
}
