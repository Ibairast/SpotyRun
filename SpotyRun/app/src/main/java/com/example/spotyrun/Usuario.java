package com.example.spotyrun;

import com.spotify.android.appremote.api.SpotifyAppRemote;

public class Usuario {
    private static  Usuario miUsuario = null;
    private static  String nombreUsuario;
    private static  int idUsuario;
    private static SpotifyAppRemote mSpotifyAppRemote;
    private static String playlist;

    public static Usuario getInstance() {
        if (miUsuario == null) {
            miUsuario = new Usuario();
        }
        return miUsuario;
    }

    private Usuario() {
    }

    public void setNombreUsuario(String nombreUsu){
        nombreUsuario = nombreUsu;
    }

    public void setIdUsuario(int idUsu) {
        idUsuario = idUsu;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public SpotifyAppRemote getmSpotifyAppRemote() {
        return mSpotifyAppRemote;
    }

    public void setmSpotifyAppRemote(SpotifyAppRemote mSpotifyAppRemote) {
        Usuario.mSpotifyAppRemote = mSpotifyAppRemote;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        Usuario.playlist = playlist;
    }
}
