package io.github.dovehome.creative.generation.mixins;

import com.simibubi.create.content.processing.burner.LitBlazeBurnerBlock.FlameType;
import io.github.dovehome.creative.generation.util.CreativeGenerationConfig;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Arrays;

@Debug(export = true)
@Mixin(value = FlameType.class, remap = false)
public class FlameTypeMixin {
    @SuppressWarnings("target")
    @Shadow
    @Final
    @Mutable
    private static FlameType[] $VALUES;
    @Invoker("<init>")
    public static FlameType init(String name, int ordinal) {
        throw new AssertionError();
    }

    static {
        for (String flameType : CreativeGenerationConfig.blazeBurner.get().flameTypes) {
            creative$generation$add(flameType);
        }
    }


    @Unique
    private static void creative$generation$add(String key) {
        int ordinal = $VALUES.length;
        $VALUES = Arrays.copyOf($VALUES, ordinal + 1);
        $VALUES[ordinal] = init(key, ordinal);
    }
}
