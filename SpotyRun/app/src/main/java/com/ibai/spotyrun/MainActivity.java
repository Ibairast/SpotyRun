package com.ibai.spotyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button jugar;
    TextView exception;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        jugar = findViewById(R.id.buttonJugar);
        exception = findViewById(R.id.textView3);

        Button ranking = findViewById(R.id.ranking);
        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Playlists.class);
                startActivity(i);
            }
        });


        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Ranking.class);
                startActivity(i);
            }
        });

        Handler handler = new Handler();
        int delay = 500; //milliseconds
        handler.postDelayed(new Runnable(){
            public void run(){
                //do something
                comprobarSDK();
                handler.postDelayed(this, delay);
            }
        }, delay);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        Usuario.getInstance().obtenerPuntuaciones();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void comprobarSDK(){
        if (Usuario.getInstance().getmSpotifyAppRemote()==null){
            jugar.setEnabled(false);
            exception.setText(Usuario.getInstance().getException());
        }else{
            jugar.setEnabled(true);
        }
    }
}
