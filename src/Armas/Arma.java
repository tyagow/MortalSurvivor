package Armas;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public abstract class Arma  {
	
	public static int penetration=1;
	public int timerTempoEntreTiros;
	public  int dano;
	private int tempoRecarrega;
	public  int peso;
	public  int valor;
	public  int round,mag;
	public  int maxMag,maxRound;
	private boolean recarregando;
	boolean atirou;
	public  double angulo;
	private double X,Y;
	private int sizeX,sizeY;
	private int tempoEntreTirosMax;

	
	private BufferedImage imagem;
	private BufferedImage imagem_hud;

	private int tipo;
	private int custoRange;
	private int custoFire;
	private int custoDano;

	public abstract void definePosicaoArma(double ang,double x2,double y2);

	public abstract void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo);
	
	public abstract void SimulaSe(int Difftime);


	public abstract void atirou();
	public abstract void naoAtirou() ;
	
	public  void recarrega() {
		
		setMag(getMaxMag()+1);
		
	}	
	public  void resetaTiros() {
		
		setMag(getMaxMag());
		setRound(getMaxRound());
	}
	
	protected int getMaxRound() {
		// TODO Auto-generated method stub
		return maxRound;
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

	public void setTempoEntreTirosMax(int tempoEntreTirosMax) {
		this.tempoEntreTirosMax = tempoEntreTirosMax;
	}

	public int getTempoEntreTirosMax() {
		return tempoEntreTirosMax;
	}

	public void setCustoRange(int custoRange) {
		this.custoRange = custoRange;
	}

	public int getCustoRange() {
		return custoRange;
	}

	public void setCustoFire(int custoFire) {
		this.custoFire = custoFire;
	}

	public int getCustoFire() {
		return custoFire;
	}

	public void setCustoDano(int custoDano) {
		this.custoDano = custoDano;
	}

	public int getCustoDano() {
		return custoDano;
	}

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}

	public BufferedImage getImagem() {
		return imagem;
	}

	public void setImagem_hud(BufferedImage imagem_hud) {
		this.imagem_hud = imagem_hud;
	}

	public BufferedImage getImagem_hud() {
		return imagem_hud;
	}

	public void setMaxRound(int maxRound) {
		this.maxRound = maxRound;
	}
}
