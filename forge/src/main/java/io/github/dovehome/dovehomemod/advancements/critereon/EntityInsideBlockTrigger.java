package io.github.dovehome.dovehomemod.advancements.critereon;

import com.google.gson.JsonObject;
import io.github.dovehome.dovehomemod.base.BaseBlockTrigger;
import io.github.dovehome.dovehomemod.forge.DovehomemodForge;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class EntityInsideBlockTrigger extends BaseBlockTrigger<EntityInsideBlockTrigger.TriggerInstance> {

    static final ResourceLocation ID = DovehomemodForge.id("entity_inside_block");
    @Override
    protected @NotNull TriggerInstance createInstance(JsonObject json,
                                                      EntityPredicate.@NotNull Composite player,
                                                      @NotNull DeserializationContext context) {
        return new TriggerInstance(player, BlockPredicate.fromJson(json.get("block")));
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer player, ServerLevel level, BlockPos pos) {
        this.trigger(player, arg -> arg.matches(player, level, pos));
    }

    public static class TriggerInstance extends BaseBlockTrigger.TriggerInstance {

        public TriggerInstance(EntityPredicate.Composite player, BlockPredicate block) {
            super(ID, player, block);
        }

        @Override
        public @NotNull JsonObject serializeToJson(@NotNull SerializationContext context) {
            return super.serializeToJson(context);
        }

        @Override
        public boolean matches(ServerPlayer player, ServerLevel level, BlockPos pos) {
            return super.matches(player, level, pos);
        }
    }
}
