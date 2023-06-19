package com.example.calendarview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.HorarioViewHolder> {
    private List<Horario> horariosList;

    public HorarioAdapter(List<Horario> horariosList) {
        this.horariosList = horariosList;
    }

    @NonNull
    @Override
    public HorarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horario, parent, false);
        return new HorarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorarioViewHolder holder, int position) {
        Horario horario = horariosList.get(position);

        holder.materiaTextView.setText(horario.getMateria());
        holder.fechaTextView.setText(horario.getFecha());
        holder.horaTextView.setText(horario.getHora());
    }

    @Override
    public int getItemCount() {
        return horariosList.size();
    }

    public static class HorarioViewHolder extends RecyclerView.ViewHolder {
        public TextView materiaTextView;
        public TextView fechaTextView;
        public TextView horaTextView;

        public HorarioViewHolder(@NonNull View itemView) {
            super(itemView);
            materiaTextView = itemView.findViewById(R.id.text_view_materia);
            fechaTextView = itemView.findViewById(R.id.text_view_fecha);
            horaTextView = itemView.findViewById(R.id.text_view_hora);
        }
    }
}
