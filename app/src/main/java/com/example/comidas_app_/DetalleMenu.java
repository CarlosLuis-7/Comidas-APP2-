package com.example.comidas_app_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
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


public class DetalleMenu extends AppCompatActivity {
    ImageView img;
    TextView txtNombrePlato, txtDetallePlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_menu);
        txtNombrePlato = findViewById(R.id.txtNombrePlato);
        txtDetallePlato = findViewById(R.id.txtDescripcionPlato);
        img = findViewById(R.id.imageView);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            int resid = bundle.getInt("resID");
            img.setImageResource(resid);

        }
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
            String nombrePlato = "";


            for (int i=0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                nombrePlato +="plato" +i+""+jsonObject.optString("plato")+ "\n";


            }
            txtNombrePlato.setText(nombrePlato);

        }
        catch (IOException e){
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();


        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

}
