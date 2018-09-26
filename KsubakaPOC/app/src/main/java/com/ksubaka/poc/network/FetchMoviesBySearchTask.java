package com.ksubaka.poc.network;

import android.util.Log;

import com.google.gson.Gson;
import com.ksubaka.poc.MovieListAdapter;
import com.ksubaka.poc.data.SearchData;

import java.lang.ref.WeakReference;

public class FetchMoviesBySearchTask extends FetchMovieDataTask {
    private final WeakReference<MovieListAdapter> mMovieListAdapter;

    public FetchMoviesBySearchTask(MovieListAdapter movieListAdapter) {
        this.mMovieListAdapter = new WeakReference<>(movieListAdapter);
    }

    @Override
    protected void onPostExecute(String moviesJson) {
        super.onPostExecute(moviesJson);

        SearchData movieSearch = parseJSON(moviesJson);

        if (mMovieListAdapter.get() != null && movieSearch != null) {
            mMovieListAdapter.get().updateMoviesList(movieSearch);
        }
    }

    private SearchData parseJSON(String responseJson) {
        if (responseJson == null)
            return null;
        SearchData searchData = null;
        try {

            Log.d("FetchMoviesBySearchTask", "searchResponse : " + responseJson);

            searchData = new Gson().fromJson(responseJson, SearchData.class);

        } catch (Exception e) {
            Log.e("FetchMoviesBySearchTask", "Error parsing data" + e.getMessage());
        }
        return searchData;
    }
}

