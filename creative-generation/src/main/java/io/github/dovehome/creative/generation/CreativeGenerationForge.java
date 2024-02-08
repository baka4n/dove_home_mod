package io.github.dovehome.creative.generation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.latvian.mods.kubejs.KubeJSPaths;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Mod(CreativeGenerationForge.MODID)
public class CreativeGenerationForge {
    public static final String MODID = "creative_generation";
    // task -> 烈焰人燃烧室燃烧热量
    // task -> 燃烧等级tag标签
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);
    public static final Path configDir = FMLPaths.CONFIGDIR.get().normalize();
    public static final Gson gson = new GsonBuilder().disableJdkUnsafe().setPrettyPrinting().setLenient().create();
    public static final Path cgfDir = KubeJSPaths.dir(KubeJSPaths.DIRECTORY.resolve("preInit").resolve("creative-generation"), true);
    public static final Path blazeBurnerPath = cgfDir.resolve("blaze-level.json");
//    public static CommentedConfig blazeConfig;

    // heat level 燃烧的状态
    // flame type 火焰类型
    // fuel type 燃料等级
    public CreativeGenerationForge() {

//        blazeConfig = new TomlParser().parse(CreativeGenerationForge.blazeBurnerPath, FileNotFoundAction.CREATE_EMPTY);
//        System.out.println(blazeConfig.get("fuel-settings").getClass());
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        REGISTRATE.registerEventListeners(modEventBus);
//        MinecraftForge.EVENT_BUS.register(this);

    }
}
