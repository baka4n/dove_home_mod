package io.github.dovehome.creative.generation.util;

import io.github.dovehome.creative.generation.CreativeGenerationForge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static io.github.dovehome.creative.generation.CreativeGenerationForge.gson;

public class CreativeGenerationConfig<T> implements Supplier<T> {

    public static final List<CreativeGenerationConfig<?>> loadedConfigs = new ArrayList<>();
    public static final CreativeGenerationConfig<BlazeBurner> blazeBurner = new CreativeGenerationConfig<>(
            CreativeGenerationForge.blazeBurnerPath, BlazeBurner.class,
            new BlazeBurner()
                    .addItemTag("INFINITY")
                    .addFuel("INFINITY")
                    .addFlame("INFINITY")
    );

    final Path path;
    final Class<T> clazz;

    final T defaultValue;
    T value;
    public CreativeGenerationConfig(Path path, Class<T> clazz, T defaultValue) {

        this.path = path;
        this.clazz = clazz;
        this.defaultValue = defaultValue;
        loadOrCreate();
        loadedConfigs.add(this);
    }

    private void loadOrCreate() {
        try(BufferedReader br = Files.newBufferedReader(path)) {
            value = gson.fromJson(br, clazz);
            return;
        } catch (IOException ignored) {}
        value = defaultValue;
        save();
    }

    public void save() {
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            gson.toJson(value, bw);
        } catch (IOException ignored) {}
    }

    public static void saveAll() {
        for (CreativeGenerationConfig<?> loadedConfig : loadedConfigs) {
            loadedConfig.save();
        }
    }

    @Override
    public T get() {
        if (value == null) value = defaultValue;
        return value;
    }
}
