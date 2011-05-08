package Personagem;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import AbstractClasses.Objeto;
import Armas.Arma;
import Armas.Deagle;
import Armas.Faca;
import Armas.He;
import Armas.M4;
import Canvas.CanvasGame;
import Constantes.Constantes;


public class Heroi extends Objeto {
	public static final int IDX_ARMA_MELEE = 0;
	public static final int IDX_ARMA_SECUNDARIA = 1;
	public  static final int IDX_ARMA_PRIMARIA = 2;
	public static final int IDX_ARMA_GRANADA = 3;

	Color cor;
	public boolean LEFT,RIGHT;
	public boolean UP;
	public boolean DOWN;
	double ang;
	private int VelMaxFrente=350;
	private double VelMaxTras=200;
	private double VelMaxLado=270;
	
	
	
	private double vel;
	private double angMovimentacao;
	private boolean Atira=false;
	private int maximoVida=100;
	
	private static Arma armaMelee=new Faca();
	private static Arma armaPrimaria=new M4();
	private static Arma armaSecundaria=new Deagle();
	private static Arma armaAtiva=getArmaSecundaria();
	private static Arma armaGranada=new He();
	
	private static Arma ultimaArma;

	
	public boolean HE=false;

	public boolean PRIMARIA=false;
	public boolean SECUNDARIA=true;
	public boolean MELEE=false;
	public boolean ARMA_ANTERIOR=false;
	private int larguraMapa,alturaMapa;
	
	private BufferedImage imagem;
	private int frameX=1;
	private int frameY=0;
	private static int arma;
	
	
	public Heroi(int x,int y,BufferedImage img) {
		this.imagem= img ;
		cor=Color.black;
		this.setX(x);
		this.setY(y);
		setSizeX(imagem.getWidth()/2);
		setSizeY(imagem.getHeight()/3);
		setLife(100);
		setVivo(true);
		larguraMapa=CanvasGame.tela.Largura*16;
		alturaMapa=CanvasGame.tela.Altura*16;
		resetArmas();
		
	}
	private void resetArmas() {
	
		armaPrimaria.resetaTiros();
		getArmaSecundaria().resetaTiros();
		armaGranada.resetaTiros();
	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
	
		
		if (isVivo()) {
			calculaIA(DiffTime);

			
			trataMovimentacao(DiffTime);

			if (getX()+(getSizeX()>>1) >=larguraMapa || getY()+getSizeY()/2+1>=alturaMapa || getY()-getSizeY()/2-1 <=0 || getX()-getSizeX()/2-1<=0||colisaoBase(DiffTime)) {
				
				setX(getOldx());
				setY(getOldy());
			}
			armaAtiva.definePosicaoArma(ang, getX(), getY());
			armaAtiva.SimulaSe(DiffTime);
		}

	}


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		if (isVivo()) {
			armaAtiva.DesenhaSe(dbg, XMundo, YMundo);
			dbg.setColor(cor);
			int px =(int) (getX()-XMundo);
			int py = (int)(getY()-YMundo);
			
			AffineTransform trans = dbg.getTransform();
			dbg.translate(px, py);
			dbg.rotate(ang+(Math.PI*0.5));
			dbg.drawImage(imagem, -getSizeX()/2,-getSizeX()/2,getSizeX()/2,getSizeY()/2,getSizeX()*frameX,getSizeY()*frameY,getSizeX()*frameX+getSizeX(),getSizeY()*frameY+getSizeY(),null);
//		dbg.drawImage(AnimeSet,-14,-18,sizeX-10,sizeY-14,sizeX*frame+start,startY,(sizeX*frame)+sizeX+start,(startY)+sizeY,null);

			dbg.setTransform(trans);

			
			///// VIDA TEMPORARIO ## FAZER HUD
			dbg.drawRect((int)px-getSizeX()/2-5, (int)py-getSizeY()/2-17, 30, 10);
			dbg.setColor(Color.green);
			dbg.fillRect((int)px-getSizeX()/2-5+1, (int)py-16-getSizeY()/2, (int)(getLife()*30/maximoVida)-1, 9);
		}

	}
	private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub

		if (getLife()<0)
			setVivo(false);
		
		calculaAnguloVelocidade();
		trataDirecaoMovimentacao();
		
		
		
		trataMiraDoPersonagem();
			
		trataInputMouse();
		trataTrocaArma();
		calculaAnimacao();
			
		
		
	}
	private void trataTrocaArma() {
		// TODO Auto-generated method stub
		
		
		if (PRIMARIA) {
			
		if (armaAtiva!=null) {
				
				armaAtiva.reseta();
			}
			
			ultimaArma=armaAtiva;
			
			
			armaAtiva=armaPrimaria;
			setArma(IDX_ARMA_PRIMARIA);
			PRIMARIA=false;
		}
		else if (SECUNDARIA)  {
		if (armaAtiva!=null) {
				
				armaAtiva.reseta();
			}
			
			ultimaArma=armaAtiva;
			armaAtiva=getArmaSecundaria();
			SECUNDARIA=false;
			setArma(IDX_ARMA_SECUNDARIA);
		}
		else if (MELEE) {
		if (armaAtiva!=null) {
				
				armaAtiva.reseta();
			}
			
			ultimaArma=armaAtiva;
			armaAtiva=armaMelee;
			MELEE=false;
			setArma(IDX_ARMA_MELEE);
		}
		else if (ARMA_ANTERIOR)  {
			if (armaAtiva!=null) {
					
					armaAtiva.reseta();
				}
				
				Arma temp = armaAtiva;
				armaAtiva=ultimaArma;
				ultimaArma=temp;
				
				setArma(armaAtiva.getTipo());
				
				ARMA_ANTERIOR=false;
			}
		else if (HE)  {
				if (armaAtiva!=null) {
					
					armaAtiva.reseta();
				}
				
				ultimaArma=armaAtiva;
				armaAtiva=armaGranada;
				
				setArma(IDX_ARMA_GRANADA);
				
				HE=false;
			}
	}
	private void calculaAnimacao() {
		// TODO Auto-generated method stub
		
		switch (getArma()) {
		case IDX_ARMA_MELEE:
			frameX=0;
			frameY=2;
			break;	
			
		case IDX_ARMA_SECUNDARIA:
			frameX=1;
			frameY=1;
			break;
		case IDX_ARMA_PRIMARIA:
			frameX=1;
			frameY=2;
			break;
		case IDX_ARMA_GRANADA: 
			frameX=1;
			frameY=2;
			break;

		default:
			break;
		}
		
		if (armaAtiva.isRecarregando()) {
			frameY=0;
			
		}
		
	}
	private void trataMovimentacao(int DiffTime) {
		// TODO Auto-generated method stub
		setOldx((int) getX());
		setOldy((int) getY());
		
			setX(getX() + (Math.cos(angMovimentacao)*vel*DiffTime/1000.0f)); 
			setY(getY() - (Math.sin(angMovimentacao)*vel*DiffTime/1000.0f)); 	

		
	}
	private void trataMiraDoPersonagem() {
		// TODO Auto-generated method stub
		
		double difX =CanvasGame.getMiraAtiva().getXMundo()-getX();
		double difY =CanvasGame.getMiraAtiva().getYMundo()-getY();
		
		ang = Math.atan2(difY, difX);
	}
	
	private boolean colisaoBase(int DiffTime) {
		

		if (Constantes.colidecircular(getX(), getY(), getSizeX()/2, CanvasGame.base.getX(), CanvasGame.base.getY(), CanvasGame.base.getSizeX()/2)) {	
			
			//trataColisaoBase(DiffTime);
			return true;
			
		}else{
			
			return false;
			
		}
		
	}
	private void trataColisaoBase(int DiffTime) {
		// TODO Auto-generated method stub
		
//		setX(getOldx());
//		setY(getOldy());
		//if (CanvasGame.base.
		recarregaArmas();
		
		
		
	}
	private void trataInputMouse() {
		// TODO Auto-generated method stub
		
	
		if (CanvasGame.miraDoJogoSelecionada){
			if (CanvasGame.getMiraAtiva().isPressed()) {
				trataBotaoPressed(CanvasGame.getMiraAtiva().getButton());
				
				
			}else if (CanvasGame.getMiraAtiva().isReleased())
				trataBotaoReleased(CanvasGame.getMiraAtiva().getButton());

		}
		else {
			
			Atira=false;
			armaAtiva.naoAtirou();
		}
		
	}
	private void trataBotaoReleased(int button) {
		if (button ==MouseEvent.BUTTON1) {

			armaAtiva.naoAtirou();
		
		}
	}
	private void trataBotaoPressed(int button) {
		if (button ==MouseEvent.BUTTON1) {
				armaAtiva.atirou();
			
		}		
	}
	private void calculaAnguloVelocidade() {
		// TODO Auto-generated method stub
		
		int angAux = (int)Math.toDegrees(ang);
		
//		System.out.println("ang: "+ang);
		if(angAux<0){
			angAux=Math.abs(180-angAux)-180;
		}
		else  {
			angAux=Math.abs(180-angAux)+180;
		}
		
//		System.out.println("angAux :"+angAux);
		int angMovtmp = (int)Math.toDegrees(angMovimentacao);
		
		if(angMovtmp<0){
			angMovtmp+=360;
		}
//		System.out.println("angMove:"+angMovtmp);
	
		int anguloDif = Math.abs(angAux - angMovtmp) ;
//				System.out.println(" angulo dif 1"+anguloDif);
		if(anguloDif>180){
			anguloDif=Math.abs(anguloDif-360);
		}
//		System.out.println(" angulo dif final"+anguloDif);

		if(anguloDif<80){
			vel = VelMaxFrente-armaAtiva.getPeso();
		}else if(anguloDif<140){
			vel = VelMaxLado-armaAtiva.getPeso();
		}else{
			vel = VelMaxTras-armaAtiva.getPeso();
		}
	}
	
	private void trataDirecaoMovimentacao() {
		// TODO Auto-generated method stub
		if (UP&&RIGHT) {
			angMovimentacao=Math.toRadians(45);
			
		}
		else if (UP&&LEFT) {
			angMovimentacao=Math.toRadians(135);
			
		}
		else if (DOWN&&LEFT) {
			angMovimentacao=Math.toRadians(225);
			
		}else if (DOWN&&RIGHT) {
			angMovimentacao=Math.toRadians(315);
			
		}
			
		else if(UP){ 
			
			angMovimentacao=Math.PI/2;
			
		}else if(DOWN){
			angMovimentacao=Math.PI*3/2;
		}else if(LEFT){
			angMovimentacao=Math.PI;

		}else if(RIGHT){
			angMovimentacao=0;
		
		}else vel = 0;
	}
	
	public void respaw(int X,int Y) {
		
		this.setX(X);
		this.setY(Y);
		setVivo(true);	
		setLife(maximoVida);
		
		recarregaArmas();
		
		
	}
	public void recarregaArmas() {
		// TODO Auto-generated method stub
		
		armaPrimaria.recarrega();
		getArmaSecundaria().recarrega();
		armaGranada.recarrega();

		
	}
	public void trataClick() {
		// TODO Auto-generated method stub
//		if (Atira)
//			Atira=false;
//		else Atira=true;
//		
	}
	public static void setArmaSecundaria(Arma armaSecundaria) {
		Heroi.armaSecundaria = armaSecundaria;
	}
	public static Arma getArmaSecundaria() {
		return armaSecundaria;
	}	
	public static void setArmaMelee(Arma armaMelee) {
		Heroi.armaMelee = armaMelee;
	}
	public static Arma getArmaMelee() {
		return armaMelee;
	}
	public static void setArmaPrimaria(Arma armaPrimaria) {
		Heroi.armaPrimaria = armaPrimaria;
	}
	public static Arma getArmaPrimaria() {
		return armaPrimaria;
	}
	public static Arma getArmaGranada() {
		// TODO Auto-generated method stub
		return armaGranada;
	}
	public void setArma(int arma) {
		this.arma = arma;
	}
	public static int getArma() {
		return arma;
	}
	public final static int getIdxArmaMelee() {
		return IDX_ARMA_MELEE;
	}	
}
