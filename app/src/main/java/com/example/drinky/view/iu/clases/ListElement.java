package com.example.drinky.view.iu.clases;

import android.graphics.drawable.Icon;
import android.media.Image;

import java.io.Serializable;

public class ListElement implements Serializable {

    private int idProducto;
    private String imagen;
    private String nombre;
    private int precio;
    private String descripcion;

    public ListElement( String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
