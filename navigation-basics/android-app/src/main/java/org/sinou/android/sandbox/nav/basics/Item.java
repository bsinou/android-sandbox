package org.sinou.android.sandbox.nav.basics;

import java.util.ArrayList;

public class Item {

    String title;
    String description;
    int timeStamp;
    int size;
    String localUri;

    Item(String title) {
        this(title, "", 0, 0, "");
    }

    Item(String title, String description, int timeStamp, int size, String localUri) {
        this.title = title;
        this.description = description;
        this.timeStamp = timeStamp;
        this.size = size;
        this.localUri = localUri;
    }

    public static Item[] getItems(Item parent) {
        ArrayList<Item> mItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Item curr = new Item(parent.title + "-" + i);
            mItems.add(curr);
        }
        return mItems.toArray(new Item[50]);
    }
}
