package AbstractClasses;
import java.awt.Graphics2D;


public abstract class Objeto {
	public int sizeX,sizeY;
	public double X,Y;
	public double life, oldx, oldy;
	public boolean vivo;
	
	public abstract void SimulaSe(int DiffTime);
	public abstract void DesenhaSe(Graphics2D dbg,int XMundo,int YMundo);
	
	
	
	
	
	
	
	public void setX(double x) {
		X = x;
	}
	public double getX() {
		return X;
	}
	
	public void setY(double y) {
		Y = y;
	}
	public double getY() {
		return Y;
	}
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	public int getSizeY() {
		return sizeY;
	}
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	public int getSizeX() {
		return sizeX;
	}
	public void setOldy(int oldy) {
		this.oldy = oldy;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	public boolean isVivo() {
		return vivo;
	}
	
}