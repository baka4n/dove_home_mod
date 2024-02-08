package io.github.dovehome.dovehomemod.forge.core.recipe;

import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class SandstoneSmelting extends AbstractCookingRecipe {

    public SandstoneSmelting(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
        super(DoveRecipes.sandstoneSmelting, id, group, ingredient, result, experience, cookingTime);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(DoveBlocks.sandstoneFurnaceItem);
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return DoveRecipes.sandstone_smelting;
    }
}
