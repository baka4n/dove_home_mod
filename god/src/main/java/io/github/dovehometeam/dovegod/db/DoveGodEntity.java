package io.github.dovehometeam.dovegod.db;

import io.github.dovehometeam.dovedb.DoveDB;
import io.github.dovehometeam.dovedb.db.IEntity;
import io.github.dovehometeam.dovedb.db.base.DoveEntity;
import io.github.dovehometeam.dovegod.unique.bodyForgers.BFLevel;
import io.github.dovehometeam.dovegod.unique.cultivatingImmortals.IMCLevel;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public class DoveGodEntity extends IEntity<DoveGodEntity> {

    public IMCLevel imcLevel;//修仙属性
    public BFLevel bfLevel;//体修属性

    public DoveGodEntity() {
        imcLevel = IMCLevel.defaultIMC();
        bfLevel = BFLevel.defaultBF();
    }

    @SuppressWarnings("UnusedReturnValue")
    public static DoveGodEntity getOrCreate(Player player, Path directory, ConcurrentHashMap<String, DoveGodEntity> entities) {
        String uuid = player.getStringUUID();
        if (entities.containsKey(uuid))
            return entities.get(uuid);
        Path path = directory.resolve(uuid);
        if (Files.exists(path))
            try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
                DoveGodEntity entity = (DoveGodEntity) ois.readObject();
                entities.put(uuid, entity);
                return entity;
            } catch (IOException | ClassNotFoundException ignored) {}

        DoveGodEntity entity = entities.getOrDefault(uuid, new DoveGodEntity());
        if (!entities.containsKey(uuid)) entities.put(uuid, entity);
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DoveGodEntity that = (DoveGodEntity) o;

        return new EqualsBuilder().append(imcLevel, that.imcLevel).append(bfLevel, that.bfLevel).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(imcLevel).append(bfLevel).toHashCode();
    }
}
