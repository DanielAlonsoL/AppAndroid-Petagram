package com.danielalonso.materialdesign.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielalonso.materialdesign.MainActivity;
import com.danielalonso.materialdesign.R;
import com.danielalonso.materialdesign.adapter.MascotaAdaptador;
import com.danielalonso.materialdesign.adapter.PerfilAdaptador;
import com.danielalonso.materialdesign.pojo.Mascota;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    MainActivity mainActivity;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment PerfilFragment.
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvPerfil);

        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        //llm.setOrientation(GridLayoutManager.);

        listaMascotas.setLayoutManager(glm);

        inicializarMascotas();
        inicializarAdaptador();
        // Inflate the layout for this fragment
        return v;
    }

    public void inicializarAdaptador(){
        PerfilAdaptador adaptador = new PerfilAdaptador(mascotas, getActivity());
        listaMascotas.setAdapter(adaptador);
    }

    public void inicializarMascotas() {
        mascotas = new ArrayList<>();

        mascotas.add(new Mascota("Snoopy", R.drawable.perfil_2, 3));
        mascotas.add(new Mascota("Snoopy", R.drawable.perfil_3, 7));
        mascotas.add(new Mascota("Snoopy", R.drawable.perfil_4, 3));
        mascotas.add(new Mascota("Snoopy", R.drawable.perfil_5, 5));
        mascotas.add(new Mascota("Snoopy", R.drawable.perfil_6, 8));
        mascotas.add(new Mascota("Snoopy", R.drawable.perfil_1, 9));
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

            mainActivity.hideFloatingActionButton(); //fuerza la visibilidad

            FloatingActionButton fab = mainActivity.findViewById(R.id.fabAgregar);
        }
    }
}