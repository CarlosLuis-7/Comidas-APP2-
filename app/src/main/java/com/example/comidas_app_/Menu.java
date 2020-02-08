package com.example.comidas_app_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.StrictMode;
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

public class Menu extends AppCompatActivity {
    TextView txt1, txt2, txt3, txt4,pre1,pre2,pre3,pre4;
    CardView car, car1,car2,car3;
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
    public void getData(){
        ConsumoRest datos = new ConsumoRest();
        ArrayList<clsMenu> Datas = datos.getDataMenu();
        for(int i = 0;i<Datas.size();i++){
            if (i == 0) {
                txt1.setText(Datas.get(i).getPlato());
                pre1.setText(Double.toString(Datas.get(i).getPrecio()));
            }
            else if (i == 1) {
                txt2.setText(Datas.get(i).getPlato());
                pre2.setText(Double.toString(Datas.get(i).getPrecio()));
            }
            else if (i == 2) {
                txt3.setText(Datas.get(i).getPlato());
                pre3.setText(Double.toString(Datas.get(i).getPrecio()));
            }
            else if (i == 3) {
                txt4.setText(Datas.get(i).getPlato());
                pre4.setText(Double.toString(Datas.get(i).getPrecio()));
            }


        }

    }
}
