package jb.moovie.search;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jb.moovie.BuildConfig;
import jb.moovie.main.Movie;

import static java.lang.Integer.parseInt;

public class SearchVolley {

    private static String poster, title, year, imdbID, imdbVotes, imdbRating, runtime,
            released, plot, director, actors, genre, writer;
    private static ArrayList<Movie> moviesList = new ArrayList<>();
    private static Movie movieDetails = null;
    static String ApiKey = BuildConfig.ApiKey;
    static String url = "https://www.omdbapi.com/?apikey=" + ApiKey;

    public static void filmSearch(CharSequence s, Context ctx) {
        moviesList = new ArrayList<>();
        JsonObjectRequest searchRequest = new JsonObjectRequest
                (Request.Method.GET, url + "&s=" + s, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray gotFilms = (JSONArray) response.get("Search");
                            int count = parseInt(String.valueOf(response.get("totalResults")));
                            JSONObject currentFilm = null;
                            for (int i = 0; i < count; i++) {
                                currentFilm = (JSONObject) gotFilms.get(i);
                                poster = currentFilm.getString("Poster");
                                title = currentFilm.getString("Title");
                                year = currentFilm.getString("Year");
                                imdbID = currentFilm.getString("imdbID");
                                moviesList.add(new Movie(poster, title, year, imdbID, null, null, null,
                                        null, null, null, null, null, null));
                            }
                        } catch (JSONException e) {
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        VolleyRequest.getInstance(ctx).addToRequestQueue(searchRequest);
    }

    public static ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    public interface DetailsInterface {
        void detailsSet();
    }
    private DetailsInterface listener;
    public SearchVolley(String id, Context context) {
        this.listener = null;
        filmDetails(id, context);
    }
    public void setCustomObjectListener(DetailsInterface listener) {
        this.listener = listener;
    }

    public void filmDetails(final String id, final Context context) {
        JsonObjectRequest detailsRequest = new JsonObjectRequest
                (Request.Method.GET, url + "&i=" + id + "&plot=full", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            title = response.getString("Title");
                            poster = response.getString("Poster");
                            year = response.getString("Year");
                            imdbID = response.getString("imdbID");
                            imdbVotes = " (" + response.getString("imdbVotes") + ")";
                            imdbRating = "Rating: " + response.getString("imdbRating") + "/10 ";
                            runtime = "Runtime: " + response.getString("Runtime");
                            released = "Released: " + response.getString("Released");
                            plot = "Plot: " + response.getString("Plot");
                            director = "Director: " + response.getString("Director");
                            actors = "Actors: " + response.getString("Actors");
                            genre = "Genre: " + response.getString("Genre");
                            writer = "Writers: " + response.getString("Writer");
                            movieDetails = new Movie(poster, title, year, imdbID, imdbVotes, imdbRating, runtime,
                                    released, plot, director, actors, genre, writer);
                            if (listener != null)
                                listener.detailsSet();
                        } catch (JSONException e) {
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        VolleyRequest.getInstance(context).addToRequestQueue(detailsRequest);
    }

    public static Movie getMovieDetails() {
        return movieDetails;
    }
}
