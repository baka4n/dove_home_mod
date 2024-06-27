package io.github.dovehometeam.dovegod.unique.bodyForgers;

import io.github.dovehometeam.dovehomemod.unique.SmartLevel;

import java.io.Serial;
import java.io.Serializable;

public class BFLevel implements Serializable {
    @Serial
    private static final long serialVersionUID = 2571947993172775907L;

    public BodyForgers caste;
    public SmartLevel level;

    public static BFLevel defaultBF() {
        BFLevel bfLevel = new BFLevel();
        bfLevel.caste = BodyForgers.laic;
        bfLevel.level = SmartLevel.JIA;
        return bfLevel;
    }
}
