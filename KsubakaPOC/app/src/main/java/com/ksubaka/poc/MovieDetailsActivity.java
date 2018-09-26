package com.ksubaka.poc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ksubaka.poc.data.Movie;
import com.ksubaka.poc.data.MovieDetails;
import com.ksubaka.poc.network.FetchMovieDetailsByTitleTask;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView mDetailsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent intent = getIntent();
        Movie selectedMovie = intent.getParcelableExtra(AppConstants.MOVIE_PARCEL_KEY);

        mDetailsView = findViewById(R.id.movie_details_view);

        if(selectedMovie != null) {
            new FetchMovieDetailsByTitleTask(this).execute(AppConstants.TITLE_KEY, selectedMovie.getTitle());

            if(selectedMovie.getPoster() != null) {
                ImageView moviePoster = findViewById(R.id.movie_poster_view);

                String url = selectedMovie.getPoster();
                if(url != null && url.startsWith("http")) {
                    Picasso.with(this).load(url).into(moviePoster);
                } else {
                    moviePoster.setVisibility(View.GONE);
                }
            }
        }
        if (getSupportActionBar() != null) {
            if (selectedMovie != null && selectedMovie.getTitle() != null)
                getSupportActionBar().setTitle(selectedMovie.getTitle());

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void updateMovieDetails(MovieDetails movieDetails) {
        StringBuilder details = new StringBuilder();
        details.append("<b><big> Movie Title </big></b>: <b><big>").append(movieDetails.getTitle()).append("</big></b> <br> <br>")
                .append("<b><big>Directed by </big></b> : ").append(movieDetails.getDirector()).append("<br><br>")
                .append("<b><big>Produced by </big></b> : ").append(movieDetails.getProduction()).append("<br><br>")
                .append("<b><big>Written by </big></b> : ").append(movieDetails.getWriter()).append("<br><br>")
                .append("<b><big>Starring </big></b> : ").append(movieDetails.getActors()).append("<br><br>")
                .append("<b><big>Awards by </big></b> : ").append(movieDetails.getAwards()).append("<br><br>")
                .append("<b><big>Year of Production </big></b> : ").append(movieDetails.getYear()).append("<br><br>")
                .append("<b><big>Release date </big></b> : ").append(movieDetails.getReleased()).append("<br><br>")
                .append("<b><big>Running time </big></b> : ").append(movieDetails.getRuntime()).append("<br><br>")
                .append("<b><big>Country </big></b> : ").append(movieDetails.getCountry()).append("<br><br>")
                .append("<b><big>Language </big></b> : ").append(movieDetails.getLanguage()).append("<br><br>")
                .append("<b><big>Box office </big></b> : ").append(movieDetails.getBoxOffice()).append("<br><br>")
                .append("<b><big>Plot </big></b> : ").append(movieDetails.getPlot()).append("<br><br>")
                .append("<b><big>Genre </big></b> : ").append(movieDetails.getGenre()).append("<br><br> ")
                .append("<b><big>Website </big></b> : ").append(movieDetails.getWebsite()).append("<br><br> ");

        mDetailsView.setText(Html.fromHtml(details.toString()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
