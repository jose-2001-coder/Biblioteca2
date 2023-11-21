package com.example.biblioteca;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface userAPI {

        @GET("endpoint/obtenerDatos")
        Call<Respuesta> obtenerDatos();

        @POST("endpoint/enviarDatos")
        Call<Respuesta> enviarDatos(@Body Datos datos);

}
