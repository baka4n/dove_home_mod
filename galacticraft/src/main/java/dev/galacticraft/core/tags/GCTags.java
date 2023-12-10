package dev.galacticraft.core.tags;

import dev.galacticraft.forge.Constant;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class GCTags {
    public static final TagKey<Fluid> OIL = TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation(Constant.COMMON_NAMESPACE, "oil"));
    public static final TagKey<Fluid> FUEL = TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation(Constant.COMMON_NAMESPACE, "fuel"));
    public static final TagKey<Fluid> LIQUID_OXYGEN = TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation(Constant.COMMON_NAMESPACE, "oxygen"));
    public static final TagKey<Fluid> OXYGEN = TagKey.create(Registry.FLUID_REGISTRY,Constant.id("oxygen"));
    public static final TagKey<Fluid> INFINIBURN_MOON = TagKey.create(Registry.FLUID_REGISTRY, Constant.id("infiniburn_moon"));
    public static final TagKey<Fluid> BASE_STONE_MOON = TagKey.create(Registry.FLUID_REGISTRY, Constant.id("base_stone_moon"));
    public static final TagKey<Block> MOON_CARVER_REPLACEABLES = TagKey.create(Registry.BLOCK_REGISTRY, Constant.id("moon_carver_replaceables"));
    public static final TagKey<Block> MOON_CRATER_CARVER_REPLACEABLES = TagKey.create(Registry.BLOCK_REGISTRY, Constant.id("moon_crater_carver_replaceables"));
    public static final TagKey<Block> MOON_STONE_ORE_REPLACABLES = TagKey.create(Registry.BLOCK_REGISTRY, Constant.id("moon_stone_ore_replaceables"));
    public static final TagKey<Block> LUNASLATE_ORE_REPLACABLES = TagKey.create(Registry.BLOCK_REGISTRY, Constant.id("lunaslate_ore_replaceables"));
    public static final TagKey<Block> MACHINES = TagKey.create(Registry.BLOCK_REGISTRY, Constant.id("machines"));
}
