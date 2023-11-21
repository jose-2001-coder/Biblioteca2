package com.example.biblioteca;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class clienteAPI {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://tuservidorvuejs.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    servicio apiService = retrofit.create(servicio.class);

}
