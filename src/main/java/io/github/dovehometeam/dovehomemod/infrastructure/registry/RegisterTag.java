package io.github.dovehometeam.dovehomemod.infrastructure.registry;

import io.github.dovehometeam.dovehomemod.Dovehomemod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.Locale;

public class RegisterTag {
    public static void init() {
        Blocks.init();
    }
    public enum Blocks {
        NEEDS_SAND_TOOL;
        public final TagKey<Block> block;
        Blocks() {
            block = tag(name().toLowerCase(Locale.ROOT));
        }
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Dovehomemod.MODID, name));
        }


        public TagKey<Block> get() {
            return block;
        }
        public static void init() {

        }

    }
}
