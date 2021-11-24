
package com.training.personalaccountservice;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfileData implements Parcelable {
    private int id;
    private String name;
    private String avatar;
    private boolean isActive;

    public ProfileData(int id,String name, String avatar, boolean isActive){
        this.name=name;
        this.avatar=avatar;
        this.id=id;
        this.isActive=isActive;

    }


    protected ProfileData(Parcel in) {
        id = in.readInt();
        name = in.readString();
        avatar = in.readString();
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




    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
        parcel.writeByte((byte) (isActive ? 1 : 0));
    }
}
