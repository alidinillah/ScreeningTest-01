package com.example.screeningtest01;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("596dec7f0f000023032b8017")
    Call<List<Guest>> getGuests();
}
