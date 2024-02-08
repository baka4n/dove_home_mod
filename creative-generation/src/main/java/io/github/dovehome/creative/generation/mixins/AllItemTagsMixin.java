package io.github.dovehome.creative.generation.mixins;

import com.simibubi.create.AllTags.AllItemTags;
import com.simibubi.create.AllTags.NameSpace;
import io.github.dovehome.creative.generation.util.CreativeGenerationConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Arrays;

@Debug(export = true)
@Mixin(value = AllItemTags.class, remap = false)
public class AllItemTagsMixin {
    @Final
    @SuppressWarnings("target")
    @Shadow
    @Mutable
    private static AllItemTags[] $VALUES;

    static {
        CreativeGenerationConfig
                .blazeBurner
                .get()
                .itemTags
                .tags
                .forEach(AllItemTagsMixin::creative$generation$add);
    }

    @Unique
    private static void creative$generation$add(@NotNull String name, @Nullable String path) {
        int ordinal = $VALUES.length;
        $VALUES = Arrays.copyOf($VALUES,ordinal + 1);

        if (path == null || path.isEmpty())
            $VALUES[ordinal] = init(name, ordinal, NameSpace.MOD);
        $VALUES[ordinal] = init(name, ordinal, NameSpace.MOD, path);
    }

    @Invoker("<init>")
    public static AllItemTags init(String name, int ordinal, NameSpace namespace, String path) {
        throw new RuntimeException();
    }

    @Invoker("<init>")
    public static AllItemTags init(String name, int ordinal, NameSpace namespace) {
        throw new RuntimeException();
    }

}
