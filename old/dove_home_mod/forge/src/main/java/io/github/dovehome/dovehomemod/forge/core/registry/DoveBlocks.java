package io.github.dovehome.dovehomemod.forge.core.registry;

import com.mojang.datafixers.DSL;
import io.github.dovehome.bakalib.forge.annotations.RegistryBlock;
import io.github.dovehome.bakalib.forge.annotations.RegistryBlockEntity;
import io.github.dovehome.bakalib.forge.annotations.RegistryItem;
import io.github.dovehome.bakalib.forge.annotations.RegistryMenu;
import io.github.dovehome.dovehomemod.forge.core.blocks.*;
import io.github.dovehome.dovehomemod.forge.core.blocks.entities.CactusThornsBlockEntity;
import io.github.dovehome.dovehomemod.forge.core.blocks.entities.SandSculptureBlockEntity;
import io.github.dovehome.dovehomemod.forge.core.blocks.entities.SandstoneFurnaceBlockEntity;
import io.github.dovehome.dovehomemod.forge.core.blocks.entities.SandstoneTableBlockEntity;
import io.github.dovehome.dovehomemod.forge.core.items.CactusThornsBlockItem;
import io.github.dovehome.dovehomemod.forge.core.tab.Step1Tab;
import io.github.dovehome.dovehomemod.forge.core.world.grower.ResurrectedOakTreeGrower;
import io.github.dovehome.dovehomemod.forge.menu.SandstoneFurnaceMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.ToIntFunction;

public class DoveBlocks {
    @RegistryBlock("cactus_thorns")
    public static final CactusThornsBlock cactusThorns;
    @RegistryItem("cactus_thorns")
    public static final CactusThornsBlockItem cactusThronsItem;
    @RegistryBlockEntity("cactus_thorns")
    public static final  BlockEntityType<CactusThornsBlockEntity> cactusThornsEntity;
    @RegistryBlock("resurrected_sapling")
    public static final SaplingBlock resurrectedSapling;



    @RegistryItem("resurrected_sapling")
    public static final BlockItem resurrectedSaplingItem;

    @RegistryBlock("sand_sculpture")
    public static final SandSculptureBlock sandSculpture;

    @RegistryBlockEntity("sand_sculpture")
    public static final BlockEntityType<SandSculptureBlockEntity> sandSculptureEntity;

    @RegistryItem("sand_sculpture")
    public static final BlockItem sandSculptureItem;

    @RegistryBlock("sandstone_table")
    public static final SandStoneTableBlock sandstoneTable;

    @RegistryItem("sandstone_table")
    public static final BlockItem sandstoneTableItem;

    @RegistryBlockEntity("sandstone_table")
    public static final BlockEntityType<SandstoneTableBlockEntity> sandstoneTableEntity;

    @RegistryBlock("sandstone_furnace")
    public static final SandstoneFurnaceBlock sandstoneFurnace;
    @RegistryItem("sandstone_furnace")
    public static final BlockItem sandstoneFurnaceItem;

    @RegistryBlockEntity("sandstone_furnace")
    public static final BlockEntityType<SandstoneFurnaceBlockEntity> sandstoneFurnaceEntity;

    @RegistryMenu("sandstone_furnace")
    public static final MenuType<SandstoneFurnaceMenu> sandstoneFurnaceMenu;

    static {
        cactusThorns = new CactusThornsBlock();
        cactusThronsItem = new CactusThornsBlockItem(Step1Tab.getInstance());
        cactusThornsEntity = BlockEntityType.Builder.of(CactusThornsBlockEntity::new, cactusThorns).build(DSL.remainderType());
        resurrectedSapling = new ResurrectedSapling(new ResurrectedOakTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
        resurrectedSaplingItem = new BlockItem(resurrectedSapling, new Item.Properties().tab(Step1Tab.getInstance()));
        sandSculpture = new SandSculptureBlock(BlockBehaviour.Properties.of(Material.SAND));
        sandSculptureEntity = BlockEntityType.Builder.of(SandSculptureBlockEntity::new, sandSculpture).build(DSL.remainderType());
        sandSculptureItem = new BlockItem(sandSculpture, new Item.Properties().tab(Step1Tab.getInstance()));
        sandstoneTable = new SandStoneTableBlock(BlockBehaviour.Properties.of(Material.SAND));
        sandstoneTableItem = new BlockItem(sandstoneTable, new Item.Properties().tab(Step1Tab.getInstance()));
        sandstoneTableEntity = BlockEntityType.Builder.of(SandstoneTableBlockEntity::new, sandstoneTable).build(DSL.remainderType());
        sandstoneFurnace = new SandstoneFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F).lightLevel(litBlockEmission(13)).noOcclusion());
        sandstoneFurnaceItem = new BlockItem(sandstoneFurnace, new Item.Properties().stacksTo(10)) {
            @Override
            public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
                super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
                tooltipComponents.add(Component.translatable("sandstone.furnace.tooltip"));
            }
        };
        sandstoneFurnaceEntity = BlockEntityType.Builder.of(SandstoneFurnaceBlockEntity::new, sandstoneFurnace).build(DSL.remainderType());
        sandstoneFurnaceMenu = new MenuType<>(SandstoneFurnaceMenu::new);
    }

    @SuppressWarnings("SameParameterValue")
    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (arg) -> arg.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }
}
