package com.example.comidas_app_;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    EditText nom,ape,usu,cel,con;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nom = findViewById(R.id.txtNom);
        ape = findViewById(R.id.txtApe);
        usu = findViewById(R.id.txtUsu);
        cel = findViewById(R.id.txtCel);
        con = findViewById(R.id.txtPwd);
        progressDialog = new ProgressDialog(this);
    }

    public void registrarUsuario()
    {
        //Verificamos que las cajas de texto no esten vacías
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

            String sql ="https://appcomida.azurewebsites.net/api/Usuarios";
            URL url = null;
            try {
                // se crea la conexion al api: https://appcomida.azurewebsites.net/api/Usuarios
                url = new URL(sql);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                //crear el objeto json para enviar por POST
                JSONObject parametrosPost= new JSONObject();
                parametrosPost.put("nombre",nom.getText());
                parametrosPost.put("apellido",ape.getText());
                parametrosPost.put("celular",cel.getText());
                parametrosPost.put("usuar",usu.getText());
                parametrosPost.put("pass",con.getText());

                //DEFINIR PARAMETROS DE CONEXION
                urlConnection.setReadTimeout(15000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setRequestMethod("POST");// se puede cambiar por delete ,put ,etc
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);


                //OBTENER EL RESULTADO DEL REQUEST
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                //writer.write(getPostDataString(parametrosPost));
                writer.flush();
                writer.close();
                os.close();

                int responseCode=urlConnection.getResponseCode();// conexion OK?
                if(responseCode== HttpURLConnection.HTTP_OK){
                    BufferedReader in= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                    StringBuffer sb= new StringBuffer("");
                    String linea="";
                    while ((linea=in.readLine())!= null){
                        sb.append(linea);
                        break;

                    }
                    in.close();
                    Toast.makeText(getApplicationContext(),sb.toString(), Toast.LENGTH_SHORT);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error: "+ responseCode, Toast.LENGTH_SHORT);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();
        }

    //FUNCIONES----------------------------------------------------------------------
    //Transformar JSON Obejct a String *******************************************

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
}

