package com.example.pedidos_farmaceuticos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class consulta extends AppCompatActivity {
    ListView pedidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        basededatos db = new basededatos(this, "pedidos", null, 1);

        pedidos = findViewById(R.id.pedidos);
        ArrayList<ArrayList<String>> pe = db.cursor();

        ArrayList<String> medicinas = new ArrayList<>();
        ArrayList<String> tipos = new ArrayList<>();
        ArrayList<String> cants = new ArrayList<>();
        ArrayList<String> dists = new ArrayList<>();
        ArrayList<String> sucs = new ArrayList<>();
        ArrayList<String> datas = new ArrayList<>();

        if (pe.size() > 0){
            datas.clear();

            medicinas = pe.get(0);
            tipos = pe.get(1);
            cants = pe.get(2);
            dists = pe.get(3);
            sucs = pe.get(4);

            for(int i=0; i<medicinas.size(); i++){
                datas.add(cants.get(i)+" unidades de "+tipos.get(i)+" "+medicinas.get(i)+" de la distribuidora "+dists.get(i)+" para, "+sucs.get(i));
            }

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
            pedidos.setAdapter(arrayAdapter);
        }
    }
}