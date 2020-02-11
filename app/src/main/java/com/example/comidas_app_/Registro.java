package com.example.comidas_app_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class Registro extends AppCompatActivity {
    EditText nom, ape, usu, cel, con;
    Button guar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nom = findViewById(R.id.txtNom);
        ape = findViewById(R.id.txtApe);
        usu = findViewById(R.id.txtUsu);
        cel = findViewById(R.id.txtCel);
        con = findViewById(R.id.txtPwd);
        guar = findViewById(R.id.btnGuardar);

        guar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }

    public void registrarUsuario() {
        /*/Verificamos que las cajas de texto no esten vacías
        if (null == usu.getText()) {
            Toast.makeText(this, "Se debe ingresar un usuario", Toast.LENGTH_LONG).show();
            return;
        }
        if (null == nom.getText()) {
            Toast.makeText(this, "Se debe ingresar un nombre", Toast.LENGTH_LONG).show();
            return;
        }
        if (null == ape.getText()) {
            Toast.makeText(this, "Se debe ingresar un apellido", Toast.LENGTH_LONG).show();
            return;
        }
        if (null == cel.getText()) {
            Toast.makeText(this, "Se debe ingresar un celular", Toast.LENGTH_LONG).show();
            return;
        }

        if (null == con.getText()) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }
*/
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String sql = "https://appcomida.azurewebsites.net/api/Usuarios";

                    URL url = new URL(sql);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject parametrosPost = new JSONObject();
                    parametrosPost.put("nombre", nom.getText());
                    parametrosPost.put("apellido", ape.getText());
                    parametrosPost.put("celular", cel.getText());
                    parametrosPost.put("usuar", usu.getText());
                    parametrosPost.put("pass", con.getText());


                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(parametrosPost.toString());

                    os.flush();
                    os.close();

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();


    }
}

