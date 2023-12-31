package io.github.dovehome.bakalib.forge.registry;

import io.github.dovehome.bakalib.forge.annotations.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author baka4n
 * @since public static class Example {
 * <br>@RegistryItem("test1")</br>
 * <br>     public static final Item test1 = new Item(new Item.Properties());</br>
 * <br> }</br>
 * <br> public void exampleEvent(RegisterEvent event) {</br>
 * <br>     RegistryAll example = new RegistryAll("example_mod");</br>
 * <br>     example.add(Example.class);</br>
 * <br>     example.register(event);</br>
 * <br> }
 */
public class RegistryAll {// can multi-thread registry
    private Map<String, Block> blocks;
    private Map<String, Item> items;
    private Map<String, BlockEntityType<?>> blockEntitys;
    private Map<String, ConfiguredFeature<?, ?>> configuredFeatures;
    private Map<String, DimensionType> dimensionTypes;
    private Map<String, RecipeType<?>> recipeTypes;
    private Map<String, MenuType<?>> menus;

    private final String modid;
    private Map<String, RecipeSerializer<?>> recipeSerializers;

    public RegistryAll(String modid) {
        blockEntitys = new ConcurrentHashMap<>();
        blocks = new ConcurrentHashMap<>();
        items = new ConcurrentHashMap<>();
        configuredFeatures = new ConcurrentHashMap<>();
        dimensionTypes = new ConcurrentHashMap<>();
        recipeTypes = new ConcurrentHashMap<>();
        recipeSerializers = new ConcurrentHashMap<>();
        menus = new ConcurrentHashMap<>();
        this.modid = modid;
    }

    public void add(Class<?> clazz) {
        fieldSettings(clazz);
        methodSettings(clazz);
    }

    @Nullable
    private static Object methodFieldGet(Class<?> clazz, AccessibleObject accessibleObject) throws IllegalAccessException, InvocationTargetException {
        return accessibleObject instanceof Method ? ((Method) accessibleObject).invoke(clazz) : accessibleObject instanceof Field ? ((Field) accessibleObject).get(null) : null;
    }

    private void methodSettings(Class<?> clazz) {
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            declaredMethod.setAccessible(true);
            if (declaredMethod.getAnnotations().length == 0)
                continue;
            if (declaredMethod.getAnnotations().length == 0) continue;
            if (declaredMethod.isAnnotationPresent(RegistryObject.class)) {
                for (String s : declaredMethod.getAnnotation(RegistryObject.class).value()) {
                    objectRegistry(declaredMethod, clazz, s);
                }
                continue;
            }

            if (declaredMethod.isAnnotationPresent(RegistryItem.class)) {
                for (String s : declaredMethod.getAnnotation(RegistryItem.class).value()) {
                    set(items, clazz, declaredMethod, s);
                }
            }
            else if (declaredMethod.isAnnotationPresent(RegistryBlock.class)) {
                for (String s : declaredMethod.getAnnotation(RegistryBlock.class).value()) {
                    set(blocks, clazz, declaredMethod, s);

                }
            }
            else if (declaredMethod.isAnnotationPresent(RegistryBlockEntity.class)) {
                for (String s : declaredMethod.getAnnotation(RegistryBlockEntity.class).value()) {
                    set(blockEntitys, clazz, declaredMethod, s);

                }
            }
            else if (declaredMethod.isAnnotationPresent(RegistryConfiguredFeature.class)) {
                for (String s : declaredMethod.getAnnotation(RegistryConfiguredFeature.class).value()) {
                    set(configuredFeatures, clazz, declaredMethod, s);
                }
            }
            else if (declaredMethod.isAnnotationPresent(RegistryDimensionType.class)) {
                for (String s : declaredMethod.getAnnotation(RegistryDimensionType.class).value()) {
                    set(dimensionTypes, clazz, declaredMethod, s);
                }
            }
            else if (declaredMethod.isAnnotationPresent(RegistryRecipeType.class)) {
                for (String s : declaredMethod.getAnnotation(RegistryRecipeType.class).value()) {
                    set(recipeTypes, clazz, declaredMethod, s);
                }
            }
            else if (declaredMethod.isAnnotationPresent(RegistryRecipeSerializer.class)) {
                for (String s : declaredMethod.getAnnotation(RegistryRecipeSerializer.class).value()) {
                    set(recipeSerializers, clazz, declaredMethod, s);
                }
            }
            else if (declaredMethod.isAnnotationPresent(RegistryMenu.class)) {
                for (String s : declaredMethod.getAnnotation(RegistryMenu.class).value()) {
                    set(menus, clazz, declaredMethod, s);
                }
            }
        }
    }

    private void fieldSettings(Class<?> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (declaredField.getAnnotations().length == 0) continue;
            if (declaredField.isAnnotationPresent(RegistryObject.class)) {
                objectRegistry(declaredField, clazz, declaredField.getAnnotation(RegistryObject.class).value()[0]);
                continue;
            }
            if (declaredField.isAnnotationPresent(RegistryItem.class))
                set(items, clazz, declaredField, declaredField.getAnnotation(RegistryItem.class).value()[0]);
            else if (declaredField.isAnnotationPresent(RegistryBlock.class))
                set(blocks, clazz, declaredField, declaredField.getAnnotation(RegistryBlock.class).value()[0]);
            else if (declaredField.isAnnotationPresent(RegistryBlockEntity.class))
                set(blockEntitys, clazz, declaredField, declaredField.getAnnotation(RegistryBlockEntity.class).value()[0]);
            else if (declaredField.isAnnotationPresent(RegistryConfiguredFeature.class))
                set(configuredFeatures, clazz, declaredField, declaredField.getAnnotation(RegistryConfiguredFeature.class).value()[0]);
            else if (declaredField.isAnnotationPresent(RegistryDimensionType.class))
                set(dimensionTypes, clazz, declaredField, declaredField.getAnnotation(RegistryDimensionType.class).value()[0]);
            else if (declaredField.isAnnotationPresent(RegistryRecipeType.class))
                set(recipeTypes, clazz, declaredField, declaredField.getAnnotation(RegistryRecipeType.class).value()[0]);
            else if (declaredField.isAnnotationPresent(RegistryRecipeSerializer.class))
                set(recipeSerializers, clazz, declaredField, declaredField.getAnnotation(RegistryRecipeSerializer.class).value()[0]);
            else if (declaredField.isAnnotationPresent(RegistryMenu.class)) {
                set(menus, clazz, declaredField, declaredField.getAnnotation(RegistryMenu.class).value()[0]);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> void set(Map<String, T> map, Class<?> clazz, AccessibleObject accessibleObject, String s) {
        try {
            Object o = methodFieldGet(clazz, accessibleObject);
            if (o != null) {
                map.put(s, (T) o);
            }
        } catch (IllegalAccessException | InvocationTargetException ignored) {}
    }

    private void objectRegistry(AccessibleObject accessibleObject, Class<?> clazz, String name) {
        Object o = null;
        try {
            o = methodFieldGet(clazz, accessibleObject);
        } catch (IllegalAccessException | InvocationTargetException ignored) {}
        if (o instanceof Item item) {
            items.put(name, item);
        } else if (o instanceof Block block) {
            blocks.put(name, block);
        } else if (o instanceof BlockEntityType<?> entityType) {
            blockEntitys.put(name, entityType);
        } else if (o instanceof ConfiguredFeature<?,?> configuredFeature) {
            configuredFeatures.put(name, configuredFeature);
        } else if (o instanceof DimensionType type) {
            dimensionTypes.put(name, type);
        } else if (o instanceof RecipeType<?> type) {
            recipeTypes.put(name, type);
        } else if (o instanceof RecipeSerializer<?> serializer) {
            recipeSerializers.put(name, serializer);
        } else if (o instanceof MenuType<?> menu) {
            menus.put(name, menu);
        }
    }

    @SuppressWarnings("unused")
    public void register(@NotNull RegisterEvent event) {
        register(event, Registry.BLOCK_REGISTRY, blocks);
        register(event, Registry.BLOCK_ENTITY_TYPE_REGISTRY, blockEntitys);
        register(event, Registry.ITEM_REGISTRY, items);
        register(event, Registry.CONFIGURED_FEATURE_REGISTRY, configuredFeatures);
        register(event, Registry.DIMENSION_TYPE_REGISTRY, dimensionTypes);
        register(event, Registry.RECIPE_TYPE_REGISTRY, recipeTypes);
        register(event, Registry.RECIPE_SERIALIZER_REGISTRY, recipeSerializers);
        blockEntitys = clearAndSetNull(blockEntitys);
        blocks = clearAndSetNull(blocks);
        items = clearAndSetNull(items);
        configuredFeatures = clearAndSetNull(configuredFeatures);
        dimensionTypes = clearAndSetNull(dimensionTypes);
        recipeTypes = clearAndSetNull(recipeTypes);
        recipeSerializers = clearAndSetNull(recipeSerializers);
        System.gc();// gc map
    }

    private <T> Map<String, T> clearAndSetNull(Map<String, T> map) {
        map.clear();
        return null;
    }

    private <T> void register(@NotNull RegisterEvent event, ResourceKey<Registry<T>> registry, Map<String, T> map) {
        for (Map.Entry<String, T> entry : map.entrySet()) {
            event.register(registry, new ResourceLocation(modid, entry.getKey()), entry::getValue);
        }
    }


}
