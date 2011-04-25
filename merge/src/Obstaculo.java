import java.awt.Color;
import java.awt.Graphics2D;


public class Obstaculo extends Objeto {

	
	public Obstaculo(int _x, int _y) {
//		
//		setX((_x/16*16)>>4);
//		setY((_y/16*16)>>4);
//		
		
		setX(_x);
		setY(_y);
		setSizeX(16);
		setSizeY(16);

//		defineDimensaoEmTiles(sizeX,sizeY);
		setVivo(true);
		
//		System.out.println(getSizeX()+"  "+getSizeY());
		
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
