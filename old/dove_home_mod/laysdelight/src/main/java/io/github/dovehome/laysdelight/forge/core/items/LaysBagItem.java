package io.github.dovehome.laysdelight.forge.core.items;

import io.github.dovehome.laysdelight.forge.LaysDelight;
import io.github.dovehome.laysdelight.forge.core.tabs.LaysBagTab;
import io.github.dovehome.laysdelight.forge.registry.LaysItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class LaysBagItem extends LaysBaseItem {

    public static final Random random = new Random();

    public LaysBagItem(Properties properties) {
        super(properties.tab(LaysBagTab.getInstance()).stacksTo(1));// this has nbt
    }

    @Override
    public boolean isItem(@NotNull ItemStack stack) {
        return stack.is(LaysItems.potato_chip);
    }

    public LaysBagItem() {
        this(new Properties());
    }

    @Override
    public int defaultRan() {
        return random.nextInt(4) + 48;
    }
}
