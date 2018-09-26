package com.ksubaka.poc.data;

import java.util.List;

public class SearchData extends Error {

    private List<Movie> Search;
    private String Response;
    private String totalResults;

    public List<Movie> getSearch() {
        return Search;
    }

    public String getResponse() {
        return Response;
    }

    public String getTotalResults() {
        return totalResults;
    }

}
