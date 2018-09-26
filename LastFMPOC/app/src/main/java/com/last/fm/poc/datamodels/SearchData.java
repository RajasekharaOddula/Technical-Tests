package com.last.fm.poc.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SearchData implements Parcelable {

    public static final Parcelable.Creator<SearchData> CREATOR = new Parcelable.Creator<SearchData>() {

        @Override
        public SearchData createFromParcel(Parcel source) {
            return new SearchData(source);
        }

        @Override
        public SearchData[] newArray(int size) {
            return new SearchData[0];
        }
    };
    protected String name;
    protected String url;
    protected List<Image> image;
    protected String mbid;
    protected String streamable;
    public SearchData(Parcel parcel) {
        if (parcel != null) {
            name = parcel.readString();
            url = parcel.readString();
            //noinspection unchecked
            image = (List<Image>)parcel.readArrayList(Image.class.getClassLoader());
            mbid = parcel.readString();
            streamable = parcel.readString();
        }
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
        dest.writeList(image);
        dest.writeString(mbid);
        dest.writeString(streamable);
    }

    public String getName() {
        return name;
    }

    public void setName(String pname) {
        name = pname;
    }

    public String getUrl() {
        return url;
    }

    public List<Image> getImage() {
        return image;
    }

    public String getMbid() {
        return mbid;
    }

    public String getStreamable() {
        return streamable;
    }

    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("<b><big> Name </big></b>: <big>").append(getName()).append(" <br> <br>")
                .append("Please Clock on the following URl (or) use FAB to know more details. ").append(getUrl()).append(" </big><br><br>");

        return details.toString();
    }
}
