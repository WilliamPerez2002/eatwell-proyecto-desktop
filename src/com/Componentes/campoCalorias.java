package com.Componentes;

import java.awt.Font;
import javax.swing.JTextField;

/**
 *
 * @author marlo
 */
public class campoCalorias extends JTextField {

    public campoCalorias() {
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                soloNumero(evt);
                maximaLongitud(evt);
            }
        });
        Font font = new Font("Frankil Gothic Medium", Font.PLAIN, 30);
        this.setFont(font);
    }

    private void soloNumero(java.awt.event.KeyEvent evt) {
        char numero = evt.getKeyChar();
        if (!(Character.isDigit(numero))) {
            evt.consume();
        }
    }

    private void maximaLongitud(java.awt.event.KeyEvent evt) {
        if (this.getText().length() == 3) {
            evt.consume();
        }
    }
}
