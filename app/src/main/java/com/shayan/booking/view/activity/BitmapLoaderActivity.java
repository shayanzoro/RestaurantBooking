package com.shayan.booking.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shayan.booking.R;
import com.shayan.booking.databinding.ActivityLoaderBinding;
import com.shayan.booking.view.activity.base.BaseActivity;
import com.shayan.booking.viewmodel.BitmapLoaderViewModel;

/**
 * Created by Shayan on 9/7/2016.
 */
public class BitmapLoaderActivity extends BaseActivity implements BitmapLoaderViewModel.DataListener{

    private BitmapLoaderViewModel viewModel;
    private ActivityLoaderBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loader);
        viewModel = new BitmapLoaderViewModel(getApplicationContext(), getSupportLoaderManager(), this);
        binding.setViewModel(viewModel);
    }

    @Override
    public void showImage(Bitmap bitmap) {
        binding.singleImage.setImageBitmap(bitmap);
    }
}
