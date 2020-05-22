package com.ibai.spotyrun;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ibai.spotyrun.Model.Song;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Usuario {
    private static Usuario miUsuario = null;
    private String nombreUsuario;
    private int idUsuario;
    private SpotifyAppRemote mSpotifyAppRemote;
    private String playlist;
    private int puntuacion;
    private int vidas;
    private FirebaseFirestore db;
    private HashMap<String,Long> puntuaciones;
    private String exception="";

    public HashSet<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(HashSet<Song> canciones) {
        Usuario.canciones = canciones;
    }

    private static HashSet<Song> canciones;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numer) {
        numero = numer;
    }

    private int numero;

    public static Usuario getInstance() {
        if (miUsuario == null) {
            miUsuario = new Usuario();
        }
        return miUsuario;
    }

    public HashMap<String, Long> getPuntuaciones() {
        return puntuaciones;
    }

    private Usuario() {
         db = FirebaseFirestore.getInstance();
    }

    public void guardarPuntuacion(String user){
        Map<String, Object> punt = new HashMap<>();
        punt.put("Puntuacion", puntuacion);
        punt.put("Fecha",System.currentTimeMillis()*100);

        db.collection("Partidas").document(user).set(punt)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("SUBIDA", "DocumentSnapshot successfully written!");
                        db.collection("Partidas").document(user).collection("Puntuaciones").add(punt);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("SUBIDA", "Error writing document", e);
                    }
                });

    }

    public void obtenerPuntuaciones(){
        puntuaciones = new HashMap<>();
        db.collection("Partidas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("OBTENER", document.getId());
                                sacarPuntuacion(document.getId());
                            }
                        } else {
                            Log.w("OBTENER", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void sacarPuntuacion(String id) {
        db.collection("Partidas").document(id).collection("Puntuaciones")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("OBTENER", String.valueOf(document.get("Puntuacion")));
                                puntuaciones.put(id+" "+document.get("Puntuacion"), (Long) document.get("Puntuacion"));
                            }
                        } else {
                            Log.w("OBTENER", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
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
        this.puntuacion = puntuacion;
    }


    public SpotifyAppRemote getmSpotifyAppRemote() {
        return mSpotifyAppRemote;
    }

    public void setmSpotifyAppRemote(SpotifyAppRemote mSpotifyAppRemote) {
        this.mSpotifyAppRemote = mSpotifyAppRemote;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
