package com.example.spotyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ranking extends AppCompatActivity {
    private ListView listview;
    private ArrayList<String> names;
    HashMap<String, Long> res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        res = Usuario.getInstance().getPuntuaciones();
        List<String> empleados = new ArrayList<>(res.keySet());
        empleados.add("100Josu");
        Collections.sort(empleados);

        ListView lv = findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        lv.setAdapter(adapter);
    }
}
