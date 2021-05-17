/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obtaculos_Figuras;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;

/**
 *
 * @author emman
 */
public abstract class Figura
{

	public float[][] matrizCoordenadasOriginal = null;
	public float[][] matrizCoordenadasTransformada = null;
	public float[][] matrizColores = null;

	public float centroX;
	public float centroY;
	public float centroZ;
	public float radio;

	public int x = 0;
	public int y = 0;
	public int z = 0;

	//----------------------------------------- Getters -----------------------------------------------------
	public float[][] getMatrizCoordenadasOriginal()
	{
		return matrizCoordenadasOriginal;
	}

	public float[][] getMatrizColores()
	{
		return matrizColores;
	}

	public float[][] getMatrizCoordenadasModificada()
	{
		return matrizCoordenadasTransformada;
	}

	//--------------------------------------- Pintar figuras------------------------------------------------
	public void pintarFiguaOriginal3Colores(GL gl)
	{
		gl.glBegin(GL.GL_TRIANGLES);
		int c = 0;
		for (int x = 0; x < matrizCoordenadasOriginal.length; x++)
		{

			if (x % 3 == 0)
			{

				gl.glColor3f(matrizColores[c][0], matrizColores[c][1], matrizColores[c][2]);
				c++;
			}
			gl.glVertex3f(matrizCoordenadasOriginal[x][0], matrizCoordenadasOriginal[x][1], matrizCoordenadasOriginal[x][2]);
		}
		gl.glEnd();
	}

	public void pintarFiguaTransformada3Colores(GL gl)
	{
		gl.glBegin(GL.GL_TRIANGLES);
		int c = 0;
		for (int x = 0; x < matrizCoordenadasTransformada.length; x++)
		{

			if (x % 3 == 0)
			{

				gl.glColor3f(matrizColores[c][0], matrizColores[c][1], matrizColores[c][2]);
				c++;
			}
			gl.glVertex3f(matrizCoordenadasTransformada[x][0], matrizCoordenadasTransformada[x][1], matrizCoordenadasTransformada[x][2]);
		}
		gl.glEnd();
	}

	public void pintarFiguaTranformada4Colores(GL gl)
	{
		gl.glBegin(GL.GL_QUADS);
		int c = 0;
		for (int x = 0; x < matrizCoordenadasTransformada.length; x++)
		{
			if (x % 4 == 0)
			{
				gl.glColor3f(matrizColores[c][0], matrizColores[c][1], matrizColores[c][2]);
				c++;
			}
			gl.glVertex3f(matrizCoordenadasTransformada[x][0], matrizCoordenadasTransformada[x][1], matrizCoordenadasTransformada[x][2]);
		}
		gl.glEnd();
	}

	public void pintarFiguaTranformada3Colores(GL gl)
	{
		gl.glBegin(GL.GL_TRIANGLES);
		int c = 0;
		for (int x = 0; x < matrizCoordenadasTransformada.length; x++)
		{
			if (x % 3 == 0)
			{
				gl.glColor3f(matrizColores[c][0], matrizColores[c][1], matrizColores[c][2]);
				c++;
			}
			gl.glVertex3f(matrizCoordenadasTransformada[x][0], matrizCoordenadasTransformada[x][1], matrizCoordenadasTransformada[x][2]);
		}
		gl.glEnd();
	}

	public void pintarFiguaOriginal4Colores(GL gl)
	{
		gl.glBegin(GL.GL_QUADS);
		int c = 0;
		for (int x = 0; x < matrizCoordenadasOriginal.length; x++)
		{
			if (x % 4 == 0)
			{
				gl.glColor3f(matrizColores[c][0], matrizColores[c][1], matrizColores[c][2]);
				c++;
			}
			gl.glVertex3f(matrizCoordenadasOriginal[x][0], matrizCoordenadasOriginal[x][1], matrizCoordenadasOriginal[x][2]);
		}
		gl.glEnd();
	}

	public void pintarFiguaOriginal3Textura(GL gl, Texture textura)
	{
		textura.enable();
		textura.bind();
		gl.glBegin(GL.GL_TRIANGLES);

		for (int x = 0; x < matrizCoordenadasOriginal.length; x++)
		{
			gl.glVertex3f(matrizCoordenadasOriginal[x][0], matrizCoordenadasOriginal[x][1], matrizCoordenadasOriginal[x][2]);
			gl.glTexCoord3f(matrizCoordenadasOriginal[x][0] / 4, matrizCoordenadasOriginal[x][1] / 4, matrizCoordenadasOriginal[x][2] / 4);
		}

		gl.glEnd();
		textura.disable();

	}

	public void pintarFiguaTransformada3Textura(GL gl, Texture textura)
	{
		textura.enable();
		textura.bind();
		gl.glBegin(GL.GL_TRIANGLES);

		for (int x = 0; x < matrizCoordenadasTransformada.length; x++)
		{
			gl.glVertex3f(matrizCoordenadasTransformada[x][0], matrizCoordenadasTransformada[x][1], matrizCoordenadasTransformada[x][2]);
			gl.glTexCoord3f(matrizCoordenadasTransformada[x][0] / 4, matrizCoordenadasTransformada[x][1] / 4, matrizCoordenadasTransformada[x][2] / 4);
		}

		gl.glEnd();
		textura.disable();
	}

	//------------------------------------- Imprimir matrices ------------------------------------------------
	public void imprimirMatrizCoordenadasOriginal()
	{
		for (int x = 0; x < matrizCoordenadasOriginal.length; x++)
		{
			System.out.print("{");
			for (int y = 0; y < matrizCoordenadasOriginal[x].length; y++)
			{
				
					System.out.print(matrizCoordenadasOriginal[x][y] + "f,");
				
				if (y != matrizCoordenadasOriginal[x].length - 1)
				{
					System.out.print("\t");
				}

			}
			System.out.println("},");
		}
	}

	public void imprimirMatrizCoordenadasTransformada()
	{
		for (int x = 0; x < matrizCoordenadasTransformada.length; x++)
		{
			System.out.print("{");
			for (int y = 0; y < matrizCoordenadasTransformada[x].length; y++)
			{
				System.out.print(matrizCoordenadasTransformada[x][y] + "f,");
				if (y != matrizCoordenadasTransformada[x].length - 1)
				{
					System.out.print("\t");
				}

			}
			System.out.println("},");
		}
	}

	//---------------------------------------Transformaciones 3D-------------------------------------------------
	public void traslacionOriginal(float x, float y, float z)
	{
		float[][] matrizTraslacion =
		{
			{
				1, 0, 0, 0
			},
			{
				0, 1, 0, 0
			},
			{
				0, 0, 1, 0
			},
			{
				x, y, z, 1
			}
		};
		float resultante[][] = new float[matrizCoordenadasOriginal.length][matrizTraslacion.length];

		// se comprueba si las matrices se pueden multiplicar
		if (matrizCoordenadasOriginal[0].length == matrizTraslacion.length)
		{
			for (int i = 0; i < matrizCoordenadasOriginal.length; i++)
			{
				for (int j = 0; j < matrizTraslacion[0].length; j++)
				{
					for (int k = 0; k < matrizCoordenadasOriginal[0].length; k++)
					{
						// aquí se multiplica la matriz
						resultante[i][j] += matrizCoordenadasOriginal[i][k] * matrizTraslacion[k][j];
					}
				}
			}
		} else
		{
			//si no se cumple la condición se retorna una matriz vacía         
			System.err.println("Multiplicacion invalida");
		}
		matrizCoordenadasTransformada = resultante;
	}

	public void escalacionOriginal(float x, float y, float z)
	{
		float[][] matrizEscalacion =
		{
			{
				x, 0, 0, 0
			},
			{
				0, y, 0, 0
			},
			{
				0, 0, z, 0
			},
			{
				0, 0, 0, 1
			}
		};
		float resultante[][] = new float[matrizCoordenadasOriginal.length][matrizEscalacion.length];

		// se comprueba si las matrices se pueden multiplicar
		if (matrizCoordenadasOriginal[0].length == matrizEscalacion.length)
		{
			for (int i = 0; i < matrizCoordenadasOriginal.length; i++)
			{
				for (int j = 0; j < matrizEscalacion[0].length; j++)
				{
					for (int k = 0; k < matrizCoordenadasOriginal[0].length; k++)
					{
						// aquí se multiplica la matriz
						resultante[i][j] += matrizCoordenadasOriginal[i][k] * matrizEscalacion[k][j];
					}
				}
			}
		} else
		{
			//si no se cumple la condición se retorna una matriz vacía         
			System.err.println("Multiplicacion invalida");
		}
		matrizCoordenadasTransformada = resultante;
	}

	public void rotacionOriginalEnX(float angulo)
	{
		angulo = (float) (Math.toRadians(angulo));
		float[][] matrizEscalacion =
		{
			{
				(float) (Math.cos(angulo)), (float) (Math.sin(angulo)), 0, 0
			},
			{
				(float) (Math.sin(angulo) * -1), (float) (Math.cos(angulo)), 0, 0
			},
			{
				0, 0, 1, 0
			},
			{
				0, 0, 0, 1
			}
		};
		float resultante[][] = new float[matrizCoordenadasOriginal.length][matrizEscalacion.length];

		// se comprueba si las matrices se pueden multiplicar
		if (matrizCoordenadasOriginal[0].length == matrizEscalacion.length)
		{
			for (int i = 0; i < matrizCoordenadasOriginal.length; i++)
			{
				for (int j = 0; j < matrizEscalacion[0].length; j++)
				{
					for (int k = 0; k < matrizCoordenadasOriginal[0].length; k++)
					{
						// aquí se multiplica la matriz
						resultante[i][j] += matrizCoordenadasOriginal[i][k] * matrizEscalacion[k][j];
					}
				}
			}
		} else
		{
			//si no se cumple la condición se retorna una matriz vacía         
			System.err.println("Multiplicacion invalida");
		}
		matrizCoordenadasTransformada = resultante;
	}

	public void rotacionOriginalEnY(float angulo)
	{
		angulo = (float) (Math.toRadians(angulo));
		float[][] matrizEscalacion =
		{
			{
				(float) (Math.cos(angulo)), 0, (float) (Math.sin(angulo) * -1), 0
			},
			{
				0, 1, 0, 0
			},
			{
				(float) (Math.sin(angulo)), 0, (float) (Math.cos(angulo)), 0
			},
			{
				0, 0, 0, 1
			}
		};
		float resultante[][] = new float[matrizCoordenadasOriginal.length][matrizEscalacion.length];

		// se comprueba si las matrices se pueden multiplicar
		if (matrizCoordenadasOriginal[0].length == matrizEscalacion.length)
		{
			for (int i = 0; i < matrizCoordenadasOriginal.length; i++)
			{
				for (int j = 0; j < matrizEscalacion[0].length; j++)
				{
					for (int k = 0; k < matrizCoordenadasOriginal[0].length; k++)
					{
						// aquí se multiplica la matriz
						resultante[i][j] += matrizCoordenadasOriginal[i][k] * matrizEscalacion[k][j];
					}
				}
			}
		} else
		{
			//si no se cumple la condición se retorna una matriz vacía         
			System.err.println("Multiplicacion invalida");
		}
		matrizCoordenadasTransformada = resultante;
	}

	public void rotacionOriginalEnZ(float angulo)
	{
		angulo = (float) (Math.toRadians(angulo));
		float[][] matrizEscalacion =
		{
			{
				1, 0, 0, 0
			},
			{
				0, (float) (Math.cos(angulo)), (float) (Math.sin(angulo)), 0
			},
			{
				0, (float) (Math.sin(angulo) * -1), (float) (Math.cos(angulo)), 0
			},
			{
				0, 0, 0, 1
			},
		};
		float resultante[][] = new float[matrizCoordenadasOriginal.length][matrizEscalacion.length];

		// se comprueba si las matrices se pueden multiplicar
		if (matrizCoordenadasOriginal[0].length == matrizEscalacion.length)
		{
			for (int i = 0; i < matrizCoordenadasOriginal.length; i++)
			{
				for (int j = 0; j < matrizEscalacion[0].length; j++)
				{
					for (int k = 0; k < matrizCoordenadasOriginal[0].length; k++)
					{
						// aquí se multiplica la matriz
						resultante[i][j] += matrizCoordenadasOriginal[i][k] * matrizEscalacion[k][j];
					}
				}
			}
		} else
		{
			//si no se cumple la condición se retorna una matriz vacía         
			System.err.println("Multiplicacion invalida");
		}
		matrizCoordenadasTransformada = resultante;
	}

	public void traslacionTransformada(float x, float y, float z)
	{
		float[][] matrizTraslacion =
		{
			{
				1, 0, 0, 0
			},
			{
				0, 1, 0, 0
			},
			{
				0, 0, 1, 0
			},
			{
				x, y, z, 1
			}
		};
		float resultante[][] = new float[matrizCoordenadasTransformada.length][matrizTraslacion.length];

		// se comprueba si las matrices se pueden multiplicar
		if (matrizCoordenadasTransformada[0].length == matrizTraslacion.length)
		{
			for (int i = 0; i < matrizCoordenadasTransformada.length; i++)
			{
				for (int j = 0; j < matrizTraslacion[0].length; j++)
				{
					for (int k = 0; k < matrizCoordenadasTransformada[0].length; k++)
					{
						// aquí se multiplica la matriz
						resultante[i][j] += matrizCoordenadasTransformada[i][k] * matrizTraslacion[k][j];
					}
				}
			}
		} else
		{
			//si no se cumple la condición se retorna una matriz vacía         
			System.err.println("Multiplicacion invalida");
		}
		matrizCoordenadasTransformada = resultante;
	}

	public void rotacionTransformadaEnY(float angulo)
	{
		angulo = (float) (Math.toRadians(angulo));
		float[][] matrizEscalacion =
		{
			{
				(float) (Math.cos(angulo)), 0, (float) (Math.sin(angulo) * -1), 0
			},
			{
				0, 1, 0, 0
			},
			{
				(float) (Math.sin(angulo)), 0, (float) (Math.cos(angulo)), 0
			},
			{
				0, 0, 0, 1
			}
		};
		float resultante[][] = new float[matrizCoordenadasTransformada.length][matrizEscalacion.length];

		// se comprueba si las matrices se pueden multiplicar
		if (matrizCoordenadasTransformada[0].length == matrizEscalacion.length)
		{
			for (int i = 0; i < matrizCoordenadasTransformada.length; i++)
			{
				for (int j = 0; j < matrizEscalacion[0].length; j++)
				{
					for (int k = 0; k < matrizCoordenadasTransformada[0].length; k++)
					{
						// aquí se multiplica la matriz
						resultante[i][j] += matrizCoordenadasTransformada[i][k] * matrizEscalacion[k][j];
					}
				}
			}
		} else
		{
			//si no se cumple la condición se retorna una matriz vacía         
			System.err.println("Multiplicacion invalida");
		}
		matrizCoordenadasTransformada = resultante;
	}

	public void rotacionTransformadaEnYaOriginal(float angulo)
	{
		angulo = (float) (Math.toRadians(angulo));
		float[][] matrizEscalacion =
		{
			{
				(float) (Math.cos(angulo)), 0, (float) (Math.sin(angulo) * -1), 0
			},
			{
				0, 1, 0, 0
			},
			{
				(float) (Math.sin(angulo)), 0, (float) (Math.cos(angulo)), 0
			},
			{
				0, 0, 0, 1
			}
		};
		float resultante[][] = new float[matrizCoordenadasTransformada.length][matrizEscalacion.length];

		// se comprueba si las matrices se pueden multiplicar
		if (matrizCoordenadasTransformada[0].length == matrizEscalacion.length)
		{
			for (int i = 0; i < matrizCoordenadasTransformada.length; i++)
			{
				for (int j = 0; j < matrizEscalacion[0].length; j++)
				{
					for (int k = 0; k < matrizCoordenadasTransformada[0].length; k++)
					{
						// aquí se multiplica la matriz
						resultante[i][j] += matrizCoordenadasTransformada[i][k] * matrizEscalacion[k][j];
					}
				}
			}
		} else
		{
			//si no se cumple la condición se retorna una matriz vacía         
			System.err.println("Multiplicacion invalida");
		}
		matrizCoordenadasOriginal = resultante;
	}

	public void rotacionOriginalEnYEnOriginal(float angulo)
	{
		angulo = (float) (Math.toRadians(angulo));
		float[][] matrizEscalacion =
		{
			{
				(float) (Math.cos(angulo)), 0, (float) (Math.sin(angulo) * -1), 0
			},
			{
				0, 1, 0, 0
			},
			{
				(float) (Math.sin(angulo)), 0, (float) (Math.cos(angulo)), 0
			},
			{
				0, 0, 0, 1
			}
		};
		float resultante[][] = new float[matrizCoordenadasOriginal.length][matrizEscalacion.length];

		// se comprueba si las matrices se pueden multiplicar
		if (matrizCoordenadasOriginal[0].length == matrizEscalacion.length)
		{
			for (int i = 0; i < matrizCoordenadasOriginal.length; i++)
			{
				for (int j = 0; j < matrizEscalacion[0].length; j++)
				{
					for (int k = 0; k < matrizCoordenadasOriginal[0].length; k++)
					{
						// aquí se multiplica la matriz
						resultante[i][j] += matrizCoordenadasOriginal[i][k] * matrizEscalacion[k][j];
					}
				}
			}
		} else
		{
			//si no se cumple la condición se retorna una matriz vacía         
			System.err.println("Multiplicacion invalida");
		}
		matrizCoordenadasOriginal = resultante;
	}

}
