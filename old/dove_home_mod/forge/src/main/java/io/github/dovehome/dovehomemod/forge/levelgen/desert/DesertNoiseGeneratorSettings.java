package io.github.dovehome.dovehomemod.forge.levelgen.desert;

import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;

import java.util.List;

public record DesertNoiseGeneratorSettings(NoiseSettings noiseSettings, BlockState defaultBlock, BlockState defaultFluid, NoiseRouter noiseRouter, SurfaceRules.RuleSource surfaceRule, List<Climate.ParameterPoint> spawnTarget, int seaLevel, boolean disableMobGeneration, boolean aquifersEnabled, boolean oreVeinsEnabled, boolean useLegacyRandomSource) {
    public DesertNoiseGeneratorSettings(NoiseSettings noiseSettings,
                                        BlockState defaultBlock,
                                        BlockState defaultFluid,
                                        NoiseRouter noiseRouter,
                                        SurfaceRules.RuleSource surfaceRule,
                                        List<Climate.ParameterPoint> spawnTarget,
                                        int seaLevel,
                                        boolean disableMobGeneration,
                                        boolean aquifersEnabled,
                                        boolean oreVeinsEnabled,
                                        boolean useLegacyRandomSource) {
        this.noiseSettings = noiseSettings;
        this.defaultBlock = defaultBlock;
        this.defaultFluid = defaultFluid;
        this.noiseRouter = noiseRouter;
        this.surfaceRule = surfaceRule;
        this.spawnTarget = spawnTarget;
        this.seaLevel = seaLevel;
        this.disableMobGeneration = disableMobGeneration;
        this.aquifersEnabled = aquifersEnabled;
        this.oreVeinsEnabled = oreVeinsEnabled;
        this.useLegacyRandomSource = useLegacyRandomSource;
    }
}
