package io.github.dovehome.dovehomemod.forge.core.registry;

import io.github.dovehome.dovehomemod.forge.DovehomemodForge;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.presets.WorldPreset;

public class DoveDimensions {
    public static final ResourceKey<Level> DESERT_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(DovehomemodForge.modid, "desert"));

    public static final ResourceKey<Biome> DESERT_BIOME = ResourceKey.create(Registry.BIOME_REGISTRY, DESERT_KEY.registry());

    public static final ResourceKey<DimensionType> DESERT_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, DESERT_KEY.registry());

    public static final ResourceKey<NoiseGeneratorSettings> DESERT_NOISE_SETTINGS = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, DESERT_KEY.registry());

    public static final ResourceKey<WorldPreset> DESERT_PRESET = ResourceKey.create(Registry.WORLD_PRESET_REGISTRY, DESERT_KEY.registry());

    public static void registry() {
//        for (Field declaredField : DoveDimensions.class.getDeclaredFields()) {
//
//        }
        // OptionalLong fixedTime, boolean hasSkyLight, boolean hasCeiling, boolean ultraWarm,
        // boolean natural, double coordinateScale, boolean bedWorks, boolean respawnAnchorWorks,
        // int minY, int height, int logicalHeight, TagKey<Block> infiniburn,
        // ResourceLocation effectsLocation, float ambientLight, MonsterSettings monsterSettings
        System.out.println("Registering Desert Dimensions for " + DovehomemodForge.modid);
    }

}
