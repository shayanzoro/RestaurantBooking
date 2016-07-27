
package com.shayan.booking.rest;

import com.shayan.booking.application.Config;
import com.shayan.booking.model.rest.Customer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Shayan.
 */
@NoArgsConstructor
public class ServiceHelper {
    private final int CONNECT_TIMEOUT = 6 * 1000;
    private final int READ_TIMEOUT = 8 * 1000;

    private IService service;

    public ServiceHelper(Config config) {
        service = createAdapter(config.getApiBaseUrl()).create(IService.class);
    }

    private Retrofit createAdapter(String endPoint) {
        return new Retrofit.Builder()
                .baseUrl(endPoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createClient())
                .build();
    }

    private OkHttpClient createClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    public Observable<List<Customer>> getCustomers() {
        return service.getCustomers();
    }

    public Observable<boolean[]> getTableMap(long customerId) {
        return service.getTableMap();
    }
}


