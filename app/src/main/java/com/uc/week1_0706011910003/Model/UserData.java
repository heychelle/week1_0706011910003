package com.uc.week1_0706011910003.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class UserData implements Parcelable {

    public static ArrayList<User> saveList = new ArrayList<>();

    public UserData(Parcel in) {
    }

    public UserData(String name, String address, String s) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

}
