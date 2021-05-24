/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


import frames.menu;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author carlo
 */
public class Preguntas
{
    Random rnd = new Random();
    
    public void random()
    {
        
        int opc;
        opc = (int)(rnd.nextDouble() * 5.0);
        switch(opc){
            case 0:
                String pregunta=JOptionPane.showInputDialog(null, "¿Cuánto es 7+8+4?", "Pregunta para continuar",1);
                int iPre = Integer.parseInt(pregunta); 
                if (iPre==19)
                {
                    JOptionPane.showMessageDialog(null, "Respuesta correcta :)", "Felicidades", 1);
                }else{
                    JOptionPane.showMessageDialog(null, "Respuesta incorrecta :(", "Error", 1);
                    menu men = new menu(1);
                    men.setVisible(true);
                }
                break;
            case 1:
                String pregunta2=JOptionPane.showInputDialog(null, "¿Cuánto es 9+2-7?", "Pregunta para continuar",1);
                int iPre2 = Integer.parseInt(pregunta2);
                if (iPre2==4)
                {
                    JOptionPane.showMessageDialog(null, "Respuesta correcta :)", "Felicidades", 1);
                }else{
                    JOptionPane.showMessageDialog(null, "Respuesta incorrecta :(", "Error", 1);
                    menu men = new menu(1);
                    men.setVisible(true);
                }
                break;
            case 2:
                String pregunta3=JOptionPane.showInputDialog(null, "¿Cuánto es 7*3/3?", "Pregunta para continuar",1);
                int iPre3 = Integer.parseInt(pregunta3);
                if (iPre3==7)
                {
                    JOptionPane.showMessageDialog(null, "Respuesta correcta :)", "Felicidades", 1);
                }else{
                    JOptionPane.showMessageDialog(null, "Respuesta incorrecta :(", "Error", 1);
                    menu men = new menu(1);
                    men.setVisible(true);
                }
                break;
            case 3:
                String pregunta4=JOptionPane.showInputDialog(null, "¿Cuánto es 5-5+23?", "Pregunta para continuar",1);
                int iPre4 = Integer.parseInt(pregunta4);
                if (iPre4==23)
                {
                    JOptionPane.showMessageDialog(null, "Respuesta correcta :)", "Felicidades", 1);
                }else{
                    JOptionPane.showMessageDialog(null, "Respuesta incorrecta :(", "Error", 1);
                    menu men = new menu(1);
                    men.setVisible(true);
                }
                break;
            case 4:
                String pregunta5=JOptionPane.showInputDialog(null, "¿Cuánto es 5*4+9?", "Pregunta para continuar",1);
                int iPre5 = Integer.parseInt(pregunta5);
                if (iPre5==29)
                {
                    JOptionPane.showMessageDialog(null, "Respuesta correcta :)", "Felicidades", 1);
                }else{
                    JOptionPane.showMessageDialog(null, "Respuesta incorrecta :(", "Error", 1);
                    menu men = new menu(1);
                    men.setVisible(true);
                }
                break;
        }
    }
}
