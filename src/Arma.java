
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public abstract class Arma  {
	
	private int tempoEntreTiros;
	private int dano;
	private int tempoRecarrega;
	private int peso;
	private int valor;
	private int round,mag;
	boolean atirou;
	private double angulo;
	private double X,Y;
	private int sizeX,sizeY;
	
	BufferedImage sprite;

	public abstract void definePosicaoArma(double ang,double x2,double y2);

	public abstract void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo);
	
	public abstract void SimulaSe(int Difftime);

	public abstract void recarrega();

	public abstract void atirou();
	public abstract void naoAtirou() ;

	public void setTempoEntreTiros(int tempoEntreTiros) {
		this.tempoEntreTiros = tempoEntreTiros;
	}

	public int getTempoEntreTiros() {
		return tempoEntreTiros;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public int getDano() {
		return dano;
	}

	public void setTempoRecarrega(int tempoRecarrega) {
		this.tempoRecarrega = tempoRecarrega;
	}

	public int getTempoRecarrega() {
		return tempoRecarrega;
	}

	public void setMag(int mag) {
		this.mag = mag;
	}

	public int getMag() {
		return mag;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setY(double y) {
		Y = y;
	}

	public double getY() {
		return Y;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getRound() {
		return round;
	}

	public void setX(double x) {
		X = x;
	}

	public double getX() {
		return X;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getPeso() {
		return peso;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
}
