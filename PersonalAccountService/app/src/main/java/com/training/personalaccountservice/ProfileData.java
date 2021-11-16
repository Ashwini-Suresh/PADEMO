package com.training.personalaccountservice;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfileData implements Parcelable {

    int profileId;
    String profileName;

    public ProfileData(){
        profileId=1;
        profileName="Audi";
    }

    protected ProfileData(Parcel in) {
        profileId = in.readInt();
        profileName = in.readString();
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

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(profileId);
        dest.writeString(profileName);
    }
}
