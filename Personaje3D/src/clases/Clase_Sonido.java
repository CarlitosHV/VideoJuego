/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author vicOMG99
 */
public class Clase_Sonido 
{
    
    
    int bndS=0;
    public static Clip clipFondo;
    
    public Clase_Sonido() {
    }
    
    
    
 public void sonidoOff()
 {
    
    clipFondo.stop();
    
    
 }
    
    
    public void SonidoOn(String Nombre, int bnd)
    {
        if(bnd==0)
        {
            
            sonidoFondo(Nombre);
        
        }
        
        
        
        clipFondo.start();
    }
    
    
    
    public static synchronized void sonidoFondo(final String sonido)
	{
		new Thread(new Runnable()
		{
			// The wrapper thread is unnecessary, unless it blocks on the
			// Clip finishing; see comments.
			public void run()
			{
				File d = new File("src/sonidos/" + sonido + ".wav");
				try
				{
					clipFondo = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(d);
					clipFondo.open(inputStream);
					clipFondo.start();
				} catch (Exception e)
				{
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}    
}
