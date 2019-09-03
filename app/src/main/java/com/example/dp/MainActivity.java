package com.example.dp;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    MapFragment mapFragment = new MapFragment();
    final StatisticsFragment statisticsFragment = new StatisticsFragment();
    final MoreFragment moreFragment = new MoreFragment();
    final FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment activeFragment = mapFragment;

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
