package io.github.dovehome.dovehomemod.base;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public abstract class BaseBlockTrigger<T extends BaseBlockTrigger.TriggerInstance> extends SimpleCriterionTrigger<T> {


    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        private final BlockPredicate block;

        public TriggerInstance(ResourceLocation criterion, EntityPredicate.Composite player, BlockPredicate block) {
            super(criterion, player);
            this.block = block;
        }

        @Override
        public @NotNull JsonObject serializeToJson(@NotNull SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.add("block", block.serializeToJson());
            return jsonObject;
        }

        public boolean matches(ServerPlayer player, ServerLevel level, BlockPos pos) {
            return block.matches(level, pos);
        }
    }
}
