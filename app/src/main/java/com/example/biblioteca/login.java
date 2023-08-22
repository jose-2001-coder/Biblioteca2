package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class login extends AppCompatActivity {

    Button iniciar, crear;
    TextView crear_usuario;
    EditText usuario,contrasena, nombre, correo,direccion,Contrasena,cedula,telefono,rol;
    boolean bien=false;
    String s_usuario,s_contrasena;
    String[] u={"juan","1234"};
    ArrayList<String[]> usuarios = new ArrayList<String[]>();
    @SuppressLint({"SuspiciousIndentation", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciar=(Button)findViewById(R.id.Biniciar);
        usuario=(EditText)findViewById(R.id.usuario);
        contrasena=(EditText)findViewById(R.id.contrasena);
        crear_usuario=(TextView) findViewById(R.id.textView);


        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarios.add(u);
               s_usuario= usuario.getText().toString();
                s_contrasena= contrasena.getText().toString();
                for (int i=0; i<usuarios.size();i++){
                    if (s_usuario.equals(usuarios.get(i)[0]) && s_contrasena.equals(usuarios.get(i)[1])){
                        Intent cambio = new Intent(login.this, MainActivity.class);
                        cambio.putExtra("clave",u);
                        startActivity(cambio);
                            bien=true;
                            usuario.setText("");
                            contrasena.setText("");
                        break;
                    }
                }
                if (bien) {

                    bien=false;
                }else {
                    Toast.makeText(login.this, "Usuario o Contraseña invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        crear_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog cambio = new Dialog(login.this, android.R.style.Widget_Holo);
                cambio.setContentView(R.layout.activity_crear_usuario);
                crear=(Button)cambio.findViewById(R.id.Bcrear);
                nombre=(EditText)cambio.findViewById(R.id.etNombre);
                correo=(EditText)cambio.findViewById(R.id.etCorreo);
                direccion=(EditText)cambio.findViewById(R.id.etDireccion);
                Contrasena=(EditText)cambio.findViewById(R.id.etContrasena);
                cedula=(EditText)cambio.findViewById(R.id.etCedula);
                telefono=(EditText)cambio.findViewById(R.id.etNombre2);
                rol=(EditText)cambio.findViewById(R.id.etRol);
                cambio.show();
                crear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        base_de_datos baseDatos = new base_de_datos(login.this, "BaseDatos", 1);
                        baseDatos.Ecribir("insert into usuarios values(\""+cedula.getText().toString()+"\",\""+nombre.getText().toString()+"\",\""+telefono.getText().toString()+"\",\""+rol.getText().toString()+"\",\""+direccion.getText().toString()+"\",\""+correo.getText().toString()+"\",\""+Contrasena.getText().toString()+"\");");
                    }
                });
            }
        });

    }
}
