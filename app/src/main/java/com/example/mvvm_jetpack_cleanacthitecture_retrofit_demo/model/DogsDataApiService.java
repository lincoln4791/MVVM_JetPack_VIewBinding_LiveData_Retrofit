package com.example.mvvm_jetpack_cleanacthitecture_retrofit_demo.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface DogsDataApiService {
    @GET("DevTides/DogsApi/master/dogs.json")
    Call<List<Model_MainActivity>> getData();

}
