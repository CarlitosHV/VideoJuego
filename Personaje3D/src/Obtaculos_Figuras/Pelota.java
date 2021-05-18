/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obtaculos_Figuras;



/**
 *
 * @author emman
 */
public class Pelota extends Obstaculo
{

	public Pelota()
	{
		super.matrizCoordenadasOriginal = matrizPelota;
		super.matrizCoordenadasTransformada = matrizPelota;
		super.matrizColores = matrizColoresPelota;
		this.px = 0;
        this.py = 0;
		super.darDireccion(0);
	}
	public Pelota(int lado)
	{
		super.matrizCoordenadasOriginal = matrizPelota;
		super.matrizCoordenadasTransformada = matrizPelota;
		super.matrizColores = matrizColoresPelota;
		this.px = 0;
        this.py = 0;
		super.darDireccion(lado);
	}

	
    public float getCentroZ()
    {
        return matrizCoordenadasTransformada[171][2];
    }

   
    public float getCentroX()
    {
        return matrizCoordenadasTransformada[171][0];
    }
    public float getCentroY()
    {
        return matrizCoordenadasTransformada[171][1];
    }
	
	public static float matrizPelota[][] =
	{
		{
			-1, -3, 1,1//---lateraRojol1
		},
		{
			-1, 2, 1,1
		},
		{
			2, 2, 1,1
		},
		{
			2, -3, 1,1
		},
		{
			-1, -3, 5,1//Lateralrojo2
		},
		{
			-1, 2, 5,1
		},
		{
			2, 2, 5,1
		},
		{
			2, -3, 5,1
		},
		{
			-2, -2, 1,1//lateralDerechoRojo1
		},
		{
			-2, 1, 1,1
		},
		{
			-1, 1, 1,1
		},
		{
			-1, -2, 1,1
		},
		{
			-2, -2, 5,1//lateralDerechoRojo2
		},
		{
			-2, 1, 5,1
		},
		{
			-1, 1, 5,1
		},
		{
			-1, -2, 5,1
		},
		{
			2, -2, 1,1//LaterialIzquierdpRojo
		},
		{
			2, 1, 1,1
		},
		{
			3, 1, 1,1
		},
		{
			3, -2, 1,1
		},
		{
			2, -2, 5,1//LaterialDerechoRojo
		},
		{
			2, 1, 5,1
		},
		{
			3, 1, 5,1
		},
		{
			3, -2, 5,1
		},
		{
			2, 1, 5,1//TapaLaterialDerechoSUperior_Rojo
		},
		{
			3, 1, 5,1
		},
		{
			3, 1, 1,1
		},
		{
			2, 1, 1,1
		},
		{
			2, 1, 5,1//TapaLaterialDerechoSUperior l Rojo
		},
		{
			2, 2, 5,1
		},
		{
			2, 2, 1,1
		},
		{
			2, 1, 1,1
		},
		{
			2, 2, 5,1//TapaSuperior Rojo
		},
		{
			-1, 2, 5,1
		},
		{
			-1, 2, 1,1
		},
		{
			2, 2, 1,1
		},
		{
			-1, 1, 5,1//TapaLateralIzquierdaRojo l
		},
		{
			-1, 2, 5,1
		},
		{
			-1, 2, 1,1
		},
		{
			-1, 1, 1,1
		},
		{
			-1, 1, 5,1//TapaLateralIzquierdaRojo _
		},
		{
			-2, 1, 5,1
		},
		{
			-2, 1, 1,1
		},
		{
			-1, 1, 1,1
		},
		{
			-2, 1, 5,1//TapaLateralIzquierdaRojo
		},
		{
			-2, -2, 5,1
		},
		{
			-2, -2, 1,1
		},
		{
			-2, 1, 1,1
		},
		{
			-1, -2, 5,1//TapaLateralIzquierdaInferior_ roja
		},
		{
			-2, -2, 5,1
		},
		{
			-2, -2, 1,1
		},
		{
			-1, -2, 1,1
		},
		{
			-1, -2, 5,1//TapaLateralIzquierdaInferior- roja
		},
		{
			-1, -3, 5,1
		},
		{
			-1, -3, 1,1
		},
		{
			-1, -2, 1,1
		},
		{
			-1, -3, 5,1//TapaInferior roja
		},
		{
			2, -3, 5,1
		},
		{
			2, -3, 1,1
		},
		{
			-1, -3, 1,1
		},
		{
			2, -2, 5,1//TapaInferior Izquierda l roja
		},
		{
			2, -3, 5,1
		},
		{
			2, -3, 1,1
		},
		{
			2, -2, 1,1
		},
		{
			2, -2, 5,1//TapaInferior Izquierda _ roja
		},
		{
			3, -2, 5,1
		},
		{
			3, -2, 1,1
		},
		{
			2, -2, 1,1
		},
		{
			3, 1, 5,1//TapaLateralDerecha
		},
		{
			3, -2, 5,1
		},
		{
			3, -2, 1,1
		},
		{
			3, 1, 1,1
		},
		//------------------------------------amarillos
		{//----------trasero amarillo
			2,1,0,1
		},
		{
			-1,1,0,1
		},
		{
			-1,-2,0,1
		},
		{
			2,-2,0,1
		},
		{//----------delentero amarillo
			2,1,6,1
		},
		{
			-1,1,6,1
		},
		{
			-1,-2,6,1
		},
		{
			2,-2,6,1
		},
		{
			2,1,0,1///-----------trasero amarrillo tapa duperior
		},
		{
			-1,1,0,1
		},
		{
			-1,1,6,1
		},
		{
			2,1,6,1
		},
		{
			-1,1,0,1///-----------trasero amarrillo tapa izquierda
		},
		{
			-1,-2,0,1
		},
		{
			-1,-2,6,1
		},
		{
			-1,1,6,1
		},
		{
			2,1,0,1///-----------trasero amarrillo tapa derecha
		},
		{
			2,-2,0,1
		},
		{
			2,-2,6,1
		},
		{
			2,1,6,1
		},
		{
			-1,-2,0,1///-----------trasero amarrillo tapa derecha
		},
		{
			2,-2,0,1
		},
		{
			2,-2,6,1
		},
		{
			-1,-2,6,1
		},
		{
			-0,0,-1,1///-----------pequeño amarillo trasero
		},
		{
			1,0,-1,1
		},
		{
			1,-1,-1,1
		},
		{
			0,-1,-1,1
		},
		{
			-0,0,7,1///-----------pequeño amarillo delantero
		},
		{
			1,0,7,1
		},
		{
			1,-1,7,1
		},
		{
			0,-1,7,1
		},
		{
			0,0,7,1///-----------pequeño amarillo delantero tapa superior
		},
		{
			1,0,7,1
		},
		{
			1,0,-1,1
		},
		{
			0,0,-1,1
		},
		{
			1,0,7,1///-----------pequeño amarillo delantero tapa lateral derecha
		},
		{
			1,-1,7,1
		},
		{
			1,-1,-1,1
		},
		{
			1,0,-1,1
		},
		{
			0,0,7,1///-----------pequeño amarillo delantero tapa lateral izquierda
		},
		{
			0,-1,7,1
		},
		{
			0,-1,-1,1
		},
		{
			0,0,-1,1
		},
		{
			0,-1,7,1///-----------pequeño amarillo delantero tapa inferior
		},
		{
			1,-1,7,1
		},
		{
			1,-1,-1,1
		},
		{
			0,-1,-1,1
		},
		{
			4,0,2.5f,1///-----------pequeño amarillo lateral1
		},
		{
			4,-1,2.5f,1
		},
		{
			4,-1,3.5f,1
		},
		{
			4,0,3.5f,1
		},
		{
			-3,0,2.5f,1///-----------pequeño amarillo lateral2
		},
		{
			-3,-1,2.5f,1
		},
		{
			-3,-1,3.5f,1
		},
		{
			-3,0,3.5f,1
		},
		{
			-3,0,2.5f,1///-----------pequeño amarillo lateral tapa superior
		},		
		{
			-3,0,3.5f,1
		},
		{
			4,0,3.5f,1
		},		
		{
			4,0,2.5f,1
		},
		{
			-3,0,3.5f,1///-----------pequeño amarillo lateral tapa derecha
		},		
		{
			-3,-1,3.5f,1
		},
		{
			4,-1,3.5f,1
		},		
		{
			4,0,3.5f,1
		},
		{
			-3,0,2.5f,1///-----------pequeño amarillo lateral tapa izquierda
		},		
		{
			-3,-1,2.5f,1
		},
		{
			4,-1,2.5f,1
		},		
		{
			4,0,2.5f,1
		},
		{
			-3,-1,2.5f,1///-----------pequeño amarillo lateral tapa inferior
		},		
		{
			-3,-1,3.5f,1
		},
		{
			4,-1,3.5f,1
		},		
		{
			4,-1,2.5f,1
		},
		{
			0,3,2.5f,1///-----------pequeño amarillo superior tapa superior
		},		
		{
			1,3,2.5f,1
		},
		{
			1,3,3.5f,1
		},		
		{
			0,3,3.5f,1
		},
		{
			0,-4,2.5f,1///-----------pequeño amarillo inferior tapa inferior
		},		
		{
			1,-4,2.5f,1
		},
		{
			1,-4,3.5f,1
		},		
		{
			0,-4,3.5f,1
		},
		{
			0,3,2.5f,1///-----------pequeño amarillo superior tapa delantera
		},		
		{
			1,3,2.5f,1
		},
		{
			1,-4,2.5f,1
		},		
		{
			0,-4,2.5f,1
		},
		{
			0,3,3.5f,1///-----------pequeño amarillo superior tapa delantera
		},		
		{
			1,3,3.5f,1
		},
		{
			1,-4,3.5f,1
		},		
		{
			0,-4,3.5f,1
		},
		{
			1,3,2.5f,1///-----------pequeño amarillo superior tapa derecha
		},		
		{
			1,3,3.5f,1
		},
		{
			1,-4,3.5f,1
		},		
		{
			1,-4,2.5f,1
		},
		{
			0,3,2.5f,1///-----------pequeño amarillo superior tapa derecha
		},		
		{
			0,3,3.5f,1
		},
		{
			0,-4,3.5f,1
		},		
		{
			0,-4,2.5f,1
		},
		//centro
		{
			0.5f,-0.5f,3,1
		},
		{
			0.5f,-0.5f,3,1
		},
		{
			0.5f,-0.5f,3,1
		},
		{
			0.5f,-0.5f,3,1
		},
	};

	public static float matrizColoresPelota[][]
			=
			{
				{//-----------------------------Rojos
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f
				},
				{//--------------------------------Amarillos
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 0.0f, 0.0f//--Rojos cubito delantero 
				},
				{
					1.0f, 0.0f, 0.0f//--
				},
				{
					1.0f, 0.0f, 0.0f//--
				},
				{
					1.0f, 0.0f, 0.0f//--
				},
				{
					1.0f, 0.0f, 0.0f//--
				},
				{
					1.0f, 0.0f, 0.0f//--
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
				{
					1.0f, 1.0f, 0.0f
				},
			};

}
