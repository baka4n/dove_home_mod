package io.github.dovehome.dovehomemod.forge.core.registry;


import io.github.dovehome.bakalib.forge.annotations.RegistryRecipeSerializer;
import io.github.dovehome.bakalib.forge.annotations.RegistryRecipeType;
import io.github.dovehome.dovehomemod.forge.core.recipe.SandstoneRecipe;
import io.github.dovehome.dovehomemod.forge.core.recipe.SandstoneSmelting;
import io.github.dovehome.dovehomemod.forge.core.recipe.sandstone.Shaped;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;

public class DoveRecipes {
    @RegistryRecipeType("sandstone_recipe")
    public static final RecipeType<SandstoneRecipe> sandstoneRecipeType;
    @RegistryRecipeSerializer("shaped_sandstone_recipe")
    public static final RecipeSerializer<Shaped> shapedSandstoneSerializer;

    @RegistryRecipeType("sandstone_smelting")
    public static final RecipeType<SandstoneSmelting> sandstoneSmelting;
    @RegistryRecipeType("sandstone_smelting")
    public static final SimpleCookingSerializer<SandstoneSmelting> sandstone_smelting;

    static {
        sandstoneRecipeType = SandstoneRecipe.Type.instance;
        shapedSandstoneSerializer = new Shaped.Serializer();
        sandstoneSmelting = new RecipeType<>() {};
        sandstone_smelting = new SimpleCookingSerializer<>(SandstoneSmelting::new, 300);
//        shapedSandstoneRecipeSerializer = new ShapedSandstoneSerializer();
    }
}
