package com.example.spotyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
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
