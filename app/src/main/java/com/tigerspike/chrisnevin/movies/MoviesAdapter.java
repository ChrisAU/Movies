package com.tigerspike.chrisnevin.movies;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris.nevin on 22/12/2016.
 */

public class MoviesAdapter extends ArrayAdapter<Movie> {
    public MoviesAdapter(Context context, List<Movie> moviesList) {
        super(context, R.layout.movie_item_layout, moviesList);
    }

    public MoviesAdapter(Context context) {
        super(context, R.layout.movie_item_layout);
    }

    public ArrayList<Movie> getItems() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for(int i = 0; i < getCount(); i++){
            movies.add((Movie)getItem(i));
        }
        return movies;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = ((Activity)getContext());
        View row = convertView;
        ImageView imgPoster = null;

        if (row != null) {
            imgPoster = (ImageView) row.getTag();
        } else {
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(R.layout.movie_item_layout, parent, false);
            imgPoster = (ImageView)row.findViewById(R.id.poster_image);
            row.setTag(imgPoster);
        }

        Picasso.with(activity).load(getItem(position).getPosterUrl()).into(imgPoster);

        return row;
    }
}
