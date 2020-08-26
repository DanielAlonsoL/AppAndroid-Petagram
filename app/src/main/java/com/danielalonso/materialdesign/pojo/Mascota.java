package com.danielalonso.materialdesign.pojo;

public class Mascota {

    private int id_mascota;
    private String nombre;
    private int foto;
    private int raiting;

    public Mascota(String nombre, int foto, int raiting) {
        this.nombre = nombre;
        this.foto=foto;
        this.raiting=raiting;
    }

    public String getRaiting() {
        return String.valueOf(raiting);
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }
    public Mascota() {
    }

}
