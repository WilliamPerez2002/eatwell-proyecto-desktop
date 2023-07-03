package com.DataBase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Precondition;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

/**
 *
 * @author marlo
 */
public class AdministrarFirebase {

    private Alimento al = new Alimento();

    Firestore bd;

    private boolean guardarAlimento(String coleccion, String documento, Map<String, Object> datos) {
        bd = FirestoreClient.getFirestore();
        try {
            DocumentReference docRef = bd.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.set(datos);
            System.out.println("Agregado Correctamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public void guardar(String nombre, int calorias, ArrayList<String> categorias) {
        int id = (int) (Math.random() * 100000);
        try {
            Map<String, Object> datos = new HashMap<>();
            datos.put("Nombre", nombre);
            datos.put("Calorias", calorias);
            datos.put("Categorias", categorias);
            guardarAlimento("Alimentos", String.valueOf(id), datos);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cargarCombobox(JComboBox llenar) {
        try {
            llenar.removeAllItems();
            llenar.addItem("Seleccione un alimento");
            CollectionReference alimentos = Firebase.db.collection("Alimentos");
            ApiFuture<QuerySnapshot> qs = alimentos.get();
            for (DocumentSnapshot document : qs.get().getDocuments()) {
                llenar.addItem(document.getString("Nombre"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public ArrayList<String> cargarDatosEditar(String nombreSeleccionado, JFormattedTextField calorias) {
        al = cargarDatos(nombreSeleccionado);
        calorias.setText(String.valueOf(al.getCalorias()));
        return al.getCategrias();
    }

    private Alimento cargarDatos(String nombreSeleccionado) {
        try {
            CollectionReference alimentos = Firebase.db.collection("Alimentos");
            ApiFuture<QuerySnapshot> qs = alimentos.get();
            for (DocumentSnapshot document : qs.get().getDocuments()) {
                String nombre = document.getString("Nombre");
                if (nombre.equals(nombreSeleccionado)) {
                    String categorias[] = document.get("Categorias").toString().replace(" ", "").replace("[", "").replace("]", "").split(",");
                    ArrayList<String> cat = new ArrayList<>();
                    cat.addAll(Arrays.asList(categorias));
                    al = new Alimento(nombre, document.getDouble("Calorias"), cat);
                    break;
                }
            }
            return al;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private boolean editarAlimento(String coleccion, String documento, Map<String, Object> datos) {
        bd = FirestoreClient.getFirestore();
        try {
            DocumentReference docRef = bd.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.update(datos);
            System.out.println("Editado Correctamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public void editar(String nombre, int calorias, ArrayList<String> categorias) {
        String id = obtenerID(nombre);
        try {
            Map<String, Object> datos = new HashMap<>();
            datos.put("Nombre", nombre);
            datos.put("Calorias", calorias);
            datos.put("Categorias", categorias);
            System.out.println("mapea los datos");
            editarAlimento("Alimentos", id, datos);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String obtenerID(String nombre) {
        try {
            CollectionReference alimentos = Firebase.db.collection("Alimentos");
            ApiFuture<QuerySnapshot> qs = alimentos.get();
            for (DocumentSnapshot document : qs.get().getDocuments()) {
                if (document.getString("Nombre").equals(nombre)) {
                    return document.getId();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "";
    }

    public boolean eliminarAlimento(String nombre) {
        bd = FirestoreClient.getFirestore();
        String documento = obtenerID(nombre);
        try {
            DocumentReference docRef = bd.collection("Alimentos").document(documento);
            ApiFuture<WriteResult> result = docRef.delete(Precondition.NONE);
            System.out.println("Eliminado Correctamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
