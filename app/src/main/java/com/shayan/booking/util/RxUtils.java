package com.shayan.booking.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

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

    /**
     * Creates a subject that emits text change events for the current TextView
     */
    public static Observable<String> subscribeTextWatcher(TextView view) {
        final PublishSubject<String> subject = PublishSubject.create();
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                subject.onNext(editable.toString());
            }
        });
        return subject;
    }
}
