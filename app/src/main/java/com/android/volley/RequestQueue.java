package com.android.volley;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lh on 2017/9/1.
 */

public class RequestQueue {

    private final Network mNetwork;

    private final ResponseDelivery mDelivery;

    private final PriorityBlockingQueue<Request<?>> mCacheQueue =
            new PriorityBlockingQueue<Request<?>>();

    private final PriorityBlockingQueue<Request<?>> mNetworkQueue =
            new PriorityBlockingQueue<Request<?>>();

    private final Cache mCache;

    private AtomicInteger mSequenceGenerator = new AtomicInteger();

    public RequestQueue(Cache cache, Network network) {
        this(cache, network, new ExecutorDelivery(new Handler(Looper.getMainLooper())));
    }

    public RequestQueue(Cache cache, Network network, ResponseDelivery delivery) {
        mCache = cache;
        mNetwork = network;
        mDelivery = delivery;
    }

    public void start() {

        stop();

        CacheDispatcher cacheDispatcher = new CacheDispatcher(mCacheQueue, mNetworkQueue, mCache, mDelivery);
        cacheDispatcher.start();

        NetworkDispatcher networkDispatcher = new NetworkDispatcher(mNetworkQueue, mNetwork, mCache, mDelivery);
        networkDispatcher.start();

    }

    private void stop() {

    }

    public <T> Request<T> add(Request<T> request) {
        request.setSequence(getSequenceNumber());
        mCacheQueue.add(request);
        return request;
    }

    public int getSequenceNumber() {
        return mSequenceGenerator.incrementAndGet();
    }

}
