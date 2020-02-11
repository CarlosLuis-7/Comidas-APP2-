package com.example.comidas_app_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.View;
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
import java.util.ArrayList;

public class Menu extends AppCompatActivity implements View.OnClickListener{
    TextView txt1, txt2, txt3, txt4, pre1, pre2, pre3, pre4;
    ImageView img1,img2,img3,img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        txt1 = findViewById(R.id.txtProd1);
        txt2 = findViewById(R.id.txtprod2);
        txt3 = findViewById(R.id.txtProd3);
        txt4 = findViewById(R.id.txtProd4);
        pre1 = findViewById(R.id.txtPrecio1);
        pre2 = findViewById(R.id.txtprecio2);
        pre3 = findViewById(R.id.txtprecio3);
        pre4 = findViewById(R.id.txtPrecio4);

        getData();

    }

   public void EnviarDatosPlato1 (View view){
       Intent abrir_prod1 = new Intent(Menu.this, DetalleMenu.class);
       abrir_prod1.putExtra("resID",R.drawable.seco_guanta);
       abrir_prod1.putExtra("resIDT", txt1.getText().toString());


       startActivity(abrir_prod1);
    }

    public void EnviarDatosPlato2 (View view){
        Intent abrir_prod2 = new Intent(Menu.this, DetalleMenu.class);
        abrir_prod2.putExtra("resID",R.drawable.seco_pollo);
        abrir_prod2.putExtra("resIDT2", txt2.getText().toString());

        startActivity(abrir_prod2);
    }

    public void EnviarDatosPlato3 (View view){
        Intent abrir_prod3 = new Intent(Menu.this, DetalleMenu.class);
        abrir_prod3.putExtra("resID",R.drawable.fritada);
        abrir_prod3.putExtra("resIDT3", txt3.getText().toString());

        startActivity(abrir_prod3);
    }

    public void EnviarDatosPlato4 (View view){
        Intent abrir_prod4 = new Intent(Menu.this, DetalleMenu.class);
        abrir_prod4.putExtra("resID",R.drawable.ceviche_camaron);
        abrir_prod4.putExtra("resIDT4", txt4.getText().toString());

        startActivity(abrir_prod4);
    }

    public void getData() {
        ConsumoRest datos = new ConsumoRest();
        ArrayList<clsMenu> Datas = datos.getDataMenu();
        for (int i = 0; i < Datas.size(); i++) {
            if (i == 0) {
                txt1.setText(Datas.get(i).getPlato());
                pre1.setText(Double.toString(Datas.get(i).getPrecio()));
            } else if (i == 1) {
                txt2.setText(Datas.get(i).getPlato());
                pre2.setText(Double.toString(Datas.get(i).getPrecio()));
            } else if (i == 2) {
                txt3.setText(Datas.get(i).getPlato());
                pre3.setText(Double.toString(Datas.get(i).getPrecio()));
            } else if (i == 3) {
                txt4.setText(Datas.get(i).getPlato());
                pre4.setText(Double.toString(Datas.get(i).getPrecio()));
            }


        }

    }

   /* public void plato1(){
        Intent abrir_prod1 = new Intent(Menu.this, DetalleMenu.class);
        abrir_prod1.putExtra("resID",R.drawable.seco_guanta);
        startActivity(abrir_prod1);
    }*/



    /*Intent i = new Intent(this, DetalleMenu.class);
        i.putExtra("dato", txt1.getText().toString());
    startActivity(i);*/



  /*  public void plato2(){
        Intent abrir_prod1 = new Intent(Menu.this, DetalleMenu.class);
        abrir_prod1.putExtra("resID",R.drawable.seco_pollo);
        abrir_prod1.putExtra("resIDT", txt1.getText().toString());
        startActivity(abrir_prod1);

    }*/
    public void plato3(){
        Intent abrir_prod1 = new Intent(Menu.this, DetalleMenu.class);
        abrir_prod1.putExtra("resID",R.drawable.fritada);
        startActivity(abrir_prod1);

    }
    public void plato4(){
        Intent abrir_prod1 = new Intent(Menu.this, DetalleMenu.class);
        abrir_prod1.putExtra("resID",R.drawable.ceviche_camaron);
        startActivity(abrir_prod1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv0:
              //  plato1();
                break;
            case R.id.cv1:
              //   plato2();
                break;
            case R.id.cv2:
            //    plato3();
                break;
            case R.id.cv3:
               // plato4();
                break;
        }
    }
}
