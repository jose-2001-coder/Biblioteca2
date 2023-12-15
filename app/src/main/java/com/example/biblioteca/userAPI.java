package com.example.biblioteca;
import com.example.biblioteca.Modelos.prestamos;
import com.example.biblioteca.Modelos.rol;
import com.example.biblioteca.Modelos.tipo_equipo;
import com.example.biblioteca.Modelos.usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface userAPI {

        @GET("/rol/obtener")
        Call<List<rol>> getRoles();

        @GET("/usuario/obtener")
        Call<List<usuario>> getUser();

        @GET("/tipo-equipo/obtener")
        Call<List<tipo_equipo>> getTipoEquipos();

       @POST("/prestamos/crear")
        Call<prestamos> crearPrestamo(@Body prestamos prestar);
}
