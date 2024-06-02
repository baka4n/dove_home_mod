package io.github.dovehometeam.dovehomemod.mixin;

import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.*;

import java.util.function.Supplier;

@Mixin(Tiers.class)
@Debug(export = true)
public class ToolLevelMixin {
    //等级修改
    //flint 1 燧石
    // sand 0 沙
    // wood 2 木头
    // stone 3 石头
    // iron 4 铁
    // diamond 5 钻石
    // obsidian 6 黑曜石
    // Magical Gems 7 魔能宝石
    // Magic Steel 8 魔力钢
    ToolLevelMixin(String name, int ordinal, int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        throw new AssertionError();
    }

    @Final
    @Shadow
    @SuppressWarnings({"target", "mapping"})
    @Mutable
    private static  Tiers[] $VALUES;



}
