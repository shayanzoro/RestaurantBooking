package com.shayan.booking.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shayan.booking.R;
import com.shayan.booking.adapter.TablesAdapter;
import com.shayan.booking.databinding.FragmentTableMapBinding;
import com.shayan.booking.event.ActivityTitleChangeEvent;
import com.shayan.booking.helper.recyclerview.GridAutoFitLayoutManager;
import com.shayan.booking.helper.recyclerview.RecyclerItemClickListener;
import com.shayan.booking.model.db.TableMap;
import com.shayan.booking.model.rest.Customer;
import com.shayan.booking.view.fragment.base.BaseFragment;
import com.shayan.booking.viewmodel.TablesViewModel;

import org.greenrobot.eventbus.EventBus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Shayan on 7/27/2016.
 */
@NoArgsConstructor
public class TableMapFragment extends BaseFragment implements TablesViewModel.DataListener{

    private FragmentTableMapBinding binding;
    private TablesViewModel viewModel;

    @Setter
    private Customer customer;

    @Getter
    private TablesAdapter tablesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_table_map, container, false);
        initRecyclerView();

        viewModel = new TablesViewModel(getActivity(), this, customer.getId(), lifecycleSubject);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = binding.recyclerViewTables;
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setLayoutManager(new GridAutoFitLayoutManager(getActivity(), getResources().getDimensionPixelSize(R.dimen.table_grid_column_width)));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), (view, position) -> {

                    if (!tablesAdapter.isTableBookedByUser(position)) {
                        //the table is available
                        viewModel.bookTable(position);
                        tablesAdapter.bookTable(position);
                    } else {
                        viewModel.clearBookedTable();
                        tablesAdapter.clearBookTable();
                    }
                })
        );

        tablesAdapter = new TablesAdapter(getActivity());
        recyclerView.setAdapter(tablesAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        //change title of the parent activity
        EventBus.getDefault().post(new ActivityTitleChangeEvent(R.string.select_table_to_book));
    }

    @Override
    public void onDataChanged(TableMap tableMap) {
        tablesAdapter.updateItems(tableMap);
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
