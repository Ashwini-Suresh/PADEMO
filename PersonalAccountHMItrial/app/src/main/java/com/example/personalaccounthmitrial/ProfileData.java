package com.example.personalaccounthmitrial;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfileData implements Parcelable {

    int id;
    String name;
    String avatar;

    protected ProfileData(Parcel in) {
        id = in.readInt();
        name = in.readString();
        avatar = in.readString();

    }
    public static final Creator<ProfileData> CREATOR = new Creator<ProfileData>() {
        @Override
        public ProfileData createFromParcel(Parcel in) {
            return new ProfileData(in);
        }

        @Override
        public ProfileData[] newArray(int size) {
            return new ProfileData[size];
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ProfileData(){
        id=1;
        name="Driver1";
        avatar="avatar1";

    }
    public ProfileData(int id,String name, String avatar){
        this.name=name;
        this.avatar=avatar;
        this.id=id;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(avatar);


    }
}
