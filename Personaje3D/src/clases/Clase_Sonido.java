/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author vicOMG99
 */
public class Clase_Sonido {

    int bndS = 0;
    public static Clip clip;
    
    
    public Clase_Sonido() {
        
    }
    
  
   
    

    public void sonidoOff() {
        if (clip!=null) {
            clip.stop();
        }
    }

    public void sonidomenu(String ef) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("src/sonidos/" + ef + ".wav")));
            clip.loop(1000);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println(e.getMessage());
        } catch (UnsupportedAudioFileException e) {
            System.err.println(e.getMessage());
        }
    }

    public static synchronized void sonidoFondo(final String sonido) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                File d = new File("src/sonidos/" + sonido + ".wav");
                try {
                    clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(d);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
