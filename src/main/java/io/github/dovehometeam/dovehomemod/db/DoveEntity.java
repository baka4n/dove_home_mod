package io.github.dovehometeam.dovehomemod.db;

import io.github.dovehometeam.dovehomemod.unique.cultivatingImmortals.ImmortalCultivators;

import java.io.Serial;
import java.io.Serializable;

public class DoveEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 7168710689281527121L;
    public ImmortalCultivators icu;


    @SuppressWarnings("UnusedReturnValue")
    public static DoveEntity getAndCreate(String uuid) {
        if (DoveSQL.entities.containsKey(uuid)) {
            return DoveSQL.entities.get(uuid);
        }
        DoveEntity doveEntity = new DoveEntity();
        doveEntity.icu = ImmortalCultivators.laic;
        DoveSQL.entities.put(uuid, doveEntity);
        return doveEntity;
    }


}
