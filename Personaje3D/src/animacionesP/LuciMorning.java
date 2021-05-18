/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animacionesP;

/**
 *
 * @author Elizabeth
 */

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.InputStream;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import personajesCod.DrawLuci;
import Obtaculos_Figuras.mBall;

public class LuciMorning implements GLEventListener, MouseListener, MouseMotionListener, KeyListener{
    
    
    Texture tAtras;
    Texture tFrente;
    Texture tDerecha;
    Texture tIzquierda;
    Texture tCielo;
    Texture tPiso;
    int mfondo=0;
    
    private float view_rotx = 0.01f;
    private float view_roty = 0.01f;
    private int oldMouseX;
    private int oldMouseY;
    boolean[] keys=new boolean[256]; //to know which key is pressed
    
    //position of stan in the window
    private static final float X_POSITION=0f;
    private static final float Y_POSITION=-0.08f;
    private static final float Z_POSITION=0f;
    
    public Clip clip;
    
    public String ruta ="/sonido/";
    
    static InputStream sounds;
    
    public String Baile = "src/sonido/son1.wav";    
    
    // colision
    private static final float posX=0f;
    private static final float posY=0f;
    private static final float pos2=0f;
    
    //cabeza
    float[] centroMCabeza ={0.0f,0.5f,0.0f};
    //posicion pelota
    float posPX=1.5f;
    float posPY=0.9f;
    float posPZ=0.0f;
    
    mBall cajaCabeza;
    mBall cajaPelota;
    
    
    
    public static void main(String[] args){
        
        Frame frame = new Frame("Lucifer)");
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new LuciMorning());
        frame.add(canvas);
        frame.setSize(1000, 800);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
          
    }

    public void init(GLAutoDrawable drawable){
        
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        gl.setSwapInterval(1);
        
        // set up lighting
        float light_ambient[]={0.9f, 0.9f, 0.9f, 1.0f};
        float light_diffuse[]={0.3f, 0.3f, 0.3f, 1.0f};
        float light_specular[]={1.0f, 1.0f, 1.0f, 1.0f};
        float light_position[] = {1.0f,1.5f,1.0f,0.0f };
        
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light_ambient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light_diffuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, light_specular, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light_position, 0);
        
        gl. glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
            
        // Setup the drawing area and shading mode
        gl.glClearColor(0.9f, 0.9f, 0.9f, 0.9f); 
        gl.glShadeModel(GL.GL_SMOOTH);
        
        
       
        try {
            
           File iCielo = new File("src/imagenes/infierno2.jpg"); 
           tCielo = TextureIO.newTexture(iCielo, true);
           
           File iPiso = new File("src/imagenes/piso.jpg"); 
           tPiso = TextureIO.newTexture(iPiso, true);
           
           File iAtras = new File("src/imagenes/infierno.jpg"); 
           tAtras = TextureIO.newTexture(iAtras, true);
           
           File iFrente = new File("src/imagenes/El cielo.jpg"); 
           tFrente = TextureIO.newTexture(iFrente, true);
           
           File iIzquierda = new File("src/imagenes/infierno2.jpg"); 
           tIzquierda = TextureIO.newTexture(iIzquierda, true);
           
           File iDerecha = new File("src/imagenes/Infierno2.jpg"); 
           tDerecha = TextureIO.newTexture(iDerecha, true);
           
            
        } catch (Exception e) {
            
            System.out.println("Error -> " + e);
            
        }
     
         float []centroP={posPX,posPY,posPZ};
        cajaCabeza= new mBall(0.1f,0.1f,0.1f, centroMCabeza);
        cajaPelota= new mBall(0.3f,0.3f,0.3f, centroP);
        gl.glFlush();
        
        
        
        gl.glPushMatrix();
        
        gl.glTranslated(posPX, posPY, posPX);
        //stan.draw_pelota(gl, glu);
        
        gl.glPopMatrix();
        
        if(Interseccion(cajaCabeza,cajaPelota))
        {
            System.out.println("Colision Cabeza");
        }
        
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
        
    }
    
    
    
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){
        
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        if (height <= 0) { // avoid a divide by zero error!
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        
    }
    
    public void display(GLAutoDrawable drawable){
        
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        glu.gluLookAt(0.1f,0.0f,4.0f,// eye
                      0.0f,0.0f,0.0f,  // looking at
                      0.0f,0.0f,1.0f   // is up
                    );
        
        // Move the whole scene
        gl.glTranslatef(X_POSITION, Y_POSITION, Z_POSITION);
        gl.glRotatef(view_rotx,1.0f,0.0f,0.0f);
        gl.glRotatef(view_roty,0.0f,1.0f,0.0f);
        gl.glRotatef(90,0.0f,0.0f,1.0f);
        
        //we draw Stan in the window
        DrawLuci stan = new DrawLuci(); 
       // DrawLuci stan2 = new DrawLuci(); 
        
        
        gl.glPushMatrix();
        gl.glScaled(1f, 0.9f, 1f);
        gl.glTranslated(0f, 1f, 1f);
        gl.glRotatef(-90, 1f, 0f, 0f);
        stan.fondo(gl, glu, tPiso);
        gl.glPopMatrix();
        gl.glFlush();
        
        
        gl.glPushMatrix();
        gl.glScaled(0.7f, 0.6f, 0.7f);
        gl.glTranslated(0f, 0f, 1f);
        gl.glRotatef(90, 1f, 0f, 0f);
        stan.fondo(gl, glu, tCielo);
        gl.glPopMatrix();
        gl.glFlush();
        
        
        gl.glPushMatrix();
        gl.glScaled(0.7f, 0.6f, 0.7f);
        gl.glRotatef(90, 0f, 1f, 0f);
        stan.fondo(gl, glu, tIzquierda);
        gl.glPopMatrix();
        gl.glFlush();
        
  
        gl.glPushMatrix();
        gl.glScaled(1f, 0.9f, 1f);
        gl.glRotatef(-90, 0f, 1f, 0f);
        stan.fondo(gl, glu, tDerecha);
        gl.glPopMatrix();
        gl.glFlush();
        
        
        gl.glPushMatrix();
        gl.glScaled(1f, 0.9f, 1f);
        gl.glTranslated(-1f, -1f, 10f);
        gl.glRotatef(2, 1f, 0f, 0f);
        stan.fondo(gl, glu, tFrente);
        gl.glPopMatrix();
        gl.glFlush();
        
        
        
        gl.glPushMatrix();
        gl.glScaled(1f, 0.9f, 1f);
        gl.glTranslated(0f, 01f, 1.5f);
        gl.glRotatef(0f, 0f, 0f, 0f);
        stan.fondo(gl, glu, tAtras);
        gl.glPopMatrix();
        gl.glFlush();
        
        /*
        if (mfondo == 1) 
        {
            
            escenario1();
            
        }else
        {
            if (mfondo == 2) 
            {
                escenario2();
            }else
            {
                
                if (mfondo == 3) 
                {
                    escenario3();
                }else
                {
                    if (mfondo == 4) 
                    {
                        escenario4();
                    }else
                    {
                        System.out.println("No fondo");
                    }    
                
                }
            }
        
        }*/
        
        
        stan.draw_stan(gl, keys['W'], keys['J'], keys['Q']); 
        
    //    gl.glTranslatef(X_POSITION-3, Y_POSITION, Z_POSITION);
      //  stan2.draw_stan(gl, keys['W'], keys['J'],keys['Q']); 
        
       //efecto colision
        float []centroP={posPX,posPY,posPZ};
        cajaCabeza= new mBall(0.1f,0.1f,0.1f, centroMCabeza);
        cajaPelota= new mBall(0.3f,0.3f,0.3f, centroP);
        gl.glFlush();
        
        
        
        
        
        
        
        gl.glPushMatrix();
        
        gl.glTranslated(posPX, posPY, posPZ);
        stan.draw_pelota(gl, glu);
        
        gl.glPopMatrix();
        
        
        if(Interseccion(cajaCabeza,cajaPelota))
        {
            System.out.println("Colision Cabeza");
            
        }
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
        
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
        
    }
    
    public boolean Interseccion(mBall caja1, mBall caja2)
    {
    
        if (Math.abs(caja1.centro[0]-caja2.centro[0])>
                (caja1.extend[0]+caja2.extend[0])){
            return false;
        }
        
        if(Math.abs(caja1.centro[1]-caja2.centro[1])>
                (caja1.extend[1]+caja2.extend[1]))
        {
        
            return false;
        }
        
       if(Math.abs(caja1.centro[2]-caja2.centro[2])>
               (caja1.extend[2]+caja2.extend[2]))
        {
        
            return false;
        } 
    
        
        return true;
    
    }
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    public void keyTyped(KeyEvent e){}   
    public void keyReleased(KeyEvent e){}
    
    
    public void mousePressed(MouseEvent e){
        oldMouseX = e.getX();
        oldMouseY = e.getY();     
    }
    
    public void mouseDragged(MouseEvent e){
        
        int x = e.getX();
        int y = e.getY();
        Dimension size = e.getComponent().getSize();
        float thetaX = 360.0f * ( (float)(x-oldMouseX)/(float)size.width);
        float thetaY = 360.0f * ( (float)(oldMouseY-y)/(float)size.height);
        oldMouseX = x;
        oldMouseY = y;
        view_rotx += thetaX;
        view_roty += thetaY;
   
    }
    
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode()<250 && keys[e.getKeyCode()]==false){
            keys['W']=false;
            keys['J']=false;
            keys['Q']=false;
            keys[2] =false;
            keys[e.getKeyCode()]=true;  
            System.out.println("-->" +e.getKeyCode());
            switch (e.getKeyCode())
            {
                case 'Q':
                    //SonidoOf();
                    reproducir("gayy");
                    break;
                case 'J':
                    //SonidoOf();
                    reproducir("omg");
                    break;
                case 'W':
                    //SonidoOf();
                    reproducir("corre");
                     if (!Interseccion(cajaCabeza, cajaCabeza)) {
                        posPX-=0.1f;
                    }else
                    {
                        posPY-=0.1;
                    }
                     
                    break;
                case 'D':
                    //escenario2();
                    reproducir("boom");
                    if (posPX>=1.5) {
                        posPX+=0.1f;
                    }
                    
                    break; 
                case 1:
                    //escenario2();
                    mfondo =1;
                    break;    
                case 2:
                    //escenario2();
                    mfondo =2;
                    break;
                case 3:
                    //escenario2();
                    mfondo =3;
                    break;
                case 4:
                    //escenario2();
                    mfondo =4;
                    break;
            
            }
            
            
        }
        else
            keys[e.getKeyCode()]=false;    
    }
    
   
    public void reproducir(String archivo)
    {
       // clip = AudioSystem.getClip();
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResource(ruta+archivo+".wav")));
            clip.start();
        } catch (Exception e) {
        }
 
       
    
    
    }
   
    public void SonidoOf()
    {
    
        clip.stop();
    
    }
   
    public void escenario1()
    {
    
         try {
            
           File iCielo = new File("src/imagenes/infierno2.jpg"); 
           tCielo = TextureIO.newTexture(iCielo, true);
           
           File iPiso = new File("src/imagenes/piso.jpg"); 
           tPiso = TextureIO.newTexture(iPiso, true);
           
           File iAtras = new File("src/imagenes/infierno.jpg"); 
           tAtras = TextureIO.newTexture(iAtras, true);
           
           File iFrente = new File("src/imagenes/El cielo.jpg"); 
           tFrente = TextureIO.newTexture(iFrente, true);
           
           File iIzquierda = new File("src/imagenes/infierno2.jpg"); 
           tIzquierda = TextureIO.newTexture(iIzquierda, true);
           
           File iDerecha = new File("src/imagenes/Infierno2.jpg"); 
           tDerecha = TextureIO.newTexture(iDerecha, true);
           
            
        } catch (Exception e) {
            
            System.out.println("Error -> " + e);
            
        }
    
    }
   
    public void escenario2()
    {
    
        try {
            
           File iCielo = new File("src/imagenes/1.jpg"); 
           tCielo = TextureIO.newTexture(iCielo, true);
           
           File iPiso = new File("src/imagenes/2.jpg"); 
           tPiso = TextureIO.newTexture(iPiso, true);
           
           File iAtras = new File("src/imagenes/3.jpg"); 
           tAtras = TextureIO.newTexture(iAtras, true);
           
           File iFrente = new File("src/imagenes/4.jpg"); 
           tFrente = TextureIO.newTexture(iFrente, true);
           
           File iIzquierda = new File("src/imagenes/6.jpg"); 
           tIzquierda = TextureIO.newTexture(iIzquierda, true);
           
           File iDerecha = new File("src/imagenes/7.jpg"); 
           tDerecha = TextureIO.newTexture(iDerecha, true);
           
            
        } catch (Exception e) {
            
            System.out.println("Error -> " + e);
            
        }
    
    }
    
    
    
    public void escenario3()
    {
    
        try {
            
           File iCielo = new File("src/imagenes/4.jpg"); 
           tCielo = TextureIO.newTexture(iCielo, true);
           
           File iPiso = new File("src/imagenes/escenario1.2.jpg"); 
           tPiso = TextureIO.newTexture(iPiso, true);
           
           File iAtras = new File("src/imagenes/escenario1.3.jpg"); 
           tAtras = TextureIO.newTexture(iAtras, true);
           
           File iFrente = new File("src/imagenes/escenario1.4.jpg"); 
           tFrente = TextureIO.newTexture(iFrente, true);
           
           File iIzquierda = new File("src/imagenes/escenario1.2.jpg"); 
           tIzquierda = TextureIO.newTexture(iIzquierda, true);
           
           File iDerecha = new File("src/imagenes/escenario1.1.jpg"); 
           tDerecha = TextureIO.newTexture(iDerecha, true);
           
            
        } catch (Exception e) {
            
            System.out.println("Error -> " + e);
            
        }
    
    }
    
    public void escenario4()
    {
    
        try {
            
           File iCielo = new File("src/imagenes/infierno2.jpg"); 
           tCielo = TextureIO.newTexture(iCielo, true);
           
           File iPiso = new File("src/imagenes/piso.jpg"); 
           tPiso = TextureIO.newTexture(iPiso, true);
           
           File iAtras = new File("src/imagenes/infierno.jpg"); 
           tAtras = TextureIO.newTexture(iAtras, true);
           
           File iFrente = new File("src/imagenes/escenario1.4.jpg"); 
           tFrente = TextureIO.newTexture(iFrente, true);
           
           File iIzquierda = new File("src/imagenes/6.jpg"); 
           tIzquierda = TextureIO.newTexture(iIzquierda, true);
           
           File iDerecha = new File("src/imagenes/6.jpg"); 
           tDerecha = TextureIO.newTexture(iDerecha, true);
           
            
        } catch (Exception e) {
            
            System.out.println("Error -> " + e);
            
        }
    
    }
    
}