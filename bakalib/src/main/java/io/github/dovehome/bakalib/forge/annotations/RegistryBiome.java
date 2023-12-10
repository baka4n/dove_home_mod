package io.github.dovehome.bakalib.forge.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface RegistryBiome {
    String[] value();
}
