package io.github.dovehometeam.dovehomemod.infrastructure;

import io.github.dovehometeam.dovehomemod.Dovehomemod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.*;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = Dovehomemod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue noInfiniteWater = BUILDER.comment("No Infinite Water").define("NoInfiniteWater", true);
    public static final ForgeConfigSpec.ConfigValue<String> removeAllRecipeIncludeMod = BUILDER.comment("Remove All Recipe Include Mod").define("RemoveAllRecipeIncludeMod", "dovehomemod,kubejs,crafttweaker");
    public static final ForgeConfigSpec SPEC = BUILDER.build();


    public static boolean noInfiniteWaterBoolean;
    public static List<String> includeModidRemoveRecipe;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
       noInfiniteWaterBoolean = noInfiniteWater.get();
       includeModidRemoveRecipe = Arrays.stream(removeAllRecipeIncludeMod.get().split(",")).toList();
        // convert the list of strings into a set of items


    }
}
