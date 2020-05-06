package com.example.shoppingassistant.shoplist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class KnownTagsContainer implements Serializable {

    List<String> knownTags;

    public KnownTagsContainer() {
        knownTags = new ArrayList<String>();
    }



}
