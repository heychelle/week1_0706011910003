package com.uc.week1_0706011910003.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public String name;
    private String address;
    private String age;

    protected User(Parcel in) {
        name = in.readString();
        address = in.readString();
        age = in.readString();
    }

    public User(String name, String address, String age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(age);
    }
}
