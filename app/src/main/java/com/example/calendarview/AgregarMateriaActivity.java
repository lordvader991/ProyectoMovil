package com.example.calendarview;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AgregarMateriaActivity extends AppCompatActivity {
    private EditText materiaEditText;
    private EditText fechaEditText;
    private EditText horaEditText;
    private DatabaseHelper databaseHelper;
    private Calendar calendar;
    private DateFormat dateFormat;
    private DateFormat timeFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_agregar_materia);

        materiaEditText = findViewById(R.id.edit_text_materia);
        fechaEditText = findViewById(R.id.edit_text_fecha);
        horaEditText = findViewById(R.id.edit_text_hora);

        Button buttonFecha = findViewById(R.id.button_fecha);
        buttonFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        Button buttonHora = findViewById(R.id.button_hora);
        buttonHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        Button buttonGuardar = findViewById(R.id.button_guardar);
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarMateria();
            }
        });

        databaseHelper = new DatabaseHelper(this);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String fecha = dateFormat.format(calendar.getTime());
                        fechaEditText.setText(fecha);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        String hora = timeFormat.format(calendar.getTime());
                        horaEditText.setText(hora);
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );

        timePickerDialog.show();
    }

    private void guardarMateria() {
        String materia = materiaEditText.getText().toString();
        String fecha = fechaEditText.getText().toString();
        String hora = horaEditText.getText().toString();

        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("materia", materia);
        values.put("fecha", fecha);
        values.put("hora", hora);

        long result = database.insert("horarios", null, values);

        if (result != -1) {
            Toast.makeText(this, "Materia guardada: " + materia, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar la materia", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }
}



