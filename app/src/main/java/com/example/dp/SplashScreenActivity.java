package com.example.dp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    private SplashScreenViewModel mSplashScreenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSplashScreenViewModel = ViewModelProviders.of(this).get(SplashScreenViewModel.class);
        mSplashScreenViewModel.init(this);

        mSplashScreenViewModel.getLocationPermissionGranted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                startNextActivity();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == mSplashScreenViewModel.getRequestCode()) {
            startNextActivity();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void startNextActivity() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
