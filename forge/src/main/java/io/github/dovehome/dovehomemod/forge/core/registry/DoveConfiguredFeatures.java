package io.github.dovehome.dovehomemod.forge.core.registry;

import io.github.dovehome.bakalib.forge.annotations.RegistryConfiguredFeature;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class DoveConfiguredFeatures {

    @RegistryConfiguredFeature("resurrected_oak")
    public static final ConfiguredFeature<TreeConfiguration, Feature<TreeConfiguration>> resurrectedOak;
    static {
        resurrectedOak = new ConfiguredFeature<>(
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.ACACIA_LOG),
                        new StraightTrunkPlacer(2, 3, 1),
                        BlockStateProvider.simple(Blocks.AIR),
                        new BlobFoliagePlacer(
                                ConstantInt.of(1),
                                ConstantInt.of(0),
                                1
                        ),
                        new TwoLayersFeatureSize(1, 0, 2)
                ).build());
    }
}
