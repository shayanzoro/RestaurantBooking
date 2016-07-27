package com.shayan.booking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shayan.booking.R;
import com.shayan.booking.model.rest.Customer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Shayan on 7/9/16.
 */
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Customer> items;

    public CustomerAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Customer item = getItem(position);
        holder.customerName.setText(item.getFullName());
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    public void updateItems(List<Customer> customers) {
        items = customers;
        notifyDataSetChanged();
    }

    public Customer getItem(int position) {
        return items.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_customer_name)
        TextView customerName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
