package com.shayan.booking.view.widget;

import android.content.Context;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.TextView;

import com.shayan.booking.R;
import com.shayan.booking.model.rest.Hotel;
import com.shayan.booking.view.RateSpan;

import lombok.Setter;

/**
 * Created by Shayan on 9/21/2016.
 */
public class HotelTitleView extends TextView {

    @Setter
    private Hotel hotel;

    public HotelTitleView(Context context) {
        super(context);
    }

    public HotelTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setHotel(Hotel hotel) {
        if (!hotel.isApartment()) {
            String star = getResources().getString(R.string.icon_star);
            String thumbsUp = getResources().getString(R.string.icon_thumbs_up);
            String rateString = new String(new char[hotel.getRate()]).replace("\0", star).concat(thumbsUp);
            String text = hotel.getTitle().concat(" ").concat(rateString);

            SpannableString span = new SpannableString(text);
            span.setSpan(new RateSpan(getContext()), text.length() - rateString.length(), text.length(), 0);
            setText(span);
        } else {
            setText(hotel.getTitle());
        }
    }
}
