package com.danielalonso.materialdesign.fragment;

import com.danielalonso.materialdesign.adapter.MascotaAdaptador;
import com.danielalonso.materialdesign.pojo.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {
    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
