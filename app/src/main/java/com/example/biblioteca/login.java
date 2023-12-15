package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.biblioteca.Modelos.rol;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class login extends AppCompatActivity {

    Button iniciar, crear;
    TextView crear_usuario;
    EditText usuario, contrasena, nombre, correo, direccion, Contrasena, cedula, telefono;
    Spinner rol;
    boolean bien = false;
    String s_usuario, s_contrasena;
    String[] u = {"juan", "1234"};
    ArrayList<String[]> usuarios = new ArrayList<String[]>();

    @SuppressLint({"SuspiciousIndentation", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciar = (Button) findViewById(R.id.Biniciar);
        usuario = (EditText) findViewById(R.id.usuario);
        contrasena = (EditText) findViewById(R.id.contrasena);
        crear_usuario = (TextView) findViewById(R.id.textView);


        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarios.add(u);
                s_usuario = usuario.getText().toString();
                s_contrasena = contrasena.getText().toString();
                for (int i = 0; i < usuarios.size(); i++) {
                    if (s_usuario.equals(usuarios.get(i)[0]) && s_contrasena.equals(usuarios.get(i)[1])) {
                        Intent cambio = new Intent(login.this, MainActivity.class);
                        cambio.putExtra("clave", u);
                        startActivity(cambio);
                        bien = true;
                        usuario.setText("");
                        contrasena.setText("");
                        break;
                    }
                }
                if (bien) {

                    bien = false;
                } else {
                    Toast.makeText(login.this, "Usuario o Contraseña invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        crear_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRoles();
                Dialog cambio = new Dialog(login.this, android.R.style.Widget_Holo);
                cambio.setContentView(R.layout.activity_crear_usuario);
                crear = (Button) cambio.findViewById(R.id.Bcrear);
                nombre = (EditText) cambio.findViewById(R.id.etNombre);
                correo = (EditText) cambio.findViewById(R.id.etCorreo);
                direccion = (EditText) cambio.findViewById(R.id.etDireccion);
                Contrasena = (EditText) cambio.findViewById(R.id.etContrasena);
                cedula = (EditText) cambio.findViewById(R.id.etCedula);
                telefono = (EditText) cambio.findViewById(R.id.etNombre2);
                rol = (Spinner) cambio.findViewById(R.id.etRol);
                cambio.show();
                crear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedRol = rol.getSelectedItem().toString();
                        base_de_datos baseDatos = new base_de_datos(login.this, "BaseDatos", 1);
                        baseDatos.Ecribir("insert into usuarios values(\"" + cedula.getText().toString() + "\",\"" + nombre.getText().toString() + "\",\"" + telefono.getText().toString() + "\",\"" + direccion.getText().toString() + "\",\"" + correo.getText().toString() + "\",\"" + Contrasena.getText().toString() + "\");");
                    }
                });
            }
        });

    }


    public void getRoles() {
        clienteAPI cliente = new clienteAPI();
        userAPI userService = cliente.getClient().create(userAPI.class);
        Call<List<rol>> call = userService.getRoles();
        call.enqueue(new Callback<List<com.example.biblioteca.Modelos.rol>>() {
            @Override
            public void onResponse(Call<List<com.example.biblioteca.Modelos.rol>> call, Response<List<com.example.biblioteca.Modelos.rol>> response) {

                if (response.isSuccessful()) {
                    List<String> roles = new ArrayList<>();
                    for (rol r : response.body()) {
                        roles.add(r.getDescripcion());
                    }

                    Log.d("Ricardota1", roles.toString());
                }

            }

            @Override
            public void onFailure(Call<List<com.example.biblioteca.Modelos.rol>> call, Throwable t) {
                Log.d("Ricardota", t.getMessage());
                Toast.makeText(getApplicationContext(), "No se pudieron obtener los roles: ", Toast.LENGTH_LONG).show();
            }
        });
    }
}
