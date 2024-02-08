package io.github.dovehome.bakalib.datagen;

import io.github.dovehome.bakalib.datagen.util.Key;
import io.github.dovehome.bakalib.datagen.util.RecipeKey;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static io.github.dovehome.bakalib.datagen.util.Key.*;

public class RecipeUtil {
    /**
     * @apiNote {@code Key.A }
     */
    public static void shapedRecipe1Key(Consumer<FinishedRecipe> consumer,
                                        ItemLike like,
                                        int count,
                                        RecipeKey key,
                                        ResourceLocation addons,
                                        Key[]... patterns) {
        keyShapedRecipe(consumer, like, count, new RecipeKey[]{key}, addons, patterns);
    }
    /**
     * @apiNote {@code Key.A Key.B }
     */
    public static void shapedRecipe2Key(Consumer<FinishedRecipe> consumer,
                                        ItemLike like,
                                        int count,
                                        RecipeKey key1,
                                        RecipeKey key2,
                                        ResourceLocation addons,
                                        Key[]... patterns) {
        keyShapedRecipe(consumer, like, count, new RecipeKey[]{key1, key2}, addons, patterns);
    }
    /**
     * @apiNote {@code Key.A Key.B Key.C }
     */
    public static void shapedRecipe3Key(Consumer<FinishedRecipe> consumer,
                                        ItemLike like,
                                        int count,
                                        RecipeKey key1,
                                        RecipeKey key2,
                                        RecipeKey key3,
                                        ResourceLocation addons,
                                        Key[]... patterns) {
        keyShapedRecipe(consumer, like, count, new RecipeKey[]{key1, key2, key3}, addons, patterns);
    }
    /**
     * @apiNote {@code Key.A Key.B Key.C Key.D }
     */
    public static void shapedRecipe4Key(Consumer<FinishedRecipe> consumer,
                                        ItemLike like,
                                        int count,
                                        RecipeKey key1,
                                        RecipeKey key2,
                                        RecipeKey key3,
                                        RecipeKey key4,
                                        ResourceLocation addons,
                                        Key[]... patterns) {
        keyShapedRecipe(consumer, like, count, new RecipeKey[]{key1, key2, key3, key4}, addons, patterns);
    }
    /**
     * @apiNote {@code Key.A Key.B Key.C Key.D Key.E }
     */
    public static void shapedRecipe5Key(Consumer<FinishedRecipe> consumer,
                                        ItemLike like,
                                        int count,
                                        RecipeKey key1,
                                        RecipeKey key2,
                                        RecipeKey key3,
                                        RecipeKey key4,
                                        RecipeKey key5,
                                        ResourceLocation addons,
                                        Key[]... patterns) {
        keyShapedRecipe(consumer, like, count, new RecipeKey[]{key1, key2, key3, key4, key5}, addons, patterns);
    }
    /**
     * @apiNote {@code Key.A Key.B Key.C Key.D Key.E Key.F }
     */
    public static void shapedRecipe6Key(Consumer<FinishedRecipe> consumer,
                                        ItemLike like,
                                        int count,
                                        RecipeKey key1,
                                        RecipeKey key2,
                                        RecipeKey key3,
                                        RecipeKey key4,
                                        RecipeKey key5,
                                        RecipeKey key6,
                                        ResourceLocation addons,
                                        Key[]... patterns) {
        keyShapedRecipe(consumer, like, count, new RecipeKey[]{key1, key2, key3, key4, key5, key6}, addons, patterns);
    }
    /**
     * @apiNote {@code Key.A Key.B Key.C Key.D Key.E Key.F Key.G }
     */
    public static void shapedRecipe7Key(Consumer<FinishedRecipe> consumer,
                                        ItemLike like,
                                        int count,
                                        RecipeKey key1,
                                        RecipeKey key2,
                                        RecipeKey key3,
                                        RecipeKey key4,
                                        RecipeKey key5,
                                        RecipeKey key6,
                                        RecipeKey key7,
                                        ResourceLocation addons,
                                        Key[]... patterns) {
        keyShapedRecipe(consumer, like, count, new RecipeKey[]{key1, key2, key3, key4, key5, key6, key7}, addons, patterns);
    }
    /**
     * @apiNote {@code Key.A Key.B Key.C Key.D Key.E Key.F Key.G Key.H }
     */
    public static void shapedRecipe8Key(Consumer<FinishedRecipe> consumer,
                                        ItemLike like,
                                        int count,
                                        RecipeKey key1,
                                        RecipeKey key2,
                                        RecipeKey key3,
                                        RecipeKey key4,
                                        RecipeKey key5,
                                        RecipeKey key6,
                                        RecipeKey key7,
                                        RecipeKey key8,
                                        ResourceLocation addons,
                                        Key[]... patterns) {
        keyShapedRecipe(consumer, like, count, new RecipeKey[]{key1, key2, key3, key4, key5, key6, key7, key8}, addons, patterns);
    }
    /**
     * @apiNote {@code Key.A Key.B Key.C Key.D Key.E Key.F Key.G Key.H Key.I }
     */
    public static void shapedRecipe9Key(Consumer<FinishedRecipe> consumer,
                                        ItemLike like,
                                        int count,
                                        RecipeKey key1,
                                        RecipeKey key2,
                                        RecipeKey key3,
                                        RecipeKey key4,
                                        RecipeKey key5,
                                        RecipeKey key6,
                                        RecipeKey key7,
                                        RecipeKey key8,
                                        RecipeKey key9,
                                        ResourceLocation addons,
                                        Key[]... patterns) {
        keyShapedRecipe(consumer, like, count, new RecipeKey[]{key1, key2, key3, key4, key5, key6, key7, key8, key9}, addons, patterns);
    }

    public static void keyShapedRecipe(Consumer<FinishedRecipe> consumer,
                                       ItemLike like,
                                       int count,
                                       RecipeKey[] recipeKeys, ResourceLocation addons, Key[]... patterns) {
        ShapedRecipeBuilder shaped = ShapedRecipeBuilder.shaped(like, count);
        keysDefineSet(recipeKeys, shaped);
        for (Key[] pattern : patterns) {
            StringBuilder sb = new StringBuilder();
            for (Key key : pattern) {
                if (key.equals(NULL)) {
                    sb.append(" ");
                } else {
                    sb.append(key);
                }
            }
            shaped.pattern(sb.toString());
        }

        shaped.save(consumer, new ResourceLocation(addons.getNamespace(),  addons.getPath().isEmpty() ? addons.getPath() : addons.getPath() + "/" + RecipeBuilder.getDefaultRecipeId(shaped.getResult()).getPath()));
    }

    private static void keysDefineSet(RecipeKey[] recipeKeys, ShapedRecipeBuilder shaped) {
        shapedDefine(recipeKeys, 0, shaped, A);
        shapedDefine(recipeKeys, 1, shaped, B);
        shapedDefine(recipeKeys, 2, shaped, C);
        shapedDefine(recipeKeys, 3, shaped, D);
        shapedDefine(recipeKeys, 4, shaped, E);
        shapedDefine(recipeKeys, 5, shaped, F);
        shapedDefine(recipeKeys, 6, shaped, G);
        shapedDefine(recipeKeys, 7, shaped, H);
        shapedDefine(recipeKeys, 8, shaped, I);
    }

    public static void shapedDefine(RecipeKey[] recipeKeys, int i, ShapedRecipeBuilder shaped, Key key) {
        if (i <= recipeKeys.length - 1) {
            recipeKeys[i].accept(shaped, key);
        }
    }

    public static InventoryChangeTrigger.TriggerInstance inventoryTrigger(ItemPredicate... predicates) {
        return new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, predicates);
    }

    public static InventoryChangeTrigger.TriggerInstance has(MinMaxBounds.Ints count, ItemLike item) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(item).withCount(count).build());
    }

    public static InventoryChangeTrigger.TriggerInstance has(ItemLike itemLike) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(new ItemLike[]{itemLike}).build());
    }

    public static InventoryChangeTrigger.TriggerInstance has(TagKey<Item> tag) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(tag).build());
    }
}
