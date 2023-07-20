package com.example.facilityexample.service;

import com.example.facilityexample.model.Response;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FacilityService {

    @GET("db")
    Observable<Response> getLists1();

    @GET("db")
    Call<Response> getLists();

}
