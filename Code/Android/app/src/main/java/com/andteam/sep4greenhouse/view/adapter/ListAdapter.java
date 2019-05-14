package com.andteam.sep4greenhouse.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.andteam.sep4greenhouse.R;
import com.andteam.sep4greenhouse.model.PlantProfile;
import com.andteam.sep4greenhouse.view.ModifyPlantProfileActivity;
import com.andteam.sep4greenhouse.view.ViewPlantProfileActivity;
import com.andteam.sep4greenhouse.viewmodel.ViewPlantsViewModel;

import java.util.List;

import static com.andteam.sep4greenhouse.view.ViewPlantsFragment.EDIT_PLANT_REQUEST;
import static com.andteam.sep4greenhouse.view.ViewPlantsFragment.KEY_PLANT_PROFILE;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<PlantProfile> profiles;
    private Activity activity;
    private ViewPlantsViewModel viewModel;

    public ListAdapter(Activity activity, ViewPlantsViewModel viewModel) {
        this.activity = activity;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item_rv, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.itemInRV.setText(profiles.get(position).getName());
    }

    public void setProfiles(List<PlantProfile> profiles) {
        this.profiles = profiles;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemInRV;
        ImageButton editPlant;
        ImageButton deletePlant;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemInRV = itemView.findViewById(R.id.plant);
            itemInRV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent viewPlant = new Intent(v.getContext(), ViewPlantProfileActivity.class);
                    viewPlant.putExtra(KEY_PLANT_PROFILE, profiles.get(getAdapterPosition()));

                    activity.startActivity(viewPlant);
                }
            });
            editPlant = itemView.findViewById(R.id.edit_profile_pencil);
            deletePlant = itemView.findViewById(R.id.delete_profile);

            editPlant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // launch activity for result to get edited plant
                    // and then pass it to view model
                    Intent getPlant = new Intent(v.getContext(), ModifyPlantProfileActivity.class);
                    getPlant.putExtra(KEY_PLANT_PROFILE, profiles.get(getAdapterPosition()));
                    activity.startActivityForResult(getPlant, EDIT_PLANT_REQUEST);
                }
            });

            deletePlant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.deletePlant(profiles.get(getAdapterPosition()));
                }
            });
        }
    }
}
