package Map;

import java.awt.Color;
import java.awt.Graphics2D;

import AbstractClasses.Objeto;

public class WayPoint extends Objeto {

	public int indexNextTarget;
	public int index;
	
	public WayPoint(int _x,int _y,int _sizeX,int _sizeY) {
		X=_x;
		Y=_y;
		sizeX=_sizeX;
		sizeY=_sizeY;
		
		
		
	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(Color.white);
		dbg.fillRect((int)X-XMundo-sizeX/2, (int)Y-YMundo-sizeY/2, sizeX, sizeY);
		dbg.setColor(Color.red);
		dbg.drawString(""+indexNextTarget,(int)X-XMundo, (int)Y-YMundo);
		dbg.drawString("i:"+index,(int)X-10-XMundo, (int)Y-10-YMundo);
	}

}
