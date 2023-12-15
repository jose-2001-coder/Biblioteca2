package com.example.biblioteca.Modelos;

import java.util.ArrayList;
import java.util.Date;

public class prestamos {

    private int usuario;
    private String fechaInicio;
    private String fechaFinal;
    private ArrayList detalle= new ArrayList();

    public ArrayList getDetalle(){
        return detalle;
    }

    public void setDetalle(ArrayList detalle) {
        this.detalle = detalle;
    }

    public int getUsuario() {
        return usuario;
    }
    public void setUsuario(int id) {
        this.usuario = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

}
