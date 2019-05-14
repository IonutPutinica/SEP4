package com.andteam.sep4greenhouse.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andteam.sep4greenhouse.R;
import com.andteam.sep4greenhouse.model.PlantProfile;
import com.andteam.sep4greenhouse.repository.UserRepository;
import com.andteam.sep4greenhouse.view.adapter.ListAdapter;
import com.andteam.sep4greenhouse.viewmodel.ViewPlantsViewModel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class ViewPlantsFragment extends Fragment {

    public static final int EDIT_PLANT_REQUEST = 1337;
    public static final String KEY_PLANT_PROFILE = "KEY_PLANT_PROFILE";
    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private ViewPlantsViewModel viewModel;
    private TextView welcomeuser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate Layout and present it
        View v = inflater.inflate(R.layout.fragment_viewplants, container, false);

        welcomeuser =  v.findViewById(R.id.welcomeuser);
        welcomeuser.setText(UserRepository.getInstance().getEmail());

        // Initialize ViewModel
        viewModel = ViewModelProviders.of(this).get(ViewPlantsViewModel.class);

        recyclerView = v.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ListAdapter(getActivity(), viewModel);
        viewModel.getProfiles().observe(this, new Observer<List<PlantProfile>>() {
            @Override
            public void onChanged(@Nullable List<PlantProfile> plantProfiles) {
                adapter.setProfiles(plantProfiles);
            }
        });
        recyclerView.setAdapter(adapter);
        return v;
    }
}