package com.example.spotyrun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.types.ImageUri;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        ConstraintLayout cl = findViewById(R.id.layoutResultado);
        if(getIntent().getExtras().getBoolean("Resultado")){
            cl.setBackgroundColor(Color.GREEN);
        }else{
            cl.setBackgroundColor(Color.RED);
        }

        Button siguiente = findViewById(R.id.siguiente);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Juego.class);
                i.putExtra("Siguiente",true);
                startActivity(i);
                finish();
            }
        });

        TextView vidas = findViewById(R.id.vidas);
        vidas.setText("Te quedan "+Usuario.getInstance().getVidas()+ " vidas.");

        TextView nombre = findViewById(R.id.resultado);
        nombre.setText(getIntent().getExtras().getString("Nombre"));

        ImageView foto = findViewById(R.id.resultadoImagen);
        SpotifyAppRemote spoty = Usuario.getInstance().getmSpotifyAppRemote();
        spoty.getImagesApi().getImage(new ImageUri(getIntent().getExtras().getString("Foto"))).setResultCallback(new CallResult.ResultCallback<Bitmap>() {
            @Override
            public void onResult(Bitmap bitmap) {
                foto.setImageBitmap(bitmap);
            }
        });

    }
}
