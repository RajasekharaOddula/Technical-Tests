package com.last.fm.poc.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

public class Artist extends SearchData {

    public static final Parcelable.Creator<Artist> CREATOR = new Parcelable.Creator<Artist>() {

        @Override
        public Artist createFromParcel(Parcel source) {
            return new Artist(source);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[0];
        }
    };

    public Artist(Parcel parcel) {
        super(parcel);
        if(parcel != null) {
            listeners = parcel.readString();
        }
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(listeners);
    }

    private String listeners;

    public String getListeners() {
        return listeners;
    }

    @Override
    public String toString() {
        return super.toString() + "<b><big> Listeners </big></b>: <big>" + getListeners() + "</big> <br> <br>";
    }
}
