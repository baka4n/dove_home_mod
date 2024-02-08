package io.github.dovehome.creative.generation.mixins;

import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity.FuelType;
import io.github.dovehome.creative.generation.util.CreativeGenerationConfig;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Arrays;


@Debug(export = true)
@Mixin(value = FuelType.class, remap = false)
public class FuelTypeMixin {


    @Final
    @SuppressWarnings("target")
    @Shadow
    @Mutable
    private static FuelType[] $VALUES;
    @Invoker("<init>")
    public static FuelType init(String name, int ordinal) {
        throw new AssertionError();
    }


    static {
        for (String fuelType : CreativeGenerationConfig.blazeBurner.get().fuelTypes) {
            creative$generation$add(fuelType);
        }
    }

    @Unique
    private static void creative$generation$add(String key) {
        int ordinal = $VALUES.length;
        $VALUES = Arrays.copyOf($VALUES, ordinal + 1);
        $VALUES[ordinal] = init(key, ordinal);
    }
}
