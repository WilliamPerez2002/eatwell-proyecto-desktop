package com.DataBase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author marlo
 */
public class Reportes {

    Sentencias sen = new Sentencias();

    private ArrayList<String> codigos() {
        CollectionReference consumo = Firebase.db.collection("Alimentos");
        ArrayList<String> codigos = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> qs = consumo.get();
            for (DocumentSnapshot document : qs.get().getDocuments()) {
                codigos.add(document.getString("Nombre"));
            }
        } catch (InterruptedException | ExecutionException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return codigos;
    }

    public ArrayList<String[]> masConsumidos() {
        CollectionReference consumo = Firebase.db.collection("Alimentacion");
        ArrayList<String[]> nombresyConsumo = new ArrayList<>();
        ArrayList<String> codigo = codigos();
        for (int i = 0; i < codigo.size(); i++) {
            Query query = consumo.whereEqualTo("NOMBRE", codigo.get(i));
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            try {
                String[] datos = new String[2];
                int cantidad = 0;
                datos[0] = "";
                for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                    cantidad += document.getDouble("CANTIDAD");
                    datos[0] = document.getString("NOMBRE");
                }
                datos[1] = String.valueOf(cantidad);
                nombresyConsumo.add(datos);
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println("Error" + ex.getMessage());
            }
        }
        return ordenarMayorMenor(nombresyConsumo);
    }

    private ArrayList<String[]> ordenarMayorMenor(ArrayList<String[]> datos) {
        ArrayList<String[]> todosDatos = datos;
        todosDatos.sort(Comparator.comparingInt(arr -> Integer.valueOf(arr[1])));
        ArrayList<String[]> primerosTres = new ArrayList<>();
        for (int i = todosDatos.size() - 1; i >= 0; i--) {
            primerosTres.add(todosDatos.get(i));
        }
        return primerosTres;
    }

    public ArrayList<String[]> consumoUsuario(String Usuario) {
        CollectionReference consumo = Firebase.db.collection("Alimentacion");
        ArrayList<String[]> nombresyConsumo = new ArrayList<>();
        ArrayList<String> codigo = codigos();
        for (int i = 0; i < codigo.size(); i++) {
            Query query = consumo.whereEqualTo("NOMBRE", codigo.get(i));
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            try {
                String[] datos = new String[2];
                datos[0] = codigo.get(i);
                int cantidad = 0;
                for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                    if (Usuario.equals(document.getString("USUARIO"))) {
                        cantidad += document.getDouble("CANTIDAD");
                    }
                }
                datos[1] = String.valueOf(cantidad);
                nombresyConsumo.add(datos);
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println("Error" + ex.getMessage());
            }
        }
        return nombresyConsumo;
    }

    public ArrayList<String[]> valoresCaloricos() {
        ArrayList<String[]> datos = new ArrayList<>();
        try {
            CollectionReference alimentos = Firebase.db.collection("Alimentos");
            ApiFuture<QuerySnapshot> qs = alimentos.get();
            for (DocumentSnapshot document : qs.get().getDocuments()) {
                String[] alimento = {document.getString("Nombre"), document.getString("Unidad"), String.valueOf(document.getDouble("Calorias"))};
                datos.add(alimento);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return datos;
    }

    public ArrayList<String[]> categoriasPopulares() {
        ArrayList<String[]> datos = new ArrayList<>();
        String[] categorias = {"Bebida", "Fruta", "Grano", "Lacteo", "Proteina", "Verdura", "Grasa"};
        for (String categoria : categorias) {
            String[] cat = new String[2];
            cat[0] = categoria;
            try {
                CollectionReference alimentos = Firebase.db.collection("Alimentos");
                Query query = alimentos.whereArrayContainsAny("Categorias", Arrays.asList(categoria));
                ApiFuture<QuerySnapshot> qs = query.get();
                cat[0] = categoria;
                cat[1] = String.valueOf(qs.get().getDocuments().size());
                datos.add(cat);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
        return ordenarMayorMenor(datos);
    }
}
