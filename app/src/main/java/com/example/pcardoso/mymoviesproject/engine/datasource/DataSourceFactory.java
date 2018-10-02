package com.example.pcardoso.mymoviesproject.engine.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

public class DataSourceFactory<T> extends DataSource.Factory<Integer, T> {


    private T type;

    private MutableLiveData<T> liveDataSource;

    public DataSourceFactory(T type) {
        this.type = type;
        liveDataSource = new MutableLiveData<>();
    }
    @Override
    public DataSource<Integer, T> create() {
        liveDataSource.postValue(type);

        return (DataSource<Integer, T>) type;

        //return null;
    }
}
