import java.awt.Color;
import java.awt.Graphics2D;


public class Obstaculo extends Objeto {

	
	public Obstaculo(int _x, int _y,int sizeX,int sizeY) {
		
		setX(_x>>4);
		setY(_y>>4);
		
		defineLarguraEmTiles(sizeX,sizeY);
		
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
//		dbg.setColor(Color.RED);
//		dbg.fillRect(((int)getX()*16)-XMundo,(int) (getY()*16)-YMundo, 16, 16);
	}
	
	
	private void defineLarguraEmTiles(int sizeX, int sizeY) {
		// TODO Auto-generated method stub
		if (sizeX%16<4) {
			setSizeX(sizeX>>4);
				
		}else {
			setSizeX(sizeX>>4+1);
		}
		if (sizeY%16<4) {
			setSizeY(sizeY>>4);
				
		}else {
			setSizeY(sizeY>>4+1);
		}
		
	}
	
	
	
	
}
