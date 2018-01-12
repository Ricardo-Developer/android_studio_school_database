package com.example.ricardocarlos.schooldatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText et_username, et_password;
    Button bt_registar, bt_login;
    DataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBase(this);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        bt_login = (Button) findViewById(R.id.bt_login);
        bt_registar = (Button) findViewById(R.id.bt_registar);

        bt_registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                String resultado = db.CriarUtilizador(username, password);
                if (resultado.equals("OK")) {
                    Toast.makeText(MainActivity.this, "Registo OK", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Registo Inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                String resultado = db.ValidarLogin(username, password);
                if (resultado.equals("OK")) {
                    Toast.makeText(MainActivity.this, "Login OK", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this,Plataforma.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Login Inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }

