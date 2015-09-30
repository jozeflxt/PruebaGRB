package com.grability.myapplication.restAPI;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by Carlos  Muñoz on 23/09/2015.
 */
public interface EndPointInterface {
    @GET("/us/rss/topfreeapplications/limit={limit}/json")
    void getCategories(@Path("limit") Integer limit, Callback<Void> cb);

}
