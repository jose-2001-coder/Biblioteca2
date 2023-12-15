package com.example.biblioteca.Modelos;

import java.util.Date;

public class novedades {

    private int id;
    private String descripcion;
    private Date fecha_novedad;
    private String tipo_novedadd;
    private String estado_novedad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_novedad() {
        return fecha_novedad;
    }

    public void setFecha_novedad(Date fecha_novedad) {
        this.fecha_novedad = fecha_novedad;
    }

    public String getTipo_novedadd() {
        return tipo_novedadd;
    }

    public void setTipo_novedadd(String tipo_novedadd) {
        this.tipo_novedadd = tipo_novedadd;
    }

    public String getEstado_novedad() {
        return estado_novedad;
    }

    public void setEstado_novedad(String estado_novedad) {
        this.estado_novedad = estado_novedad;
    }
}
