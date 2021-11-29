package com.example.drinky.view.iu.clases;

import android.graphics.drawable.Icon;
import android.media.Image;

public class ListElement {

    private int idProducto;
    private Icon imagen;
    private String nombre;
    private int precio;

    public ListElement( String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public Icon getImagen() {
        return imagen;
    }

    public void setImagen(Icon imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

}
