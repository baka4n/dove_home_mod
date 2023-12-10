package dev.galacticraft.forge;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Constant {
    String MOD_ID = "galacticraft";
    String COMMON_NAMESPACE = "c";
    Logger LOGGER = LoggerFactory.getLogger("Galacticraft");

    static ResourceLocation id(String id) {
        return new ResourceLocation(MOD_ID, id);
    }
}
