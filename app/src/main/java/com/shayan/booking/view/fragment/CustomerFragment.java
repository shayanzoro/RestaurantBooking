package com.shayan.booking.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shayan.booking.R;
import com.shayan.booking.adapter.CustomerAdapter;
import com.shayan.booking.adapter.RecyclerItemClickListener;
import com.shayan.booking.databinding.FragmentCustomerBinding;
import com.shayan.booking.event.TitleChangeEvent;
import com.shayan.booking.model.rest.Customer;
import com.shayan.booking.util.ShayanLogger;
import com.shayan.booking.view.fragment.base.BaseFragment;
import com.shayan.booking.viewmodel.CustomerViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import lombok.NoArgsConstructor;

/**
 * Created by Shayan on 7/26/2016.
 */
@NoArgsConstructor
public class CustomerFragment extends BaseFragment implements CustomerViewModel.DataListener {

    private FragmentCustomerBinding binding;
    private CustomerAdapter customerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer, container, false);
        initRecyclerView();

        CustomerViewModel viewModel = new CustomerViewModel(this, getActivity(), lifecycleSubject);
        viewModel.subscribeTextWatcher(binding.editTextCustomerName);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = binding.recyclerviewCustomerList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), (view, position) -> {
                    ShayanLogger.d("CustomerFragment", "item clicked " + position);
                })
        );

        customerAdapter = new CustomerAdapter(getActivity());
        recyclerView.setAdapter(customerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        //change title of the parent activity
        EventBus.getDefault().post(new TitleChangeEvent(R.string.customers));
    }

    @Override
    public void onDataChanged(List<Customer> customers) {
        customerAdapter.updateItems(customers);
    }

    @Override
    public void onNoConnection() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void clearSearch() {
        binding.editTextCustomerName.setText("");
    }
}
