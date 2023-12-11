package io.github.dovehome.dovehomemod.advancements.critereon;

import com.google.gson.JsonObject;
import io.github.dovehome.dovehomemod.forge.DovehomemodForge;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CraftingRecipeTrigger extends SimpleCriterionTrigger<CraftingRecipeTrigger.TriggerInstance> {

    static final ResourceLocation ID = DovehomemodForge.id("crafting_recipe");

    @Override
    protected @NotNull TriggerInstance createInstance(@NotNull JsonObject json, EntityPredicate.@NotNull Composite player, @NotNull DeserializationContext context) {
        return new TriggerInstance(player, ItemPredicate.fromJson(json.get("item")), json.has("nbt") ? NbtPredicate.fromJson(json.get("nbt")) : null);
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
        private final NbtPredicate nbt;
        public TriggerInstance(EntityPredicate.Composite player, ItemPredicate item, @Nullable NbtPredicate nbt) {
            super(CraftingRecipeTrigger.ID, player);
            this.item = item;
            this.nbt = nbt;
        }

        public TriggerInstance(ItemPredicate item) {
            super(CraftingRecipeTrigger.ID, EntityPredicate.Composite.ANY);
            this.item = item;

            nbt = null;
        }

        public TriggerInstance(EntityPredicate.Composite player, TagKey<Item> tagKey) {
            super(CraftingRecipeTrigger.ID, player);
            this.item = new ItemPredicate(tagKey, null, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, EnchantmentPredicate.NONE, EnchantmentPredicate.NONE, null, NbtPredicate.ANY);
            nbt = null;
        }

        public TriggerInstance(TagKey<Item> tagKey) {
            super(CraftingRecipeTrigger.ID, EntityPredicate.Composite.ANY);
            this.item = new ItemPredicate(tagKey, null, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, EnchantmentPredicate.NONE, EnchantmentPredicate.NONE, null, NbtPredicate.ANY);
            nbt = null;
        }

        public TriggerInstance(EntityPredicate.Composite player, ItemLike... like) {
            super(CraftingRecipeTrigger.ID, player);
            this.item = new ItemPredicate(null, Arrays.stream(like).map(ItemLike::asItem).collect(Collectors.toSet()), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, EnchantmentPredicate.NONE, EnchantmentPredicate.NONE, null, NbtPredicate.ANY);
            nbt = null;
        }

        public TriggerInstance(ItemLike... like) {
            super(CraftingRecipeTrigger.ID, EntityPredicate.Composite.ANY);
            this.item = new ItemPredicate(null, Arrays.stream(like).map(ItemLike::asItem).collect(Collectors.toSet()), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, EnchantmentPredicate.NONE, EnchantmentPredicate.NONE, null, NbtPredicate.ANY);
            nbt = null;
        }

        @Override
        public @NotNull JsonObject serializeToJson(@NotNull SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.add("item", this.item.serializeToJson());
            if (nbt != null) {
                jsonObject.add("nbt", this.nbt.serializeToJson());
            }
            return jsonObject;
        }

        public boolean matches(ItemStack item) {
            if (nbt != null) {
                return nbt.matches(item) && this.item.matches(item);
            }
            return this.item.matches(item);
        }
    }
}
