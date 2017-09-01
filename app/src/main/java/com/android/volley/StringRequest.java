package com.android.volley;

/**
 * Created by lh on 2017/9/1.
 */

public class StringRequest extends Request<String>{

    private Response.Listener<String> mListener;

    public StringRequest(String url, Response.Listener<String> listener) {
        this(Method.GET, url, listener);
    }

    public StringRequest(int method, String url, Response.Listener<String> listener) {
        super(method, url);
        mListener = listener;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed = new String(response.data);
        return Response.success(parsed);
    }

    @Override
    protected void deliverResponse(String response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }


}
