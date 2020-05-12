package com.ibai.spotyrun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.types.ImageUri;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        ConstraintLayout cl = findViewById(R.id.layoutResultado);
        LottieAnimationView anim = findViewById(R.id.acierto_anim);
        if(getIntent().getExtras().getBoolean("Resultado")){
            anim.setAnimation("8808-correct-animation.json");
        }else{
            anim.setAnimation("4698-wrong-answer.json");
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
