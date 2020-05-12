package com.ibai.spotyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Playlists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_playlists);

        IntentFilter filter = new IntentFilter("avisoPersonalizado");

        Usuario.getInstance().setPuntuacion(0);
        Usuario.getInstance().setVidas(3);

        Button X = findViewById(R.id.buttonSplash);
        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario.getInstance().setPlaylist("songs");
                Intent i = new Intent(getApplicationContext(),SplashActivity.class);
                startActivity(i);
            }
        });

        Button disney = findViewById(R.id.buttonDisney);
        disney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario.getInstance().setPlaylist("spotify:playlist:4BYBmf7eMuBicaNE3kJYTU");
                Intent i = new Intent(getApplicationContext(),Juego.class);
                startActivity(i);
            }
        });

        Button top = findViewById(R.id.top);
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario.getInstance().setPlaylist("spotify:playlist:37i9dQZEVXbNFJfN1Vw8d9");
                Usuario.getInstance().setNumero(51);
                Intent i = new Intent(getApplicationContext(),Juego.class);
                startActivity(i);
            }
        });

        Button bso = findViewById(R.id.bso);
        bso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario.getInstance().setPlaylist("spotify:album:1Mzu7H3DbZ2OBr44Wc8PP8");
                Usuario.getInstance().setNumero(51);
                Intent i = new Intent(getApplicationContext(),Juego.class);
                startActivity(i);
            }
        });

        Button verbena = findViewById(R.id.verbena);
        verbena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario.getInstance().setPlaylist("spotify:playlist:0OfkB0lDBhmtG0aPptIBFp");
                Usuario.getInstance().setNumero(52);
                Intent i = new Intent(getApplicationContext(),Juego.class);
                startActivity(i);
            }
        });

        Button ochenta = findViewById(R.id.ochenta);
        ochenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario.getInstance().setPlaylist("spotify:playlist:37i9dQZF1DWU4xtX4v6Z9l");
                Usuario.getInstance().setNumero(51);
                Intent i = new Intent(getApplicationContext(),Juego.class);
                startActivity(i);
            }
        });

        // TODO Top 50 Espña - spotify:user:spotifycharts:playlist:37i9dQZEVXbNFJfN1Vw8d9
        // TODO BSO - spotify:album:1Mzu7H3DbZ2OBr44Wc8PP8
        // TODO Verbena - spotify:user:114125244:playlist:0OfkB0lDBhmtG0aPptIBFp
        // TODO 80s - spotify:user:spotify:playlist:37i9dQZF1DWU4xtX4v6Z9l
    }
}
