package com.danielalonso.materialdesign.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielalonso.materialdesign.MainActivity;
import com.danielalonso.materialdesign.R;
import com.danielalonso.materialdesign.adapter.MascotaAdaptador;
import com.danielalonso.materialdesign.pojo.Mascota;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentRecyclerView extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    public FragmentRecyclerView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
        inicializarMascotas();
        inicializarAdaptador();
        // Inflate the layout for this fragment
        return v;
    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        listaMascotas.setAdapter(adaptador);
    }

    public void inicializarMascotas() {
        mascotas = new ArrayList<>();

        mascotas.add(new Mascota("Lorito", R.drawable.pet6, 3));
        mascotas.add(new Mascota("Osito", R.drawable.pet4, 3));
        mascotas.add(new Mascota("Bugs", R.drawable.pet7, 3));
        mascotas.add(new Mascota("Chikis", R.drawable.pet3, 6));
        mascotas.add(new Mascota("Mili", R.drawable.pet2, 8));
        mascotas.add(new Mascota("Max", R.drawable.pet1, 9));
        mascotas.add(new Mascota("Felix", R.drawable.pet5, 2));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {

            mainActivity.showFloatingActionButton(); //fuerza la visibilidad

            FloatingActionButton fab = mainActivity.findViewById(R.id.fabAgregar);
        }
    }
}