/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obtaculos_Figuras;

import java.util.Random;

/**
 *
 * @author emman
 */
public abstract class Obstaculo extends Figura
{

    public int lado;
    public int px;
    public int py;
    public int pz;

    
    public void darDireccion(int lado)
    {
        this.px=0;
        this.py=0;
        this.pz=0;
		
        this.x=0;
        this.y=0;
		this.z=0;
		
        Random aleatorio = new Random();
        if (lado == 0)
        {
            lado = (int) (Math.random() * 4) + 1;
        }

        int direccion = (int) (Math.random() * 3) + 1;

        switch (lado)
        {
            case 1:
                this.px = aleatorio.nextInt(161) - 80;
                this.py = 0;
                this.pz = -90;
                switch (direccion)
                {
                    case 1:
                        this.x = -1;
                        this.y = 0;
						this.z = 1;
                        break;
                    case 2:
                        this.x = 0;
                        this.y = 0;
                        this.z = 1;
						
                        break;
                    case 3:
                        this.x = 1;
                        this.y = 0;
                        this.z = 1;
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                this.px = 90;
                this.py = 0;
				this.pz = aleatorio.nextInt(161) - 80;
                switch (direccion)
                {
                    case 1:
                        this.x = -1;
                        this.y =0;
						this.z=-1;
                        break;
                    case 2:
                        this.x = -1;
                        this.y = 0;
                        this.z = 0;
                        break;
                    case 3:
                        this.x = -1;
                        this.y = 0;
                        this.z = +1;
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                 this.px = aleatorio.nextInt(161) - 80;
                this.py = 0;
				 this.pz = 57;
                switch (direccion)
                {
                    case 1:
                        this.x = 1;
                        this.y = 0;
                        this.z = -1;
                        break;
                    case 2:
                        this.x = 0;
                        this.y = 0;
                        this.z = -1;
                        break;
                    case 3:
                        this.x = -1;
                        this.y = 0;
                        this.z = -1;
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                this.px = -90;
                this.py = 0;
				this.pz = aleatorio.nextInt(161) - 80;
                switch (direccion)
                {
                    case 1:
                        this.x = 1;
                        this.y = 0;
                        this.z = 1;
                        break;
                    case 2:
                        this.x = 1;
                        this.y = 0;
                        this.z = 0;
                        break;
                    case 3:
                        this.x = 1;
                        this.y = 0;
                        this.z = -1;
                        break;
                    default:
                        break;
                }
                break;
            default:

        }
        traslacionTransformada(this.px, this.py,this.pz);
    }
}
