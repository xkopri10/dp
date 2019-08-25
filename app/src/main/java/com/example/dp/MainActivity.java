package com.example.dp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {



    MapFragment mapFragment = new MapFragment();
    final StatisticsFragment statisticsFragment = new StatisticsFragment();
    final MoreFragment moreFragment = new MoreFragment();
    Fragment activeFragment = mapFragment;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        fragmentManager.beginTransaction().add(R.id.fragment_container, mapFragment, "3").commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, statisticsFragment, "2").hide(statisticsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, moreFragment, "1").hide(moreFragment).commit();

        bottomNavigationView.setSelectedItemId(R.id.action_map);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_map:
                        fragmentManager.beginTransaction().hide(activeFragment).show(mapFragment).commit();
                        activeFragment = mapFragment;
                        return true;
                    case R.id.action_statistics:
                        fragmentManager.beginTransaction().hide(activeFragment).show(statisticsFragment).commit();
                        activeFragment = statisticsFragment;
                        return true;
                    case R.id.action_more:
                        fragmentManager.beginTransaction().hide(activeFragment).show(moreFragment).commit();
                        activeFragment = moreFragment;
                        return true;
                }

                return false;
            }
        });
    }
}
