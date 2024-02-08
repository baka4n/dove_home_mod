package io.github.dovehome.creative.generation.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BlazeBurner {
    public ItemTags itemTags;
    public ArrayList<String> fuelTypes;
    public ArrayList<String> flameTypes;

    public BlazeBurner addFuel(String name) {
        if (fuelTypes == null) {
            fuelTypes = new ArrayList<>();
        }
        fuelTypes.add(name.toUpperCase(Locale.ROOT));
        return this;
    }
    public BlazeBurner addFlame(String name) {
        if (flameTypes == null) {
            flameTypes = new ArrayList<>();
        }
        flameTypes.add(name.toUpperCase(Locale.ROOT));
        return this;
    }

    public BlazeBurner addItemTag(String name, String path) {
        if (itemTags == null) {
            itemTags = new ItemTags();
        }
        itemTags.tags.put(name, path);
        return this;
    }


    public BlazeBurner addItemTag(String name) {
        if (itemTags == null) {
            itemTags = new ItemTags();
        }
        itemTags.tags.put(name, "item/" + name.toLowerCase(Locale.ROOT));
        return this;
    }


    public static class ItemTags {
        public Map<String, String> tags = new HashMap<>();
    }

}
