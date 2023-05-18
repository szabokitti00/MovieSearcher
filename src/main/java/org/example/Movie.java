package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    @JsonProperty("id")
    private String id;

    @JsonProperty("original_language")
    private String orginalLanguage;

    @JsonProperty("original_title")
    private String orginalTitle;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("popularity")
    private String popularity;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("title")
    private String title;

    @JsonProperty("video")
    private boolean video;

    @JsonProperty("vote_average")
    private double voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("imdb_id")
    private String imdbID;

    @JsonProperty("wikidata_id")
    private String wikidataID;

    @JsonProperty("genres")
    private List<Genre> genres;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrginalLanguage() {
        return orginalLanguage;
    }

    public void setOrginalLanguage(String orginalLanguage) {
        this.orginalLanguage = orginalLanguage;
    }

    public String getOrginalTitle() {
        return orginalTitle;
    }

    public void setOrginalTitle(String orginalTitle) {
        this.orginalTitle = orginalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getWikidataID() {
        return wikidataID;
    }

    public void setWikidataID(String wikidataID) {
        this.wikidataID = wikidataID;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String CreateGenreString(){
        String gen = "";
        if (genres != null) {
            for (int i = 0; i < genres.size() - 1; i++) {
                gen += genres.get(i).getName() + ", ";
            }
            gen += genres.get(genres.size() - 1).getName();
        }
        return gen;
    }
}
