package com.backend;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CamposTexto {
    public void textoDefectoUsuario(JTextField textoUsuario){
        if (textoUsuario.getText().equals("INGRESE SU USUARIO")) {
            textoUsuario.setText("");
        }
    }
    
    public void textoUsuarioUIContraseña(JTextField textoUsuario,JTextField textoUsuarioContraseña,JLabel errorUsuario, JLabel errorContrasenia){
        errorUsuario.setText("");
        errorContrasenia.setText("");
        textoUsuarioContraseña.setText(textoUsuario.getText());
        textoUsuarioContraseña.setEditable(false);
    }
    
    public void textoContraseñaDefecto(JPasswordField contraseña){
        if (String.valueOf(contraseña.getPassword()).equals("**********")) {
            contraseña.setText("");
            
        } 
    }
}
