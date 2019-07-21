package com.example.projectweek2.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
@Parcel
public class MoviesModel {

    int movieId;
    double Vote_average;
    String posterPath;
    String tvTitle;
    String tvOverview;
    String language;
    String releaseDate;
    String backdropPath;

    public MoviesModel() {
    }

    public MoviesModel(JSONObject jsonObject) throws JSONException {

        posterPath = jsonObject.getString("poster_path");
        tvTitle = jsonObject.getString("title");
        tvOverview = jsonObject.getString("overview");
        language = jsonObject.getString("original_language");
        releaseDate = jsonObject.getString("release_date");
        backdropPath = jsonObject.getString("backdrop_path");
        Vote_average = jsonObject.getDouble("vote_average");
        movieId = jsonObject.getInt("id");



    }

    public static List<MoviesModel> fromJsonarray(JSONArray jsonArray) throws JSONException {
        List<MoviesModel> moviesModelList = new ArrayList<>();
        for (int i =0; i < jsonArray.length(); i++){
            moviesModelList.add(new MoviesModel(jsonArray.getJSONObject(i)));
        }
        return  moviesModelList;
    }



    public double getVote_average() {
        return Vote_average;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath) ;
    } 

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath) ;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public String getTvOverview() {
        return tvOverview;
    }

    public String getLanguage() {
        return language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getMovieId() {
        return movieId;
    }
}
