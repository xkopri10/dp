package com.example.dp;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

/**
* Singleton pattern
*/

// access to FIREBASE or ROOM
public class LocationRepository {

    private static LocationRepository instance;
    private ArrayList<Location> dataSet = new ArrayList<>();

    public static LocationRepository getInstance() {
        if (instance == null) {
            instance = new LocationRepository();
        }
        return instance;
    }

    // get data from FireBase or server
    public MutableLiveData<List<Location>> getLocations(){
        setLocations();

        MutableLiveData<List<Location>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    // here set data from datasnapshot to dataSet value !!!!
    private void setLocations(){
        dataSet.add(
                new Location("id","prvni","desc")
        );
        dataSet.add(
                new Location("id","druhy","desc")
        );
    }
}
