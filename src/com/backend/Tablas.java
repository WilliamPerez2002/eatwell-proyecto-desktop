/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend;

import com.DataBase.Sentencias;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marlo
 */
public class Tablas {

    public void buscador(DefaultTableModel modelo, JTable usuarios, JTextField busqueda, JLabel error) {
        if (busqueda.getText().isEmpty()) {
            error.setText("Sin Valores");
        } else {
            Sentencias sn = new Sentencias();
            modelo.setNumRows(0);
            ArrayList<String[]> users = sn.recuperarUsuarios(busqueda.getText());
            for (int i = 0; i < users.size(); i++) {
                modelo.addRow(users.get(i));
            }
            usuarios.setModel(modelo);
            error.setText("");
        }
    }
}
