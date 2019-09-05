package com.example.dp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class StatisticFragmentViewModel extends ViewModel {

    private MutableLiveData<List<Location>> mLocations;
    private LocationRepository mLocationRepository;

    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if (mLocations != null) {
            return;
        }
        mLocationRepository = LocationRepository.getInstance();
        mLocations = mLocationRepository.getLocations();
    }

    public void addNewLocation(final Location newLocation){
        mIsUpdating.setValue(true);

        List<Location> currentLocations = mLocations.getValue();
        currentLocations.add(newLocation);
        mLocations.postValue(currentLocations);
        mIsUpdating.setValue(false);

    }

    public LiveData<List<Location>> getLocations(){
        return mLocations;
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }

}
