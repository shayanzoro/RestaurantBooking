package com.shayan.booking.viewmodel;


import com.shayan.booking.model.rest.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shayan on 10/3/2016.
 */
public class HotelsViewModel implements ViewModel {

    private DataListener dataListener;

    public HotelsViewModel(DataListener dataListener) {
        this.dataListener = dataListener;
        getHotels();
    }

    private void getHotels() {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4,"https://a2.cdn-hotels.com/landing/images/2ddeb5d1-8fa5-4d55-b78d-62e5b0d208bf_20130621092935.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square", 4, "https://exp.cdn-hotels.com/hotels/1000000/150000/140600/140596/140596_275_z.jpg"));
        hotels.add(new Hotel("artotel Amsterdam", 4, "http://media-cdn.tripadvisor.com/media/photo-s/02/39/a9/e9/warwick-seattle-hotel.jpg"));
        hotels.add(new Hotel("RemBrandt Square Amsterdam", 4, "https://a2.cdn-hotels.com/landing/images/0004d096-68a2-4a59-8ea1-e83dbdfca218_20130507152627.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Square Amsterdam", 4, "http://www.rwsentosa.com/portals/0/rws%20revamp/hotels/hard%20rock%20hotel/Gallery/Enlarge/HRH01.jpg"));
        hotels.add(new Hotel("Hotel - Rem-Brandt Amsterdam", 4, "https://media-cdn.tripadvisor.com/media/photo-s/03/0c/2e/fe/hotel-ibis-hermosillo.jpg"));
        hotels.add(new Hotel("Hotel Brandt Square", 4, "http://www.hotelcoandi.ro/static/galleries/hotel/l/hotel.jpg"));
        hotels.add(new Hotel("Hotel Sebastians", 4, "http://doubletree3.hilton.com/resources/media/dt/DCAAEDT/en_US/img/shared/full_page_image_gallery/main/DT_exteriordusk_2_677x380_FitToBoxSmallDimension_Center.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 5, "https://media-cdn.tripadvisor.com/media/photo-o/02/1d/cf/18/hotel-exterior.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "https://a2.cdn-hotels.com/landing/images/2326e818-8707-451f-b8c7-d3ecfbf13dc9_20130319151450.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 3, "http://www.synthesio.com/wp-content/uploads/2013/08/HOTEL_2323685b.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "http://bristol.zaccherahotels.com/files/2012/07/Bristol_Esterno_0063_Sito.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "http://provenancehotels.com/assets/content/preston_exterior_013.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "http://ludaytravel.com/images/hotels/urlet65sdf.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "https://media-cdn.tripadvisor.com/media/photo-s/09/7d/1a/7a/hotel-exterior-looking.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "http://www.hotelurso.com/content/uploads/main/galleries/100/thumbs/930x585xC/hotel_urso_madrid_comun_3_1414600222.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "https://media-cdn.tripadvisor.com/media/photo-s/02/26/34/83/lemon-tree-hotel-ahmedabad.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "https://upload.wikimedia.org/wikipedia/commons/6/61/TremontHouse_ca1830s_byJamesBennett_Boston_SimonsUPNE.png"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "https://media-cdn.tripadvisor.com/media/photo-s/07/b5/a5/4d/loews-hollywood-hotel.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "https://media-cdn.tripadvisor.com/media/photo-o/05/f6/81/14/chigdem-hotel.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "https://media-cdn.tripadvisor.com/media/photo-s/0b/54/b1/c0/exterior.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "http://www.antalyahotels.com/upload/1241525862_Miracle_Main.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "https://media-cdn.tripadvisor.com/media/photo-s/04/b9/60/96/naumi-hotel.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "http://arthotelgranparadiso.com/wp-content/uploads/2015/06/img-03.jpg"));
        hotels.add(new Hotel("Hampshire Hotel - Rem-Brandt Square Amsterdam", 4, "http://booking.ir/media/catalog/product/cache/7/image/9df78eab33525d08d6e5fb8d27136e95/0/0/002.jpg"));
        dataListener.onDataReceived(hotels);
    }

    @Override
    public void onDestroy() {
        dataListener = null;
    }

    public interface DataListener{
        void onDataReceived(List<Hotel> hotels);
    }
}
