package com.andteam.sep4greenhouse.network;

import android.util.Log;

import com.andteam.sep4greenhouse.model.PlantDTO;
import com.andteam.sep4greenhouse.model.PlantProfile;
import com.andteam.sep4greenhouse.viewmodel.VoidCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.andteam.sep4greenhouse.network.NetworkConfig.BASE_URL;

public class AddPlantRequest implements Callback<Void> {

    private VoidCallBack callback;

    public void start(PlantProfile plant, VoidCallBack requestCallback) {
        this.callback = requestCallback;

        // Build Retrofit Connection
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Reference to RetrofitAPI class
        RetrofitAPI api = retrofit.create(RetrofitAPI.class);
        Call<Void> call = api.addPlantProfile(plant);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.code() == 200) {
            callback.onReturn(true);
        } else {
            callback.onReturn(false);
        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        callback.onReturn(false);
        Log.d("Login failed", t.toString());
    }
}
