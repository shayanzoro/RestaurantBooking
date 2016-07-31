package com.shayan.booking.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Shayan on 7/10/2016.
 */
public class RxUtils {

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
