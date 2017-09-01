package com.android.volley;

import android.content.Context;

/**
 * Created by lh on 2017/9/1.
 */

public class Volley {

    public static RequestQueue newRequestQueue(Context context) {
        HttpStack stack = new HurlStack();
        Network network = new BasicNetwork(stack);
        RequestQueue queue = new RequestQueue(network);
        queue.start();
        return queue;
    }

}
