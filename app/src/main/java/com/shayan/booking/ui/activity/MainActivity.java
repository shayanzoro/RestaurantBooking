package com.shayan.booking.ui.activity;

import android.os.Bundle;

import com.shayan.booking.R;
import com.shayan.booking.application.Config;
import com.shayan.booking.application.App;
import com.shayan.booking.db.DataBaseManager;
import com.shayan.booking.rest.ServiceHelper;
import com.shayan.booking.ui.activity.base.BaseActivity;

import javax.inject.Inject;


/**
 * Created by Shayan on 6/3/2016.
 */
public class MainActivity extends BaseActivity {

    @Inject
    ServiceHelper serviceHelper;
    @Inject
    Config config;
    @Inject
    DataBaseManager dataBaseManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        App.get(this).getServiceComponent().inject(this);

    }

}
