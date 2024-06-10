package io.github.dovehometeam.dovehomemod.db;

import io.github.dovehometeam.dovehomemod.unique.cultivatingImmortals.ImmortalCultivators;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import static io.github.dovehometeam.dovehomemod.Dovehomemod.ran;

public class DoveEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 7168710689281527121L;
    public ImmortalCultivators icu;//修仙等第
    public BigInteger level;//修仙等级


    @SuppressWarnings("UnusedReturnValue")
    public static DoveEntity getAndCreate(String uuid) {
        if (DoveSQL.entities.containsKey(uuid)) {
            return DoveSQL.entities.get(uuid);
        }
        DoveEntity doveEntity = new DoveEntity();
        doveEntity.icu = ImmortalCultivators.laic;
        doveEntity.level = new BigInteger(String.valueOf(ran.nextInt(1, 100)));
        DoveSQL.entities.put(uuid, doveEntity);

        return doveEntity;
    }

    @Override
    public String toString() {
        return "DoveEntity{" + "icu=" + icu +
                ", level=" + level +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoveEntity that = (DoveEntity) o;
        return icu == that.icu && Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icu, level);
    }
}
