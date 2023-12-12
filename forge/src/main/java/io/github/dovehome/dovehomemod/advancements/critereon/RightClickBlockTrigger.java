package io.github.dovehome.dovehomemod.advancements.critereon;

import com.google.gson.JsonObject;
import io.github.dovehome.dovehomemod.base.BaseBlockTrigger;
import io.github.dovehome.dovehomemod.forge.DovehomemodForge;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RightClickBlockTrigger extends BaseBlockTrigger<RightClickBlockTrigger.TriggerInstance> {

    static final ResourceLocation ID = DovehomemodForge.id("right_click_block");

    @Override
    protected @NotNull TriggerInstance createInstance(JsonObject json,
                                                      EntityPredicate.@NotNull Composite player,
                                                      @NotNull DeserializationContext context) {
        return new TriggerInstance(player, BlockPredicate.fromJson(json.get("block")), json.has("isShift") ? GsonHelper.getAsBoolean(json, "isShift") : null, ItemPredicate.fromJson(json.get("mainHand")), ItemPredicate.fromJson(json.get("offHand")));
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer player, ServerLevel level, BlockPos pos) {
        this.trigger(player, arg -> arg.matches(player, level, pos));
    }

    public static class TriggerInstance extends BaseBlockTrigger.TriggerInstance {
        @Nullable
        private final Boolean isShift;

        private final ItemPredicate mainHand;

        private final ItemPredicate offHand;
        public TriggerInstance(EntityPredicate.Composite player, BlockPredicate block, @Nullable Boolean isShift,  ItemPredicate mainHand, ItemPredicate offHand) {
            super(ID, player, block);
            this.isShift = isShift;
            this.mainHand = mainHand;
            this.offHand = offHand;
        }

        @Override
        public @NotNull JsonObject serializeToJson(@NotNull SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);

            if (isShift != null) {
                jsonObject.addProperty("isShift", isShift);
            }
            jsonObject.add("mainHand", mainHand.serializeToJson());
            jsonObject.add("offHand", offHand.serializeToJson());

            return jsonObject;
        }

        public boolean matches(ServerPlayer player, ServerLevel level, BlockPos pos) {
            if (isShift != null) {
                if (isShift != player.isShiftKeyDown()) {
                    return false;
                }
            }
            return super.matches(player, level, pos) && offHand.matches(player.getOffhandItem()) && mainHand.matches(player.getMainHandItem());
        }
    }
}
