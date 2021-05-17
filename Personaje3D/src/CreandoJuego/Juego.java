package CreandoJuego;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.j2d.TextRenderer;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import frames.Niveles;

import java.awt.Font;
import java.awt.Frame;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import javax.media.opengl.GL;
import static javax.media.opengl.GL.GL_DEPTH_TEST;
import static javax.media.opengl.GL.GL_LEQUAL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public class Juego implements GLEventListener
{

	public static Boolean conMusica = true;
	public static Clip clipFondo;
	public static String musicaFondo;
	public static String sonidoPunto;
	public static String sonidoPerdida;

	private Texture texturaFondoSuelo;
	private Texture texturaFondoPared;
	public static String imagenTexturaSuelo;
	public static String imagenTexturaPared;
	String archivo = "textura_diamante_rojo3.jpg";
	private Texture texturaDiamante;
	public static float colorFondoR;
	public static float colorFondoG;
	public static float colorFondoB;

	public static Figura personajePrincipal;
	public static ArrayList<Figura> arregloObjetivos = new ArrayList<Figura>();
	public static ArrayList<Obstaculo> arregloObstaculos = new ArrayList<Obstaculo>();

	boolean jugando = true;
	int pintadas = 0;

	static boolean regresarVentanaActiva = false;
	static RegresarAlMenu n;

	static int x = 0;
	static int z = 0;
	public static int limiteSuperior;
	public static int limiteInferior;
	public static int limiteDerecho;
	public static int limiteIzquierdo;
	public static int distancialimiteEntreObjetivoYPersonajes;
	public static int distancialimiteEntreObstaculosYPersonajes;

	public static int puntuacion = 0;
	public static int puntuacionCoordenadaX;
	public static int puntuacionCoordenadaY;
	public static float colorPuntR;
	public static float colorPuntG;
	public static float colorPuntB;

	public static int ladoAGirar = 4;
	public static int ladoActual = 4;

	static Animator animator;
	public static Frame frame;

	public static void main(String[] args)
	{
		frame = new Frame("Simple JOGL Application");
		GLCanvas canvas = new GLCanvas();

		if (conMusica)
		{
			sonidoFondo(musicaFondo);
		}

		canvas.addGLEventListener(new Juego());

		KeyListener kl = new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
			}

			@Override
			public void keyPressed(KeyEvent evt)
			{
				switch (evt.getKeyCode())
				{
					case VK_UP:
						if (z > limiteSuperior)
						{
							z--;
						}
						ladoAGirar = 8;
						break;
					case VK_RIGHT:
						if (x < limiteDerecho)
						{
							x++;
						}
						ladoAGirar = 6;
						break;
					case VK_LEFT:
						if (x > limiteIzquierdo)
						{
							x--;
						}
						ladoAGirar = 4;
						break;
					case VK_DOWN:
						if (z < limiteInferior)
						{
							z++;
						}
						ladoAGirar = 2;
						break;
					default:
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
			}
		};
		frame.addKeyListener(kl);

		frame.add(canvas);
		frame.setSize(640, 480);
		animator = new Animator(canvas);
		frame.addWindowListener(new WindowAdapter()
		{

			@Override
			public  void windowClosing(WindowEvent e)
			{
				// Run this on another thread than the AWT event queue to
				// make sure the call to Animator.stop() completes before
				// exiting
				new Thread(new Runnable()
				{

					public void run()
					{
						animator.stop();
						if (conMusica)
						{
							clipFondo.stop();

						}
						frame.dispose();
						Niveles nuevo = new Niveles();
						nuevo.seleccionP = 1;
						nuevo.setVisible(true);

						if (n!=null)
						{
							n.dispose();
						}

					}
				}).start();
			}
		});
		// Center frame
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setExtendedState(MAXIMIZED_BOTH);
		animator.start();
	}

	public void init(GLAutoDrawable drawable)
	{
		// Use debug pipeline
		// drawable.setGL(new DebugGL(drawable.getGL()));

		GL gl = drawable.getGL();
		try
		{
			texturaFondoSuelo = TextureIO.newTexture(new File("src\\imagenes\\" + imagenTexturaSuelo), true);
			texturaFondoPared = TextureIO.newTexture(new File("src\\imagenes\\" + imagenTexturaPared), true);
			texturaDiamante = TextureIO.newTexture(new File(archivo), true);
		} catch (Exception ex)
		{
			System.out.println("Error en tuxtura");
		}
		// Enable VSync
		gl.setSwapInterval(1);

		// Setup the drawing area and shading mode
		gl.glClearColor(colorFondoR, colorFondoG, colorFondoB, 0.0f);
		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL_LEQUAL);
		gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.

		personajePrincipal.escalacionOriginal(2, 2, 2);

	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		GL gl = drawable.getGL();
		GLU glu = new GLU();

		if (height <= 0)
		{ // avoid a divide by zero error!

			height = 1;
		}
		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(40.0f, h, 1.0, 900.0);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public void display(GLAutoDrawable drawable)
	{
		++pintadas;
		GL gl = drawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(-1.5f, 0.0f, -150.0f);
		gl.glRotatef(40, 1.0f, 0.0f, 0.0f);//y
		//gl.glRotatef(, 0.0f, 1.0f, 0.0f);//x
		if (jugando)
		{
			pintarEscenario(gl);
			pintarPersonajePrincipal(gl);
			comprobarToqueDeObjetivos();
			pintarObjetivos(gl);
			comprobarReboteObstaculo();
			pintarObstaculos(gl);
			comprobarPerdida();
			pintarPuntuacion();
		} else
		{
			pintarEscenario(gl);
			pintarPersonajePrincipal(gl);
			pintarPuntuacionFinal();
			animator.stop();
			if (conMusica)
			{
				clipFondo.stop();
			}

			n = new RegresarAlMenu(frame);
			n.setVisible(true);

		}
		gl.glFlush();

	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged)
	{
	}

	public void comprobarPerdida()
	{
		//recorre todos los obstaculoa para ver si alguno fue tocado
		for (int i = 0; i < Juego.arregloObstaculos.size(); i++)
		{
			float x1 = personajePrincipal.centroX;
			float y1 = personajePrincipal.centroY;
			float z1 = personajePrincipal.centroZ;

			float x2 = ((Pelota) arregloObstaculos.get(i)).getCentroX();
			float y2 = ((Pelota) arregloObstaculos.get(i)).getCentroY();
			float z2 = ((Pelota) arregloObstaculos.get(i)).getCentroZ();

			float distanciaEntreObjetos = (float) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2) + Math.pow((z2 - z1), 2));

			if (distanciaEntreObjetos < distancialimiteEntreObstaculosYPersonajes)
			{
				jugando = false;
				sonido(sonidoPerdida);
			}

		}
	}

	public void comprobarToqueDeObjetivos()
	{
		for (int i = 0; i < arregloObjetivos.size(); i++)
		{
			float x1 = personajePrincipal.centroX;
			float y1 = personajePrincipal.centroY;
			float z1 = personajePrincipal.centroZ;

			float x2 = arregloObjetivos.get(i).centroX;
			float y2 = arregloObjetivos.get(i).centroY;
			float z2 = arregloObjetivos.get(i).centroZ;

			float distanciaEntreObjetos = (float) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2) + Math.pow((z2 - z1), 2));

			if (distanciaEntreObjetos < distancialimiteEntreObjetivoYPersonajes)
			{
				arregloObjetivos.remove(i);
				if (personajePrincipal instanceof Perro)
				{
					arregloObjetivos.add(new Hueso());
				} else if (personajePrincipal instanceof Colibri)
				{
					arregloObjetivos.add(new Diamante());
				} else if (personajePrincipal instanceof Raton)
				{
					arregloObjetivos.add(new Queso());
				}
				arregloObstaculos.add(new Pelota());
				sonido(sonidoPunto);
				++puntuacion;

			}
		}

	}

	public void pintarObjetivos(GL gl)
	{
		TextRenderer txt = new TextRenderer(new Font("Sugarpunch DEMO", Font.PLAIN, 20));
		txt.beginRendering(600, 600);
		txt.setColor(colorPuntR, colorPuntG, colorPuntB, 1f);
		//txt.draw("Puntuación: " + puntuacion, puntuacionCoordenadaX, puntuacionCoordenadaY);
		txt.setColor(1f, 1f, 1f, 1f);
		txt.endRendering();

		for (Figura objetivo : arregloObjetivos)
		{
			if (objetivo instanceof Diamante)
			{
				objetivo.pintarFiguaTransformada3Textura(gl, texturaDiamante);

			} else if (objetivo instanceof Hueso)
			{
				objetivo.pintarFiguaTranformada4Colores(gl);
			} else if (objetivo instanceof Queso)
			{
				objetivo.pintarFiguaTranformada3Colores(gl);
			}
		}
	}

	public void pintarObstaculos(GL gl)
	{
		for (Figura obstaculo : arregloObstaculos)
		{

			if (pintadas % 3 == 0)
			{
				obstaculo.traslacionTransformada(obstaculo.x, obstaculo.y, obstaculo.z);
			}
			obstaculo.pintarFiguaTranformada4Colores(gl);
		}
	}

	private void comprobarReboteObstaculo()
	{
		for (int i = 0; i < arregloObstaculos.size(); i++)
		{
			if (((Pelota) (arregloObstaculos.get(i))).getCentroX() == (limiteIzquierdo - 10))
			{
				arregloObstaculos.remove(i);
				arregloObstaculos.add(new Pelota(4));
			} else if (((Pelota) (arregloObstaculos.get(i))).getCentroX() == (limiteDerecho + 10))
			{

				arregloObstaculos.remove(i);
				arregloObstaculos.add(new Pelota(2));
			} else if (((Pelota) (arregloObstaculos.get(i))).getCentroZ() == (limiteSuperior - 10))
			{

				arregloObstaculos.remove(i);
				arregloObstaculos.add(new Pelota(1));
			} else if (((Pelota) (arregloObstaculos.get(i))).getCentroZ() == (limiteInferior + 10))
			{

				arregloObstaculos.remove(i);
				arregloObstaculos.add(new Pelota(3));
			}
		}
	}

	public void pintarEscenario(GL gl)
	{
		pintarSuelo(gl);
		pintarParedFrontal(gl);
		pintarParedIzquierda(gl);
		pintarParedDErecha(gl);
	}

	public void pintarSuelo(GL gl)
	{
		texturaFondoSuelo.enable();
		texturaFondoSuelo.bind();
		gl.glBegin(GL.GL_QUADS);
		//gl.glColor3f(0.5f, 0.5f, 1.0f);
		gl.glTexCoord3f(0f, 0, 0); //superior izquierda
		gl.glVertex3f(-90, -4, -90);

		gl.glTexCoord3f(1f, 0f, 0); //inferior izquierda
		gl.glVertex3f(-90, -4, 57);

		gl.glTexCoord3f(1f, 1f, 1); //inferior derecha
		gl.glVertex3f(90, -4, 57);

		gl.glTexCoord3f(0f, 1f, 1); //superior derecha
		gl.glVertex3f(90, -4, -90);

		gl.glEnd();
		texturaFondoSuelo.disable();
	}

	public void pintarParedFrontal(GL gl)
	{
		texturaFondoPared.enable();
		texturaFondoPared.bind();
		gl.glBegin(GL.GL_QUADS);
		//gl.glColor3f(0.5f, 0.5f, 1.0f);
		gl.glTexCoord3f(0f, 0, 0); //superior izquierda
		gl.glVertex3f(-90, 25, -90);

		gl.glTexCoord3f(0f, 1f, 1); //inferior izquierda
		gl.glVertex3f(-90, -4, -90);

		gl.glTexCoord3f(1f, 1f, 1); //inferior derecha
		gl.glVertex3f(90, -4, -90);

		gl.glTexCoord3f(1f, 0f, 0); //superior derecha
		gl.glVertex3f(90, 25, -90);

		gl.glEnd();
		texturaFondoPared.disable();
	}

	public void pintarParedIzquierda(GL gl)
	{
		texturaFondoPared.enable();
		texturaFondoPared.bind();
		gl.glBegin(GL.GL_QUADS);
		//gl.glColor3f(0.5f, 0.5f, 1.0f);
		gl.glTexCoord3f(0f, 0, 0); //superior izquierda
		gl.glVertex3f(-90, 35, 90);

		gl.glTexCoord3f(0f, 1f, 1); //inferior izquierda
		gl.glVertex3f(-90, -4, 90);

		gl.glTexCoord3f(1f, 1f, 1); //inferior derecha
		gl.glVertex3f(-90, -4, -90);

		gl.glTexCoord3f(1f, 0f, 0); //superior derecha
		gl.glVertex3f(-90, 35, -90);

		gl.glEnd();
		texturaFondoPared.disable();
	}

	public void pintarParedDErecha(GL gl)
	{
		texturaFondoPared.enable();
		texturaFondoPared.bind();
		gl.glBegin(GL.GL_QUADS);
		//gl.glColor3f(0.5f, 0.5f, 1.0f);
		gl.glTexCoord3f(0f, 0, 0); //superior izquierda
		gl.glVertex3f(90, 35, 90);

		gl.glTexCoord3f(0f, 1f, 1); //inferior izquierda
		gl.glVertex3f(90, -4, 90);

		gl.glTexCoord3f(1f, 1f, 1); //inferior derecha
		gl.glVertex3f(90, -4, -90);

		gl.glTexCoord3f(1f, 0f, 0); //superior derecha
		gl.glVertex3f(90, 35, -90);

		gl.glEnd();
		texturaFondoPared.disable();
	}

	public void pintarPuntuacion()
	{
		TextRenderer txt = new TextRenderer(new Font("Sugarpunch DEMO", Font.PLAIN, 20));
		txt.beginRendering(600, 600);
		txt.setColor(colorPuntR, colorPuntG, colorPuntB, 1f);
		txt.draw("Puntuación: " + puntuacion, puntuacionCoordenadaX, puntuacionCoordenadaY);
		txt.setColor(1f, 1f, 1f, 1f);
		txt.endRendering();
	}

	public void pintarPuntuacionFinal()
	{
		TextRenderer txt = new TextRenderer(new Font("Sugarpunch DEMO", Font.PLAIN, 60));
		txt.beginRendering(600, 600);
		txt.setColor(colorPuntR, colorPuntG, colorPuntB, 1f);
		txt.draw("Puntuación: " + puntuacion, 120, 250);
		txt.setColor(1f, 1f, 1f, 1f);
		txt.endRendering();

	}

	public void pintarPersonajePrincipal(GL gl)
	{
		darDileccion(ladoAGirar);
		personajePrincipal.traslacionOriginal(x, 0, z);
		if (personajePrincipal instanceof Perro)
		{
			personajePrincipal.pintarFiguaTranformada4Colores(gl);

			personajePrincipal.centroX = personajePrincipal.matrizCoordenadasTransformada[563][0];
			personajePrincipal.centroY = personajePrincipal.matrizCoordenadasTransformada[563][1];
			personajePrincipal.centroZ = personajePrincipal.matrizCoordenadasTransformada[563][2];
		} else if (personajePrincipal instanceof Colibri)
		{
			personajePrincipal.pintarFiguaTranformada3Colores(gl);

			personajePrincipal.centroX = personajePrincipal.matrizCoordenadasTransformada[344][0];
			personajePrincipal.centroY = personajePrincipal.matrizCoordenadasTransformada[344][1];
			personajePrincipal.centroZ = personajePrincipal.matrizCoordenadasTransformada[344][2];
		} else if (personajePrincipal instanceof Raton)
		{
			personajePrincipal.pintarFiguaTranformada4Colores(gl);

			personajePrincipal.centroX = personajePrincipal.matrizCoordenadasTransformada[539][0];
			personajePrincipal.centroY = personajePrincipal.matrizCoordenadasTransformada[539][1];
			personajePrincipal.centroZ = personajePrincipal.matrizCoordenadasTransformada[539][2];
		}
	}

	public static void darDileccion(int ladoAGirar)
	{
		switch (ladoAGirar)
		{
			case 8:
				switch (Juego.ladoActual)
				{
					case 4:
						personajePrincipal.rotacionOriginalEnYEnOriginal(-90);
						Juego.ladoActual = 8;
						break;
					case 8:
						Juego.ladoActual = 8;
						break;
					case 6:
						personajePrincipal.rotacionOriginalEnYEnOriginal(90);
						Juego.ladoActual = 8;
						break;
					case 2:
						personajePrincipal.rotacionOriginalEnYEnOriginal(180);
						Juego.ladoActual = 8;
						break;
					default:
				}
				break;
			case 6:
				switch (Juego.ladoActual)
				{
					case 4:
						personajePrincipal.rotacionOriginalEnYEnOriginal(180);
						Juego.ladoActual = 6;
						break;
					case 8:
						personajePrincipal.rotacionOriginalEnYEnOriginal(-90);
						Juego.ladoActual = 6;
						break;
					case 6:
						Juego.ladoActual = 6;
						break;
					case 2:
						personajePrincipal.rotacionOriginalEnYEnOriginal(90);
						Juego.ladoActual = 6;
						break;
					default:
				}
				break;
			case 2:
				switch (Juego.ladoActual)
				{
					case 4:
						personajePrincipal.rotacionOriginalEnYEnOriginal(90);
						Juego.ladoActual = 2;
						break;
					case 8:
						personajePrincipal.rotacionOriginalEnYEnOriginal(180);
						Juego.ladoActual = 2;
						break;
					case 6:
						personajePrincipal.rotacionOriginalEnYEnOriginal(-90);
						Juego.ladoActual = 2;
						break;
					case 2:
						Juego.ladoActual = 2;
						break;
					default:
				}
				break;
			case 4:
				switch (Juego.ladoActual)
				{
					case 4:
						Juego.ladoActual = 4;
						break;
					case 8:
						personajePrincipal.rotacionOriginalEnYEnOriginal(90);
						Juego.ladoActual = 4;
						break;
					case 6:
						personajePrincipal.rotacionOriginalEnYEnOriginal(180);
						Juego.ladoActual = 4;
						break;
					case 2:
						personajePrincipal.rotacionOriginalEnYEnOriginal(-90);
						Juego.ladoActual = 4;
						break;
					default:
				}
				break;
			default:
		}
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

	public static synchronized void sonido(final String sonido)
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
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(d);
					clip.open(inputStream);
					clip.start();
				} catch (Exception e)
				{
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}
}
