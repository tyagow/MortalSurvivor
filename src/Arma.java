
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public abstract class Arma  {
	
	public static int penetration=1;
	private int timerTempoEntreTiros;
	private int dano;
	private int tempoRecarrega;
	private int peso;
	private int valor;
	private int round,mag;
	private int maxMag;
	private boolean recarregando;
	boolean atirou;
	private double angulo;
	private double X,Y;
	private int sizeX,sizeY;
	
	
	BufferedImage imagem;
	BufferedImage imagem_hud;

	private int tipo;

	public abstract void definePosicaoArma(double ang,double x2,double y2);

	public abstract void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo);
	
	public abstract void SimulaSe(int Difftime);


	public abstract void atirou();
	public abstract void naoAtirou() ;
	
	public  void recarrega() {
		
		setMag(getMaxMag()+1);
		
	}
	
	public void reseta() {
		atirou=false;
		
		
	}
	public void setTempoEntreTiros(int tempoEntreTiros) {
		this.timerTempoEntreTiros = tempoEntreTiros;
	}

	public int getTempoEntreTiros() {
		return timerTempoEntreTiros;
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

	public void setRecarregando(boolean recarregando) {
		this.recarregando = recarregando;
	}

	public boolean isRecarregando() {
		return recarregando;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setMaxMag(int maxMag) {
		this.maxMag = maxMag;
	}

	public int getMaxMag() {
		return maxMag;
	}
}
