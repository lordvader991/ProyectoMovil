package com.example.calendarview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MostrarHorariosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HorarioAdapter horarioAdapter;
    private List<Horario> horariosList;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_mostrar_horarios);

        recyclerView = findViewById(R.id.recycler_view_horarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getReadableDatabase();

        horariosList = new ArrayList<>();
        horarioAdapter = new HorarioAdapter(horariosList);
        recyclerView.setAdapter(horarioAdapter);

        cargarHorarios();
    }

    private void cargarHorarios() {
        horariosList.clear();

        Cursor cursor = database.query("horarios", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String materia = cursor.getString(cursor.getColumnIndex("materia"));
                String fecha = cursor.getString(cursor.getColumnIndex("fecha"));
                String hora = cursor.getString(cursor.getColumnIndex("hora"));

                Horario horario = new Horario(materia, fecha, hora);
                horariosList.add(horario);
            } while (cursor.moveToNext());
        }

        cursor.close();

        horarioAdapter.notifyDataSetChanged();
    }
}
