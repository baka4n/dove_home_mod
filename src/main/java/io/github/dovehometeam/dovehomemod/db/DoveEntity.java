package io.github.dovehometeam.dovehomemod.db;

import io.github.dovehometeam.dovehomemod.db.team.DoveTeamEntity;
import io.github.dovehometeam.dovehomemod.unique.bodyForgers.BFLevel;
import io.github.dovehometeam.dovehomemod.unique.cultivatingImmortals.IMCLevel;
import net.minecraft.world.entity.player.Player;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class DoveEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 7168710689281527121L;

    public IMCLevel imcLevel;//修仙属性
    public BFLevel bfLevel;//体修属性
    public static final ConcurrentHashMap<String, LinkedList<DoveTeamEntity.TeamChunkPos>> playerTeamClaimTcp = new ConcurrentHashMap<>();

    public boolean hasPlayerTeamClaimTcp(Player player, DoveTeamEntity.TeamChunkPos pos) {
        String uuid = player.getStringUUID();
        if (!DoveSQL.tcpList.contains(pos))
            return true;
        if (!playerTeamClaimTcp.containsKey(uuid)) return false;
        return playerTeamClaimTcp.get(uuid).contains(pos);
    }


    @SuppressWarnings("UnusedReturnValue")
    public static DoveEntity getAndCreate(String uuid) {
        if (DoveSQL.entities.containsKey(uuid))
            return DoveSQL.entities.get(uuid);
        DoveEntity doveEntity = new DoveEntity();
        doveEntity.imcLevel = IMCLevel.defaultIMC();
        doveEntity.bfLevel = BFLevel.defaultBF();
        DoveSQL.entities.put(uuid, doveEntity);
        return doveEntity;
    }
}
