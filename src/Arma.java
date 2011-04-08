
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public abstract class Arma  {
	
	int tempoEntreTiros;
	int dano;
	int tempoRecarrega;
	int peso;
	int valor;
	int round,mag;
	boolean atirou;
	double angulo;
	double X,Y;
	int sizeX,sizeY;
	
	BufferedImage sprite;

	public abstract void definePosicaoArma(double ang,double x2,double y2);

	public abstract void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo);
	
	public abstract void SimulaSe(int Difftime);

	public abstract void recarrega();

	public abstract void atirou();
	public abstract void naoAtirou() ;
}
