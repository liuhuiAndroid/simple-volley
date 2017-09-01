package com.android.volley;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by lh on 2017/9/1.
 */

public class CacheDispatcher extends Thread {

    private final BlockingQueue<Request<?>> mCacheQueue;

    private final BlockingQueue<Request<?>> mNetworkQueue;

    private final Cache mCache;

    private final ResponseDelivery mDelivery;

    private volatile boolean mQuit = false;

    public CacheDispatcher(PriorityBlockingQueue<Request<?>> cacheQueue,
                           PriorityBlockingQueue<Request<?>> networkQueue,
                           Cache cache, ResponseDelivery delivery) {
        mCacheQueue = cacheQueue;
        mNetworkQueue = networkQueue;
        mCache = cache;
        mDelivery = delivery;
    }

    @Override
    public void run() {
        Request<?> request;
        while (true) {
            request = null;
            try {
                request = mCacheQueue.take();
            } catch (InterruptedException e) {
                if (mQuit) {
                    return;
                }
                continue;
            }
            try {
                Cache.Entry entry = mCache.get(request.getCacheKey());
                if (entry == null) {
                    mNetworkQueue.put(request);
                    continue;
                }

                Response<?> response = request.parseNetworkResponse(
                        new NetworkResponse(entry.data));
                mDelivery.postResponse(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void quit() {
        mQuit = true;
        interrupt();
    }

}
