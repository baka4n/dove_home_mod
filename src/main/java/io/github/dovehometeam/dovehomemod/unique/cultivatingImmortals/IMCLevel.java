package io.github.dovehometeam.dovehomemod.unique.cultivatingImmortals;

import io.github.dovehometeam.dovehomemod.auxiliary.IPelletSettings;
import io.github.dovehometeam.dovehomemod.unique.SmartLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static io.github.dovehometeam.dovehomemod.Dovehomemod.ran;

public class IMCLevel implements Serializable {
    @Serial
    private static final long serialVersionUID = -5403419615887146185L;

    public ImmortalCultivators caste;

    public SmartLevel level;

    public BigDecimal impurity, aqua_value;
    //体内杂质, 灵气量， 杂质百分比为 杂质/(灵气量+杂质) 锻体可以减少杂质量

    public ConcurrentHashMap<String, BigDecimal> drugResistance;
    // 物品注册名 耐药性
    //耐药性为1.0以下的基数，越小越无效

    public static IMCLevel defaultIMC() {
        IMCLevel imcLevel = new IMCLevel();
        imcLevel.caste = ImmortalCultivators.laic;
        imcLevel.level = SmartLevel.JIA;
        imcLevel.impurity = BigDecimal.valueOf(ran.nextDouble(0.0, 1.0));
        imcLevel.aqua_value = new BigDecimal(ran.nextInt(0, 10));
        ConcurrentHashMap<String, BigDecimal> drug = new ConcurrentHashMap<>();
        for (Map.Entry<ResourceKey<Item>, Item> entry : ForgeRegistries.ITEMS.getEntries()) {
            if (entry.getValue() instanceof IPelletSettings) {
                drug.put(entry.getKey().location().toString(), BigDecimal.valueOf(1.0));
            }
        }//初始化写入基础耐药性
        imcLevel.drugResistance = drug;
        return imcLevel;
    }

    @Override
    public String toString() {
        return "IMCLevel{" +
                "caste=" + caste +
                ", level=" + level +
                ", impurity=" + impurity +
                ", aqua_value=" + aqua_value +
                ", drugResistance=" + drugResistance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IMCLevel imcLevel = (IMCLevel) o;
        return caste == imcLevel.caste && level == imcLevel.level && Objects.equals(impurity, imcLevel.impurity) && Objects.equals(aqua_value, imcLevel.aqua_value) && Objects.equals(drugResistance, imcLevel.drugResistance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caste, level, impurity, aqua_value, drugResistance);
    }
}
