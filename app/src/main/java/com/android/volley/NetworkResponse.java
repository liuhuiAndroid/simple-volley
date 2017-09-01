package com.android.volley;

/**
 * Created by lh on 2017/9/1.
 */

public class NetworkResponse {

    public final byte[] data;

    public NetworkResponse(byte[] data, int responseCode) {
        this.data = data;
    }
}
