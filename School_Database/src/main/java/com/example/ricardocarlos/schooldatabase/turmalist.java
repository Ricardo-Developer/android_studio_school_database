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

import static com.example.ricardocarlos.schooldatabase.turma.ListaDown;
import static com.example.ricardocarlos.schooldatabase.turma.ListaUp;


public class turmalist extends AppCompatActivity {

    ListView lv;
    Button bt_add, bt_del, bt_edt, bt_asc, bt_des;

    int position = -1;

    DataBase db;
    ArrayAdapter<turma> adapter;
    List<turma> turmas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turmalist);

        lv = (ListView) findViewById((R.id.lv));
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_del = (Button) findViewById(R.id.bt_del);
        bt_edt = (Button) findViewById(R.id.bt_edt);
        bt_asc = (Button) findViewById(R.id.bt_asc);
        bt_des = (Button) findViewById(R.id.bt_des);

        turmas = new ArrayList<turma>();

        db = new DataBase(turmalist.this);

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
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(turmalist.this, editarTurma.class);
                i.putExtra("opcao", "inserir");
                startActivityForResult(i,1);

            }
        });

        bt_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ano = String.valueOf(turmas.get(position).getAno());
                String letra = turmas.get(position).getLetra().toString();
                String id = String.valueOf(turmas.get(position).getId());

                Intent j = new Intent(turmalist.this, editarTurma.class);
                j.putExtra("ano", ano);
                j.putExtra("letra", letra);
                j.putExtra("id",id);
                j.putExtra("opcao", "alterar");
                j.putExtra("posicao",position);

                startActivityForResult(j, 1);

            }
        });


        bt_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < 0) {
                    Toast.makeText(turmalist.this, "Selecione uma Turma!", Toast.LENGTH_LONG).show();
                } else {
                    String resultado = db.apagarTurma(turmas.get(position).getId());
                    Listar();

                }
            }
        });

       bt_asc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(turmas,ListaUp);
                adapter = new ArrayAdapter<turma>(turmalist.this, android.R.layout.simple_list_item_1, turmas);
                lv.setAdapter(adapter);
            }
        });
        bt_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(turmas,ListaDown);
                adapter = new ArrayAdapter<turma>(turmalist.this, android.R.layout.simple_list_item_1, turmas);
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
        turmas.clear();
        Cursor t = db.verTurma();
        if (t.getCount() > 0) {
            t.moveToFirst();
            do {
                int id = t.getInt(t.getColumnIndex("id"));
                int ano = t.getInt(t.getColumnIndex("ano"));
                String letra= t.getString(t.getColumnIndex("letra")).toString();
                turma a = new turma(id, ano, letra);
                turmas.add(a);
            } while (t.moveToNext());
        }
        ArrayAdapter<turma> adapter = new ArrayAdapter<turma>(this, android.R.layout.simple_list_item_1, turmas);
        lv.setAdapter(adapter);

    }
    }

