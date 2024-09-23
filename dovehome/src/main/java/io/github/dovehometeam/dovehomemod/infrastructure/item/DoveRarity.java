package io.github.dovehometeam.dovehomemod.infrastructure.item;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

public class DoveRarity {
    /*
    废品0，下品1、中品2、上品3、极品4、地品5、天品6、仙品7
    */
    public static final Rarity IMMORTALS = Rarity.create("IMMORTALS", ChatFormatting.GOLD);//仙品

    public static final Rarity SKY = Rarity.create("SKY", ChatFormatting.YELLOW);//天品
    public static final Rarity EARTH = Rarity.create("EARTH", ChatFormatting.GREEN);//地品
    public static final Rarity POLE = Rarity.create("POLE", ChatFormatting.DARK_RED);//极品
    public static final Rarity UPPER = Rarity.create("UPPER", ChatFormatting.RED);//上品
    public static final Rarity MID_LEVEL = Rarity.create("MID_LEVEL", ChatFormatting.LIGHT_PURPLE);//中品
    public static final Rarity LOWER = Rarity.create("LOWER", ChatFormatting.AQUA);//下品
    public static final Rarity DEPRECATED = Rarity.create("DEPRECATED", ChatFormatting.WHITE);//上品
}
