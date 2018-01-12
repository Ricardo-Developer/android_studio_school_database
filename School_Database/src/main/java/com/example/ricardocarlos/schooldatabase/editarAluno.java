package com.example.ricardocarlos.schooldatabase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editarAluno extends AppCompatActivity {

    EditText et_nome, et_numero, et_email, et_telefone, et_morada;
    Button bt_salvar, bt_voltar, bt_edit, bt_ligar, bt_mapa, bt_email;
    Intent i;
    int posicao = -1;
    int id=0;


    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_aluno);

        et_nome = (EditText) findViewById(R.id.et_nome);
        et_numero = (EditText) findViewById(R.id.et_numero);
        et_email = (EditText) findViewById(R.id.et_email);
        et_telefone = (EditText) findViewById(R.id.et_telefone);
        et_morada = (EditText) findViewById(R.id.et_morada);

        bt_salvar = (Button) findViewById(R.id.bt_salvar);
        bt_voltar = (Button) findViewById(R.id.bt_voltar);
        bt_edit = (Button) findViewById(R.id.bt_edit);
        bt_ligar = (Button) findViewById(R.id.bt_ligar);
        bt_mapa = (Button) findViewById(R.id.bt_mapa);
        bt_email = (Button) findViewById(R.id.bt_email);

        db = new DataBase(editarAluno.this);

        i = getIntent();


        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = et_nome.getText().toString();
                int telefone = Integer.valueOf(et_telefone.getText().toString());
                int num = Integer.valueOf(et_numero.getText().toString());
                String morada = et_morada.getText().toString();
                String email = et_email.getText().toString();


                String resultado = db.criarAluno(nome, num, morada, email, telefone);
                if (resultado.equals("OK")) {
                    Toast.makeText(editarAluno.this, "Aluno Inserido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(editarAluno.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }

                /*i = getIntent();
                i.putExtra("nome", nome);
                i.putExtra("telefone", telefone);
                i.putExtra("email", email);
                i.putExtra("morada", morada);
                i.putExtra("num", num);
                i.putExtra("posicao", posicao);*/
                setResult(1, i);
                finish();
            }
        });

        if (i.getExtras().get("opcao").toString().equals("alterar")) {
            et_nome.setText(i.getExtras().get("nome").toString());
            et_telefone.setText(i.getExtras().get("telefone").toString());
            et_email.setText(i.getExtras().get("email").toString());
            et_morada.setText(i.getExtras().get("morada").toString());
            et_numero.setText(i.getExtras().get("num").toString());
            posicao = Integer.parseInt(i.getExtras().get("posicao").toString());
            id=Integer.parseInt(i.getExtras().get("id").toString());
        }

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

       bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nome = et_nome.getText().toString();
                int telefone = Integer.valueOf(et_telefone.getText().toString());
                int num = Integer.valueOf(et_numero.getText().toString());
                String morada = et_morada.getText().toString();
                String email = et_email.getText().toString();




                String resultado = db.editarAluno(id, nome, num, morada, email, telefone);
                if (resultado.equals("OK")) {
                    Toast.makeText(editarAluno.this, "Aluno Alterado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(editarAluno.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }

                i = getIntent();
                i.putExtra("nome", nome);
                i.putExtra("telefone", telefone);
                i.putExtra("email", email);
                i.putExtra("morada", morada);
                i.putExtra("num", num);
                i.putExtra("posicao", posicao);
                i.putExtra("id",id);
                setResult(1, i);
                finish();


            }
        });

        bt_ligar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_telefone.getText().toString().equals("")) {
                    Toast.makeText(editarAluno.this, "Selecione Contacto", Toast.LENGTH_SHORT).show();
                }else {
                    Uri number = Uri.parse("tel:" + et_telefone.getText().toString());
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                    startActivity(callIntent);
                }
            }
        });

        bt_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_email.getText().toString().equals("")) {
                    Toast.makeText(editarAluno.this, "Email vazio", Toast.LENGTH_SHORT).show();
                } else {
                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);


                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{et_email.getText().toString()});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email para " + et_nome.getText().toString());
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "E-mail gerado pelo App Colégio Nossa Senhora");
                    emailIntent.setType("text/plain");
                    startActivity(emailIntent);
                }}
        });

        bt_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_morada.getText().toString().equals("")) {
                    Toast.makeText(editarAluno.this, "Morada vazia!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(editarAluno.this, MapsActivity.class);

                    i.putExtra("morada", et_morada.getText().toString());
                    i.putExtra("nome", et_nome.getText().toString());
                    startActivityForResult(i, 1);}
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == 1) {

        }
    }
}
