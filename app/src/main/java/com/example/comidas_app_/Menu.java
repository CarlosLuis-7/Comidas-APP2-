package com.example.comidas_app_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Menu extends AppCompatActivity {
    TextView txt1, txt2, txt3, txt4,pre1,pre2,pre3,pre4;
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
        //direccion de web service
        String sql = "https://appcomida.azurewebsites.net/api/Menus";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url=null;
        HttpURLConnection conn;


        try {
            url = new URL(sql);
            conn=(HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            String json="";

            while((inputLine=in.readLine())!=null){
                response.append(inputLine);

            }
            json = response.toString();
            JSONArray jsonArray = null;

            jsonArray = new JSONArray(json);
            String Plato = "";
            String Precio = "";
            String Descrip = "";

            for (int i=0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Plato =jsonObject.optString("plato");
                Precio = "Precio: $ "+jsonObject.optString("precio");


            }
            txt1.setText(Plato);
            pre1.setText(Precio);

        }
        catch (IOException e){
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();


        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}
