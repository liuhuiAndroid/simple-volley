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

    private final PriorityBlockingQueue<Request<?>> mNetworkQueue =
            new PriorityBlockingQueue<Request<?>>();

    private AtomicInteger mSequenceGenerator = new AtomicInteger();

    public RequestQueue(Network network) {
        this(network,new ExecutorDelivery(new Handler(Looper.getMainLooper())));
    }

    public RequestQueue(Network network, ResponseDelivery delivery) {
        mNetwork = network;
        mDelivery = delivery;
    }

    public void start() {

        stop();

        NetworkDispatcher networkDispatcher = new NetworkDispatcher(mNetworkQueue, mNetwork, mDelivery);
        networkDispatcher.start();

    }

    private void stop() {

    }

    public <T> Request<T> add(Request<T> request) {
        request.setSequence(getSequenceNumber());
        mNetworkQueue.add(request);
        return request;
    }

    public int getSequenceNumber() {
        return mSequenceGenerator.incrementAndGet();
    }

}
