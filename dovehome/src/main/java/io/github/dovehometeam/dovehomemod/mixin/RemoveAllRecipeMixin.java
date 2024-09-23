package io.github.dovehometeam.dovehomemod.mixin;

import com.google.gson.JsonElement;
import io.github.dovehometeam.dovehomemod.infrastructure.Config;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mixin(RecipeManager.class)
public class RemoveAllRecipeMixin {
    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("HEAD"))
    private void apply(Map<ResourceLocation, JsonElement> pObject,
                       ResourceManager pResourceManager,
                       ProfilerFiller pProfiler,
                       CallbackInfo ci) {
        Set<ResourceLocation> resourceLocations = new HashSet<>(pObject.keySet());
        resourceLocations
                .removeIf(key -> Config.includeModidRemoveRecipe.contains(key.getNamespace()));
        for (ResourceLocation location : resourceLocations) pObject.remove(location);
    }
}
