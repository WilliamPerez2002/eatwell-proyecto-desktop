package com.backend;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author marlo
 */
public class Botones {
    
    public void cambiarColorBotonLogin(JPanel boton, int caso){
        if (caso == 0) {
            boton.setBackground(new Color(72,140,120));
        }else{
            boton.setBackground(new Color(72,127,120));
        }
        
    };
    
}
