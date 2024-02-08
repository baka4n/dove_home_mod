package io.github.dovehome.bakalib.forge.registry;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.*;

import java.util.function.Consumer;

public class ForgeEventRegistry implements IEventBus {
    public enum Type {
        FORGE, MOD;
    }
    private final IEventBus forgeBus, modBus;
    private Type type = Type.FORGE;
    public ForgeEventRegistry(IEventBus modBus) {
        this.forgeBus = MinecraftForge.EVENT_BUS;
        this.modBus = modBus;
    }

    public ForgeEventRegistry setType(Type type) {
        this.type = type;
        return this;
    }

    public ForgeEventRegistry registry(Object object) {
        register(object);
        return this;
    }


    public <T extends Event> ForgeEventRegistry listener(Consumer<T> consumer) {
        addListener(consumer);
        return this;
    }

    public <T extends Event> ForgeEventRegistry listener(EventPriority priority, Consumer<T> consumer) {
        addListener(priority, consumer);
        return this;
    }

    public <T extends Event> ForgeEventRegistry listener(EventPriority priority, boolean receiveCancelled, Consumer<T> consumer) {
        addListener(priority, receiveCancelled, consumer);
        return this;
    }
    public <T extends Event> ForgeEventRegistry listener(EventPriority priority, boolean receiveCancelled, Class<T> eventType, Consumer<T> consumer) {
        addListener(priority, receiveCancelled, eventType, consumer);
        return this;
    }

    public <T extends GenericEvent<? extends F>, F> ForgeEventRegistry genericListener(Class<F> genericClassFilter, Consumer<T> consumer) {
        addGenericListener(genericClassFilter, consumer);
        return this;
    }

    public <T extends GenericEvent<? extends F>, F> ForgeEventRegistry genericListener(Class<F> genericClassFilter, EventPriority priority, Consumer<T> consumer) {
        addGenericListener(genericClassFilter, priority, consumer);
        return this;
    }

    public <T extends GenericEvent<? extends F>, F> ForgeEventRegistry genericListener(Class<F> genericClassFilter, EventPriority priority, boolean receiveCancelled, Consumer<T> consumer) {
        addGenericListener(genericClassFilter, priority, receiveCancelled, consumer);
        return this;
    }


    public <T extends GenericEvent<? extends F>, F> ForgeEventRegistry genericListener(Class<F> genericClassFilter, EventPriority priority, boolean receiveCancelled, Class<T> eventType, Consumer<T> consumer) {
        addGenericListener(genericClassFilter, priority, receiveCancelled, eventType, consumer);
        return this;
    }

    @Override
    public void register(Object target) {
        if (type.equals(Type.FORGE)) {
            forgeBus.register(target);
        } else {
            modBus.register(target);
        }
    }

    @Override
    public <T extends Event> void addListener(Consumer<T> consumer) {
        if (type.equals(Type.FORGE)) {
            forgeBus.addListener(consumer);
        } else {
            modBus.addListener(consumer);
        }
    }

    @Override
    public <T extends Event> void addListener(EventPriority priority, Consumer<T> consumer) {
        if (type.equals(Type.FORGE)) {
            forgeBus.addListener(priority, consumer);
        } else {
            modBus.addListener(priority, consumer);
        }
    }

    @Override
    public <T extends Event> void addListener(EventPriority priority, boolean receiveCancelled, Consumer<T> consumer) {
        if (type.equals(Type.FORGE)) {
            forgeBus.addListener(priority, receiveCancelled, consumer);
        } else {
            modBus.addListener(priority, receiveCancelled, consumer);
        }
    }

    @Override
    public <T extends Event> void addListener(EventPriority priority, boolean receiveCancelled, Class<T> eventType, Consumer<T> consumer) {
        if (type.equals(Type.FORGE)) {
            forgeBus.addListener(priority, receiveCancelled, eventType, consumer);
        } else {
            modBus.addListener(priority, receiveCancelled, eventType, consumer);
        }
    }

    @Override
    public <T extends GenericEvent<? extends F>, F> void addGenericListener(Class<F> genericClassFilter, Consumer<T> consumer) {
        if (type.equals(Type.FORGE)) {
            forgeBus.addGenericListener(genericClassFilter, consumer);
        } else {
            modBus.addGenericListener(genericClassFilter, consumer);
        }
    }

    @Override
    public <T extends GenericEvent<? extends F>, F> void addGenericListener(Class<F> genericClassFilter, EventPriority priority, Consumer<T> consumer) {
        if (type.equals(Type.FORGE)) {
            forgeBus.addGenericListener(genericClassFilter, priority, consumer);
        } else {
            modBus.addGenericListener(genericClassFilter, priority, consumer);
        }
    }

    @Override
    public <T extends GenericEvent<? extends F>, F> void addGenericListener(Class<F> genericClassFilter, EventPriority priority, boolean receiveCancelled, Consumer<T> consumer) {
        if (type.equals(Type.FORGE)) {
            forgeBus.addGenericListener(genericClassFilter, priority, receiveCancelled, consumer);
        } else {
            modBus.addGenericListener(genericClassFilter, priority, receiveCancelled, consumer);
        }
    }

    @Override
    public <T extends GenericEvent<? extends F>, F> void addGenericListener(Class<F> genericClassFilter, EventPriority priority, boolean receiveCancelled, Class<T> eventType, Consumer<T> consumer) {
        if (type.equals(Type.FORGE)) {
            forgeBus.addGenericListener(genericClassFilter, priority, receiveCancelled, eventType, consumer);
        } else {
            modBus.addGenericListener(genericClassFilter, priority, receiveCancelled, eventType, consumer);
        }
    }

    @Override
    public void unregister(Object object) {
        if (type.equals(Type.FORGE)) {
            forgeBus.unregister(object);
        } else {
            modBus.unregister(object);
        }
    }

    @Override
    public boolean post(Event event) {
        if (type.equals(Type.FORGE)) {
            return forgeBus.post(event);
        } else {
            return modBus.post(event);
        }
    }

    @Override
    public boolean post(Event event, IEventBusInvokeDispatcher wrapper) {
        if (type.equals(Type.FORGE)) {
            return forgeBus.post(event, wrapper);
        } else {
            return modBus.post(event, wrapper);
        }
    }

    @Override
    public void shutdown() {
        if (type.equals(Type.FORGE)) {
            forgeBus.shutdown();
        } else {
            modBus.shutdown();
        }
    }

    @Override
    public void start() {
        if (type.equals(Type.FORGE)) {
            forgeBus.start();
        } else {
            modBus.start();
        }
    }


}
