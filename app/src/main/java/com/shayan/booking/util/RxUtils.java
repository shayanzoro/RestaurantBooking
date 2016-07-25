package com.shayan.booking.util;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shayan on 7/10/2016.
 */
public class RxUtils {

    public static void runDelayed(long milliseconds, Runnable runnable) {
        runDelayedAsObservable(milliseconds).subscribe(integer -> {
            runnable.run();
        });
    }

    public static Observable<Integer> runDelayedAsObservable(long milliseconds) {
        return Observable.just(1)
                .delaySubscription(milliseconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
