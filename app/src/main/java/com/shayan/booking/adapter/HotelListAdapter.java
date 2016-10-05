package com.shayan.booking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shayan.booking.R;
import com.shayan.booking.model.rest.Hotel;
import com.shayan.booking.view.widget.AsyncImageView;
import com.shayan.booking.view.widget.HotelTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shayan on 9/12/16.
 */
public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.ViewHolder> {

    List<Hotel> items = new ArrayList<>();
    private LayoutInflater inflater;

    public HotelListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_hotel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hotel item = items.get(position);

        holder.imageView.loadImage(item.getImageUrl());
        holder.titleView.setHotel(item);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void addItems(List<Hotel> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AsyncImageView imageView;
        HotelTitleView titleView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (AsyncImageView) itemView.findViewById(R.id.hotel_image);
            titleView = (HotelTitleView) itemView.findViewById(R.id.hotel_title);

        }
    }
}
