package io.github.dovehome.dovehomemod.forge.chunk.mixin;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {
    //生成建筑物结构时调用
    @Inject(method = "createStructures", at = @At("HEAD"))
    private void createStructures(RegistryAccess registryAccess,
                                  RandomState random,
                                  StructureManager structureManager,
                                  ChunkAccess chunk,
                                  StructureTemplateManager structureTemplateManager,
                                  long seed,
                                  CallbackInfo ci) {

    }
    //应用于群系装饰
    @Inject(method = "applyBiomeDecoration", at = @At("RETURN"))
    private void applyBiomeDecoration(WorldGenLevel level,
                                      ChunkAccess chunk,
                                      StructureManager structureManager,
                                      CallbackInfo ci) {

    }
}
