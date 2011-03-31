import java.awt.Graphics2D;


public abstract class Objeto {
	int sizeX,sizeY;
	double X,Y,life,oldx,oldy;
	boolean vivo;
	public abstract void SimulaSe(int DiffTime);
	public abstract void DesenhaSe(Graphics2D dbg,int XMundo,int YMundo);
	
}