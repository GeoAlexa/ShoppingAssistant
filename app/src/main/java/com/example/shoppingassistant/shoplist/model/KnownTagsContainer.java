package com.example.shoppingassistant.shoplist.model;

import java.util.ArrayList;
import java.util.List;

public class KnownTagsContainer {

    private static final String KNOWN_TAGS_KEY = "known_tags";

    List<String> knownTags;

    public KnownTagsContainer() {
        knownTags = new ArrayList<String>();
    }

    public String getTag(int index) {
        return knownTags.get(index);
    }

    public KnownTagsContainer addTag(String tag) {
        knownTags.add(tag);
        return  this;
    }

    public int length() {
        return knownTags.size();
    }
}
