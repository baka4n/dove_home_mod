package io.github.dovehometeam.dovehomemod.db.team;

import io.github.dovehometeam.dovedb.db.IEntity;
import net.minecraft.world.level.ChunkPos;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

public class DoveTeamEntity extends IEntity<DoveTeamEntity> implements Serializable {
    @Serial
    private static final long serialVersionUID = 7168710689281527121L;
    public String name;
    public LinkedList<String> players;//uuid
    public int level;
    public LinkedList<TeamChunkPos> claimChunk;//存储的区块坐标
    public LinkedList<TeamChunkPos> loaderClaimChunk;//常加载的区块坐标

    @Override
    public String toString() {
        return "DoveTeamEntity{" +
                "name='" + name + '\'' +
                ", players=" + players +
                ", level=" + level +
                ", claimChunk=" + claimChunk +
                ", loaderClaimChunk=" + loaderClaimChunk +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoveTeamEntity that = (DoveTeamEntity) o;
        return level == that.level && Objects.equals(name, that.name) && Objects.equals(players, that.players) && Objects.equals(claimChunk, that.claimChunk) && Objects.equals(loaderClaimChunk, that.loaderClaimChunk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, players, level, claimChunk, loaderClaimChunk);
    }

    public record TeamChunkPos(int x, int z) implements Serializable {
            @Serial
            private static final long serialVersionUID = -3982942337350664027L;

        @Override
            public String toString() {
                return "TeamChunkPos{" +
                        "x=" + x +
                        ", z=" + z +
                        '}';
            }

        public boolean is(ChunkPos pos) {
                return x == pos.x && z == pos.z;
            }


    }


}
