package com.example.projectweek2.models;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.projectweek2.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class DetailActivity extends YouTubeBaseActivity {
    TextView tvtile,tvOverview,tvlang,tvdate;
    RatingBar ratingBar;
    private static final String youtube_api ="AIzaSyC2Wk5hDdS6GVao3DVnd53znH7RY9aVM3I";
    private static final String trailer_api = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    MoviesModel moviesModel;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvtile = (TextView)findViewById(R.id.tvTitle);
        tvOverview = (TextView)findViewById(R.id.tvOverview);
        tvlang = (TextView)findViewById(R.id.tvLanguage);
        tvdate = (TextView)findViewById(R.id.tvReleaseDate);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.player);


        moviesModel= (MoviesModel) Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvtile.setText(moviesModel.getTvTitle());
        tvOverview.setText(moviesModel.getTvOverview());
        tvlang.setText(moviesModel.getLanguage());
        tvdate.setText(moviesModel.getReleaseDate());
        ratingBar.setRating( (float) moviesModel.getVote_average());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(trailer_api,moviesModel.getMovieId()),new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    if (results.length() == 0){
                        return;
                    }
                    JSONObject jsonObject = results.getJSONObject(0);
                    String youtubekey = jsonObject.getString("key");
                    initialize(youtubekey);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });



    }

    private void initialize(final String youtubekey) {
        youTubePlayerView.initialize(youtube_api, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(youtubekey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
