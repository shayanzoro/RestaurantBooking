package com.shayan.booking.viewmodel;

import android.content.Context;

import com.shayan.booking.application.App;
import com.shayan.booking.db.DataBaseManager;
import com.shayan.booking.model.db.TableMap;
import com.shayan.booking.rest.ServiceHelper;
import com.shayan.booking.util.GeneralUtils;
import com.trello.rxlifecycle.FragmentEvent;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shayan on 7/27/2016.
 */
public class TablesViewModel implements ViewModel {

    @Inject
    ServiceHelper serviceHelper;
    @Inject
    DataBaseManager dataBaseManager;

    private Context context;
    private DataListener dataListener;
    private long customerId;

    private Observable<FragmentEvent> lifecycleObservable;

    public TablesViewModel(Context context, DataListener dataListener, long customerId, Observable<FragmentEvent> lifecycleObservable) {
        this.context = context;
        this.dataListener = dataListener;
        this.customerId = customerId;
        this.lifecycleObservable = lifecycleObservable;

        App.get(context).getServiceComponent().inject(this);

        dataListener.showProgress();
        getData();
    }

    private void getData() {
        if (GeneralUtils.isOnline(context)) {
            fetchTableMapFromServer();
        } else if (dataBaseManager.isTableMapSaved(customerId)) {
            getCachedTableMap();
        } else {
            //device is offline, this table map hasn't been saved before
            dataListener.onNoConnection();
            dataListener.hideProgress();
        }
    }

    private void onDataReady(TableMap tableMap) {
        dataListener.onDataChanged(tableMap);
        dataListener.hideProgress();
    }

    private void fetchTableMapFromServer() {
        serviceHelper.getTableMap(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tables -> {
                    TableMap tableMap;

                    if (dataBaseManager.isTableMapSaved(customerId)) {
                        dataBaseManager.updateTableMap(customerId, tables);
                        tableMap = dataBaseManager.getTableMap(customerId);
                    } else {
                        tableMap = new TableMap(customerId, tables);
                        dataBaseManager.insertTableMap(tableMap);
                    }

                    onDataReady(tableMap);
                });
    }

    private void getCachedTableMap() {
        onDataReady(dataBaseManager.getTableMap(customerId));
    }

    public void bookTable(int tablePosition) {
        dataBaseManager.updateBookedTable(customerId, tablePosition);
    }

    public void clearBookedTable() {
        dataBaseManager.clearBookedTable(customerId);
    }

    @Override
    public void onDestroy() {
        context = null;
        dataListener = null;
    }

    public interface DataListener{

        void onDataChanged(TableMap tableMap);

        void hideProgress();

        void showProgress();

        void onNoConnection();

    }
}
