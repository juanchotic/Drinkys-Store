package com.example.drinky.view.iu.clases;

import android.graphics.drawable.Icon;
import android.media.Image;

import java.io.Serializable;

public class ListElement implements Serializable {

    private int precio;
    private boolean popular;
    private String idProducto, imagen, nombre, categoria, descripcion;

    public ListElement( String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.popular = false;
        this.descripcion = "";
        this.categoria = "";
        this.idProducto = "";
    }

    public ListElement(int precio, String nombre, String categoria, String descripcion) {
        this.precio = precio;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.popular = false;
    }

    public ListElement(int precio, boolean popular, String nombre, String categoria, String descripcion) {
        this.idProducto = "";
        this.precio = precio;
        this.popular = popular;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public ListElement(String idProducto, int precio, boolean popular, String nombre, String categoria, String descripcion) {
        this.precio = precio;
        this.popular = popular;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public ListElement(String idProducto, int precio, boolean popular, String imagen, String nombre, String categoria, String descripcion) {
        this.idProducto = idProducto;
        this.precio = precio;
        this.popular = popular;
        this.imagen = imagen;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
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

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }
}
