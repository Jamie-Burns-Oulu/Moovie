package jb.moovie.search;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jb.moovie.BuildConfig;

import static java.lang.Integer.parseInt;

public class SearchVolley {

    private static String poster, title, year, imdbID;
    private static ArrayList<Movie> moviesList = new ArrayList<>();
    private static Movie movieDetails = null;
    static String ApiKey = BuildConfig.ApiKey;
    static String url = "http://www.omdbapi.com/?apikey=" + ApiKey;

    public static void filmSearch(CharSequence s, Context ctx) {
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
                                moviesList.add(new Movie(poster, title, year, imdbID, null, null,null,
                                        null, null, null, null, null, null));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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


    public static void filmDetails(final String imdbID, final Context context) {
        JsonObjectRequest detailsRequest = new JsonObjectRequest
                (Request.Method.GET, url + "&i=" + imdbID + "&plot=full", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String title = response.getString("Title");
                            String poster = response.getString("Poster");
                            String year = response.getString("Year");
                            String imdbID = response.getString("imdbID");
                            String imdbVotes = " ("+response.getString("imdbVotes")+")";
                            String imdbRating = "Rating: " +response.getString("imdbRating")+"/10 ";
                            String runtime = "Runtime: "+response.getString("Runtime");
                            String released = "Released: "+response.getString("Released");
                            String plot = "Plot: "+response.getString("Plot");
                            String director = "Director: "+response.getString("Director");
                            String actors = "Actors: "+response.getString("Actors");
                            String genre = "Genre: "+response.getString("Genre");
                            String writer = "Writers: "+response.getString("Writer");
                            movieDetails = new Movie(poster, title, year, imdbID, imdbVotes, imdbRating,runtime,
                                    released, plot,director,actors, genre, writer);
                        } catch (JSONException e) {
                            e.printStackTrace();
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
