package com.example.comidas_app_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Registro extends AppCompatActivity {
    private EditText nom,ape,usu,cel,con;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        firebaseAuth = FirebaseAuth.getInstance();
        nom = findViewById(R.id.txtNom);
        ape = findViewById(R.id.txtApe);
        usu = findViewById(R.id.txtUsu);
        cel = findViewById(R.id.txtCel);
        con = findViewById(R.id.txtPwd);
        progressDialog = new ProgressDialog(this);
    }

    public void registrarUsuario(View V) {

        //Obtenemos el email y la contraseña desde las cajas de texto
        String usuario = usu.getText().toString().trim();
        String password = con.getText().toString().trim();
        String apellido = ape.getText().toString().trim();
        String nombre = nom.getText().toString().trim();
        String celular = cel.getText().toString().trim();


        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(usuario)) {
            Toast.makeText(this, "Se debe ingresar un usuario", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(this, "Se debe ingresar un nombre", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(apellido)) {
            Toast.makeText(this, "Se debe ingresar un apellido", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(celular)) {
            Toast.makeText(this, "Se debe ingresar un celular", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();


/*        firebaseAuth.createUserWithEmailAndPassword(usuario, nombre, apellido, celular, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(Registro.this, "Se ha registrado el usuario: " + usu.getText(), Toast.LENGTH_LONG).show();
                        } else {

                            if (task.getException() instanceof FirebaseAuthUserCollisionException) { // cuando haya una colision


                                Toast.makeText(Registro.this, "El usuario ya existe ", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Registro.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
                        }

                        progressDialog.dismiss();
                    }
                });
*/
    }
}
