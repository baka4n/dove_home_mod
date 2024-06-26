package io.github.dovehometeam.dovedb.db;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BaseSQL<T extends IEntity<T>> implements ISQL<T> {
    protected final AtomicReference<ConcurrentHashMap<String, T>> entities = new AtomicReference<>();
    protected final AtomicReference<Path> directory = new AtomicReference<>();

    public AtomicReference<ConcurrentHashMap<String, T>> value() {
        return entities;
    }

    @Override
    public AtomicReference<Path> directory() {
        return directory;
    }
}
