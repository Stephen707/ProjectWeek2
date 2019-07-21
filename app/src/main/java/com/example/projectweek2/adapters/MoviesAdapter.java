package com.example.projectweek2.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projectweek2.R;
import com.example.projectweek2.models.DetailActivity;
import com.example.projectweek2.models.MoviesModel;

import org.parceler.Parcels;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{
    Context context;
    List<MoviesModel> movies;


    public MoviesAdapter(Context context, List<MoviesModel> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MoviesModel model = movies.get(i);
        viewHolder.bind(model);


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTile,tvRelease,tvLanguage,tvOverview;
        ImageView ivPoster;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTile = itemView.findViewById(R.id.tvTitle);
            tvRelease = itemView.findViewById(R.id.tv_release);
            tvLanguage = itemView.findViewById(R.id.tv_language);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            relativeLayout = itemView.findViewById(R.id.container);

         }


        public void bind(final MoviesModel model) {
            tvTile.setText(model.getTvTitle());
            tvRelease.setText(model.getReleaseDate());
            tvLanguage.setText(model.getLanguage());
            tvOverview.setText(model.getTvOverview());
            String imageurl = model.getPosterPath();
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageurl = model.getBackdropPath();
            }
            Glide.with(context).load(imageurl).into(ivPoster);

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("movie", Parcels.wrap(model));

                    context.startActivity(intent);
                }
            });


        }
    }
}
