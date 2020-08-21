package com.danielalonso.materialdesign;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private FloatingActionButton fabAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Agregando toolbar a la pantalla principal
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaMascotas = findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
        inicializarMascotas();
        inicializarAdaptador();

        fabAgregar = findViewById(R.id.fabAgregar);

        fabAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    //MODICAR PARA SOLICITAR PERMISO
                    int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        abrirCamara();
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, "Por favor aprueba el permiso", Toast.LENGTH_SHORT);
                        toast.show();
                    }
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutMe:
                // User chose the "Settings" item, show the app settings UI...
                Intent about = new Intent(this, AcercaDe.class);
                startActivity(about);
                break;

            case R.id.contact:
                Intent contacto = new Intent(this, Contacto.class);
                startActivity(contacto);
                break;

            case R.id.favorito:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent fav = new Intent(this, Favoritas.class);
                startActivity(fav);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    /*
        @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    */

    public void abrirCamara (){
        //Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivity(i);

        startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
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
}