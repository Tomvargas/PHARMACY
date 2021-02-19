package com.example.pedidos_farmaceuticos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nombre, cantidad;
    Spinner tipo;
    RadioButton cofarma, empsehar, cemefar;
    CheckBox principal, secundaria;
    Button ok, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.medicinaName);
        cantidad = findViewById(R.id.medicinaCant);

        tipo = findViewById(R.id.spinner);

        cofarma = findViewById(R.id.cofarma);
        empsehar = findViewById(R.id.empsehar);
        cemefar = findViewById(R.id.cemefar);

        principal = findViewById(R.id.principal);
        secundaria = findViewById(R.id.secundario);

        ok = findViewById(R.id.btnok);
        cancel = findViewById(R.id.btncancel);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nombre.getText().toString();
                String tipe = tipo.getSelectedItem().toString();
                String cant = cantidad.getText().toString();

                if (name.isEmpty() || tipe.isEmpty() || cant.isEmpty()){
                    Toast.makeText(MainActivity.this, "Debe llenar todos los campos de texto", Toast.LENGTH_SHORT).show();
                }else{
                    if (!cofarma.isChecked() && !empsehar.isChecked() && !cemefar.isChecked()){
                        Toast.makeText(MainActivity.this, "Debe seleccionar un distribuidor", Toast.LENGTH_SHORT).show( );
                    }else{
                        if(!principal.isChecked() && !secundaria.isChecked()){
                            Toast.makeText(MainActivity.this, "Debe seleccionar una sucursal", Toast.LENGTH_SHORT).show();
                        }else{
                            Intent intent  = new Intent(getApplicationContext(), review.class);
                            intent.putExtra("medicamento", name);
                            intent.putExtra("tipo", tipe);
                            intent.putExtra("cant", cant);
                            if (cofarma.isChecked()){
                                intent.putExtra("distri", "cofarma");
                            }else{
                                if(empsehar.isChecked()){
                                    intent.putExtra("distri", "empsehar");
                                }else{
                                    if(cemefar.isChecked()){
                                        intent.putExtra("distri", "cemefar");
                                    }
                                }
                            }

                            if (principal.isChecked()){
                                intent.putExtra("sucursal", "FARMACIA PRINCIPAL CALLE FRANCISCO DE MARCO NUMERO 12");
                            }else{
                                if(secundaria.isChecked()){
                                    intent.putExtra("sucursal", "FARMACIA SECUNDARIA ES CALLE ALCAZABILLA No3.");
                                }
                            }

                            startActivity(intent);

                        }
                    }
                }
            }
        });

    }
}