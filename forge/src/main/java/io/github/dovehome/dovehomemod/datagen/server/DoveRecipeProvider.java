package io.github.dovehome.dovehomemod.datagen.server;

import io.github.dovehome.bakalib.datagen.RecipeUtil;
import io.github.dovehome.bakalib.datagen.util.Key;
import io.github.dovehome.bakalib.datagen.util.RecipeKey;
import io.github.dovehome.dovehomemod.forge.DovehomemodForge;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static io.github.dovehome.bakalib.datagen.util.Key.*;

public class DoveRecipeProvider extends RecipeProvider {
    public static final RecipeKey SANDSTONE_TAG = new RecipeKey(Tags.Items.SANDSTONE).setTag(true);
    public static final RecipeKey STICK_ITEM = new RecipeKey(Items.STICK).setLike(true);
    public static final RecipeKey GREEN_DYE = new RecipeKey(Items.GREEN_DYE).setLike(true);

    public static final RecipeKey SAND = new RecipeKey(Items.SAND).setLike(true);

    public DoveRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        smelting(Tags.Items.COBBLESTONE, Items.STONE, 0.0F, 300, DovehomemodForge.id("smelting/sandstone"), consumer);
        smelting(Items.CACTUS, Items.GREEN_DYE, 0.01F, 300, DovehomemodForge.id("smelting/sandstone"), consumer);


        RecipeUtil.shapedRecipe2Key(consumer,
                Items.COARSE_DIRT,
                1,
                GREEN_DYE,
                SANDSTONE_TAG,
                DovehomemodForge.id("base"),
                new Key[] {A,B},
                new Key[]{B, A});
        RecipeUtil.shapedRecipe1Key(consumer,
                Items.SANDSTONE,
                1,
                SAND,
                DovehomemodForge.id("base"),
                new Key[] { A, A },
                new Key[] { A, A });
        sandstoneToolsBuildAll(
                new ItemLike[] {
                        DoveItems.sandStonePickaxe,
                        DoveItems.sandStoneAxe,
                        DoveItems.sandStoneHoe,
                        DoveItems.sandStoneShovel,
                        DoveItems.sandstoneKnife,
                        DoveItems.sandStoneSword
                }, consumer,
                new Key[][][] {
                        { {A, A}, {B, A}},
                        {{A, B}, {A, A}},
                        {{A, A}, { NULL, B}},
                        {{A}, {B}},
                        {{B}, {A}},
                        {{ NULL, A }, {B, NULL}}
                }, DovehomemodForge.id("tools/sandstone"));
//        RecipeUtil.smeltingRecipe(consumer, new RecipeKey(Tags.Items.COBBLESTONE).setTag(true), Items.STONE, 0.0F, 300, DovehomemodForge.id("smelting/sandstone"));
//        RecipeUtil.smeltingRecipe(consumer, new RecipeKey(Items.CACTUS).setLike(true), Items.GREEN_DYE, 0.01F, 300, DovehomemodForge.id("smelting/sandstone"));
    }

    private void smelting(Item input, ItemLike result, float exp, int cookTime, ResourceLocation addons, @NotNull Consumer<FinishedRecipe> consumer) {
        ResourceLocation defaultRecipeId = RecipeBuilder.getDefaultRecipeId(result);
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(input), result, exp, cookTime)
                .unlockedBy("has_" + RecipeBuilder.getDefaultRecipeId(input).getPath(), has(input))
                .save(consumer, new ResourceLocation(addons.getNamespace(), addons.getPath().isEmpty() ? defaultRecipeId.getPath() : addons.getPath() + "/" + defaultRecipeId.getPath()));
    }

    public static void smelting(TagKey<Item> input, ItemLike result, float exp, int cookTime, ResourceLocation addons, @NotNull Consumer<FinishedRecipe> consumer) {
        ResourceLocation defaultRecipeId = RecipeBuilder.getDefaultRecipeId(result);
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(input), result, exp, cookTime)
                .unlockedBy("has_" + input.location().getPath(), has(input))
                .save(consumer, new ResourceLocation(addons.getNamespace(), addons.getPath().isEmpty() ? defaultRecipeId.getPath() : addons.getPath() + "/" + defaultRecipeId.getPath()));
    }

    public static void sandstoneToolsBuildAll(ItemLike[] likes, Consumer<FinishedRecipe> consumer, Key[][][] patterns_s, ResourceLocation addons) {
        int len = likes.length;
        if (likes.length > patterns_s.length) len = patterns_s.length;
        for (int i = 0; i < len; i++) {
            ItemLike like = likes[i];
            Key[][] patterns = patterns_s[i];
            sandstoneToolsBuild(like, consumer, addons, patterns);
        }
    }
    //A and B
    public static void sandstoneToolsBuild(ItemLike result, Consumer<FinishedRecipe> consumer, ResourceLocation addons, Key[]... patterns) {
        RecipeUtil.shapedRecipe2Key(consumer, result, 1, SANDSTONE_TAG, STICK_ITEM, addons, patterns);
    }
}
