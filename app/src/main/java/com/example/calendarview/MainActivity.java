package com.example.calendarview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button agregarButton;
    private Button mostrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);

        agregarButton = findViewById(R.id.button_agregar);
        agregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un intent para iniciar la actividad AgregarMateriaActivity
                Intent intent = new Intent(MainActivity.this, AgregarMateriaActivity.class);
                startActivity(intent);
            }
        });

        mostrarButton = findViewById(R.id.button_mostrar);
        mostrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un intent para iniciar la actividad MostrarHorariosActivity
                Intent intent = new Intent(MainActivity.this, MostrarHorariosActivity.class);
                startActivity(intent);
            }
        });
    }
}



