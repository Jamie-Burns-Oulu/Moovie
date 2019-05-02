package jb.moovie.search;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jb.moovie.BuildConfig;

public class SearchVolley {

    static String title, year;

    public static void filmSearch(CharSequence s, Context ctx) {
        String ApiKey = BuildConfig.ApiKey;
        String url = "http://www.omdbapi.com/?apikey="+ApiKey+"&s=";

        JsonObjectRequest searchRequest = new JsonObjectRequest
                (Request.Method.GET, url + s, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray gotFilms = (JSONArray) response.get("Search");
                            JSONObject currentFilm = null;
                            for(int i = 0; i < response.length(); i++) {
                                currentFilm = (JSONObject) gotFilms.get(i);
                                title = currentFilm.getString("Title");
                                year = currentFilm.getString("Year");
                                Log.d("TEST", title + " " + year);
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
}
