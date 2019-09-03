package com.example.Retrofit;

import com.example.StudentsViewHolder.BaseNewsModels;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    @GET("everything")
    Call<BaseNewsModels> getBaseNewsModel(@Query("q") String q, @Query("from") String from,
                                          @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);
}