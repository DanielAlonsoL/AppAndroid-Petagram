package com.danielalonso.materialdesign.db;

import android.content.ContentValues;
import android.content.Context;

import com.danielalonso.materialdesign.R;
import com.danielalonso.materialdesign.pojo.Mascota;

import java.util.ArrayList;

public class ConstructorMascotas {

    private Context context;
    private static final int LIKE=0;

    public ConstructorMascotas(Context context){
        this.context = context;
    }

    public ArrayList<Mascota> obtenerMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        /*
        mascotas.add(new Mascota("Lorito", R.drawable.pet6, 3));
        mascotas.add(new Mascota("Osito", R.drawable.pet4, 3));
        mascotas.add(new Mascota("Bugs", R.drawable.pet7, 3));
        mascotas.add(new Mascota("Chikis", R.drawable.pet3, 6));
        mascotas.add(new Mascota("Mili", R.drawable.pet2, 8));
        mascotas.add(new Mascota("Max", R.drawable.pet1, 9));
        mascotas.add(new Mascota("Felix", R.drawable.pet5, 2));
         */

        BaseDatos db = new BaseDatos(context);
        insertarMascotas(db);

        return db.obtenerTodasLasMascotas();
    }

    public void insertarMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Lorito");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,R.drawable.pet6);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING,LIKE);

        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Osito");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,R.drawable.pet4);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING,LIKE);

        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Bugs");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,R.drawable.pet7);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING,LIKE);

        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Chikis");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,R.drawable.pet3);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING, LIKE);

        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Mili");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,R.drawable.pet2);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING,LIKE);

        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Max");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,R.drawable.pet1);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING,LIKE);

        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Felix");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,R.drawable.pet5);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING,LIKE);

        db.insertarMascota(contentValues);
    }
}
