package Map;
import java.awt.Color;
import java.awt.Graphics2D;

import AbstractClasses.Objeto;


public class Obstaculo extends Objeto {

public int tileSetLinha, tileSetColuna;

	public Obstaculo(int _x, int _y,int _sizeX,int _sizeY,int _tileSetColuna,int _tileSetLinha) {

		
	X=(_x/16)*16;
	Y=(_y/16)*16;
	sizeX=_sizeX;
	sizeY=_sizeY;
	tileSetLinha=_tileSetLinha;
	tileSetColuna=_tileSetColuna;
	
	
//		defineDimensaoEmTiles(sizeX,sizeY);
		vivo=true;

		
	}
	
	@Override
	public void SimulaSe(int DiffTime) {

		
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int xTela, int yTela) {
		// TODO Auto-generated method stub
		if (tileSetLinha != 0 || tileSetColuna != 0) {

			dbg.drawImage(Data.Imagem.obstaculos,(int) getX() - xTela - getSizeX()/2,(int) getY() - yTela - getSizeY()/2,(int) getX() + getSizeX() - xTela - getSizeX()/2,(int) getY() + getSizeY() - yTela - getSizeY()/2, (tileSetColuna*32), (tileSetLinha*32), tileSetColuna*32 + 32, tileSetLinha*32 + 32, null);
			//dbg.drawRect((int)getX() - xTela, (int)getY() - yTela, getSizeX(), getSizeY());
			if (Constantes.Constantes.testeGradeColisao) {
				dbg.setColor(Color.RED);
				dbg.fillRect(((int)getX()-sizeX/2)-xTela,(int) (getY()-sizeY/2)-yTela, sizeX, sizeY);
			}
		}else {
			if (Constantes.Constantes.testeGradeColisao) {
				dbg.setColor(Color.RED);
				dbg.fillRect(((int)getX()-sizeX/2)-xTela,(int) (getY()-sizeY/2)-yTela, sizeX, sizeY);
			}
		}
		
	}
	
	

	
	
	
	
}
