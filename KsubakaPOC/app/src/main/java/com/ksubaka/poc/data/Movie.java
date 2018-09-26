package com.ksubaka.poc.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String Title;
    private String Year;
    private String Poster;

    private String imdbID;
    private String Type;

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[0];
        }
    };

    public Movie(Parcel parcel) {
        if(parcel != null) {
            Title = parcel.readString();
            Year = parcel.readString();
            Poster = parcel.readString();
            imdbID = parcel.readString();
            Type = parcel.readString();
        }
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Year);
        dest.writeString(Poster);
        dest.writeString(imdbID);
        dest.writeString(Type);
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getYear() {
        return Year;
    }

    public String getPoster() {
        return Poster;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }
}
