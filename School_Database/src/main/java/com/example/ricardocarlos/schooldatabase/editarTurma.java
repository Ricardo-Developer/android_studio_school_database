package com.example.ricardocarlos.schooldatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class editarTurma extends AppCompatActivity {

    EditText et_ano, et_desig;
    Button bt_criar, bt_voltar, bt_Editar;
    RadioButton rb_manha, rb_tarde;

    Intent i;
    int posicao = -1;
    int id=0;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_turma);

        et_ano= (EditText)findViewById(R.id.et_ano);
        et_desig= (EditText)findViewById(R.id.et_desig);
        bt_criar = (Button) findViewById(R.id.bt_criar);
        bt_voltar = (Button) findViewById(R.id.bt_voltar);
        bt_Editar = (Button) findViewById(R.id.bt_Editar);
        rb_manha = (RadioButton) findViewById(R.id.rb_manha);
        rb_tarde = (RadioButton) findViewById(R.id.rb_tarde);

        db = new DataBase(editarTurma.this);

        i = getIntent();


        bt_criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String letra = et_desig.getText().toString();
                int ano = Integer.valueOf(et_ano.getText().toString());
                String resultado = db.criarTurma(ano, letra);
                if (resultado.equals("OK")) {
                    Toast.makeText(editarTurma.this, "Turma Inserida", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(editarTurma.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }

                setResult(1, i);
                finish();
            }
        });
        if (i.getExtras().get("opcao").toString().equals("alterar")) {
            et_ano.setText(i.getExtras().get("ano").toString());
            et_desig.setText(i.getExtras().get("letra").toString());
            posicao = Integer.parseInt(i.getExtras().get("posicao").toString());
            id=Integer.parseInt(i.getExtras().get("id").toString());
        }

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bt_Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String letra = et_desig.getText().toString();
                int ano = Integer.valueOf(et_ano.getText().toString());

                String resultado = db.editarTurma(id, ano, letra);
                if (resultado.equals("OK")) {
                    Toast.makeText(editarTurma.this, "Turma Alterada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(editarTurma.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }

                i = getIntent();
                i.putExtra("ano", ano);
                i.putExtra("letra", letra);
                i.putExtra("posicao", posicao);
                i.putExtra("id",id);
                setResult(1, i);
                finish();


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
