package com.example.biblioteca.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.biblioteca.Modelos.prestamos;
import com.example.biblioteca.Modelos.tipo_equipo;
import com.example.biblioteca.Modelos.usuario;
import com.example.biblioteca.R;
import com.example.biblioteca.clienteAPI;
import com.example.biblioteca.databinding.FragmentSlideshowBinding;
import com.example.biblioteca.userAPI;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment {
    Spinner spinnerUsuarios, spinnerTiposEquipo;
    Button bcrear;

    EditText f1, f2, h1, h2, cantidad;
    List<usuario> usuarios;
    List<tipo_equipo> tiposEquipo;

    prestamos paquetePrestamo;
    Map<String, Object> detalleEquipo;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        paquetePrestamo= new prestamos();
        spinnerUsuarios = (Spinner) root.findViewById(R.id.usuario);
        spinnerTiposEquipo = (Spinner) root.findViewById(R.id.estado);
        bcrear = (Button) root.findViewById(R.id.Bcrear);
        f1 = (EditText) root.findViewById(R.id.etNombre2);
        f2 = (EditText) root.findViewById(R.id.etCorreo);
        h1 = (EditText) root.findViewById(R.id.etDireccion);
        h2 = (EditText) root.findViewById(R.id.etCedula);
        cantidad=(EditText) root.findViewById(R.id.cantidad);
        detalleEquipo=new HashMap<>();
        getUser();
        getTipoEquipo();
        bcrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               paquetePrestamo.setFechaInicio((f1.getText().toString() + " " + h1.getText().toString()));
               paquetePrestamo.setFechaInicio((f2.getText().toString()+" "+h2.getText().toString()));
                detalleEquipo.put("cantidad",Integer.parseInt(cantidad.getText().toString()));
                Log.d("plus", detalleEquipo.toString());
                ArrayList d= new ArrayList();
                d.add(detalleEquipo);
                paquetePrestamo.setDetalle(d);
                Log.d("error",paquetePrestamo.toString());
                crearPrestamo();
            }
        });

        return root;
    }


    private void getUser() {
        clienteAPI cliente = new clienteAPI();
        userAPI userService = cliente.getClient().create(userAPI.class);
        Call<List<usuario>> call = userService.getUser();

        call.enqueue(new Callback<List<usuario>>() {
            @Override
            public void onResponse(Call<List<usuario>> call, Response<List<usuario>> response) {
                if (response.isSuccessful()) {
                    usuarios =response.body();
                    cargarSpiner(response.body());
                } else {
                    Log.d("error", "No se obtuvieron los usuarios");
                }
            }

            @Override
            public void onFailure(Call<List<usuario>> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
    }

    private void cargarSpiner(List<usuario> usuarios) {
        Log.d("info", usuarios.toString());
        List<String> arrayUsers = new ArrayList<>();
        for (usuario user : usuarios) {
            arrayUsers.add(user.getNombre() + " " + user.getApellidos());
        }
        ArrayAdapter usuarioAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, arrayUsers);
        usuarioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsuarios.setAdapter(usuarioAdapter);
        spinnerUsuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paquetePrestamo.setUsuario(usuarios.get(position).getCedula());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getTipoEquipo() {
        clienteAPI cliente = new clienteAPI();
        userAPI userService = cliente.getClient().create(userAPI.class);
        Call<List<tipo_equipo>> call = userService.getTipoEquipos();

        call.enqueue(new Callback<List<tipo_equipo>>() {
            @Override
            public void onResponse(Call<List<tipo_equipo>> call, Response<List<tipo_equipo>> response) {
                if (response.isSuccessful()) {
                    tiposEquipo = response.body();
                    cargarSpinnerTipo(response.body());
                } else {
                    Log.d("error", "No se obtuvieron los tipos de equipo");
                }
            }

            @Override
            public void onFailure(Call<List<tipo_equipo>> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
    }

    private void cargarSpinnerTipo(List<tipo_equipo> tipoEquipos) {
        List<String> arrayTipo = new ArrayList<>();
        for (tipo_equipo tipos : tipoEquipos) {
            arrayTipo.add(tipos.getDescripcion());
        }
        ArrayAdapter tipoAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, arrayTipo);
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTiposEquipo.setAdapter(tipoAdapter);
        spinnerTiposEquipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                detalleEquipo.put("tipo", tiposEquipo.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void crearPrestamo(){
        clienteAPI cliente = new clienteAPI();
        userAPI userService = cliente.getClient().create(userAPI.class);
        Call<prestamos> call = userService.crearPrestamo(paquetePrestamo);
        call.enqueue(new Callback<prestamos>() {
            @Override
            public void onResponse(Call<prestamos> call, Response<prestamos> response) {
                Log.d("error",response.body().toString());
                if(response.isSuccessful()){
                    Toast.makeText(getContext(),"Préstamo creado", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"No se pudo crear el préstamo", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<prestamos> call, Throwable t) {
                Toast.makeText(getContext(),"Fallo la petición: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
