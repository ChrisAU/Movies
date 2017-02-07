package com.tigerspike.chrisnevin.movies;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by chris.nevin on 23/12/2016.
 */

public class DetailActivity extends AppCompatActivity {

    private Movie mMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        mMovie = intent.getParcelableExtra("movie");

        setTitle(mMovie.title);

        ImageView imgPoster = (ImageView)findViewById(R.id.poster_image);
        String posterUrl = mMovie.getPosterUrl();
        Picasso.with(this).load(posterUrl).into(imgPoster);

        String releaseDate = String.format(Locale.UK, getResources().getString(R.string.release_date), mMovie.releaseDate);
        TextView txtReleaseDate = (TextView)findViewById(R.id.release_date_text);
        if (txtReleaseDate != null) {
            txtReleaseDate.setText(releaseDate);
        }

        TextView txtVoteAverage = (TextView)findViewById(R.id.vote_average_text);
        if (txtVoteAverage != null) {
            String voteAverage = String.format(Locale.UK, getResources().getString(R.string.rating), mMovie.voteAverage);
            txtVoteAverage.setText(voteAverage);
        }

        TextView txtPlotSynopsis = (TextView)findViewById(R.id.plot_synopsis_text);
        if (txtPlotSynopsis != null) {
            txtPlotSynopsis.setText(mMovie.overview);
        }
    }
}
