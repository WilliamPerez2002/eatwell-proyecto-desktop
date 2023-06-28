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
            boton.setBackground(new Color(136,158,205));
        }else{
            boton.setBackground(new Color(86,117,184));
        }
        
    };
    
}
