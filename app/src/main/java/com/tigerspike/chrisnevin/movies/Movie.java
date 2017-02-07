package com.tigerspike.chrisnevin.movies;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chris.nevin on 22/12/2016.
 */

public class Movie implements Parcelable {
    Double voteAverage;
    String title;
    private String posterPath;
    String overview;
    String releaseDate;
    int id;

    String getPosterUrl() {
        return "https://image.tmdb.org/t/p/w185" + posterPath;
    }

    private Movie(int id, String title, String overview, Double voteAverage, String posterPath, String releaseDate) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
    }

    static Movie movieFromJsonObject(JSONObject object) throws JSONException {
        return new Movie(
                object.getInt("id"),
                object.getString("title"),
                object.getString("overview"),
                object.getDouble("vote_average"),
                object.getString("poster_path"),
                object.getString("release_date"));
    }

    static Movie[] moviesFromJsonObject(JSONObject object) throws JSONException {
        List<Movie> buffer = new ArrayList<Movie>();

        JSONArray results = object.getJSONArray("results");
        for (int i = 0; i < results.length(); i++) {
            buffer.add(movieFromJsonObject(results.getJSONObject(i)));
        }

        return buffer.toArray(new Movie[]{});
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeDouble(voteAverage);
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
    }

    public static final Parcelable.Creator<Movie> CREATOR =
            new Parcelable.Creator<Movie>() {
                public Movie createFromParcel(Parcel in) {
                    return new Movie(in);
                }

                public Movie[] newArray(int size) {
                    return new Movie[size];
                }
            };

    private Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        overview = in.readString();
        voteAverage = in.readDouble();
        posterPath = in.readString();
        releaseDate = in.readString();
    }
}
