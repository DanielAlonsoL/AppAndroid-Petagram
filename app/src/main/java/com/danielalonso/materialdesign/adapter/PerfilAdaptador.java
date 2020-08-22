package com.danielalonso.materialdesign.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danielalonso.materialdesign.R;
import com.danielalonso.materialdesign.pojo.Mascota;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder> {

    @NonNull
    ArrayList<Mascota> mascotas;
    Activity activity;

    //Constructor
    public PerfilAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    //Inflar el layout y lo pasara al viewHolder para obtener los views
    @NonNull
    @Override
    public PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_perfil, parent, false);
        return new PerfilViewHolder(v);
    }


    //Asocia cada elemento de la lista con cada View
    @Override
    public void onBindViewHolder(@NonNull final PerfilAdaptador.PerfilViewHolder perfilViewHolder, int position) {

        final Mascota mascota = mascotas.get(position);
        perfilViewHolder.imgPerfil.setImageResource(mascota.getFoto());
        perfilViewHolder.tvTotalRaiting.setText(mascota.getRaiting());

    }

    @Override
    public int getItemCount() { //cantidad de elementos que contiene mi lista
        return mascotas.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder {

        private ShapeableImageView imgPerfil;
        private MaterialTextView tvTotalRaiting;
        private ShapeableImageView icTotalRaiting;

        public PerfilViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPerfil = (ShapeableImageView) itemView.findViewById(R.id.imgPerfil);
            tvTotalRaiting = (MaterialTextView) itemView.findViewById(R.id.tvTotalRaiting);
            icTotalRaiting = (ShapeableImageView) itemView.findViewById(R.id.icTotalRaiting);
        }
    }}
