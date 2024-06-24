package io.github.dovehometeam.dovedb.db;

import java.io.Serial;
import java.io.Serializable;

public abstract class IEntity<T extends IEntity<T>> implements Serializable {

    @Serial
    private static final long serialVersionUID = 7387211682909232393L;


}
