package com.example.spotyrun;

import com.spotify.android.appremote.api.SpotifyAppRemote;

public class Usuario {
    private static  Usuario miUsuario = null;
    private static  String nombreUsuario;
    private static  int idUsuario;
    private static SpotifyAppRemote mSpotifyAppRemote;
    private static String playlist;
    private static int puntuacion;
    private static int vidas;

    public static Usuario getInstance() {
        if (miUsuario == null) {
            miUsuario = new Usuario();
        }
        return miUsuario;
    }

    private Usuario() {
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        Usuario.vidas = vidas;
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

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        Usuario.puntuacion = puntuacion;
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