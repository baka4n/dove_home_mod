package io.github.dovehome.bakalib.forge.registry;

import io.github.dovehome.bakalib.forge.annotations.RegistryCapability;
import io.github.dovehome.bakalib.forge.capabilities.BaseCapability;
import io.github.dovehome.bakalib.forge.capabilities.BaseCapabilityProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegistryCapabilityAll {
    public final Map<String, Capability<? extends BaseCapability>> capabilities = new ConcurrentHashMap<>();
    public final Map<String, Class<? extends BaseCapability>> classes = new ConcurrentHashMap<>();

    private final String modid;

    public RegistryCapabilityAll(String modid) {
        this.modid = modid;
    }

    @SuppressWarnings("unchecked")
    public void add(Class<?> clazz) {
        for (Field declaredField : clazz.getDeclaredFields()) {
            declaredField.setAccessible(true);
            if (declaredField.isAnnotationPresent(RegistryCapability.class)) {
                try {
                    RegistryCapability annotation = declaredField.getAnnotation(RegistryCapability.class);
                    capabilities.put(annotation.value(), (Capability<? extends BaseCapability>) declaredField.get(null));
                    classes.put(annotation.value(), annotation.clazz());
                } catch (IllegalAccessException ignored) {}
            }
        }
    }

    public void registry(RegisterCapabilitiesEvent event) {
        for (var capability : classes.values()) {
            event.register(capability);
        }
    }

    public void clone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            for (Capability<? extends BaseCapability> value : capabilities.values()) {
                event.getOriginal().getCapability(value).ifPresent(old -> {
                    if (old.isDeathRetention()) {
                        event.getOriginal().getCapability(value).ifPresent(new_ -> {
                            new_.copyFrom(old);
                        });
                    }
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseCapability> void attach(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            var array = capabilities.keySet().stream().toList();
            List<Capability<? extends BaseCapability>> list1 = capabilities.values().stream().toList();
            List<Class<? extends BaseCapability>> list = classes.values().stream().toList();
            for (int i = 0; i < array.size(); i++) {
                if (!player.getCapability(list1.get(i)).isPresent()) {
                    event.addCapability(new ResourceLocation(modid, array.get(i)), new BaseCapabilityProvider<>((Capability<T>) list1.get(i), (Class<T>) list.get(i)));
                }
            }
        }
    }
}
