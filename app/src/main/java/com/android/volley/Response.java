package com.android.volley;

/**
 * Created by lh on 2017/9/1.
 */

public class Response<T> {

    public final Cache.Entry cacheEntry;

    public interface Listener<T> {
        void onResponse(T response);
    }

    public final T result;

    private Response(T result, Cache.Entry cacheEntry) {
        this.result = result;
        this.cacheEntry = cacheEntry;
    }

    public static <T> Response<T> success(T result, Cache.Entry cacheEntry) {
        return new Response<T>(result,cacheEntry);
    }


}
