package Personagem;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import AbstractClasses.Objeto;
import Armas.Arma;
import Armas.Meele;
import Armas.He;
import Canvas.CanvasGame;
import Constantes.Constantes;
import Data.Imagem;
import Gerenciadores.GerenciadorArma;
import Gerenciadores.GerenciadorObstaculos;


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
	
	private static Arma armaMelee=GerenciadorArma.meeleAtiva;//GerenciadorArma.armas.get(GerenciadorArma.FACA);
	private static Arma armaPrimaria=GerenciadorArma.primariaAtiva; //armas.get(GerenciadorArma.m4);
	private static Arma armaSecundaria=GerenciadorArma.secundariaAtiva;//armas.get(GerenciadorArma.DE);
	private static Arma armaAtiva=armaSecundaria;
	private static Arma armaEspecial=GerenciadorArma.especialAtiva;//armas.get(GerenciadorArma.HE);
	
	private static Arma ultimaArma;

	
	public boolean HE=false;

	public boolean PRIMARIA=false;
	public boolean SECUNDARIA=true;
	public boolean MELEE=false;
	public boolean ARMA_ANTERIOR=false;
	private int larguraMapa,alturaMapa;
	
	private BufferedImage imagem;
	private BufferedImage imagemLegs;

	private int frameX=1;
	private int frameY=0;
	private static int arma;
	
	
	public Heroi(int _x,int _y,BufferedImage img) {
		this.imagem= img ;
		imagemLegs=Imagem.legs;
		cor=Color.black;
		X=(_x);
		Y=(_y);
		setSizeX(imagem.getWidth()/2);
		setSizeY(imagem.getHeight()/3);
		setLife(100);
		setVivo(true);
		larguraMapa=CanvasGame.tela.Largura*16;
		alturaMapa=CanvasGame.tela.Altura*16;
		
		
		armaMelee=GerenciadorArma.meeleAtiva;//armas.get(GerenciadorArma.FACA);
		armaPrimaria=GerenciadorArma.primariaAtiva;////armas.get(GerenciadorArma.m4);
		armaSecundaria=GerenciadorArma.secundariaAtiva;
		armaEspecial=GerenciadorArma.especialAtiva;//GerenciadorArma.armas.get(GerenciadorArma.HE);
		
		
		//System.out.println(armaSecundaria.imagem);
		
		resetArmas();

		
	}
	private void resetArmas() {
	
		armaPrimaria.resetaTiros();
		armaSecundaria.resetaTiros();
		armaEspecial.resetaTiros();
		
	}
	@Override
	public void SimulaSe(int DiffTime) {
	
		
		if (vivo) {

			
			trataMovimentacao(DiffTime);
			
			calculaIA(DiffTime);

			calculaAnguloVelocidade();
			trataDirecaoMovimentacao();
		
			trataMiraDoPersonagem();
				
			trataInputMouse();
			trataTrocaArma();
			calculaAnimacao();
			
			armaAtiva.definePosicaoArma(ang, X, Y);
			armaAtiva.SimulaSe(DiffTime);
			if (life<0)
				vivo=(false);
		}

	}


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		if (isVivo()) {
			armaAtiva.DesenhaSe(dbg, XMundo, YMundo);
			dbg.setColor(cor);
			int px =(int) (getX()-XMundo);
			int py = (int)(getY()-YMundo);
			
			AffineTransform trans = dbg.getTransform();
			dbg.translate(px, py);
			dbg.rotate(ang+(Math.PI*0.5));
			dbg.drawImage(imagemLegs, 0,0,sizeX/2,sizeY/2,sizeX*frameX,sizeY*frameX,sizeX*frameY+sizeX,sizeY*frameX+sizeY,null);

			dbg.drawImage(imagem, -sizeX/2,-sizeX/2,sizeX/2,sizeY/2,sizeX*frameX,sizeY*frameY,sizeX*frameX+sizeX,sizeY*frameY+sizeY,null);
//		dbg.drawImage(AnimeSet,-14,-18,sizeX-10,sizeY-14,sizeX*frame+start,startY,(sizeX*frame)+sizeX+start,(startY)+sizeY,null);
//			dbg.drawImage(imagemLegs, -sizeX/2,-sizeX/2,sizeX/2,sizeY/2,sizeX*frameX,sizeY*frameX,sizeX*frameY+sizeX,sizeY*frameX+sizeY,null);

			dbg.setTransform(trans);

			
			///// VIDA TEMPORARIO ## FAZER HUD
			dbg.drawRect((int)px-getSizeX()/2-5, (int)py-getSizeY()/2-17, 30, 10);
			dbg.setColor(Color.green);
			dbg.fillRect((int)px-getSizeX()/2-5+1, (int)py-16-getSizeY()/2, (int)(life*30/maximoVida)-1, 9);
		}

	}
	private void calculaIA(int DiffTime) {
		
		if (CanvasGame.base!=null) {
			if (X+(sizeX>>1) >=larguraMapa ||X-sizeX/2-1<=0||colisaoBase()) {
				
				X=(oldx);
			
			}
			if ( Y+sizeY/2+1>=alturaMapa || Y-sizeY/2-1 <=0 ||colisaoBase() )
				Y=(oldy);
		}
		trataColisaoMapa();
		/*for (int i=0;i<GerenciadorObstaculos.obstaculos.size();i++) {
		
			
			if (Constantes.colideQuadrado(X-sizeX/2,Y-sizeY/2,sizeX,sizeY,GerenciadorObstaculos.obstaculos.get(i).X-GerenciadorObstaculos.obstaculos.get(i).sizeX/2,GerenciadorObstaculos.obstaculos.get(i).Y-GerenciadorObstaculos.obstaculos.get(i).sizeY/2,GerenciadorObstaculos.obstaculos.get(i).sizeX,GerenciadorObstaculos.obstaculos.get(i).sizeY)) { // &&CanvasGame.base.Y+CanvasGame.base.sizeY<Y)
//				
//				double tempX=X;
//				System.out.println("X" +  X);
//				System.out.println("oldx" +  oldx);
				X=oldx;
				Y=oldy;
				
//				if (Constantes.colideQuadrado(X-sizeX/2,Y-sizeY/2,sizeX,sizeY,GerenciadorObstaculos.obstaculos.get(i).X-GerenciadorObstaculos.obstaculos.get(i).sizeX/2,GerenciadorObstaculos.obstaculos.get(i).Y-GerenciadorObstaculos.obstaculos.get(i).sizeY/2,GerenciadorObstaculos.obstaculos.get(i).sizeX,GerenciadorObstaculos.obstaculos.get(i).sizeY)) { // &&CanvasGame.base.Y+CanvasGame.base.sizeY<Y)
//					
//					X=tempX;
//					Y=oldx;
//
//					if (Constantes.colideQuadrado(X-sizeX/2,Y-sizeY/2,sizeX,sizeY,GerenciadorObstaculos.obstaculos.get(i).X-GerenciadorObstaculos.obstaculos.get(i).sizeX/2,GerenciadorObstaculos.obstaculos.get(i).Y-GerenciadorObstaculos.obstaculos.get(i).sizeY/2,GerenciadorObstaculos.obstaculos.get(i).sizeX,GerenciadorObstaculos.obstaculos.get(i).sizeY)) { // &&CanvasGame.base.Y+CanvasGame.base.sizeY<Y)
//						
//					X=oldx;
//					Y=oldy;
//					}
//					
//					
//					
				}				
//				
//				
//			}
//			if (Constantes.colideQuadrado(X-sizeX/2,Y-sizeY/2,sizeX,sizeY,GerenciadorObstaculos.obstaculos.get(i).X-GerenciadorObstaculos.obstaculos.get(i).sizeX/2,GerenciadorObstaculos.obstaculos.get(i).Y-GerenciadorObstaculos.obstaculos.get(i).sizeY/2,GerenciadorObstaculos.obstaculos.get(i).sizeX,GerenciadorObstaculos.obstaculos.get(i).sizeY)) { // &&CanvasGame.base.Y+CanvasGame.base.sizeY<Y)
//			
//				X=oldx;
//				Y=oldy;
//			break;	
//			}
			
		}
		
*/
			
		
		
	}
	
	private void trataColisaoMapa() {
		int bx = (int)(X/32); 
		int by = (int)(Y/32);
		int bxold = (int)(oldx/32); 
		int byold = (int)(oldy/32);
		
		if(GerenciadorObstaculos.mapa[bx][by]==1){
			
			if(GerenciadorObstaculos.mapa[bx][byold]==0){
				Y = oldy;
			}else if(GerenciadorObstaculos.mapa[bxold][by]==0){
				X = oldx;
			}else{
				Y = oldy;
				X = oldx;
			}
	}
//return aux;			
}
	private boolean colisaoBaseY() {
		// TODO Auto-generated method stub
//		
//		if (Constantes.colideQuadradoUni(Y,sizeY,CanvasGame.base.Y,CanvasGame.base.sizeY)) //&&CanvasGame.base.Y+CanvasGame.base.sizeY<Y)
//			return true;
//		else 
			return false;

	}
	private boolean colisaoBase() {
		return (Constantes.colideQuadrado(X-sizeX/2,Y-sizeY/2,sizeX,sizeY,CanvasGame.base.X-CanvasGame.base.sizeX/2,CanvasGame.base.Y-CanvasGame.base.sizeY/2,CanvasGame.base.sizeX,CanvasGame.base.sizeY)); // &&CanvasGame.base.Y+CanvasGame.base.sizeY<Y)

	}
	private void trataTrocaArma() {
		// TODO Auto-generated method stub
		
		
		if (PRIMARIA) {
			
		if (armaAtiva!=null) {
				
				armaAtiva.reseta();
			}
			
			ultimaArma=armaAtiva;
			
			
			armaAtiva=armaPrimaria;
			arma=(IDX_ARMA_PRIMARIA);
			PRIMARIA=false;
		}
		else if (SECUNDARIA)  {
		if (armaAtiva!=null) {
				
				armaAtiva.reseta();
			}
			
			ultimaArma=armaAtiva;
			armaAtiva=getArmaSecundaria();
			SECUNDARIA=false;
			arma=(IDX_ARMA_SECUNDARIA);
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
				
				setArma(armaAtiva.tipo);
				
				ARMA_ANTERIOR=false;
			}
		else if (HE)  {
				if (armaAtiva!=null) {
					
					armaAtiva.reseta();
				}
				
				ultimaArma=armaAtiva;
				armaAtiva=armaEspecial;
				
				setArma(IDX_ARMA_GRANADA);
				
				HE=false;
			}
	}
	private void calculaAnimacao() {
		// TODO Auto-generated method stub
		
		switch (arma) {
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
		
		if (armaAtiva.recarregando) {
			frameY=0;
			
		}
		
	}
	private void trataMovimentacao(int DiffTime) {
		// TODO Auto-generated method stub
		oldx=((int) X);

		oldy=((int) Y);
		
			X+=(+(Math.cos(angMovimentacao)*vel*DiffTime/1000.0f)); 
			Y-=((Math.sin(angMovimentacao)*vel*DiffTime/1000.0f)); 	

		
	}
	private void trataMiraDoPersonagem() {
		
		double difX =CanvasGame.miraAtiva.getXMundo()-X;
		double difY =CanvasGame.miraAtiva.getYMundo()-Y;
		
		ang = Math.atan2(difY, difX);
	}
	
	private boolean colisaoBase(int DiffTime) {
		

		if (Constantes.colidecircular(X, Y, sizeX/2, CanvasGame.base.X, CanvasGame.base.Y, CanvasGame.base.sizeX/2)) {	
			
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
			vel = VelMaxFrente-armaAtiva.peso;
		}else if(anguloDif<140){
			vel = VelMaxLado-armaAtiva.peso;
		}else{
			vel = VelMaxTras-armaAtiva.peso;
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
		armaEspecial.recarrega();

		
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
		return armaEspecial;
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
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_Q){
			ARMA_ANTERIOR=true;
		}
		if(keyCode == KeyEvent.VK_F){
			HE=true;
		}
		if(keyCode == KeyEvent.VK_W){
			UP=true;
		}
		if(keyCode == KeyEvent.VK_S){
			DOWN=true;
		}
		if(keyCode == KeyEvent.VK_A){
			LEFT=true;
		}
		if(keyCode == KeyEvent.VK_D){
			RIGHT=true;
		}
		if(keyCode == KeyEvent.VK_1){
			MELEE=true;
		}
		if(keyCode == KeyEvent.VK_2){
			SECUNDARIA=true;
		}
		
	}
	public void keyReleased(int keyCode) {
		if(keyCode == KeyEvent.VK_W){
			UP=false;
		}
		if(keyCode == KeyEvent.VK_S){
			DOWN=false;
		}
		if(keyCode == KeyEvent.VK_A){
			LEFT=false;
		}
		if(keyCode == KeyEvent.VK_D){
			RIGHT=false;
		}		
	}	
}
