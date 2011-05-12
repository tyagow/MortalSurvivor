package Map;
import java.awt.Color;
import java.awt.Graphics2D;

import AbstractClasses.Objeto;


public class Obstaculo extends Objeto {

int linha,coluna;
	public Obstaculo(int _x, int _y,int _sizeX,int _sizeY,int _linha,int _coluna) {

		
	X=(_x/16)*16;
	Y=(_y/16)*16;
	sizeX=_sizeX;
	sizeY=_sizeY;
	linha=_linha;
	coluna=_coluna;
	
	
//		defineDimensaoEmTiles(sizeX,sizeY);
		vivo=true;

		
	}
	
	@Override
	public void SimulaSe(int DiffTime) {

		
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		if (linha !=0&&coluna !=0) {
			
			
		}else {
			if (Constantes.Constantes.testeGradeColisao) {
				dbg.setColor(Color.RED);
				dbg.fillRect(((int)getX()-sizeX/2)-XMundo,(int) (getY()-sizeY/2)-YMundo, sizeX, sizeY);
			}
		}
		
	}
	
	
	private void defineDimensaoEmTiles(int sizeX, int sizeY) {
		// TODO Auto-generated method stub
//		if (sizeX%16<4) {
			setSizeX(sizeX);
//				
//		}else {
//			setSizeX(sizeX>>4+1);
//		}
//		if (sizeY%16<4) {
			setSizeY(sizeY);
				
//		}else {
//			setSizeY(sizeY>>4+1);
//		}
		
	}
	
	
	
	
}
