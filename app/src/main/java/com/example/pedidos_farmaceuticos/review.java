package com.example.pedidos_farmaceuticos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class review extends AppCompatActivity {
    Button ok, cancel;
    TextView txt1, txt2, txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        basededatos DB = new basededatos(this, "pedidos", null, 1);

        Bundle bundle = getIntent().getExtras();

        String medicamento = bundle.getString("medicamento");
        String tipo = bundle.getString("tipo");
        String cantidad = bundle.getString("cant");
        String distribuidora = bundle.getString("distri");
        String sucursal = bundle.getString("sucursal");

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);

        txt1.setText("Pedidos a la distribuidora "+distribuidora);
        txt2.setText(cantidad+" Unidades de "+tipo+" "+medicamento);
        txt3.setText(sucursal);

        ok=findViewById(R.id.onbtn);
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Boolean res = DB.insertar(medicamento,tipo,cantidad,distribuidora,sucursal);
                if (res){
                    Toast.makeText(review.this, "Pedido enviado", Toast.LENGTH_SHORT).show();

                    onBackPressed();

                }else{
                    Toast.makeText(review.this, "Pedido no enviado", Toast.LENGTH_SHORT).show();
                }
            }});
        cancel=findViewById(R.id.cancelbtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                onBackPressed();
            }});
    }
}