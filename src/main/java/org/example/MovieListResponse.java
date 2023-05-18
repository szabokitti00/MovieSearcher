package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieListResponse {
    @JsonProperty("page")
    private int page;

    @JsonProperty("results")
    private List<Movie> results;

    @JsonProperty("total_Pages")
    private int totalPages;

    @JsonProperty("total_results")
    private int totalResults;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
