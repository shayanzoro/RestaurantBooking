package com.shayan.booking.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shayan.booking.R;
import com.shayan.booking.model.rest.Customer;

import java.util.List;

/**
 * Created by Shayan on 7/9/16.
 */
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Customer> items;

    public CustomerAdapter(Context context, List<Customer> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Customer item = getItem(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Customer getItem(int position) {
        return items.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        @Bind(R.id.form_title)
//        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
        }
    }
}
