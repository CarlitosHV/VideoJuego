/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obtaculos_Figuras;

/**
 *
 * @author vicOMG99
 */
public class mBall 
{

    public float[] centro;
    public float[] extend;
    
    public mBall(final float ancho,final float alto, final float profundidad, float[]c)
    {
        centro =c;
        extend = new float[3];
        
        extend[0] = ancho;
        extend[1] = alto;
        extend[2] = profundidad;
    }

    
}
