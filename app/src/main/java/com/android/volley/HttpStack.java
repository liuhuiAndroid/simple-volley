package com.android.volley;

import java.io.IOException;

/**
 * Created by lh on 2017/9/1.
 */

public interface HttpStack {

    NetworkResponse performRequest(Request<?> request) throws IOException;

}
