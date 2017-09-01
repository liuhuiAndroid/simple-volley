package com.android.volley;

import java.io.IOException;

/**
 * Created by lh on 2017/9/1.
 */

public class BasicNetwork implements Network{

    protected final HttpStack mHttpStack;

    public BasicNetwork(HttpStack httpStack) {
        mHttpStack = httpStack;
    }


    @Override
    public NetworkResponse performRequest(Request<?> request) throws IOException {
        NetworkResponse networkResponse = mHttpStack.performRequest(request);
        return networkResponse;
    }

}
