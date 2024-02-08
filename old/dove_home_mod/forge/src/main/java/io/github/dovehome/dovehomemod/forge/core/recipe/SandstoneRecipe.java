package io.github.dovehome.dovehomemod.forge.core.recipe;

import io.github.dovehome.dovehomemod.forge.core.registry.DoveRecipes;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

public interface SandstoneRecipe extends Recipe<SimpleContainer> {

    @Override
    default @NotNull RecipeType<?> getType() {
        return DoveRecipes.sandstoneRecipeType;
    }

    class Type implements RecipeType<SandstoneRecipe> {
        private Type() {}
        public static final Type instance = new Type();


    }
}
