package com.example.spotyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Track;

public class Juego extends AppCompatActivity {
    public static SpotifyAppRemote mSpotifyAppRemote;
    Track track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_juego);

        mSpotifyAppRemote=Usuario.getInstance().getmSpotifyAppRemote();

        TextView nombre=findViewById(R.id.editText);

        Button aceptar= findViewById(R.id.buttonAceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpotifyAppRemote.getPlayerApi().pause();
                String[] palabras =nombre.getText().toString().split(" ");
                String[] album =track.album.name.split(" ");
                String[] nomb =track.name.split(" ");

                int contAlbum=palabras.length;
                int contNombre=palabras.length;


                for (String p:palabras){
                    for (int i=0; i<album.length;i++){
                        if (album[i].equalsIgnoreCase(p)){
                            contAlbum--;
                            album[i]="";
                        }
                    }
                    for (int i=0; i< nomb.length;i++){
                        if (nomb[i].equalsIgnoreCase(p)){
                            contNombre--;
                            nomb[i]="";
                        }
                    }
                }

                if (contAlbum<palabras.length||contNombre<palabras.length){
                    Usuario.getInstance().setPuntuacion(Usuario.getInstance().getPuntuacion()+100);
                    Log.d("ACERTADO","ACERTADO");
                    Intent i = new Intent(getApplicationContext(),Resultado.class);
                    i.putExtra("Nombre",track.name+ " - "+track.album.name);
                    i.putExtra("Foto",track.imageUri.raw);
                    i.putExtra("Resultado",true);
                    startActivity(i);
                    finish();
                }else{
                    Log.d("FALLO","FALLO");
                    Usuario.getInstance().setVidas(Usuario.getInstance().getVidas()-1);
                    if (Usuario.getInstance().getVidas()==0){
                        Intent i = new Intent(getApplicationContext(),GameOver.class);
                        startActivity(i);
                        finish();
                    }else{
                        Intent i = new Intent(getApplicationContext(),Resultado.class);
                        i.putExtra("Nombre",track.name+ " - "+track.album.name);
                        i.putExtra("Foto",track.imageUri.raw);
                        i.putExtra("Resultado",false);
                        startActivity(i);
                        finish();
                    }
                }

            }
        });

        // Play a playlist
        mSpotifyAppRemote.getPlayerApi().skipToIndex(Usuario.getInstance().getPlaylist(), (int) (Math.random()*85+1));

        // Subscribe to PlayerState
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    track = playerState.track;
                    if (track != null) {
                        Log.d("MainActivity", track.name + " by " + track.artist.name + " album "+track.album);
                    }

                });

    }

}
