package com.last.fm.poc.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

public class Album extends SearchData {

    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {

        @Override
        public Album createFromParcel(Parcel source) {
            return new Album(source);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[0];
        }
    };

    public Album(Parcel parcel) {
        super(parcel);
        if(parcel != null) {
            artist = parcel.readString();
        }
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(artist);
    }

    protected String artist;

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return super.toString() + "<b><big> Artist </big></b>: <big>" + getArtist() + "</big> <br> <br>";
    }
}