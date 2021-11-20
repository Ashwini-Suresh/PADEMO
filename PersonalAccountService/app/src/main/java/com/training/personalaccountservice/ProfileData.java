package com.training.personalaccountservice;

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
        settings = in.readString();
        isActive = in.readByte() != 0;
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

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    String settings;
    boolean isActive;

    public ProfileData(){
        id=1;
        name="Driver1";
        avatar="avatar1";
        settings=null;
        isActive=true;
    }
    public ProfileData(int id,String name, String avatar/*, boolean isActive*/){
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
        parcel.writeString(settings);
        parcel.writeByte((byte) (isActive ? 1 : 0));
    }
}
