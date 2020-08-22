package com.danielalonso.materialdesign.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danielalonso.materialdesign.pojo.Mascota;
import com.danielalonso.materialdesign.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    //Constructor
    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    //Inflar el layout y lo pasara al viewHolder para obtener los views
    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    //Asocia cada elemento de la lista con cada View
    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvRaiting.setText(mascota.getRaiting());
        mascotaViewHolder.tvNombre.setText(mascota.getNombre());

        mascotaViewHolder.icHueso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity,"Diste like a "+mascota.getNombre(),Toast.LENGTH_SHORT).show();
                int r = Integer.parseInt(mascota.getRaiting())+1;
                mascota.setRaiting(r);
                mascotaViewHolder.tvRaiting.setText(mascota.getRaiting());
            }
        });

    }

    @Override
    public int getItemCount() { //cantidad de elementos que contiene mi lista
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ShapeableImageView imgFoto;
        private MaterialTextView tvNombre;
        private MaterialTextView tvRaiting;
        private ShapeableImageView icHueso;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = (ShapeableImageView) itemView.findViewById(R.id.imgMascota);
            tvNombre = (MaterialTextView) itemView.findViewById(R.id.tvNombre);
            tvRaiting = (MaterialTextView) itemView.findViewById(R.id.tvRaiting);
            icHueso = (ShapeableImageView) itemView.findViewById(R.id.icHueso);
        }
    }
}
