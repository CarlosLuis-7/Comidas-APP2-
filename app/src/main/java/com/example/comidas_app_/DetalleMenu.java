package com.example.comidas_app_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comidas_app_.Modelo.clsMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class DetalleMenu extends AppCompatActivity {

    ImageView img;
    TextView txtNombrePlato, txtDetallePlato, txtCantidad1;
    Button btnAumentar, btnDisminuir;
    private int contador=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_menu);
        txtNombrePlato = findViewById(R.id.txtNombrePlato);
        txtDetallePlato = findViewById(R.id.txtDescripcionPlato);
        txtCantidad1= findViewById(R.id.txtCantidad1);
        btnAumentar = findViewById(R.id.btnAumentar);
        btnDisminuir = findViewById(R.id.btnDisminuir);
        img = findViewById(R.id.imageView);
        Bundle bundle = getIntent().getExtras();


        btnAumentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador++;
                txtCantidad1.setText(""+contador);
            }
        });

        btnDisminuir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contador --;
                txtCantidad1.setText(""+contador );
            }
        });


        int resid = bundle.getInt("resID");
        img.setImageResource(resid);

      /*  String resIDT4 = getIntent().getStringExtra("resIDT4");
        String resIDT3 = getIntent().getStringExtra("resIDT3");
        String resIDT2 = getIntent().getStringExtra("resIDT2");*/

        String resIDT = getIntent().getStringExtra("resIDT");
        txtNombrePlato.setText("-"+resIDT);



       /* String dato = getIntent().getStringExtra("dato");
        txtNombrePlato.setText("--"+ dato);*/
    clsMenu menu = new clsMenu();
    ConsumoRest consumo = new ConsumoRest();
    menu=consumo.getDataMenuDetalle(resIDT);
        txtNombrePlato.setText(menu.getPlato());
        txtNombrePlato.setText(menu.getDescripcion());


    }



}
