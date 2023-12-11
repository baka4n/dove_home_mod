package io.github.dovehome.dovehomemod.datagen.server;

import io.github.dovehome.dovehomemod.advancements.critereon.CraftingRecipeTrigger;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveDimensions;
import io.github.dovehome.dovehomemod.forge.core.registry.DoveItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.ChangeDimensionTrigger;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

import static io.github.dovehome.dovehomemod.forge.DovehomemodForge.advancementMain;

public class DoveAdvancementProvider extends AdvancementProvider {


    @SuppressWarnings("deprecation")
    public DoveAdvancementProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public void run(@NotNull CachedOutput output) {
        tabs = getTags();

        super.run(output);
    }

    public List<Consumer<Consumer<Advancement>>> getTags() {
        return List.of(DoveAdvancementProvider::register);
    }

    public static void register(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Builder
                .advancement()
                .addCriterion("first_join_desert", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(DoveDimensions.DESERT_KEY))
                .display(DoveItems.sandStonePickaxe, Component.translatable("dovehomemod.root.advancement.title"), Component.translatable("dovehomemod.root.advancement.tips"), null, FrameType.GOAL, true, true, false)
                .save(consumer, advancementMain("root"));
        Advancement sand =
                Advancement.Builder
                        .advancement()
                        .parent(root)
                        .addCriterion("sand", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.SAND))
                        .display(Blocks.SAND, Component.translatable("dovehomemod.sand.advancement.title"), Component.translatable("dovehomemod.sand.advancement.title"), null, FrameType.TASK, true, true, false)
                        .save(consumer, advancementMain("sand"));
        Advancement sandstone = Advancement.Builder
                .advancement()
                .parent(sand)
                .addCriterion("sandstone", new CraftingRecipeTrigger.TriggerInstance(Tags.Items.SANDSTONE))
                .display(Blocks.SANDSTONE, Component.translatable("dovehomemod.sandstone.advancement.title"), Component.translatable("dovehomemod.sandstone.advancement.tips"), null, FrameType.TASK, true, true, false)
                .save(consumer, advancementMain("sandstone"));
        Advancement stick = Advancement.Builder
                .advancement()
                .parent(sand)
                .addCriterion("stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .display(Items.STICK, Component.translatable("dovehomemod.stick.advancement.title"), Component.translatable("dovehomemod.stick.advancement.tips"), null, FrameType.TASK, true, true, false)
                .save(consumer, advancementMain("stick"));
        Advancement sandstone_pickaxe = Advancement.Builder
                .advancement()
                .parent(sandstone)
                .addCriterion("sandstone_pickaxe", new CraftingRecipeTrigger.TriggerInstance(DoveItems.sandStonePickaxe))
                .display(DoveItems.sandStonePickaxe, Component.translatable("dovehomemod.sandstone.pickaxe.advancement.title"), Component.translatable("dovehomemod.sandstone.tools.advancement.tips"), null, FrameType.TASK, true, true, false)
                .save(consumer, advancementMain("sandstone_pickaxe"));

        Advancement sandstone_axe = Advancement.Builder
                .advancement()
                .parent(sandstone)
                .addCriterion("sandstone_pickaxe", new CraftingRecipeTrigger.TriggerInstance(DoveItems.sandStonePickaxe))
                .display(DoveItems.sandStonePickaxe, Component.translatable("dovehomemod.sandstone.axe.advancement.title"), Component.translatable("dovehomemod.sandstone.tools.advancement.tips"), null, FrameType.TASK, true, true, false)
                .save(consumer, advancementMain("sandstone_axe"));
        Advancement sandstone_sword = Advancement.Builder
                .advancement()
                .parent(sandstone)
                .addCriterion("sandstone_pickaxe", new CraftingRecipeTrigger.TriggerInstance(DoveItems.sandStoneAxe))
                .display(DoveItems.sandStoneAxe, Component.translatable("dovehomemod.sandstone.sword.advancement.title"), Component.translatable("dovehomemod.sandstone.tools.advancement.tips"), null, FrameType.TASK, true, true, false)
                .save(consumer, advancementMain("sandstone_sword"));
        Advancement sandstone_shovel = Advancement.Builder
                .advancement()
                .parent(sandstone)
                .addCriterion("sandstone_pickaxe", new CraftingRecipeTrigger.TriggerInstance(DoveItems.sandStoneShovel))
                .display(DoveItems.sandStoneShovel, Component.translatable("dovehomemod.sandstone.shovel.advancement.title"), Component.translatable("dovehomemod.sandstone.tools.advancement.tips"), null, FrameType.TASK, true, true, false)
                .save(consumer, advancementMain("sandstone_shovel"));
        Advancement sandstone_hoe = Advancement.Builder
                .advancement()
                .parent(sandstone)
                .addCriterion("sandstone_pickaxe", new CraftingRecipeTrigger.TriggerInstance(DoveItems.sandStoneHoe))
                .display(DoveItems.sandStoneHoe, Component.translatable("dovehomemod.sandstone.hoe.advancement.title"), Component.translatable("dovehomemod.sandstone.tools.advancement.tips"), null, FrameType.TASK, true, true, false)
                .save(consumer, advancementMain("sandstone_hoe"));
        Advancement sandstone_knife = Advancement.Builder
                .advancement()
                .parent(sandstone)
                .addCriterion("sandstone_knife", new CraftingRecipeTrigger.TriggerInstance(DoveItems.sandstoneKnife))
                .display(DoveItems.sandstoneKnife, Component.translatable("dovehomemod.sandstone.knife.advancement.title"), Component.translatable("dovehomemod.sandstone.knife.advancement.tips"), null, FrameType.TASK, true, true, false)
                .save(consumer, advancementMain("sandstone_knife"));
    }

    @Override
    public @NotNull String getName() {
        return "dove home mod advancement provider";
    }

    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
        super.registerAdvancements(consumer, fileHelper);

    }
}
