package com.andteam.sep4greenhouse.network;

import com.andteam.sep4greenhouse.model.PlantDTO;
import com.andteam.sep4greenhouse.model.PlantProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitAPI {

    // 1
//    @GET("/plantprofile")
//    Call<PlantResponse> getPlantProfileData(@Path("pId") int id);

    // 2
    @GET("/plantprofile")
    Call<List<PlantProfile>> getAllPlantProfiles();

    // 3
    @POST("/plantprofile")
    Call<Void> addPlantProfile(@Body PlantProfile profile);

    // 4
    @DELETE("/plantprofile/{id}")
    Call<Void> deletePlantProfile(@Path("pId") int id);

    // 5
    @PUT("/plantprofile")
    Call<Void> modifyPlantProfile(@Body PlantProfile profile);

    // 6
    @POST("/water")
    Call<Void> waterPlant(@Path("pId") int id);

}
