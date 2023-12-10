package io.github.dovehome.laysdelight.forge.registry;

import io.github.dovehome.laysdelight.forge.LaysDelight;
import io.github.dovehome.laysdelight.forge.core.items.*;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import static io.github.dovehome.laysdelight.forge.LaysDelight.items;

public class LaysItems {
    // url https://www.lays.com.cn/product.html
    // 味经典原味、黄瓜味、青柠味、德克萨斯烧烤味、意大利香浓红烩味、清新小番茄味、海苔味、麻辣锅味、黑胡椒味、墨西哥鸡汁番茄味、清新芥香味、香辣小龙虾味、黄金炒蟹味、鳗鱼味
    public static final PotatoChipsItem potato_chip;
    public static final WavePotatoChipsItem wave_potato_chip;
    public static final LaysBagItem american_original_flavor_classic_lays_bag;
    public static final LaysCannedItem loyal_original_flavor_lays_canned;
    public static final LaysBagItem cucumber_lays_bag;
    public static final LaysBagItem lime_lays_bag;
    public static final LaysBagItem texas_bbq_lays_bag;
    public static final LaysBagItem italian_red_stew_lays_bag;
    public static final LaysBagItem fresh_cheery_tomato_lays_bag;
    public static final LaysBagItem seaweed_lays_bag;
    public static final LaysBagItem spicy_hotpot_lays_bag;
    public static final LaysBagItem black_pepper_lays_bag;
    public static final LaysBagItem mexican_chicken_tomatp_sauce_lays_bag;
    public static final LaysBagItem fresh_mustard_scent_lays_bag;
    public static final LaysBagItem spicy_crayfish_lays_bag;
    public static final LaysBagItem golden_fried_crayfish_lays_bag;
    public static final LaysBagItem eel_lays_bag;
    public static final LaysBagItem fragrant_spicy_hot_pot_lays_bag;
    public static final LaysBagItem rockfired_sea_salt_lays_bag;
    public static final LaysBagItem vine_pepper_bobo_chicken_lays_bag;
    public static final LaysBagItem crispy_grilled_fish_lays_bag;
    public static final LaysBagItem garlic_oysters_lays_bag;
    public static final LaysBagItem cumin_lamb_skewers_lays_bag;
    public static final LaysBagItem sichuan_spicy_chicken_lays_bag;
    public static final LaysBagItem laotan_pickled_cabbage_lays_bag;
    public static final LaysCannedItem fresh_tomato_flavor_canned;
    public static final LaysCannedItem emerald_cucumber_canned;
    static {
        items.put("potato_chip", potato_chip = new PotatoChipsItem(new Item.Properties(), 1, 0.2F));
        items.put("wave_potato_chip", wave_potato_chip = new WavePotatoChipsItem(new Item.Properties(), 1, 0.1F));
        items.put("american_original_flavor_classic_lays_bag", american_original_flavor_classic_lays_bag = bags());
        items.put("cucumber_lays_bag", cucumber_lays_bag = bags());
        items.put("lime_lays_bag", lime_lays_bag = bags());
        items.put("texas_bbq_lays_bag", texas_bbq_lays_bag = bags());
        items.put("italian_red_stew_lays_bag", italian_red_stew_lays_bag = bags());
        items.put("fresh_cheery_tomato_lays_bag", fresh_cheery_tomato_lays_bag = bags());
        items.put("seaweed_lays_bag", seaweed_lays_bag = bags());
        items.put("spicy_hotpot_lays_bag", spicy_hotpot_lays_bag = bags());
        items.put("black_pepper_lays_bag", black_pepper_lays_bag = bags());
        items.put("mexican_chicken_tomatp_sauce_lays_bag", mexican_chicken_tomatp_sauce_lays_bag = bags());
        items.put("fresh_mustard_scent_lays_bag", fresh_mustard_scent_lays_bag = bags());
        items.put("spicy_crayfish_lays_bag", spicy_crayfish_lays_bag = bags());
        items.put("golden_fried_crayfish_lays_bag", golden_fried_crayfish_lays_bag = bags());
        items.put("eel_lays_bag", eel_lays_bag = bags());
        items.put("fragrant_spicy_hot_pot_lays_bag", fragrant_spicy_hot_pot_lays_bag = bags());
        items.put("rockfired_sea_salt_lays_bag", rockfired_sea_salt_lays_bag = bags());
        items.put("vine_pepper_bobo_chicken_lays_bag", vine_pepper_bobo_chicken_lays_bag = bags());
        items.put("crispy_grilled_fish_lays_bag", crispy_grilled_fish_lays_bag = bags());
        items.put("garlic_oysters_lays_bag", garlic_oysters_lays_bag = bags());
        items.put("cumin_lamb_skewers_lays_bag", cumin_lamb_skewers_lays_bag = bags());
        items.put("sichuan_spicy_chicken_lays_bag", sichuan_spicy_chicken_lays_bag = bags());
        items.put("laotan_pickled_cabbage_lays_bag", laotan_pickled_cabbage_lays_bag = bags());

        items.put("loyal_original_flavor_lays_canned", loyal_original_flavor_lays_canned = canned());
        items.put("emerald_cucumber_canned", emerald_cucumber_canned = canned());
        items.put("fresh_tomato_flavor_canned", fresh_tomato_flavor_canned = canned());

    }

    @NotNull
    private static LaysCannedItem canned() {
        return new LaysCannedItem();
    }

    @NotNull
    private static LaysBagItem bags() {
        return new LaysBagItem();
    }

    @NotNull
    private static LaysWoveItem wove() {
        return new LaysWoveItem();
    }

    public static void registry() {}
}
