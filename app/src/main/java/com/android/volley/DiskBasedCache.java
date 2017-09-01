package com.android.volley;

import android.util.LruCache;

import java.io.File;

/**
 * Created by lh on 2017/9/1.
 */

public class DiskBasedCache implements Cache {

    private static final int DEFAULT_DISK_USAGE_BYTES = 5 * 1024 * 1024;

    private final File mRootDirectory;

    private final int mMaxCacheSizeInBytes;

    private LruCache<String, String> mMemoryCache;

    public DiskBasedCache(File rootDirectory) {
        this(rootDirectory, DEFAULT_DISK_USAGE_BYTES);
    }

    public DiskBasedCache(File rootDirectory, int maxCacheSizeInBytes) {
        mRootDirectory = rootDirectory;
        mMaxCacheSizeInBytes = maxCacheSizeInBytes;

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 2;
        mMemoryCache = new LruCache<String, String>(cacheSize) {
            @Override
            protected int sizeOf(String key, String s) {
                return s.length();
            }
        };
    }

    @Override
    public Entry get(String key) {
        String s = mMemoryCache.get(key);
        if (s != null) {
            Entry entry = new Entry();
            entry.data = s.getBytes();
            return entry;
        } else {
            return null;
        }
    }

    @Override
    public void put(String key, Entry entry) {
        mMemoryCache.put(key, new String(entry.data));
    }

}
