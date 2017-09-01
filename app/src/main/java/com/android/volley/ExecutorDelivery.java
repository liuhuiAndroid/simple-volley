package com.android.volley;

import android.os.Handler;

import java.util.concurrent.Executor;

/**
 * Created by lh on 2017/9/1.
 */

public class ExecutorDelivery implements ResponseDelivery {

    private final Executor mResponsePoster;

    public ExecutorDelivery(final Handler handler) {
        mResponsePoster = new Executor() {
            @Override
            public void execute(Runnable command) {
                handler.post(command);
            }
        };
    }

    @Override
    public void postResponse(Request<?> request, Response<?> response) {
        mResponsePoster.execute(new ResponseDeliveryRunnable(request, response));
    }

    private class ResponseDeliveryRunnable implements Runnable{

        private final Request mRequest;
        private final Response mResponse;

        public ResponseDeliveryRunnable(Request<?> request, Response<?> response) {
            mRequest = request;
            mResponse = response;
        }

        @Override
        public void run() {
            mRequest.deliverResponse(mResponse.result);
        }
    }

}
