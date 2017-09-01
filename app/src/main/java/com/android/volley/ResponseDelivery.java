package com.android.volley;

/**
 * Created by lh on 2017/9/1.
 */

public interface ResponseDelivery {

    void postResponse(Request<?> request, Response<?> response);

}
