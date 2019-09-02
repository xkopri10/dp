package com.example.dp;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LocationRecycleAdapter extends RecyclerView.Adapter<LocationRecycleAdapter.MyViewHolder> {

    private List<Location> mLocations = new ArrayList<>();
    private Context mContext;

    public LocationRecycleAdapter(Context context, List<Location> locations) {
        mLocations = locations;
        mContext = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.item);
        }
    }


    @NonNull
    @Override
    public LocationRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        final Location location = mLocations.get(i);
        viewHolder.name.setText(location.getName());
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }

}
