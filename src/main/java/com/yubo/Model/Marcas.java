package com.yubo.Model;

public class Marcas {
    private String nombreEspecilidad;

    public Marcas() {
    }

    public Marcas(String nombreEspecilidad) {
        this.nombreEspecilidad = nombreEspecilidad;
    }

    public String getNombreEspecilidad() {
        return nombreEspecilidad;
    }

    public void setNombreEspecilidad(String nombreEspecilidad) {
        this.nombreEspecilidad = nombreEspecilidad;
    }

    @Override
    public String toString() {
        return  nombreEspecilidad;
    }
}

