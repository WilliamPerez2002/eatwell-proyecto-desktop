package com.DataBase;

import java.util.ArrayList;

public class Alimento {
    private String Nombre,Unidad;
    private double calorias;
    private ArrayList<String> categrias;

    public Alimento(String Nombre, String Unidad, double calorias, ArrayList<String> categrias) {
        this.Nombre = Nombre;
        this.calorias = calorias;
        this.categrias = categrias;
        this.Unidad = Unidad;
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

    public String getUnidad() {
        return Unidad;
    }
}
