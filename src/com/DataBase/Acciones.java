package com.DataBase;

import com.backend.*;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Acciones {

    private final Sentencias ejecuciones = new Sentencias();
    private final CamposTexto txt = new CamposTexto();

    public void siguiente(JTextField usuario, JTabbedPane pestañas, JTextField textoUsuarioContraseña, JLabel errorUsuario,JLabel errorContrUsuario,JLabel errorContrContr) {
        String estado = this.ejecuciones.comprobarUsuarioYBloqueo(usuario);
        if (estado.equals("vacio")) {
            errorUsuario.setText("Usuario no existente");
        } else if (estado.equals("NO")) {
            errorUsuario.setText("Usuario bloqueado");
        } else {
            this.txt.textoUsuarioUIContraseña(usuario, textoUsuarioContraseña,errorContrUsuario,errorContrContr);
            pestañas.setSelectedIndex(1);
        }
    }
    
    public boolean iniciar(JPasswordField contraseña, JTextField usuario,JLabel errorUsuarioContraseña, JLabel errorUsuario){
        if (!this.ejecuciones.iniciarSesion(String.valueOf(contraseña.getPassword()), usuario.getText(),errorUsuario)) {
            errorUsuarioContraseña.setText("Contraseña incorrecta");
            return false;
        }else{
            return true;
        }
    }
        public void reiniciarLogin(JLabel errorUsuario,JTextField texto, JTabbedPane login){
        errorUsuario.setText("");
        texto.setText("INGRESE SU USUARIO");
        login.setSelectedIndex(0);
    }
}
