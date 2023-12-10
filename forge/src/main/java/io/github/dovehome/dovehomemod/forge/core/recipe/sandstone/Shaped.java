package io.github.dovehome.dovehomemod.forge.core.recipe.sandstone;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.dovehome.dovehomemod.forge.core.recipe.SandstoneRecipe;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public record Shaped(ResourceLocation id, ItemStack out, NonNullList<Ingredient> recipeItems) implements SandstoneRecipe {
    @Override
    public boolean matches(@NotNull SimpleContainer container, Level level) {
        if (level.isClientSide()) return true;
        for (int i = 0; i < recipeItems.size(); i++) {
            if (!recipeItems.get(i).test(container.getItem(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer container) {
        return out;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return out.copy();
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return DoveRecipes.shapedSandstoneSerializer;
    }

    public static class Serializer implements RecipeSerializer<Shaped> {

        @Override
        public @NotNull Shaped fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
            ResourceLocation type = new ResourceLocation(GsonHelper.getAsString(json, "type", ""));
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            JsonArray keys = GsonHelper.getAsJsonArray(json, "key");
            Map<String, Ingredient> keyMap = new HashMap<>();
            ketSet(keys, keyMap);
            JsonArray pattern = GsonHelper.getAsJsonArray(json, "pattern");
            int size = pattern.size();
            int length = pattern.get(0).getAsString().length();
            NonNullList<Ingredient> list = NonNullList.withSize(size * length, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < length; j++) {
                    int k = i * length + j;
                    list.add(k, keyMap.get(String.valueOf(pattern.get(i).getAsString().charAt(j))));
                }
            }
            return new Shaped(type, output, list);
        }

        private static void ketSet(JsonArray keys, Map<String, Ingredient> keyMap) {
            for (JsonElement key : keys) {
                JsonObject k = key.getAsJsonObject();
                String value = GsonHelper.getAsString(k, "value", "");
                String type = GsonHelper.getAsString(k, "type", "item");
                String id = GsonHelper.getAsString(k, "id", "");
                JsonObject nbt = GsonHelper.getAsJsonObject(k, "nbt", new JsonObject());
                if (value.isEmpty()) {
                    continue;
                }
                if (type.equals("tag")) {
                    keyMap.put(value, Ingredient.of(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(id))));
                    continue;
                }
                ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(id)));
                if (!nbt.isJsonNull()) {
                    try {
                        CompoundTag compoundTag = TagParser.parseTag(nbt.toString());
                        stack.setTag(compoundTag);
                    } catch (CommandSyntaxException ignored) {
                    }
                }
                keyMap.put(value, Ingredient.of(stack));
            }
        }

        @Override
        public @Nullable Shaped fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> recipeItems =  NonNullList.withSize(buffer.readInt(),  Ingredient.EMPTY);
            recipeItems.replaceAll(ignored -> Ingredient.fromNetwork(buffer));
            ItemStack output = buffer.readItem();
            return new Shaped(recipeId, output, recipeItems);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, Shaped recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(), false);
        }
    }

}
