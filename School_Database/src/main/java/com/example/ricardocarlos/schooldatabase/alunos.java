package com.example.ricardocarlos.schooldatabase;

import java.util.Comparator;

/**
 * Created by Ana Pinto on 04/09/2017.
 */

public class alunos {
    private int id;
    String nome;
    String email;
    int telefone;
    String morada;
    int numero;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public alunos (int id, String n, int t, String e, String m, int num){
        this.id=id;
        nome= n;
        telefone= t;
        email= e;
        morada= m;
        numero= num;
    }

    /* ordenar listView*/
    @Override
    public String toString(){
        return nome+"\n"+telefone+"\n"+email+"\n"+morada+"\n"+numero;
    }

    public static Comparator<alunos> ListaUp = new Comparator<alunos>() {
        @Override
        public int compare(alunos s1, alunos s2) {
            String Nome1 = s1.getNome().toUpperCase();
            String Nome2 = s2.getNome().toUpperCase();
            return Nome1.compareTo(Nome2);
        }
    };

    public static Comparator<alunos> ListaDown = new Comparator<alunos>() {
        @Override
        public int compare(alunos s1, alunos s2) {
            String Nome1 = s1.getNome().toUpperCase();
            String Nome2 = s2.getNome().toUpperCase();
            return Nome2.compareTo(Nome1);
        }
    };


    public int getId() {
        return id;
    }
}
