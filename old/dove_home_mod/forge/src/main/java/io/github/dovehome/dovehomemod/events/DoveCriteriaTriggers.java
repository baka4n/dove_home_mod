package io.github.dovehome.dovehomemod.events;

import io.github.dovehome.dovehomemod.advancements.critereon.CraftingRecipeTrigger;
import io.github.dovehome.dovehomemod.advancements.critereon.EntityInsideBlockTrigger;
import io.github.dovehome.dovehomemod.advancements.critereon.GameModeChangeTrigger;
import io.github.dovehome.dovehomemod.advancements.critereon.RightClickBlockTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class DoveCriteriaTriggers {
    public static final CraftingRecipeTrigger crafting_recipe = CriteriaTriggers.register(new CraftingRecipeTrigger());
    public static final GameModeChangeTrigger gamemode_change = CriteriaTriggers.register(new GameModeChangeTrigger());
    public static final RightClickBlockTrigger right_click_block = CriteriaTriggers.register(new RightClickBlockTrigger());
    public static final EntityInsideBlockTrigger entity_inside_block = CriteriaTriggers.register(new EntityInsideBlockTrigger());
    public static void init() {
    }
}
