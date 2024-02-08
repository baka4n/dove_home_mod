package io.github.dovehome.laysdelight.forge.core.items;

import io.github.dovehome.laysdelight.forge.core.tabs.LaysCannedTab;
import io.github.dovehome.laysdelight.forge.registry.LaysItems;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class LaysWoveItem extends LaysBaseItem {
    public static final Random random = new Random();
    public LaysWoveItem(Properties properties) {
        super(properties.tab(LaysCannedTab.getInstance()).stacksTo(1));// this has nbt
    }

    @Override
    public boolean isItem(@NotNull ItemStack stack) {
        return stack.is(LaysItems.wave_potato_chip);
    }

    @Override
    public int defaultRan() {
        return random.nextInt(8) + 26;
    }

    public LaysWoveItem() {
        this(new Properties());
    }
}
