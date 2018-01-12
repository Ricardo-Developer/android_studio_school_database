package com.example.ricardocarlos.schooldatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.ricardocarlos.schooldatabase.alunos.ListaDown;
import static com.example.ricardocarlos.schooldatabase.alunos.ListaUp;


public class alunolist extends AppCompatActivity {

    ListView lv;
    Button bt_inserir, bt_editar, bt_apagar, bt_asc, bt_des;
    String nome;
    String email;
    int telefone;
    String morada;
    int num;
    int position = -1;

    DataBase db;
    ArrayAdapter<alunos> adapter;
    List<alunos> aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunolist);

        lv = (ListView) findViewById((R.id.lv));
        bt_inserir = (Button) findViewById(R.id.bt_inserir);
        bt_apagar = (Button) findViewById(R.id.bt_apagar);
        bt_editar = (Button) findViewById(R.id.bt_editar);
        bt_asc = (Button) findViewById(R.id.bt_asc);
        bt_des = (Button) findViewById(R.id.bt_des);

        aluno = new ArrayList<alunos>();

        db = new DataBase(alunolist.this);

        Listar();
        /*encotrar posição array*/
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lv.setItemChecked(i, true);
                lv.setSelected(true);
                position = i;

            }
        });


        /*inserir aluno*/
        Intent i = getIntent();
        bt_inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(alunolist.this, editarAluno.class);
                i.putExtra("opcao", "inserir");
                startActivityForResult(i,1);

            }
        });

        bt_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*selecionar item para editar*/
                String nome = aluno.get(position).getNome().toString();
                String email = aluno.get(position).getEmail().toString();
                String morada = aluno.get(position).getMorada().toString();
                String telefone = String.valueOf(aluno.get(position).getTelefone());
                String num = String.valueOf(aluno.get(position).getNumero());
                String id = String.valueOf(aluno.get(position).getId());

                Intent j = new Intent(alunolist.this, editarAluno.class);
                j.putExtra("nome", nome);
                j.putExtra("telefone", telefone);
                j.putExtra("email", email);
                j.putExtra("morada", morada);
                j.putExtra("num", num);
                j.putExtra("id",id);
                j.putExtra("opcao", "alterar");
                j.putExtra("posicao",position);

                startActivityForResult(j, 1);

            }
        });

        bt_apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < 0) {
                    Toast.makeText(alunolist.this, "Selecione um Aluno!", Toast.LENGTH_LONG).show();
                } else {
                    String resultado = db.apagarAluno(aluno.get(position).getId());
                    Listar();

                }
            }
        });

        bt_asc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(aluno,ListaUp);
                adapter = new ArrayAdapter<alunos>(alunolist.this, android.R.layout.simple_list_item_1, aluno);
                lv.setAdapter(adapter);
            }
        });
        bt_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(aluno,ListaDown);
                adapter = new ArrayAdapter<alunos>(alunolist.this, android.R.layout.simple_list_item_1, aluno);
                lv.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Listar();
    }

    private void Listar() {
        aluno.clear();
        Cursor c = db.verAluno();
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                int id = c.getInt(c.getColumnIndex("id"));
                String nome = c.getString(c.getColumnIndex("nome")).toString();
                String email = c.getString(c.getColumnIndex("email")).toString();
                int telefone = c.getInt(c.getColumnIndex("telefone"));
                String morada = c.getString(c.getColumnIndex("morada")).toString();
                int numero = c.getInt(c.getColumnIndex("numero"));

                alunos a = new alunos(id, nome, telefone, email, morada, numero);
                aluno.add(a);
            } while (c.moveToNext());
        }
        ArrayAdapter<alunos> adapter = new ArrayAdapter<alunos>(this, android.R.layout.simple_list_item_1, aluno);
        lv.setAdapter(adapter);

    }

}
