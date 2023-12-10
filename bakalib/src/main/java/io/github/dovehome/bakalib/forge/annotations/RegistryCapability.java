package io.github.dovehome.bakalib.forge.annotations;

import io.github.dovehome.bakalib.forge.capabilities.BaseCapability;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RegistryCapability {
    String value();
    Class<? extends BaseCapability> clazz();
}
