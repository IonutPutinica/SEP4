package com.andteam.sep4greenhouse.network;

import android.util.Log;

import com.andteam.sep4greenhouse.model.PlantProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.andteam.sep4greenhouse.network.NetworkConfig.BASE_URL;

public class GetAllPlantsRequest implements Callback<List<PlantProfile>> {

    private AllPlantsCallback callback;

    public void start(AllPlantsCallback requestCallback) {
        this.callback = requestCallback;

        // Build Retrofit Connection
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Reference to RetrofitAPI class
        RetrofitAPI api = retrofit.create(RetrofitAPI.class);
        Call<List<PlantProfile>> call = api.getAllPlantProfiles();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<PlantProfile>> call, Response<List<PlantProfile>> response) {
        if (response.code() == 200) {
            callback.onReturn(response.body());
        } else {
            callback.onReturn(response.body());
        }
    }

    @Override
    public void onFailure(Call<List<PlantProfile>> call, Throwable t) {
        Log.d("Login failed", t.toString());
    }
}
