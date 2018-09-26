package com.ksubaka.poc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.ksubaka.poc.data.Movie;
import com.ksubaka.poc.network.FetchMoviesBySearchTask;

public class MovieListActivity extends AppCompatActivity {

    private EditText searchView;

    private MovieListAdapter movieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        searchView = findViewById(R.id.search_view);
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*
         * defining and attaching the Adapter to RecyclerView
         */
        movieListAdapter = new MovieListAdapter(this, movieItemClickListener);
        mRecyclerView.setAdapter(movieListAdapter);

        setSearchListener();
    }

    private final OnItemClickListener movieItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Movie movie = movieListAdapter.getMoviesList().get(position);

            if(movie != null) {
                Intent intent = new Intent(MovieListActivity.this, MovieDetailsActivity.class);

                intent.putExtra(AppConstants.MOVIE_PARCEL_KEY, movie);

                MovieListActivity.this.startActivity(intent);
            }
        }
    };

    private void setSearchListener() {

        searchView.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                String searchKey = query.toString().toLowerCase();
                if(searchKey.isEmpty()) {
                    movieListAdapter.updateMoviesList(null);
                } else {
                    new FetchMoviesBySearchTask(movieListAdapter).execute(AppConstants.SEARCH_KEY, searchKey);
                }
            }
        });
    }

}
