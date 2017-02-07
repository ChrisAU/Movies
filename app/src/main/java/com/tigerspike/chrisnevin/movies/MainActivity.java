package com.tigerspike.chrisnevin.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final API api = new API();
    private API.SortMode sortMode;
    private MoviesAdapter mMoviesAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        int yOffset = 0;

        if (savedInstanceState == null || !savedInstanceState.containsKey("movies") || !savedInstanceState.containsKey("sortMode") || !savedInstanceState.containsKey("yOffset")) {
            mMoviesAdapter = new MoviesAdapter(this);
            setSortMode(API.SortMode.HighestRated);
        } else {
            yOffset = savedInstanceState.getInt("yOffset");
            sortMode = API.SortMode.valueOf(savedInstanceState.getString("sortMode"));
            ArrayList<Movie> movieArrayList = savedInstanceState.getParcelableArrayList("movies");
            mMoviesAdapter = new MoviesAdapter(this, movieArrayList);
        }

        MoviesGridView gridView = (MoviesGridView) findViewById(R.id.gridview);
        gridView.setAdapter(mMoviesAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class)
                        .putExtra("movie", mMoviesAdapter.getItem(position));
                startActivity(intent);
            }
        });
        if (yOffset > 0) {
            gridView.scrollTo(0, yOffset);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        MoviesGridView gridView = (MoviesGridView) findViewById(R.id.gridview);
        outState.putInt("yOffset", gridView.computeVerticalScrollOffset());
        outState.putString("sortMode", sortMode.toString());
        outState.putParcelableArrayList("movies", mMoviesAdapter.getItems());
        super.onSaveInstanceState(outState);
    }

    private void setSortMode(API.SortMode sortMode) {
        this.sortMode = sortMode;
        setTitle(sortMode == API.SortMode.HighestRated ? "Highest Rated" : "Most Popular");
        api.fetchMovies(sortMode, mMoviesAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_sort_highest_rated:
                setSortMode(API.SortMode.HighestRated);
                return true;
            case R.id.action_sort_most_popular:
                setSortMode(API.SortMode.MostPopular);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
