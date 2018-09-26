package com.last.fm.poc.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable{

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {

        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[0];
        }
    };

    public Image(Parcel parcel) {
        if(parcel != null) {
            text = parcel.readString();
            size = parcel.readString();
        }
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(size);
    }

    @SerializedName(value="#text")
    private String text;
    private String size;

    public String getText() {
        return text;
    }

    public String getSize() {
        return size;
    }
}
