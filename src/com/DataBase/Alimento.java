package com.DataBase;

import java.util.ArrayList;

public class Alimento {
    private String Nombre;
    private double calorias;
    private ArrayList<String> categrias;

    public Alimento(String Nombre, double calorias, ArrayList<String> categrias) {
        this.Nombre = Nombre;
        this.calorias = calorias;
        this.categrias = categrias;
    }

    public Alimento() {
    }
    
    public String getNombre() {
        return Nombre;
    }

    public double getCalorias() {
        return calorias;
    }

    public ArrayList<String> getCategrias() {
        return categrias;
    }   
}
