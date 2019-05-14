package com.andteam.sep4greenhouse.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.andteam.sep4greenhouse.R;
import com.andteam.sep4greenhouse.model.PlantDTO;
import com.andteam.sep4greenhouse.model.PlantProfile;

import static com.andteam.sep4greenhouse.view.ViewPlantsFragment.KEY_PLANT_PROFILE;

public class ViewPlantProfileActivity extends AppCompatActivity {

    private TextView readOnlyPlantName;
    private TextView readOnlyCurrentTemperature;
    private TextView readOnlyHumidityLevel;
    private TextView readOnlyCO2Level;
    private TextView readOnlyLightLevel;
    Button waterPlantButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_plant_profile_activity);

        readOnlyPlantName = findViewById(R.id.read_only_plant_name_text_id);
        readOnlyCurrentTemperature = findViewById(R.id.read_only_temperature_text_id);
        readOnlyHumidityLevel= findViewById(R.id.read_only_profile_name_text_id);
        readOnlyCO2Level= findViewById(R.id.read_only_co2_text_id);
        readOnlyLightLevel= findViewById(R.id.read_only_light_text_id);

        PlantProfile profile = (PlantProfile) getIntent().getExtras().get(KEY_PLANT_PROFILE);
        PlantDTO plant = profile.getPlant();

        readOnlyPlantName.setText(plant.getPlantName());
        readOnlyCurrentTemperature.setText(Double.toString(plant.getTemperature()));
        readOnlyHumidityLevel.setText(Double.toString(plant.getHumidity()));
        readOnlyCO2Level.setText(Double.toString(plant.getCO2()));
        readOnlyLightLevel.setText(Integer.toString(plant.getLight()));



        waterPlantButton = findViewById(R.id.water_plant_button);

        waterPlantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Your plant is getting watered now!", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
