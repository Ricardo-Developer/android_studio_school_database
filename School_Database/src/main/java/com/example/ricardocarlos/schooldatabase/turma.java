package com.example.ricardocarlos.schooldatabase;

import java.util.Comparator;

/**
 * Created by Ricardo Carlos on 06/09/2017.
 */

public class turma {
    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public turma(int id) {
        this.id = id;
    }

    private int id;
    String letra;
    int ano;

    public turma (int id,  int ano, String letra){
        this.id=id;
        this.ano= ano;
        this.letra= letra;

    }

    /* ordenar listView*/
    @Override
    public String toString(){
        return ano+" - "+letra;
    }

    public static Comparator<turma> ListaUp = new Comparator<turma>() {
        @Override
        public int compare(turma s1, turma s2) {
            String letra1 = s1.getLetra().toUpperCase();
            String letra2 = s2.getLetra().toUpperCase();
            return letra1.compareTo(letra2);
        }
    };

    public static Comparator<turma> ListaDown = new Comparator<turma>() {
        @Override
        public int compare(turma s1, turma s2) {
            String letra1 = s1.getLetra().toUpperCase();
            String letra2 = s2.getLetra().toUpperCase();
            return letra2.compareTo(letra1);
        }
    };


    public int getId() {
        return id;
    }

}
