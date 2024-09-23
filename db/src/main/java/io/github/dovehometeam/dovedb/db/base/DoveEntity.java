package io.github.dovehometeam.dovedb.db.base;

import io.github.dovehometeam.dovedb.DoveDB;
import io.github.dovehometeam.dovedb.db.IEntity;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.Position;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class DoveEntity extends IEntity<DoveEntity> {

    private String test;

    public DoveEntity(String modid) {
        this.test = modid;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static DoveEntity getOrCreate(Player player, Path directory, ConcurrentHashMap<String, DoveEntity> entities) {
        String uuid = player.getStringUUID();
        if (entities.containsKey(uuid))
            return entities.get(uuid);
        Path path = directory.resolve(uuid);
        if (Files.exists(path))
            try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
                DoveEntity entity = (DoveEntity) ois.readObject();
                entities.put(uuid, entity);
                return entity;
            } catch (IOException | ClassNotFoundException ignored) {}

        DoveEntity entity = entities.getOrDefault(uuid, new DoveEntity(DoveDB.MODID));
        if (!entities.containsKey(uuid)) entities.put(uuid, entity);
        return entity;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DoveEntity that = (DoveEntity) o;

        return new EqualsBuilder().append(test, that.test).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(test).toHashCode();
    }
}
