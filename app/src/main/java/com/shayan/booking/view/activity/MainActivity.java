package com.shayan.booking.view.activity;

import android.os.Bundle;

import com.shayan.booking.R;
import com.shayan.booking.event.TitleChangeEvent;
import com.shayan.booking.view.activity.base.BaseActivity;
import com.shayan.booking.view.fragment.CustomerFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * Created by Shayan on 6/3/2016.
 */
public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        showCustomerFragment();
    }

    private void showCustomerFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new CustomerFragment())
                .commit();
    }

    @Subscribe
    public void onTitleChangeEvent(TitleChangeEvent event) {
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
