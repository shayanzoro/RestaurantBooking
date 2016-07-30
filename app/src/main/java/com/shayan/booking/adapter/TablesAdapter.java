package com.shayan.booking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shayan.booking.R;
import com.shayan.booking.model.db.TableMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Shayan on 7/27/2016.
 */
public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private TableMap tableMap;


    public TablesAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_table, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!isTableAvailable(position)) {
            holder.reservedText.setVisibility(View.VISIBLE);
            holder.yourTableText.setVisibility(View.INVISIBLE);

        } else if (isTableBookedByUser(position)) {
            holder.reservedText.setVisibility(View.INVISIBLE);
            holder.yourTableText.setVisibility(View.VISIBLE);

        } else {
            holder.reservedText.setVisibility(View.INVISIBLE);
            holder.yourTableText.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return tableMap == null ? 0 : tableMap.getTableMap().length;
    }

    public Boolean getItem(int position) {
        return tableMap.getTableMap()[position];
    }

    public void updateItems(TableMap tableMap) {
        this.tableMap = tableMap;
        notifyDataSetChanged();
    }

    public void bookTable(int position) {
        tableMap.setBookedTable(position);
        notifyDataSetChanged();
    }

    public void clearBookTable() {
        tableMap.clearBookedTable();
        notifyDataSetChanged();
    }

    public boolean isTableBookedByUser(int position) {
        return tableMap.getBookedTable() == position;
    }

    public boolean isTableAvailable(int position) {
        return tableMap.getTableMap()[position];
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_table_root)
        public View root;
        @Bind(R.id.item_table_reserved)
        public TextView reservedText;
        @Bind(R.id.item_table_your_table)
        TextView yourTableText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
