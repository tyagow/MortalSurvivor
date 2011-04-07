import java.awt.Graphics2D;


public abstract class Objeto {
	public int sizeX,sizeY;
	public double X;
	public  double Y;
	double life, oldx, oldy;
	boolean vivo;
	
	public abstract void SimulaSe(int DiffTime);
	public abstract void DesenhaSe(Graphics2D dbg,int XMundo,int YMundo);
	
}