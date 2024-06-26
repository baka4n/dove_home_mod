package io.github.dovehometeam.dovedb.db;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BaseSQL implements ISQL {
    protected final AtomicReference<ConcurrentHashMap<String, ? extends IEntity<?>>> entities = new AtomicReference<>();
    protected final AtomicReference<Path> directory = new AtomicReference<>();

    @Override
    public AtomicReference<ConcurrentHashMap<String, ? extends IEntity<?>>> value() {
        return entities;
    }

    @Override
    public AtomicReference<Path> directory() {
        return directory;
    }
}
