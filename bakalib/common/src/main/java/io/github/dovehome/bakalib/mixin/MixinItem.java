package io.github.dovehome.bakalib.mixin;

import io.github.dovehome.bakalib.registry.IRegistry;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

@Debug(export = true)
@Mixin(Item.class)
public class MixinItem implements IRegistry<MixinItem> {
}
