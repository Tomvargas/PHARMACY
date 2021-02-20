package com.example.pedidos_farmaceuticos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        List<basededatos.Data> pe = db.cursor();
        for(int i = 0; i< pe.size(); i++){
            System.out.println(pe.get(i).toString());
        }
    }
}