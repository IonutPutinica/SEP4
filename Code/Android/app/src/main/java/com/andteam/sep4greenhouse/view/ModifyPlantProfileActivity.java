package com.andteam.sep4greenhouse.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;


import com.andteam.sep4greenhouse.R;
import com.andteam.sep4greenhouse.model.PlantDTO;
import com.andteam.sep4greenhouse.model.PlantProfile;
import com.andteam.sep4greenhouse.viewmodel.ViewPlantsViewModel;

import static com.andteam.sep4greenhouse.view.ViewPlantsFragment.KEY_PLANT_PROFILE;


public class ModifyPlantProfileActivity extends AppCompatActivity {

    private ImageButton editPlant;
    private ImageButton deletePlant;
    private EditText profileName;
    private EditText waterAmount;
    private EditText waterInterval;
    private Button saveInput;
    ScrollView scrollmodifplant;
    private ViewPlantsViewModel viewModel;
    private PlantProfile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_modify_plant_profile);
        viewModel = ViewModelProviders.of(this).get(ViewPlantsViewModel.class);

        scrollmodifplant= (ScrollView)findViewById(R.id.scrollmodifplant);

        profileName = findViewById(R.id.profile_name);
        waterAmount = findViewById(R.id.water_amount);
        waterInterval = findViewById(R.id.watering_interval);

        saveInput = findViewById(R.id.savebutton);
        saveInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        profile = (PlantProfile) getIntent().getExtras().getSerializable(KEY_PLANT_PROFILE);
        profileName.setText(profile.getName());
        waterAmount.setText(Double.toString(profile.getWater()));
        waterInterval.setText(Integer.toString(profile.getwInterval()));
    }

    private void saveChanges() {
        String name = profileName.getText().toString();
        double water = Double.parseDouble(waterAmount.getText().toString());
        int wInterval = Integer.parseInt(waterInterval.getText().toString());
        PlantProfile profile = new PlantProfile( this.profile.getProfileId(), name, water, wInterval,  this.profile.getPlant());
        viewModel.editProfile(profile);
    }

}