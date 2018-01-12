package com.example.ricardocarlos.schooldatabase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Plataforma extends AppCompatActivity {

    Button bt_ver, bt_aluno, bt_turma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plataforma);

        bt_ver = (Button) findViewById(R.id.bt_ver);
        bt_aluno = (Button) findViewById(R.id.bt_aluno);
        bt_turma = (Button) findViewById(R.id.bt_turma);

        bt_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=SPICFPjbLag")));
                Log.i("Video", "Video Playing....");

            }
        });

        bt_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Plataforma.this,alunolist.class);
                startActivity(i);
            }
        });

        bt_turma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Plataforma.this,turmalist.class);
                startActivity(i);

            }
        });
    }
}
