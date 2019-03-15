package com.satyaraj.app.workex;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.satyaraj.app.workex.base.BaseRepository;
import com.satyaraj.app.workex.pojo.Response;
import com.satyaraj.app.workex.pojo.Work;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class MainRepository extends BaseRepository<MainPresenter> {

    private CompositeDisposable mCompositeDisposable;
    private ApiCall mApiCall;

    public MainRepository() {
        this.mCompositeDisposable = new CompositeDisposable();
        this.mApiCall = getApiCall();
    }

    public void makeRequest(){
        mCompositeDisposable.add(mApiCall.getList("https://us-central1-workex-swiggy-demo.cloudfunctions.net/helloWorld")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ArrayList<Work>>() {

                    @Override
                    public void onSuccess(ArrayList<Work> response) {
                        getActions().showList(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,  e.getMessage());
                    }
                })
        );
    }


    private Retrofit retrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient())
                .baseUrl("https://us-central1-workex-swiggy-demo.cloudfunctions.net")
                .build();
    }


    private OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }

    private Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    private ApiCall getApiCall() {
        return retrofit().create(ApiCall.class);
    }
}
