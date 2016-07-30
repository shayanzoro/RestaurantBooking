package com.shayan.booking.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork;
import com.shayan.booking.application.App;
import com.shayan.booking.db.DataBaseManager;
import com.shayan.booking.model.rest.Customer;
import com.shayan.booking.rest.ServiceHelper;
import com.shayan.booking.util.GeneralUtils;
import com.shayan.booking.util.RxUtils;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.RxLifecycle;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shayan on 7/25/2016.
 */
public class CustomerViewModel implements ViewModel {

    @Inject
    ServiceHelper serviceHelper;
    @Inject
    DataBaseManager dataBaseManager;

    private Observable<FragmentEvent> lifecycleObservable;
    public ObservableInt cancelSearchVisibility;

    private DataListener dataListener;
    private Context context;
    private String searchText;

    public CustomerViewModel(DataListener dataListener, Context context, Observable<FragmentEvent> lifecycleObservable) {
        this.dataListener = dataListener;
        this.context = context;
        this.lifecycleObservable = lifecycleObservable;
        cancelSearchVisibility = new ObservableInt(View.GONE);

        App.get(context).getServiceComponent().inject(this);

        getData();
    }

    private void getData() {
        dataListener.showProgress();

        if (GeneralUtils.isOnline(context)) {
            fetchCustomersFromServer();
        } else if (dataBaseManager.getCustomersCount() > 0) {
            getCachedCustomers();
        } else {
            //device is offline, and it's the first time that app is fired
            dataListener.onNoConnection();
            dataListener.hideProgress();
            listenForConnection();
        }
    }

    private void onDataReady(List<Customer> customers) {
        dataListener.onDataChanged(customers);
        dataListener.hideProgress();
    }

    private void fetchCustomersFromServer() {
        serviceHelper.getCustomers()
                .compose(RxLifecycle.bindUntilFragmentEvent(lifecycleObservable, FragmentEvent.DESTROY_VIEW))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(customers -> {
                    onDataReady(customers);
                    dataBaseManager.insertOrUpdateCustomers(customers);
                });
    }

    private void getCachedCustomers() {
        onDataReady(dataBaseManager.getAllCustomers());
    }

    private void doSearch() {
        List<Customer> searchResults = dataBaseManager.getMatchedCustomers(searchText);
        dataListener.onDataChanged(searchResults);
        cancelSearchVisibility.set(View.VISIBLE);
    }

    private void cancelSearch() {
        dataListener.onDataChanged(dataBaseManager.getAllCustomers());
        cancelSearchVisibility.set(View.GONE);
    }

    public void subscribeTextWatcher(TextView textView) {
        RxUtils.subscribeTextWatcher(textView)
                .delaySubscription(500, TimeUnit.MILLISECONDS)
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(text -> {
                    searchText = text;
                    if (!TextUtils.isEmpty(searchText)) {
                        doSearch();
                    } else {
                        cancelSearch();
                    }

                }, throwable -> throwable.printStackTrace());
    }

    private void listenForConnection() {
        ReactiveNetwork.observeNetworkConnectivity(context)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycle.bindUntilFragmentEvent(lifecycleObservable, FragmentEvent.DESTROY_VIEW))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(connectivity -> {
                    if (connectivity.getState() == NetworkInfo.State.CONNECTED) {
                        dataListener.connected();
                        getData();
                    }

                }, Throwable::printStackTrace);
    }

    public void onClickCancel(View view) {
        dataListener.clearSearch();
        cancelSearch();
    }

    @Override
    public void onDestroy() {
        context = null;
        dataListener = null;
        searchText = null;
    }

    public interface DataListener {
        void onDataChanged(List<Customer> customers);

        void hideProgress();

        void showProgress();

        void clearSearch();

        void onNoConnection();

        void connected();
    }
}
