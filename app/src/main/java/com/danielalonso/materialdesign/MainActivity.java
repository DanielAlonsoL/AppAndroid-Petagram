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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.danielalonso.materialdesign.adapter.MascotaAdaptador;
import com.danielalonso.materialdesign.adapter.PageAdapter;
import com.danielalonso.materialdesign.fragment.FragmentRecyclerView;
import com.danielalonso.materialdesign.fragment.PerfilFragment;
import com.danielalonso.materialdesign.pojo.Mascota;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FloatingActionButton fabAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Agregando toolbar a la pantalla principal
        toolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setSupportActionBar(toolbar);

        setUpViewPager();
        /*

         */

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

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new FragmentRecyclerView());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_perfil);
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

    public void showFloatingActionButton() {
        fabAgregar.show();
    }

    public void hideFloatingActionButton() {
        fabAgregar.hide();
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


}