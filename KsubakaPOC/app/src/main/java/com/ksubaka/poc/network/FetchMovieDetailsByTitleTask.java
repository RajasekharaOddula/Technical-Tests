package com.ksubaka.poc.network;

import android.util.Log;

import com.google.gson.Gson;
import com.ksubaka.poc.MovieDetailsActivity;
import com.ksubaka.poc.data.MovieDetails;

import java.lang.ref.WeakReference;

public class FetchMovieDetailsByTitleTask extends FetchMovieDataTask {
    private final WeakReference<MovieDetailsActivity> detailsActivity;

    public FetchMovieDetailsByTitleTask(MovieDetailsActivity activity) {
        this.detailsActivity = new WeakReference<>(activity);
    }

    @Override
    protected void onPostExecute(String movieDetailsJson) {
        super.onPostExecute(movieDetailsJson);

        MovieDetails movieDetails = parseJSON(movieDetailsJson);

        if (detailsActivity.get() != null && movieDetails != null) {
            detailsActivity.get().updateMovieDetails(movieDetails);
        }
    }

    private MovieDetails parseJSON(String responseJson) {
        if (responseJson == null)
            return null;
        MovieDetails movieDetails = null;
        try {

            Log.d("MovieListActivity", "MovieDetailsResp : " + responseJson);
            movieDetails = new Gson().fromJson(responseJson, MovieDetails.class);

        } catch (Exception e) {
            Log.e("MovieListActivity", "Error parsing data" + e.getMessage());
        }
        return movieDetails;
    }
}
