package com.ksubaka.poc.data;

import android.os.Parcel;

import java.util.List;

public class MovieDetails extends Movie {
    public MovieDetails(Parcel parcel) {
        super(parcel);
    }

    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    private List<Rating> Ratings;
    private String Metascore;
    private String imdbRating;
    private String imdbVotes;
    private String Production;
    private String BoxOffice;
    private String DVD;
    private String Website;
    private String Response;

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getWriter() {
        return Writer;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCountry() {
        return Country;
    }

    public String getAwards() {
        return Awards;
    }

    public List<Rating> getRatings() {
        return Ratings;
    }

    public String getMetascore() {
        return Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getProduction() {
        return Production;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public String getDVD() {
        return DVD;
    }

    public String getWebsite() {
        return Website;
    }

    public String getResponse() {
        return Response;
    }

}
