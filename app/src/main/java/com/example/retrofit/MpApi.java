package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MpApi {
    @GET("posts")
    Call<List<Model>> getData();

    @GET("branches")
    Call<List<Branches>> getBranches();
}
