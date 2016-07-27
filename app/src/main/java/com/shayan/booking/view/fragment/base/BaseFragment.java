package com.shayan.booking.view.fragment.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.FragmentEvent;

import rx.subjects.BehaviorSubject;

/**
 * Created by Shayan on 7/26/2016.
 */
public class BaseFragment extends Fragment {

    /**
     * Subject used for handling life cycles of rx subscriptions
     */
    protected final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        lifecycleSubject.onNext(FragmentEvent.CREATE);
        super.onCreate(savedInstanceState);
    }

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @CallSuper
    @Override
    public void onStart() {
        lifecycleSubject.onNext(FragmentEvent.START);
        super.onStart();
    }

    @CallSuper
    @Override
    public void onResume() {
        lifecycleSubject.onNext(FragmentEvent.RESUME);
        super.onResume();
    }

    @CallSuper
    @Override
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
    }

    @CallSuper
    @Override
    public void onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
    }
}
