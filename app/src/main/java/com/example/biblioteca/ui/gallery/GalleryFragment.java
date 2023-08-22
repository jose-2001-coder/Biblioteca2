package com.example.biblioteca.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.biblioteca.R;
import com.example.biblioteca.base_de_datos;
import com.example.biblioteca.databinding.FragmentGalleryBinding;
import com.example.biblioteca.login;

public class GalleryFragment extends Fragment {

    ScrollView clase;
    Button borrar,guardar;
    EditText serial,referencia,codigo;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Spinner spinner=(Spinner)  root.findViewById(R.id.spinner);
        TextView txt=(TextView) root.findViewById(R.id.selected2);
        ImageView img=(ImageView)root.findViewById(R.id.imageView2);
        ScrollView clase=(ScrollView)root.findViewById(R.id.contenedor);
        Button borrar=(Button) root.findViewById(R.id.button2);
        Button guardar=(Button) root.findViewById(R.id.button);
        EditText serial=(EditText)root.findViewById(R.id.serial);
        EditText referencia=(EditText)root.findViewById(R.id.referencia);
        EditText codigo=(EditText)root.findViewById(R.id.codigo);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position){

                        case 0:
                            clase.setVisibility(View.INVISIBLE);
                            break;
                        case 1:
                            clase.setVisibility(View.VISIBLE);
                            txt.setText(parent.getSelectedItem().toString());
                            img.setImageResource(R.drawable.video2);
                            break;
                        case 2:
                            clase.setVisibility(View.VISIBLE);
                            txt.setText(parent.getSelectedItem().toString());
                            img.setImageResource(R.drawable.pc);
                            break;
                        case 3:
                            clase.setVisibility(View.VISIBLE);
                            txt.setText(parent.getSelectedItem().toString());
                            img.setImageResource(R.drawable.images);
                            break;
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serial.setText(null);
                referencia.setText(null);
                codigo.setText(null);
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                base_de_datos baseDatos = new base_de_datos(getContext(), "BaseDatos", 1);

            }
        });

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}