package com.android.volley;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by lh on 2017/9/1.
 */

public class NetworkDispatcher extends Thread {

    private final BlockingQueue<Request<?>> mQueue;
    private final Network mNetwork;
    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private volatile boolean mQuit = false;

    public NetworkDispatcher(BlockingQueue<Request<?>> queue, Network network,Cache cache,
                             ResponseDelivery delivery) {
        mQueue = queue;
        mNetwork = network;
        mCache = cache;
        mDelivery = delivery;
    }

    @Override
    public void run() {
        Request<?> request;
        while (true) {
            request = null;
            try {
                request = mQueue.take();
            } catch (InterruptedException e) {
                if (mQuit) {
                    return;
                }
                continue;
            }

            try {
                NetworkResponse networkResponse = mNetwork.performRequest(request);
                Response<?> response = request.parseNetworkResponse(networkResponse);

                mCache.put(request.getCacheKey(), response.cacheEntry);

                mDelivery.postResponse(request, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void quit() {
        mQuit = true;
        interrupt();
    }
}
