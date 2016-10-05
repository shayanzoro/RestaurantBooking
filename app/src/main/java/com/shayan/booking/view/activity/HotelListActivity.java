package com.shayan.booking.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shayan.booking.R;
import com.shayan.booking.adapter.HotelListAdapter;
import com.shayan.booking.databinding.ActivityHotelListBinding;
import com.shayan.booking.model.rest.Hotel;
import com.shayan.booking.util.imageloader.PauseScrollListener;
import com.shayan.booking.viewmodel.HotelsViewModel;

import java.util.List;

/**
 * Created by Shayan on 9/12/16.
 */
public class HotelListActivity extends AppCompatActivity implements HotelsViewModel.DataListener{

    private ActivityHotelListBinding binding;
    private HotelsViewModel viewModel;
    private HotelListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hotel_list);
        initViews();

        viewModel = new HotelsViewModel(this);
        binding.setViewModel(viewModel);
    }

    private void initViews() {
        adapter = new HotelListAdapter(this);
        RecyclerView recyclerView = binding.hotelList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnScrollListener(new PauseScrollListener(this));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onDataReceived(List<Hotel> hotels) {
        adapter.addItems(hotels);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
