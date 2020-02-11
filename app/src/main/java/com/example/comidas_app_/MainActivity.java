package com.example.comidas_app_;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //defining view objects
    EditText TextUsuario, TextPassword;
    Button btnRegistrar, btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextUsuario = findViewById(R.id.txt_usuario);
        TextPassword = findViewById(R.id.txt_password);
        btnRegistrar = findViewById(R.id.btn_registrar);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);

    }

     private void loginUsuario1(){
         String url = "https://appcomida.azurewebsites.net/api/Usuarios";
         RequestQueue requestQueue = Volley.newRequestQueue(this);
         StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
                 if (response.trim().equals("Success")) {
                     Toast.makeText(getApplicationContext(), "Ingreso exitoso", Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(getApplicationContext(), Menu.class);
                     startActivity(intent);
                 } else {
                     Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                 }
             }
         }, new Response.ErrorListener(){
             @Override
             public void onErrorResponse(VolleyError error){
                 Toast.makeText(MainActivity.this, "error: "+ error.toString(),Toast.LENGTH_LONG).show();

             }
         }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<String, String>();
                params.put("usuar",TextUsuario.getText().toString());
                params.put("pass",TextPassword.getText().toString());
                return params;
             }
         };
         requestQueue.add(stringRequest);

        /*/Obtenemos el email y la contraseña desde las cajas de texto
        String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        // logeamos el usuario
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Bienvenido: " + TextEmail.getText(), Toast.LENGTH_LONG).show();
                            Intent abrir_v2 = new Intent(MainActivity.this, Menu.class );
                            startActivity(abrir_v2);


                        } else {

                            if (task.getException() instanceof FirebaseAuthUserCollisionException) { // cuando haya una colision


                                Toast.makeText(MainActivity.this, "El usuario ya existe ", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
                        }

                        progressDialog.dismiss();
                    }
                });
*/
    }
  /*  private void loginUsuario2() {
        String url = "https://appcomida.azurewebsites.net/api/Usuarios";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objres = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject objres = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), objres.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Menu.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error: " + error.toString(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usuar", TextUsuario.getText().toString());
                params.put("pass", TextPassword.getText().toString());
                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
*/
    private void registrarUsuario() {

        Intent abrir_reg = new Intent(MainActivity.this, Registro.class );
        startActivity(abrir_reg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_registrar:
                registrarUsuario();
                break;
            case R.id.btnLogin:
                loginUsuario1();
                break;
        }
    }
}
