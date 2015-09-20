package com.example.nadiawallace.hackathon_2015;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;


/**
 * Created by kccandon on 9/19/15.
 */
public class LocationHandler {

    private LocationManager locationManager;
    private Location lastKnownLocation;

    public LocationHandler(Context ctx){
         locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        try{
            lastKnownLocation = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
        } catch (SecurityException e){

        }

    }




    public String getLocationLink() {
        double latitude = lastKnownLocation.getLatitude();
        double longitude = lastKnownLocation.getLongitude();
        String latS = Double.toString(latitude);
        String longS = Double.toString(longitude);

        String link = "//www.google.com/maps/place/" + latS + ",+" + longS + "/";
        return link;
    }

}
