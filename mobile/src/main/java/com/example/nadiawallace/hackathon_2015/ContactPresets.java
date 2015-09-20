package com.example.nadiawallace.hackathon_2015;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by nadiawallace on 9/19/15.
 */
public class ContactPresets implements Parcelable {

    private final List<String> emergencyContacts;
    private final List<String> presentContacts;

    public static final Parcelable.Creator<ContactPresets> CREATOR = new Parcelable.Creator<ContactPresets>() {

        @Override
        public ContactPresets createFromParcel(Parcel source) {
            return new ContactPresets(source);
        }

        @Override
        public ContactPresets[] newArray(int size) {
            return new ContactPresets[size];
        }
    };

    public ContactPresets(Parcel source){
        this.emergencyContacts = null;
        this.presentContacts = null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }


    public String getEmergencyContacts () {

        return "";
    }

    public String getPresentContacts() {

        return "";
    }
}
