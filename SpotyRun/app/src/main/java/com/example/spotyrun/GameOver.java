package com.example.spotyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        EditText nombre = findViewById(R.id.nombre);

        TextView puntuaciones = findViewById(R.id.puntuaciones);
        puntuaciones.setText("Has obtenido "+Usuario.getInstance().getPuntuacion()+" puntos!");

        Button inicio = findViewById(R.id.inicio);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario.getInstance().guardarPuntuacion(nombre.getText().toString());

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
