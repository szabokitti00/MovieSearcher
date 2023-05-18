package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class MovieManager {
    private static final OkHttpClient client = new OkHttpClient();

    //Finds movies which contains int the title the given string, or the title is the given string
    public static List<Movie> SearchByName(String title){

        String url = "https://api.themoviedb.org/3/search/movie?query=" + title;

        List<Movie> movies = null;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MmJkZWIyMzQ1OGJiZjJhZjc2MTUxM2RlYzZkNmIyNyIsInN1YiI6IjY0NjNjZWI2ZTNmYTJmMDEwM2EyOTM2YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.uZUbUX39sAtaGeoFkDcyKUE6LGiJN28kb1-DRuneoFs")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String jsonResponse = response.body().string();

                ObjectMapper objectMapper = new ObjectMapper();
                MovieListResponse movieListResponse = objectMapper.readValue(jsonResponse, MovieListResponse.class);

                movies = movieListResponse.getResults();

            } else {
                System.out.println("Request was not successful. Response code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    //Finds the movie with the given id
    public static Movie SearchByID(String id) {

        Movie movie = null;

        String url = "https://api.themoviedb.org/3/movie/" + id;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MmJkZWIyMzQ1OGJiZjJhZjc2MTUxM2RlYzZkNmIyNyIsInN1YiI6IjY0NjNjZWI2ZTNmYTJmMDEwM2EyOTM2YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.uZUbUX39sAtaGeoFkDcyKUE6LGiJN28kb1-DRuneoFs")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()){
                String jsonResponse = null;
                if (response.body() != null) {
                    jsonResponse = response.body().string();
                }

                ObjectMapper objectMapper = new ObjectMapper();
                movie = objectMapper.readValue(jsonResponse, Movie.class);

            } else {
                System.out.println("Request was not successful. Response code: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return movie;
    }

    //Finds similar movies for the movie with the given id
    public static List<Movie> SearchSimilarByID(String id) {

        List<Movie> movies = null;

        String url = "https://api.themoviedb.org/3/movie/" + id + "/similar";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MmJkZWIyMzQ1OGJiZjJhZjc2MTUxM2RlYzZkNmIyNyIsInN1YiI6IjY0NjNjZWI2ZTNmYTJmMDEwM2EyOTM2YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.uZUbUX39sAtaGeoFkDcyKUE6LGiJN28kb1-DRuneoFs")
                .build();

        try (Response response = client.newCall(request).execute()){
            if (response.isSuccessful()) {
                String jsonResponse = null;
                if (response.body() != null) {
                    jsonResponse = response.body().string();
                }

                ObjectMapper objectMapper = new ObjectMapper();
                MovieListResponse movieListResponse = objectMapper.readValue(jsonResponse, MovieListResponse.class);

                movies = movieListResponse.getResults();
            } else {
                System.out.println("Request was not successful. Response code: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return movies;
    }

    //Finds external ids on TMDb and writes out the wikidata and IMDb url for the movie with the given TMDb id
    //Summary from wikipedia page and the url for wikipedia page not implemented
    public static void ExternalIdFinder(String id){
        String url = "https://api.themoviedb.org/3/movie/" + id + "/external_ids";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MmJkZWIyMzQ1OGJiZjJhZjc2MTUxM2RlYzZkNmIyNyIsInN1YiI6IjY0NjNjZWI2ZTNmYTJmMDEwM2EyOTM2YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.uZUbUX39sAtaGeoFkDcyKUE6LGiJN28kb1-DRuneoFs")
                .build();

        try (Response response = client.newCall(request).execute()){
            if (response.isSuccessful()){
                String jsonResponse = null;
                if (response.body() != null) {
                    jsonResponse = response.body().string();
                }

                ObjectMapper objectMapper = new ObjectMapper();
                Movie movie = objectMapper.readValue(jsonResponse, Movie.class);

                if (movie.getWikidataID() != null){
                    System.out.println("Wikipedia URL: https://www.wikidata.org/wiki/" + movie.getWikidataID());
                }
                if (movie.getImdbID() != null){
                    System.out.println("IMDb URL: https://www.imdb.com/title/" + movie.getImdbID());
                }
            } else {
                System.out.println("Request was not successful. Response code: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Writes out movie data
    public static void WriteMovie(Movie movie){
       // SearchByID(movie.getId());
        System.out.println(movie.getId() + "  |  " + movie.getTitle() + "  |  " + movie.getVoteAverage() + "  |  " + movie.getReleaseDate() + "  |  " + movie.CreateGenreString());
    }

    //Classifies searched movie based on avarage votes of similar movies
    public static void ClassifyMovie(List<Movie> similars, Movie movie){
        final int verygood = 4;
        final int good = 2;
        final int avarage = 0;
        final int bad = -2;

        double sumVotes = 0;

        for (Movie m : similars) {
            sumVotes += m.getVoteAverage();
        }

        double avgSumVotes = sumVotes / similars.size();
        if (movie.getVoteAverage() >= avgSumVotes + verygood) {
            System.out.println("Very good!");
        }
        else if (movie.getVoteAverage() >= avgSumVotes + good) {
            System.out.println("Good!");
        }
        else if (movie.getVoteAverage() >= avgSumVotes + avarage) {
            System.out.println("Avarage!");
        }
        else if(movie.getVoteAverage() >= avgSumVotes + bad) {
            System.out.println("Bad!");
        }
        else {
            System.out.println("Very bad!");
        }
    }
}
