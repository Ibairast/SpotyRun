package com.example.spotyrun.SpotifyConnect;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.example.spotyrun.Model.EndPoints;
import com.example.spotyrun.Model.Playlist;
import com.example.spotyrun.Model.Song;
import com.example.spotyrun.VolleyCallBack;
import com.google.common.net.MediaType;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PlaylistService {

    private static final String ENDPOINT = EndPoints.PLAYLIST.toString();
    private static final String ENDPOINTME = EndPoints.PLAYLISTME.toString();
    private SharedPreferences msharedPreferences;
    private RequestQueue mqueue;
    private String URL;
    JSONObject payload;
    private String playlistID;
    private ArrayList<Playlist> playlists = new ArrayList<>();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    public PlaylistService(RequestQueue queue, SharedPreferences sharedPreferences) {
        mqueue = queue;
        msharedPreferences = sharedPreferences;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void get(final VolleyCallBack callBack) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ENDPOINTME, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                JSONArray jsonArray = response.optJSONArray("items");

                for (int n = 0; n < jsonArray.length(); n++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(n);
                        Playlist playlist = gson.fromJson(jsonObject.toString(), Playlist.class);
                        try {
                            playlist.setImageURL(jsonObject.optJSONArray("images").optJSONObject(0).getString("url"));
                        } catch (NullPointerException e) {
                            playlist.setImageURL(null);
                        }
                        playlists.add(playlist);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                callBack.onSuccess();
            }
        }, error -> get(() -> {

        })) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = msharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        mqueue.add(jsonObjectRequest);

    }


    public void put(Song song) {
        JSONObject payload = preparePayload(song);

        playlistID = msharedPreferences.getString("playlist", "");
        URL = String.format(ENDPOINT, playlistID);

        JsonObjectRequest jsonObjectRequest = preparePutRequest(payload, Request.Method.POST);
        mqueue.add(jsonObjectRequest);
    }

    public void delete(Song song) {
        payload = preparePayload(song);
    }


    private JSONObject preparePayload(Song song) {
        JSONArray uriArray = new JSONArray();
        uriArray.put("spotify:track:" + song.getId());
        JSONObject uris = new JSONObject();
        try {
            uris.put("uris", uriArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return uris;
    }


    private JsonObjectRequest preparePutRequest(JSONObject payload, int method) {
        return new JsonObjectRequest(method, URL, payload, response -> {
        }, error -> {
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = msharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
    }
}
