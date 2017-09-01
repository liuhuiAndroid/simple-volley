package com.android.volley;

/**
 * Created by lh on 2017/9/1.
 */

public interface Cache {

    Entry get(String key);

    void put(String key, Entry entry);

    class Entry {
        public byte[] data;
    }
}
