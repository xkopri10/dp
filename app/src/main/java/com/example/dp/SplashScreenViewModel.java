package com.example.dp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SplashScreenViewModel extends ViewModel {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    private MutableLiveData<Boolean> mLocationPermissionGranted = new MutableLiveData<>();

    public void init(Activity contextActivity){
        askForPermission(contextActivity);
    }

    public LiveData<Boolean> getLocationPermissionGranted() {
        return mLocationPermissionGranted;
    }

    public void askForPermission(Activity contextActivity) {
        if (ContextCompat.checkSelfPermission(contextActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted.setValue(true);
        } else {
            ActivityCompat.requestPermissions(contextActivity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
        }
    }

    public int getRequestCode() {
        return REQUEST_CODE_LOCATION_PERMISSION;
    }
}
