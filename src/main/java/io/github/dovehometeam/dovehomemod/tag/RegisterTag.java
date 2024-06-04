package io.github.dovehometeam.dovehomemod.tag;

import io.github.dovehometeam.dovehomemod.Dovehomemod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.Locale;

public enum RegisterTag {
    ;
    public enum Blocks {
        NEEDS_SAND_TOOL;
        public final TagKey<Block> block;
        Blocks() {
            block = tag(name().toLowerCase(Locale.ROOT));
        }
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Dovehomemod.MODID, name));
        }
    }
}
