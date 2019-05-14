package com.andteam.sep4greenhouse.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.widget.Toast;

import com.andteam.sep4greenhouse.model.PlantDTO;
import com.andteam.sep4greenhouse.model.PlantProfile;
import com.andteam.sep4greenhouse.network.AllPlantsCallback;
import com.andteam.sep4greenhouse.network.DeletePlantProfile;
import com.andteam.sep4greenhouse.network.GetAllPlantsRequest;
import com.andteam.sep4greenhouse.network.ModifyPlantProfile;

import java.util.LinkedList;
import java.util.List;

public class ViewPlantsViewModel extends AndroidViewModel implements AllPlantsCallback, VoidCallBack {

    private MutableLiveData<List<PlantProfile>> profilesLiveData;
    private List<PlantProfile> profiles;

    public ViewPlantsViewModel(Application application) {
        super(application);
        profiles = new LinkedList<>();
        PlantDTO plantDTO = new PlantDTO(1, "Plant name", 50, 500, 66.3, 99);
        PlantProfile profile = new PlantProfile(1, "Plant profile", 0.300, 3, plantDTO);
        profiles.add(profile);
        profilesLiveData = new MutableLiveData<>();
        profilesLiveData.setValue(profiles);
    }

    public LiveData<List<PlantProfile>> getProfiles() {
        new GetAllPlantsRequest().start(this);
        return profilesLiveData;
    }

    public void editProfile(PlantProfile profile) {
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getProfileId() == profile.getProfileId()) {
                profiles.set(i, profile);
            }
        }
        profilesLiveData.setValue(profiles);
        new ModifyPlantProfile().start(profile, this);
    }

    public void deletePlant(PlantProfile profile) {
        new DeletePlantProfile().start(profile, this);
    }

    @Override
    public void onReturn(List<PlantProfile> response) {
        profiles = response;
        profilesLiveData.setValue(response);
    }

    // Callback method for add/modify//delete request
    @Override
    public void onReturn(boolean success) {
        if (success)
            Toast.makeText(this.getApplication(), "Changes applied!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this.getApplication(), "Task failed successfully", Toast.LENGTH_LONG).show();
    }
}
