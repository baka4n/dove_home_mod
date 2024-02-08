package io.github.dovehome.dovehomemod.advancements.critereon;

import com.google.gson.JsonObject;
import io.github.dovehome.dovehomemod.forge.DovehomemodForge;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.GameType;
import org.jetbrains.annotations.NotNull;

public class GameModeChangeTrigger extends SimpleCriterionTrigger<GameModeChangeTrigger.TriggerInstance> {
    static final ResourceLocation ID = DovehomemodForge.id("gamemode_change");

    @Override
    protected @NotNull TriggerInstance createInstance(@NotNull JsonObject json,
                                                      EntityPredicate.@NotNull Composite player,
                                                      @NotNull DeserializationContext context) {
        return new TriggerInstance(player, GameType.valueOf(GsonHelper.getAsString(json, "type")));
    }

    public void trigger(ServerPlayer player, GameType type) {
        this.trigger(player, arg -> arg.matches(type));
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final GameType type;
        public TriggerInstance(EntityPredicate.Composite player, GameType type) {
            super(ID, player);
            this.type = type;
        }

        public static TriggerInstance hasMode(GameType type) {
            return new TriggerInstance(EntityPredicate.Composite.ANY, type);
        }

        @Override
        public @NotNull JsonObject serializeToJson(@NotNull SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.addProperty("type", type.name());
            return jsonObject;
        }

        public boolean matches(GameType newType) {
            return newType.equals(type);
        }
    }
}
