package com.shayan.booking.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.shayan.booking.R;
import com.shayan.booking.event.ActivityTitleChangeEvent;
import com.shayan.booking.view.activity.base.BaseActivity;
import com.shayan.booking.view.fragment.CustomerFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import lombok.Getter;


/**
 * Created by Shayan on 6/3/2016.
 */
public class MainActivity extends BaseActivity {

    @Getter
    private CustomerFragment customerFragment = new CustomerFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        addFragment(customerFragment);
    }

    public void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(fragment.getClass().getSimpleName())
                .replace(R.id.fragment_container, fragment)
                .commitAllowingStateLoss();
    }

    @Subscribe
    public void onActivityTitleChangeEvent(ActivityTitleChangeEvent event) {
        Object title = event.getTitle();
        if(title instanceof String)
            setToolbarTitle((String) title);
        else if(title instanceof Integer)
            setToolbarTitle((Integer) title);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
