package com.android.volley;

import android.content.Context;

import java.io.File;

/**
 * Created by lh on 2017/9/1.
 */

public class Volley {

    private static final String DEFAULT_CACHE_DIR = "volley";

    public static RequestQueue newRequestQueue(Context context) {

        File cacheDir = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);

        HttpStack stack = new HurlStack();
        Network network = new BasicNetwork(stack);
        RequestQueue queue = new RequestQueue(new DiskBasedCache(cacheDir),network);
        queue.start();
        return queue;
    }

}
