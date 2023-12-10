package io.github.dovehome.dovehomemod.forge.menu;

import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class SandstoneFurnaceMenu extends AbstractFurnaceMenu {

    public SandstoneFurnaceMenu(int containerId, Inventory playerInventory) {
        super(DoveBlocks.sandstoneFurnaceMenu, RecipeType.SMELTING, RecipeBookType.FURNACE, containerId, playerInventory);
    }

    public SandstoneFurnaceMenu(int containerId, Inventory playerInventory, Container furnaceContainer, ContainerData furnaceData) {
        super(DoveBlocks.sandstoneFurnaceMenu, RecipeType.SMELTING, RecipeBookType.FURNACE, containerId, playerInventory, furnaceContainer, furnaceData);
    }
}
