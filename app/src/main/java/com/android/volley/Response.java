package com.android.volley;

/**
 * Created by lh on 2017/9/1.
 */

public class Response<T> {

    public interface Listener<T> {
        void onResponse(T response);
    }

    public final T result;

    private Response(T result) {
        this.result = result;
    }

    public static <T> Response<T> success(T result) {
        return new Response<T>(result);
    }


}
