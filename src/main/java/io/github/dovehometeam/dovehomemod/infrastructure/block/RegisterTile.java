package io.github.dovehometeam.dovehomemod.infrastructure.block;

import com.mojang.datafixers.DSL;
import io.github.dovehometeam.dovehomemod.infrastructure.block.tile.AbrasiveSandStoneBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

import static io.github.dovehometeam.dovehomemod.AllRegistry.entityTypes;

public enum RegisterTile implements Supplier<BlockEntityType<? extends BlockEntity>> {
    abrasive_sand_stone(() -> BlockEntityType.Builder.of(AbrasiveSandStoneBlockEntity::new, RegisterBlock.abrasive_sand_stone.get()).build(DSL.remainderType())),
    ;

    public final RegistryObject<BlockEntityType<? extends BlockEntity>> t;

    RegisterTile(Supplier<BlockEntityType<? extends BlockEntity>> type) {
        this.t = entityTypes.register(name().toLowerCase(Locale.ROOT), type);
    }

    @Override
    public BlockEntityType<?> get() {
        return t.get();
    }

    public static void init() {}
}
