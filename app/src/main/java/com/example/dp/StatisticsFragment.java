package com.example.dp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class StatisticsFragment extends Fragment {

    private StatisticFragmentViewModel mMainActivityViewModel;
    private LocationRecycleAdapter locationRecycleAdapter;
    private RecyclerView recyclerView;
    private Button button;

    private MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        recyclerView = view.findViewById(R.id.rec);
        button = view.findViewById(R.id.butt);

        mMainActivityViewModel = ViewModelProviders.of(this).get(StatisticFragmentViewModel.class);
        mMainActivityViewModel.init();
        mMainActivityViewModel.getLocations().observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(@Nullable List<Location> locations) {
                // for example
                // update map - map.notifyChanges or something like that
                // or adapter.setnotify...
                locationRecycleAdapter.notifyDataSetChanged();
            }
        });

        mMainActivityViewModel.getIsUpdating().observe(activity, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                recyclerView.smoothScrollToPosition(mMainActivityViewModel.getLocations().getValue().size()-1);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityViewModel.addNewLocation(
                        new Location("55", "nova lokace ", "bla bla")
                );
            }
        });

        initRecycleView();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initRecycleView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);

        locationRecycleAdapter = new LocationRecycleAdapter(activity, mMainActivityViewModel.getLocations().getValue());
        recyclerView.setAdapter(locationRecycleAdapter);

    }
}
