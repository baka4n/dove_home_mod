package io.github.dovehome.laysdelight.forge.core.items;

public class WavePotatoChipsItem extends PotatoChipsItem {
    @Deprecated(forRemoval = true, since = "to use other <init>")
    public WavePotatoChipsItem(Properties properties) {
        super(properties, 1, 0.1F);
    }

    public WavePotatoChipsItem(Properties properties, int nutrition, float saturationMod) {
        super(properties, nutrition, saturationMod);
    }
}
