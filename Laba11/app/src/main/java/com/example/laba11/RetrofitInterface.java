package com.example.laba11;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("recipes2022.json")
    Call<List<JSONStructure>> fetchRecieps();
}
