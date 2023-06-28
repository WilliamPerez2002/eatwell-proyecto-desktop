package com.DataBase;

import com.backend.*;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Acciones {

    private final Sentencias ejecuciones = new Sentencias();
    private final CamposTexto txt = new CamposTexto();

    public void siguiente(JTextField usuario, JTabbedPane pestañas, JTextField textoUsuarioContraseña, JLabel errorUsuario, JLabel errorContrUsuario, JLabel errorContrContr) {
        String estado = this.ejecuciones.comprobarUsuarioYBloqueo(usuario);
        if (estado.equals("vacio")) {
            errorUsuario.setText("Usuario no existente");
        } else if (estado.equals("NO")) {
            errorUsuario.setText("Usuario bloqueado");
        } else {
            this.txt.textoUsuarioUIContraseña(usuario, textoUsuarioContraseña, errorContrUsuario, errorContrContr);
            pestañas.setSelectedIndex(1);
        }
    }

    public boolean iniciar(JPasswordField contraseña, JTextField usuario, JLabel errorUsuarioContraseña, JLabel errorUsuario) {
        if (!this.ejecuciones.iniciarSesion(String.valueOf(contraseña.getPassword()), usuario.getText(), errorUsuario)) {
            errorUsuarioContraseña.setText("Contraseña incorrecta");
            return false;
        } else {
            return true;
        }
    }

    public void reiniciarLogin(JLabel errorUsuario, JTextField texto, JTabbedPane login) {
        errorUsuario.setText("");
        texto.setText("INGRESE SU USUARIO");
        login.setSelectedIndex(0);
    }

    public void cambiarPestañaMenu(JTabbedPane pestañas, int pestaña) {
        pestañas.setSelectedIndex(pestaña);
    }

    public void cambiarContraseña(String administrador, JLabel errorCambioOrigen, JLabel errorCambioNuevo, JPasswordField contrasena1, JPasswordField contrasena2, JPasswordField contrasena3) {
        String contrasenaInicial = String.valueOf(contrasena1.getPassword());
        String contrasenaUno = String.valueOf(contrasena2.getPassword());
        String contrasenaDos = String.valueOf(contrasena3.getPassword());
        boolean primerFiltro = ejecuciones.compararContraseña(contrasenaInicial, administrador, errorCambioOrigen);
        boolean segundoFiltro = ejecuciones.comprarNuevasContrasenas(contrasenaUno, contrasenaDos, errorCambioNuevo);
        if (primerFiltro == true && segundoFiltro == true) {
            limpiarCambioContraseña(errorCambioOrigen, errorCambioNuevo, contrasena1, contrasena2, contrasena3);
        }
        contrasena1.setText("");
        contrasena2.setText("");
        contrasena3.setText("");
    }

    public void limpiarCambioContraseña(JLabel errorCambioOrigen, JLabel errorCambioNuevo, JPasswordField contrasena1, JPasswordField contrasena2, JPasswordField contrasena3) {
        errorCambioOrigen.setText("");
        errorCambioNuevo.setText("");
        contrasena1.setText("");
        contrasena2.setText("");
        contrasena3.setText("");
    }
}
