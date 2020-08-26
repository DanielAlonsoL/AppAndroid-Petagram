package com.danielalonso.materialdesign.presentador;

import android.content.Context;

import com.danielalonso.materialdesign.db.ConstructorMascotas;
import com.danielalonso.materialdesign.fragment.IRecyclerViewFragmentView;
import com.danielalonso.materialdesign.pojo.Mascota;

import java.util.ArrayList;

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerMascotas();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}
