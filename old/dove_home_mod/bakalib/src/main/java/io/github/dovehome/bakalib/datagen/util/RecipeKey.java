package io.github.dovehome.bakalib.datagen.util;

import net.minecraft.core.Registry;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static io.github.dovehome.bakalib.datagen.RecipeUtil.has;

public class RecipeKey implements Consumer<ShapedRecipeBuilder> {
    @Nullable
    protected final TagKey<Item> itemTagKey;
    @Nullable
    protected final ItemLike like;
    @Nullable
    protected final Ingredient ingredient;

    public boolean isTag, isLike;
    public RecipeKey(@NotNull TagKey<Item> item) {
        itemTagKey = item;
        like = null;
        ingredient = null;
    }

    public RecipeKey(@NotNull ItemLike item) {
        itemTagKey = null;
        like = item;
        ingredient = null;
    }

    public RecipeKey(@NotNull Ingredient item) {
        itemTagKey = null;
        like = null;
        ingredient = item;
    }

    public RecipeKey setLike(boolean like) {
        isLike = like;
        return this;
    }

    public RecipeKey setTag(boolean tag) {
        isTag = tag;
        return this;
    }

    @SuppressWarnings("deprecation")
    public void accept(ShapedRecipeBuilder builder, Key key) {
        char name = key.name().charAt(0);
        if (itemTagKey != null) {
            builder.define(name, itemTagKey);
            if (isTag) {
                builder.unlockedBy("has_" + itemTagKey.location().getPath(), has(itemTagKey));
            }
        } else if (ingredient != null) {
            builder.define(name, ingredient);
        } else if (like != null) {
            builder.define(name, like);
            if (isLike) {
                builder.unlockedBy("has_" + Registry.ITEM.getKey(like.asItem()).getPath(), has(like));
            }
        }
    }

    @Override
    public void accept(ShapedRecipeBuilder builder) {

    }
}
