package com.example.ricardocarlos.schooldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ricardo Carlos on 01/09/2017.
 */


public class DataBase extends SQLiteOpenHelper {

    private static int versao = 1;
    private static String nomeBD = "ExemploBaseDados";

    public  DataBase(Context context) {
        super(context, nomeBD, null, versao);
    }

    //* sistema login
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tabela = "CREATE TABLE Utilizador(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE, password TEXT);";
        String tabela2 = "CREATE TABLE ALUNOS(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, numero INTEGER, morada TEXT, email TEXT, telefone INTEGER );";
        String tabela3 = "CREATE TABLE TURMAS(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ano INTEGER, letra TEXT);";


        db.execSQL(tabela);
        db.execSQL(tabela2);
        db.execSQL(tabela3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        versao++;
        db.execSQL("DROP TABLE IF EXISTS Utilizador;");
        onCreate(db);
    }

    public String ValidarLogin(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE username = ? AND password = ?",
                new String[]{username, password});

        if (c.getCount() > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }

    public String CriarUtilizador (String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        long resultado = db.insert("Utilizador", null, cv);
        if (resultado > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }

    //* crud alunos

    public Cursor verAluno () {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM ALUNOS",
                null);
        return c;

    }

    public String criarAluno (String nome, int numero, String morada, String email, int telefone) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", nome);
        cv.put("numero", numero);
        cv.put("telefone", telefone);
        cv.put("morada", morada);
        cv.put("email", email);
        long resultado = db.insert("ALUNOS", null, cv);
        if (resultado > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }

    public String apagarAluno (int id) {
        SQLiteDatabase db = getWritableDatabase();
        long resultado = db.delete("ALUNOS", "id=?", new String[]{String.valueOf(id)});
        if (resultado > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }

    public String editarAluno (int id, String nome, int numero, String morada, String email, int telefone) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", nome);
        cv.put("numero", numero);
        cv.put("telefone", telefone);
        cv.put("morada", morada);
        cv.put("email", email);
        long resultado = db.update("ALUNOS", cv,"id=?",new String[]{String.valueOf(id)});
        if (resultado > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }

    //* crud turmas

    public Cursor verTurma () {
        SQLiteDatabase db = getReadableDatabase();
        Cursor t = db.rawQuery("SELECT * FROM TURMAS",
                null);
        return t;

    }

    public String criarTurma ( int ano, String letra) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ano", ano);
        cv.put("letra", letra);

        long resultado = db.insert("TURMAS", null, cv);
        if (resultado > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }

    public String apagarTurma (int id) {
        SQLiteDatabase db = getWritableDatabase();
        long resultado = db.delete("TURMAS", "id=?", new String[]{String.valueOf(id)});
        if (resultado > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }

    public String editarTurma (int id, int ano, String letra) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ano", ano);
        cv.put("letra", letra);
        long resultado = db.update("Turmas", cv,"id=?",new String[]{String.valueOf(id)});
        if (resultado > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }



}
