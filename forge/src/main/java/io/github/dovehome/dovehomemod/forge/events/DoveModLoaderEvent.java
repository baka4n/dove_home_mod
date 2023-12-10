package io.github.dovehome.dovehomemod.forge.events;

import io.github.dovehome.dovehomemod.forge.core.registry.DoveBlocks;
import io.github.dovehome.dovehomemod.forge.core.screen.SandstoneFurnaceScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class DoveModLoaderEvent {
    public static void commonSetup(FMLCommonSetupEvent event) {
//        event.enqueueWork(() -> {
//            Path path = FMLPaths.CONFIGDIR.get();
//            Path ftbquests = path.resolve("ftbquests").resolve("quests");
//            if (!Files.exists(ftbquests)) {
//                try {
//                    Files.createDirectories(ftbquests);
//                } catch (IOException ignored) {}
//            }
//            setFtbqData(ftbquests);// reset everyone to run client
//        });
    }

//    private static void setFtbqData(Path ftbquests) {
//        Path resolve = ftbquests.resolve("data.snbt");
//        if (!Files.exists(resolve)) {
//            SNBTCompoundTag tag = new SNBTCompoundTag();
//            tag.putString("default_autoclaim_rewards", "disabled");
//            tag.putBoolean("default_consume_items", false);
//            tag.putBoolean("default_quest_disable_jei", false);
//            tag.putString("default_quest_shape", "circle");
//            tag.putBoolean("default_reward_team", false);
//            tag.putInt("detection_delay", 20);
//            tag.putBoolean("disable_gui", false);
//            tag.putBoolean("drop_loot_crates", false);
//            tag.putInt("emergency_items_cooldown", 300);
//            tag.putDouble("grid_scale", 0.5d);
//            tag.putString("lock_message", "");
//            SNBTCompoundTag loot_crate_no_drop = new SNBTCompoundTag();
//            loot_crate_no_drop.putInt("boss", 0);
//            loot_crate_no_drop.putInt("monster", 600);
//            loot_crate_no_drop.putInt("passive", 4000);
//            tag.put("loot_crate_no_drop", loot_crate_no_drop);
//            tag.putBoolean("pause_game", false);
//            tag.putString("progression_mode", "linear");
//            tag.putInt("version", 13);
//            SNBT.write(resolve, tag);
//        }
//    }

    public static void init(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(DoveBlocks.sandstoneFurnaceMenu, SandstoneFurnaceScreen::new);
        });
    }
}
