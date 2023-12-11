package io.github.dovehome.dovehomemod.forge.events;

import io.github.dovehome.dovehomemod.advancements.critereon.CraftingRecipeTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class DoveCriteriaTriggers {
    public static final CraftingRecipeTrigger crafting_recipe = CriteriaTriggers.register(new CraftingRecipeTrigger());
    public static void init() {

    }
}
