package io.github.dovehome.dovehomemod.datagen.worldgen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.*;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

public class DoveWorldgenProvider implements DataProvider {
    public static final RegistryAccess.Writable registrable = RegistryAccess.builtinCopy();
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().setLenient().create();
    private final DataGenerator generator;

    private final String modid;

    public DoveWorldgenProvider(DataGenerator dataGenerator, String modid) {
        generator = dataGenerator;
        this.modid = modid;
    }

    public ResourceLocation id(String name) {
        return new ResourceLocation(modid, name);
    }

    private Registry<LevelStem> registryDimension() {
        WritableRegistry<LevelStem> writableRegistry = new MappedRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.stable(), null);
        return writableRegistry;
    }

    public void levelStem(WritableRegistry<LevelStem> writableRegistry, String name ,DimensionType desertType, ChunkGenerator chunkGenerator) {
        writableRegistry.register(
                ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, id(name)),
                new LevelStem(Holder.direct(desertType), chunkGenerator),
                Lifecycle.stable()
        );
    }

    @Override
    public void run(@NotNull CachedOutput output) throws IOException {
        Path path = this.generator.getOutputFolder();
        var dtnamicops = RegistryOps.create(JsonOps.INSTANCE, registrable);

    }

    @Override
    public @NotNull String getName() {
        return "Worldgen:" + modid;
    }
}
