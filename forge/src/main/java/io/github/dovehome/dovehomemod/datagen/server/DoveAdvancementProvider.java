package io.github.dovehome.dovehomemod.datagen.server;

import io.github.dovehome.dovehomemod.forge.DovehomemodForge;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
        Advancement root =
                Advancement.Builder
                        .advancement()
                        .save(consumer, DovehomemodForge.idStr("root"));

    }

    public static Advancement save(Advancement.Builder builder, Consumer<Advancement> consumer, ResourceLocation location) {
        return builder.save(consumer, location.toString());
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
