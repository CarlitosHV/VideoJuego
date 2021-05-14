/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animacionesP;

/**
 *
 * @author carlo
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
import javax.swing.JOptionPane;
import personajesCod.drawrobot;

public class Robot implements GLEventListener, MouseListener, MouseMotionListener, KeyListener {

    Texture tAtras;
    Texture tFrente;
    Texture tDerecha;
    Texture tIzquierda;
    Texture tCielo;
    Texture tPiso;
    int mFondo;

    public Clip clip;

    public String ruta = "src/sonidos/";

    static InputStream sonidos;

    public String Hola = "src/sonidos/robot1.wav";
    public String Salto = "src/sonidos/robot2.wav";
    public String Mano = "src/sonidos/robot3.wav";
    public String firmes = "src/sonidos/robot4.wav";
    public String arco = "src/sonidos/robot5.wav";

    //colisión
    private static final float posX = 0f;
    private static final float posY = 0f;
    private static final float posZ = 0f;

    //mBallcentro stan
    float[] centroMCabeza = {0.0f, 0.2f, 0.0f};

    //posición de la pelota
    float posPX = 1.5f;
    float posPY = -0.9f;
    float posPZ = 0.0f;

   // mCaja cajaCabeza;
    //mCaja cajaPelota;

    private float view_rotx = 0.01f;
    private float view_roty = 0.01f;
    private int oldMouseX;
    private int oldMouseY;
    boolean[] keys = new boolean[256]; //to know which key is pressed

    //position of stan in the window
    private static final float X_POSITION = 0f;
    private static final float Y_POSITION = -0.08f;
    private static final float Z_POSITION = 0f;

    public static void main(String[] args) {

        Frame frame = new Frame("Robot extraño. (Presiona H en tu teclado para instrucciones)");
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new Robot());
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

    public void init(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        gl.setSwapInterval(1);

        // set up lighting
        float light_ambient[] = {0.9f, 0.9f, 0.9f, 1.0f};
        float light_diffuse[] = {0.3f, 0.3f, 0.3f, 1.0f};
        float light_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float light_position[] = {1.0f, 1.5f, 1.0f, 0.0f};

        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light_ambient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light_diffuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, light_specular, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light_position, 0);

        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.9f, 0.9f, 0.9f, 0.9f);
        gl.glShadeModel(GL.GL_SMOOTH);

        try {
            File iCielo = new File("src/fondos/imagencielo.jpeg");
            tCielo = TextureIO.newTexture(iCielo, true);

            File iPiso = new File("src/fondos/imagensuelo.jpg");
            tPiso = TextureIO.newTexture(iPiso, true);

            File iAtras = new File("src/fondos/imagen3.png");
            tAtras = TextureIO.newTexture(iAtras, true);

            File iFrente = new File("src/fondos/imagen1.jpg");
            tFrente = TextureIO.newTexture(iFrente, true);

            File iIzquierda = new File("src/fondos/imagen2.jpg");
            tIzquierda = TextureIO.newTexture(iIzquierda, true);

            File iDerecha = new File("src/fondos/imagen4.jpg");
            tDerecha = TextureIO.newTexture(iDerecha, true);

        } catch (Exception e) {
        }

        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

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

    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        GLU glu = new GLU();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);

        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        glu.gluLookAt(0.1f, 0.0f, 4.0f,// eye
                0.0f, 0.0f, 0.0f, // looking at
                0.0f, 0.0f, 1.0f // is up
        );

        // Move the whole scene
        gl.glTranslatef(X_POSITION, Y_POSITION, Z_POSITION);
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(90, 0.0f, 0.0f, 1.0f);

        //we draw robot in the window
        drawrobot robot = new drawrobot();
        /*
        //piso nuevo
        gl.glPushMatrix();
        gl.glScaled(1.0f, 0.7f, 1.0f);
        gl.glTranslated(0.0f, 0.0f, 1.0f);
        gl.glRotatef(-90, 1.0f, 0.0f, 0.0f);
        robot.fondo(gl, glu, tPiso);
        gl.glPopMatrix();
        gl.glFlush();

        //nuevo cielo
        gl.glPushMatrix();
        gl.glScaled(1.0f, 0.7f, 1.0f);
        gl.glTranslated(0.0f, 0.0f, 1.0f);
        gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
        robot.fondo(gl, glu, tCielo);
        gl.glPopMatrix();
        gl.glFlush();

        //nuevo izquierdo
        gl.glPushMatrix();
        gl.glScaled(1.0f, 0.9f, 1.0f);
        gl.glRotatef(90, 0.0f, 1.0f, 0.0f);
        robot.fondo(gl, glu, tIzquierda);
        gl.glPopMatrix();
        gl.glFlush();

        //nuevo derecho
        gl.glPushMatrix();
        gl.glScaled(1.0f, 0.9f, 1.0f);
        gl.glRotatef(-90, 0.0f, 1.0f, 0.0f);
        robot.fondo(gl, glu, tDerecha);
        gl.glPopMatrix();
        gl.glFlush();

        //nuevo frente
        gl.glPushMatrix();
        gl.glScaled(1.0f, 0.9f, 1.0f);
        gl.glTranslated(-1.0f, -1.0f, 10.0f);
        gl.glRotatef(0, 0.0f, 0.0f, 1.0f);
        robot.fondo(gl, glu, tFrente);
        gl.glPopMatrix();
        gl.glFlush();

        //nuevo atrás
        gl.glPushMatrix();
        gl.glScaled(1.0f, 1.0f, 1.0f);
        gl.glTranslated(0.0f, 0.0f, 0.0f);
        robot.fondo(gl, glu, tAtras);
        gl.glPopMatrix();
        gl.glFlush();
        */
        robot.draw_robot(gl, keys['W'], keys['J'], keys['Q'], keys['K'], keys['M']);

        //efecto colisión
       /* float[] centroP = {posX, posY, posZ};
        cajaCabeza = new mCaja(0.2f, 0.9f, 0.1f, centroMCabeza);
        cajaPelota = new mCaja(0.5f, 0.15f, 0.15f, centroP);
        gl.glFlush();

        gl.glPushMatrix();
        gl.glTranslated(posPX, posPY, posPZ);
        robot.draw_caja(gl, glu);
        gl.glPopMatrix();

        if (Interseccion(cajaCabeza, cajaPelota)) {
            System.out.println("Colisión con la cabeza");
            
        }*/

        // Flush all drawing operations to the graphics card
        gl.glFlush();

       

    }
/*
    public boolean Interseccion(mCaja caja1, mCaja caja2) {
        if (Math.abs(caja1.centro[0] - caja2.centro[0]) > (caja1.extend[0] + caja2.extend[0])) {
            return false;
        }
        if (Math.abs(caja1.centro[1] - caja2.centro[1]) > (caja1.extend[1] + caja2.extend[1])) {
            return false;
        }
        if (Math.abs(caja1.centro[2] - caja2.centro[2]) > (caja1.extend[2] + caja2.extend[2])) {
            return false;
        }

        return true;
    }
*/
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        oldMouseX = e.getX();
        oldMouseY = e.getY();
    }

    public void mouseDragged(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();
        Dimension size = e.getComponent().getSize();
        float thetaX = 360.0f * ((float) (x - oldMouseX) / (float) size.width);
        float thetaY = 360.0f * ((float) (oldMouseY - y) / (float) size.height);
        oldMouseX = x;
        oldMouseY = y;
        view_rotx += thetaX;
        view_roty += thetaY;

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 250 && keys[e.getKeyCode()] == false) {
            keys['W'] = false;
            keys['J'] = false;
            keys['Q'] = false;
            keys['K'] = false;
            keys['M'] = false;
            keys[e.getKeyCode()] = true;

            switch (e.getKeyCode()) {
                case 'J':
                    //SoundOf();
                    reproducir(Salto);
                    break;
                case 'W':

                    reproducir(Hola);

                    break;
                case 'Q':
                    reproducir(arco);
                    break;
                case 'K':
                    reproducir(Mano);
                    break;
                case 'M':

                    reproducir(firmes);
                    break;
                case 'D':
                    JOptionPane.showMessageDialog(null, "Desarrollado por: Carlos Hernández");
                    break;
                case 'H':
                    JOptionPane.showMessageDialog(null, "Presiona J, W, Q, K ó M para las diferentes funciones");
                    break;
                case 'O':
                    
                    reproducir(Hola);
/*                    if (!Interseccion(cajaCabeza, cajaPelota)) {
                        posPX -= 0.1f;
                    } else {
                        posPX -= 0.1f;
                    }*/
                    SoundOf();
                    break;
                case 'P':
                    if (posPX <= 1.5f) {
                        posPX += 0.1f;
                    }
                    mFondo = 3;
                    break;
                case 'Z':
                    int confirmacion = JOptionPane.YES_NO_OPTION;
                    JOptionPane.showConfirmDialog(null, "¿Deseas abandonar la partida?", "Precaución", confirmacion);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        
                       // Menu men = new Menu();
                        //men.setVisible(true);
                        
                    } else {
                      
                    
                    }
                    break;
            }
        } else {
            keys[e.getKeyCode()] = false;
        }
    }

    public void reproducir(String archivo) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(archivo)));
            clip.start();
            clip.loop(1000);
           
        } catch (Exception e) {
            System.err.println("Error");
        }

    }

    public void SoundOf() {
        clip.stop();
    }

}
