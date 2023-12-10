package io.github.dovehome.dovehomemod.datagen.server;

import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DoveTableLootProvider extends LootTableProvider {
    private final String modid;

    public DoveTableLootProvider(DataGenerator generator, String modid) {
        super(generator);
        this.modid = modid;
    }

    @Override
    protected void validate(@NotNull Map<ResourceLocation, LootTable> tables, @NotNull ValidationContext ctx) {
    }

    @Override
    protected @NotNull List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>>
    getTables() {
        ArrayList<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> pairs = new ArrayList<>();
        return pairs;
    }
}
