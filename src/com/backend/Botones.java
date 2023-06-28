package com.backend;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author marlo
 */
public class Botones {

    public void cambiarColorBotones(JPanel boton, int caso) {
        if (caso == 0) {
            boton.setBackground(new Color(136, 158, 205));
        } else {
            boton.setBackground(new Color(86, 117, 184));
        }

    }

    public void cambiarColorBotonesMenu(JPanel boton, int caso) {
        if (caso == 0) {
            boton.setBackground(new Color(75, 77, 102));
        } else {
            boton.setBackground(new Color(120, 123, 156));
        }
    }
}
