package com.last.fm.poc;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.last.fm.poc.datamodels.Album;
import com.last.fm.poc.datamodels.Artist;
import com.last.fm.poc.datamodels.SearchData;
import com.last.fm.poc.datamodels.Track;

import java.lang.reflect.Type;
import java.util.List;

public class AppConfigurations {

    public static final int ALBUM = 0;
    public static final int ARTIST = 1;
    public static final int TRACK = 2;

    public static final String STR_ALBUM = "album";
    public static final String STR_ARTIST = "artist";
    public static final String STR_TRACK = "track";

    /**
     * this variable can be used while implementing xml response type.
     */
    public static boolean isJsonFormat = true;
    private static int searchType = ALBUM;

    public static int getSearchType() {
        return searchType;
    }

    public static void setSearchType(int type) {
        searchType = type;
    }

    public static String getSearchTypeString() {
        String searchString = STR_ALBUM;
        Log.i("AppConfig", "getSearchTypeString - searchString : " + searchString);
        Log.i("AppConfig", "getSearchTypeString - searchType : " + searchType);
        switch (searchType) {
            case ALBUM:
                searchString = STR_ALBUM;
                break;
            case ARTIST:
                searchString = STR_ARTIST;
                break;
            case TRACK:
                searchString = STR_TRACK;
                break;
        }
        Log.i("AppConfig", "getSearchTypeString -searchString111 : " + searchString);
        return searchString;
    }

    public static String getSearchKey(String searchKey) {
        String key = "method=";
        switch (getSearchType()) {
            case ALBUM:
                key = key + "album.search&album=" + searchKey;
                break;
            case ARTIST:
                key = key + "artist.search&artist=" + searchKey;
                break;
            case TRACK:
                key = key + "track.search&track=" + searchKey;
                break;
            default:
                key = key + "album.search&album=" + searchKey;
        }
        return key;
    }

    public static Type getSearchModelType() {
        Type listType = new TypeToken<List<SearchData>>() {
        }.getType();
        switch (getSearchType()) {
            case ALBUM:
                listType = new TypeToken<List<Album>>() {
                }.getType();
                break;
            case ARTIST:
                listType = new TypeToken<List<Artist>>() {
                }.getType();
                break;
            case TRACK:
                listType = new TypeToken<List<Track>>() {
                }.getType();
                break;
        }
        return listType;
    }
}
