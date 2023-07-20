package com.Componentes;

import java.awt.Font;
import javax.swing.JTextField;

/**
 *
 * @author marlo
 */
public class nombreAlimentos extends JTextField {

    public nombreAlimentos() {
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                soloLetras(evt);
                maximaLongitud(evt);
            }
        });
        Font font = new Font("Frankil Gothic Medium", Font.PLAIN, 30);
        this.setFont(font);
    }

    private void soloLetras(java.awt.event.KeyEvent evt) {
        char entrada = evt.getKeyChar();
        if (!Character.isLetter(entrada)&&!Character.isSpaceChar(entrada)) {
            evt.consume();
        }
    }

    private void maximaLongitud(java.awt.event.KeyEvent evt) {
        if (this.getText().length() == 15) {
            evt.consume();
        }
    }
}
