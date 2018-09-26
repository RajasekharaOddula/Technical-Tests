package com.ksubaka.poc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ksubaka.poc.data.Movie;
import com.ksubaka.poc.data.SearchData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieItemViewHolder> {

    private final OnItemClickListener itemClickListener;
    private final Context ctContext;

    private List<Movie> moviesList = new ArrayList<>();

    private boolean response = true;

    public List<Movie> getMoviesList() {
        return moviesList;
    }

    public void updateMoviesList(SearchData searchData) {
        if(searchData != null) {
            if(searchData.getResponse() == null || !searchData.getResponse().equalsIgnoreCase("True")) {
                response = false;
                moviesList.clear();
                Movie error = new Movie(null);
                error.setTitle(searchData.getError());
                moviesList.add(error);
            } else if (searchData.getSearch() != null) {
                response = true;
                this.moviesList = searchData.getSearch();
            }
        } else {
            response = false;
            moviesList.clear();
        }
        this.notifyDataSetChanged();
    }

    public MovieListAdapter(Context context, OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        ctContext = context;
    }

    @NonNull
    @Override
    public MovieListAdapter.MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);

        final MovieItemViewHolder movieViewHolder = new MovieItemViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(response)
                itemClickListener.onItemClick(movieViewHolder.getAdapterPosition());
            }
        });

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MovieItemViewHolder holder, int position) {
        holder.updateMovieDataToItemView(moviesList.get(position).getTitle());
        Picasso.with(ctContext).load(moviesList.get(position).getPoster()).into(holder.movieThumbImageView);
    }

    @Override
    public int getItemCount() {
        if(moviesList != null)
            return moviesList.size();

        return 0;
    }

    public static class MovieItemViewHolder extends RecyclerView.ViewHolder {

        public final TextView movieNameView;
        public final ImageView movieThumbImageView;

        public MovieItemViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            movieNameView = itemLayoutView.findViewById(R.id.movie_name_view);
            movieThumbImageView = itemLayoutView.findViewById(R.id.img_movie_icon);
        }

        public void updateMovieDataToItemView(String name) {
            movieNameView.setText(name);
        }
    }
}
