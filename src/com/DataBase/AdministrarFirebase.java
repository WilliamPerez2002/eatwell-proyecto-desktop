package com.DataBase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Precondition;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author marlo
 */
public class AdministrarFirebase {

    private Alimento al = new Alimento();

    private boolean guardarAlimento(String coleccion, String documento, Map<String, Object> datos, JLabel exito) {
        try {
            DocumentReference docRef = Firebase.db.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.set(datos);
            exito.setText("Ultimo alimento agregado con exito");
            return true;
        } catch (Exception e) {
            exito.setText("Error");
            return false;
        }
    }

    public void guardar(String nombre, double calorias, ArrayList<String> categorias, JLabel exito) {
        int id = (int) (Math.random() * 100000);
        try {
            Map<String, Object> datos = new HashMap<>();
            datos.put("Nombre", nombre);
            datos.put("Calorias", calorias);
            datos.put("Categorias", categorias);
            guardarAlimento("Alimentos", String.valueOf(id), datos,exito);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cargarCombobox(JComboBox<String> llenar, JLabel error) {
        try {
            llenar.setModel(new DefaultComboBoxModel<>());
            llenar.addItem("Seleccione un alimento");
            CollectionReference alimentos = Firebase.db.collection("Alimentos");
            ApiFuture<QuerySnapshot> qs = alimentos.get();
            for (DocumentSnapshot document : qs.get().getDocuments()) {
                llenar.addItem(document.getString("Nombre"));
            }
        } catch (Exception e) {
            error.setText("Error: " + e.getMessage());
        }
    }

    public ArrayList<String> cargarDatosEditar(String nombreSeleccionado, JTextField calorias) {
        al = cargarDatos(nombreSeleccionado);
        System.out.println(al.getCalorias());
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

    private boolean editarAlimento(String coleccion, String documento, Map<String, Object> datos, JLabel exito) {
        try {
            DocumentReference docRef = Firebase.db.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.update(datos);
            return true;
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return false;
        }
    }

    public void editar(String nombre, double calorias, ArrayList<String> categorias, JLabel exito) {
        String id = obtenerID(nombre);
        try {
            Map<String, Object> datos = new HashMap<>();
            datos.put("Nombre", nombre);
            datos.put("Calorias", calorias);
            datos.put("Categorias", categorias);
            editarAlimento("Alimentos", id, datos,exito);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
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

    public boolean eliminarAlimento(String nombre, JLabel eliminado, String datoEliminado) {
        String documento = obtenerID(nombre);
        try {
            DocumentReference docRef = Firebase.db.collection("Alimentos").document(documento);
            ApiFuture<WriteResult> result = docRef.delete(Precondition.NONE);
            eliminado.setText(datoEliminado+" eliminado correctamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return false;
        }
    }
        public boolean nombreDuplicado(String nombre, JLabel error, JLabel exito) {
            if (nombre.length()==0) {
                error.setText("Nombre Vacio");
                return true;
            }
        try {
            CollectionReference alimentos = Firebase.db.collection("Alimentos");
            ApiFuture<QuerySnapshot> qs = alimentos.get();
            for (DocumentSnapshot document : qs.get().getDocuments()) {
                if (document.getString("Nombre").toLowerCase().equals(nombre.toLowerCase())) {
                    error.setText("Nombre duplicado");
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            error.setText("Error: " + e.getMessage());
            return true;
        }
    }
}
