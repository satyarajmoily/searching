package com.satyaraj.app.workex;

import com.satyaraj.app.workex.pojo.Response;
import com.satyaraj.app.workex.pojo.Work;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiCall {

    @GET
    Single<ArrayList<Work>> getList(@Url String url);
}
