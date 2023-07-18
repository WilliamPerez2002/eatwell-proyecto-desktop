package com.DataBase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author marlo
 */
public class ReportesFireBase {

    private ArrayList<String> codigos() {
        CollectionReference consumo = Firebase.db.collection("Alimentos");
        ArrayList<String> codigos = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> qs = consumo.get();
            for (DocumentSnapshot document : qs.get().getDocuments()) {
                codigos.add(document.getId());
            }
        } catch (InterruptedException | ExecutionException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return codigos;
    }

    private ArrayList<String[]> datosConsumo() {
        CollectionReference consumo = Firebase.db.collection("Alimentacion");
        ArrayList<String[]> nombresyConsumo = new ArrayList<>();
        ArrayList<String> codigo = codigos();
        for (int i = 0; i < codigo.size(); i++) {
            Query query = consumo.whereEqualTo("ID_ALIMENTO", codigo.get(i));
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
        return nombresyConsumo;
    }

    public ArrayList<String[]> masConsumidos() {
        ArrayList<String[]> todosDatos = datosConsumo();
        todosDatos.sort(Comparator.comparingInt(arr -> Integer.valueOf(arr[1])));
        ArrayList<String[]> primerosTres = new ArrayList<>();
        for (int i = todosDatos.size() - 1; i >= Math.max(0, todosDatos.size() - 3); i--) {
            primerosTres.add(todosDatos.get(i));
        }
        return primerosTres;
    }
}
