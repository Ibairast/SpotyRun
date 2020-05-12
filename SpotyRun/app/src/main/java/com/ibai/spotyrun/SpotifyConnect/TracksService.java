package com.ibai.spotyrun.SpotifyConnect;

import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ibai.spotyrun.Model.EndPoints;
import com.ibai.spotyrun.Model.Song;
import com.google.common.net.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class TracksService {
    private static final String ENDPOINT = EndPoints.TRACKS.toString();
    private SharedPreferences msharedPreferences;
    private RequestQueue mqueue;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public TracksService(RequestQueue queue, SharedPreferences sharedPreferences) {
        mqueue = queue;
        msharedPreferences = sharedPreferences;
    }

    public void put(Song song) {
        JSONObject payload = preparePutPayload(song);
        JsonObjectRequest jsonObjectRequest = prepareSongLibraryRequest(payload, Request.Method.PUT);
        mqueue.add(jsonObjectRequest);
    }

    public void delete(Song song) {
        JSONObject payload = preparePutPayload(song);
        System.out.println(payload);
    }


    private JSONObject preparePutPayload(Song song) {
        JSONArray idarray = new JSONArray();
        idarray.put(song.getId());
        JSONObject ids = new JSONObject();
        try {
            ids.put("ids", idarray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ids;
    }

    private JsonObjectRequest prepareSongLibraryRequest(JSONObject payload, int method) {
        return new JsonObjectRequest(method, ENDPOINT, payload, response -> {
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
