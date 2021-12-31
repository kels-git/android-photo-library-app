package com.example.imagelibraryapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Photo_Model implements Parcelable {


    private int mPhotoLibrary;
    private String mPhotoTitle;
    private String mPhotoDescription;



    public Photo_Model(){

    }

    public Photo_Model(int mPhotoLibrary, String mPhotoTitle, String mPhotoDescription) {

        this.mPhotoLibrary = mPhotoLibrary;
        this.mPhotoTitle = mPhotoTitle;
        this.mPhotoDescription = mPhotoDescription;
    }


    protected Photo_Model(Parcel in) {
        mPhotoLibrary = in.readInt();
        mPhotoTitle = in.readString();
        mPhotoDescription = in.readString();
    }

    public static final Creator<Photo_Model> CREATOR = new Creator<Photo_Model>() {
        @Override
        public Photo_Model createFromParcel(Parcel in) {
            return new Photo_Model(in);
        }

        @Override
        public Photo_Model[] newArray(int size) {
            return new Photo_Model[size];
        }
    };

    public int getmPhotoLibrary() {
        return mPhotoLibrary;
    }

    public void setmPhotoLibrary(int mPhotoLibrary) {
        this.mPhotoLibrary = mPhotoLibrary;
    }

    public String getmPhotoTitle() {
        return mPhotoTitle;
    }

    public void setmPhotoTitle(String mPhotoTitle) {
        this.mPhotoTitle = mPhotoTitle;
    }

    public String getmPhotoDescription() {
        return mPhotoDescription;
    }

    public void setmPhotoDescription(String mPhotoDescription) {
        this.mPhotoDescription = mPhotoDescription;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mPhotoLibrary);
        parcel.writeString(mPhotoTitle);
        parcel.writeString(mPhotoDescription);
    }
}
