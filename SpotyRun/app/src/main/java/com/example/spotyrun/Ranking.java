package com.example.spotyrun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ranking extends AppCompatActivity {
    private ListView listview;
    private List<String> names;
    HashMap<String, Long> res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        res = Usuario.getInstance().getPuntuaciones();
        ArrayList<String> empleados = new ArrayList<>(res.keySet());
        Collections.sort(empleados);
        Collections.reverse(empleados);


        String[] nombres = new String[empleados.size()];
        empleados.toArray(nombres);

        String[][] nombresAOrdenar = new String[nombres.length][2];

        for (int i=0; i< nombres.length ; i++) {
            //Extrae valores de array, el numero será la columna a ordenar.
            String[] parts = nombres[i].split(" ");
            nombresAOrdenar[i][0] =  parts[1];
            nombresAOrdenar[i][1] =  parts[0];
        }

        //Ordena valores por columna 1 (indice 0)
        Arrays.sort(nombresAOrdenar, new ColumnComparator(0));

        //Agrega valores ordenados a array original
        for (int j=0; j< nombres.length ; j++) {
            nombres[j] = nombresAOrdenar[j][1].toString() + " "+ nombresAOrdenar[j][0].toString();
        }

        names= Arrays.asList(nombres);
        Collections.reverse(names);


        ListView lv = findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        lv.setAdapter(adapter);
    }
}

class ColumnComparator implements Comparator {
    int columnToSort;
    ColumnComparator(int columnToSort) {
        this.columnToSort = columnToSort;
    }
    //sobreescribe metodo compare
    public int compare(Object o1, Object o2) {
        String[] row1 = (String[]) o1;
        String[] row2 = (String[]) o2;

        int res = Integer.parseInt(row1[0].toString()) -  Integer.parseInt(row2[0].toString());
        return res;
    }
}
