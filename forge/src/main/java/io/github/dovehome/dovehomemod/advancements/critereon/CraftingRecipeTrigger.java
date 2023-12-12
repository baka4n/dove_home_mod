package io.github.dovehome.dovehomemod.advancements.critereon;

import com.google.gson.JsonObject;
import io.github.dovehome.dovehomemod.forge.DovehomemodForge;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CraftingRecipeTrigger extends SimpleCriterionTrigger<CraftingRecipeTrigger.TriggerInstance> {

    static final ResourceLocation ID = DovehomemodForge.id("crafting_recipe");

    @Override
    protected @NotNull TriggerInstance createInstance(@NotNull JsonObject json, EntityPredicate.@NotNull Composite player, @NotNull DeserializationContext context) {
        return new TriggerInstance(player, ItemPredicate.fromJson(json.get("item")));
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }


    public void trigger(ServerPlayer player, ItemStack item) {
        this.trigger(player, arg -> arg.matches(item));

    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        private final ItemPredicate item;
        public TriggerInstance(EntityPredicate.Composite player, ItemPredicate item) {
            super(CraftingRecipeTrigger.ID, player);
            this.item = item;
        }

        @NotNull
        private static ItemPredicate tagSet(TagKey<Item> tagKey) {
            return new ItemPredicate(tagKey, null, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, EnchantmentPredicate.NONE, EnchantmentPredicate.NONE, null, NbtPredicate.ANY);
        }

        @NotNull
        private static ItemPredicate itemSet(ItemLike[] like) {
            return new ItemPredicate(null, Arrays.stream(like).map(ItemLike::asItem).collect(Collectors.toSet()), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, EnchantmentPredicate.NONE, EnchantmentPredicate.NONE, null, NbtPredicate.ANY);
        }

        public static TriggerInstance hasTagKey(TagKey<Item> tagKey) {
            return new TriggerInstance(EntityPredicate.Composite.ANY, tagSet(tagKey));
        }

        public static TriggerInstance hasItemLike(ItemLike... like) {
            return new TriggerInstance(EntityPredicate.Composite.ANY, itemSet(like));
        }

        public static TriggerInstance hasItemStack(ItemStack stack) {
            return new TriggerInstance(EntityPredicate.Composite.ANY, hasStack(stack));
        }

        @NotNull
        private static ItemPredicate hasStack(ItemStack stack) {
            return new ItemPredicate(null, new HashSet<>(List.of(stack.getItem())), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, EnchantmentPredicate.NONE, EnchantmentPredicate.NONE, null, new NbtPredicate(stack.getTag()));
        }

        @Override
        public @NotNull JsonObject serializeToJson(@NotNull SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.add("item", this.item.serializeToJson());
            return jsonObject;
        }

        public boolean matches(ItemStack item) {
            return this.item.matches(item);
        }
    }
}
