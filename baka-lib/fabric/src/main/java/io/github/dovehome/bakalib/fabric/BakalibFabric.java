package io.github.dovehome.bakalib.fabric;

import io.github.dovehome.bakalib.Bakalib;
import net.fabricmc.api.ModInitializer;

public class BakalibFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Bakalib.init();
    }
}