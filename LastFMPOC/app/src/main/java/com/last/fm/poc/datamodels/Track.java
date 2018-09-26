package com.last.fm.poc.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

public class Track extends Album{

    public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>() {

        @Override
        public Track createFromParcel(Parcel source) {
            return new Track(source);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[0];
        }
    };

    public Track(Parcel parcel) {
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
