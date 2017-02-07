package com.tigerspike.chrisnevin.movies;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by chris.nevin on 22/12/2016.
 */

public class API {
    private final String LOG_TAG = API.class.getSimpleName();

    public enum SortMode {
        HighestRated,
        MostPopular,
    }

    public void fetchMovies(SortMode sortMode, final MoviesAdapter moviesAdapter) {
        moviesAdapter.clear();
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/{sort_mode}")
                .addPathParameter("sort_mode", sortMode == SortMode.HighestRated ? "top_rated" : "popular")
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            moviesAdapter.addAll(Movie.moviesFromJsonObject(response));
                        }
                        catch (JSONException e) {
                            Log.e(LOG_TAG, "Error ", e);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(LOG_TAG, "Error ", anError);
                    }
                });
    }
}
