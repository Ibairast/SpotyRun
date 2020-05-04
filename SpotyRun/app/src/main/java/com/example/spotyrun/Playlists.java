package com.example.spotyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        Usuario.getInstance().setPuntuacion(0);
        Usuario.getInstance().setVidas(3);

        Button disney = findViewById(R.id.buttonDisney);
        disney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario.getInstance().setPlaylist("spotify:playlist:4BYBmf7eMuBicaNE3kJYTU");
                Intent i = new Intent(getApplicationContext(),Juego.class);
                startActivity(i);
            }
        });
    }
}
