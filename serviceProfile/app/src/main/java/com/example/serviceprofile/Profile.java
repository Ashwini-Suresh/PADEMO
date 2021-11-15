package com.example.serviceprofile;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {
    int id;
    String name;

    public Profile() {
        id=1;
        name="Audi";
    }
    public Profile(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Profile(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }
    public void readFromParcel(Parcel parcel){
        id= parcel.readInt();
        name= parcel.readString();
    }
}
