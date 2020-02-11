package com.example.comidas_app_;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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
                ConsumoRest consumo = new ConsumoRest();
               if(consumo.getDataUsuarios(TextUsuario.getText().toString(), TextPassword.getText().toString())){
                   Intent abrir_reg = new Intent(MainActivity.this, Menu.class );
                   startActivity(abrir_reg);
               } else {
                   Toast.makeText(getApplicationContext(),"Credenciales incorrectas", Toast.LENGTH_SHORT);
               }


               // loginUsuario1();
                break;
        }
    }
}
