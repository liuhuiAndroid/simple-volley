package com.android.volley;

/**
 * Created by lh on 2017/9/1.
 */

public class HttpHeaderParser {

    public static Cache.Entry parseCacheHeaders(NetworkResponse response) {

        Cache.Entry entry = new Cache.Entry();
        entry.data = response.data;
        return entry;

    }

}
