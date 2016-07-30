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
import com.shayan.booking.databinding.FragmentCustomerBinding;
import com.shayan.booking.event.ActivityTitleChangeEvent;
import com.shayan.booking.event.TableMapFragmentShowEvent;
import com.shayan.booking.helper.recyclerview.RecyclerItemClickListener;
import com.shayan.booking.model.rest.Customer;
import com.shayan.booking.view.fragment.base.BaseFragment;
import com.shayan.booking.viewmodel.CustomerViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Shayan on 7/26/2016.
 */
@NoArgsConstructor
public class CustomerFragment extends BaseFragment implements CustomerViewModel.DataListener {

    private FragmentCustomerBinding binding;

    @Getter
    private CustomerAdapter customerAdapter;
    private CustomerViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer, container, false);
        initRecyclerView();

        viewModel = new CustomerViewModel(this, getActivity(), lifecycleSubject);
        viewModel.subscribeTextWatcher(binding.editTextCustomerName);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = binding.recyclerviewCustomerList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), (view, position) -> {
                    Customer customer = customerAdapter.getItem(position);
                    EventBus.getDefault().post(new TableMapFragmentShowEvent(customer));
                })
        );

        customerAdapter = new CustomerAdapter(getActivity());
        recyclerView.setAdapter(customerAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        //change title of the parent activity
        EventBus.getDefault().post(new ActivityTitleChangeEvent(R.string.customers));
    }

    @Override
    public void onDataChanged(List<Customer> customers) {
        customerAdapter.updateItems(customers);
    }

    @Override
    public void hideProgress() {
        binding.progressFullscreen.stop();
    }

    @Override
    public void showProgress() {
        binding.progressFullscreen.start();
    }

    @Override
    public void clearSearch() {
        binding.editTextCustomerName.setText("");
    }

    @Override
    public void onNoConnection() {
        binding.textNoConnection.setVisibility(View.VISIBLE);
    }

    @Override
    public void connected() {
        binding.textNoConnection.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.onDestroy();
    }
}
