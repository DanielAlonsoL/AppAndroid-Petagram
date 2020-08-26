package com.danielalonso.materialdesign.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.danielalonso.materialdesign.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCrearTablaMascota = "CREATE TABLE "+ConstantesBaseDatos.TABLE_MASCOTAS+"("+
                                        ConstantesBaseDatos.TABLE_MASCOTAS_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                        ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE   + " TEXT, "+
                                        ConstantesBaseDatos.TABLE_MASCOTAS_FOTO     + " INTEGER, "+
                                        ConstantesBaseDatos.TABLE_MASCOTAS_RAITING + " INTEGER "+
                                        ")";
        sqLiteDatabase.execSQL(queryCrearTablaMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ConstantesBaseDatos.TABLE_MASCOTAS);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM "+ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId_mascota(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            //mascotaActual.setRaiting(registros.getInt(3));

            mascotas.add(mascotaActual);
        }

        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }

}
