package Personagem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;
import Constantes.Constantes;
import Data.Imagem;
import GameState.GamePanel;
import Gerenciadores.GerenciadorEfeitos;
import Gerenciadores.GerenciadorObstaculos;
import Gerenciadores.GerenciadorRespawn;
import Gerenciadores.GerenciadorTorre;
import Map.Obstaculo;
import Map.WayPoint;
import Torre.Torre;

public class Inimigo extends Objeto {
	
	private static final int ATRAS_BASE = 2;
	private static final int TRATAR_COLISAO = 0;
	private static final int ATRAS_HEROI = 1;

	int objX,objY;
	double angAux;
	int dano;
	public double vel;
	private int tempoEntreAtaque;
	public int maximoVida;
	public int maxVel=100;
	public int tipoAssasino;
	private int larguraMapa;
	private int alturaMapa;
	private int estado;
	private double ang;
	private double campoDeVisao;
	private int frameX;
	private int frameY;
	BufferedImage img;
	int velx;
	int vely;
	
	WayPoint target  = null;
	public int tipo;
	private int objSizeX;
	private int objSizeY;
	private boolean colidiuObstaculo;
	private int objSecundarioX=-1;
	private int objSecundarioY=-1;
	private boolean primeiraVez=true;

	
//public Inimigo(BufferedImage img,int _tipo,int _objX,int _objY) {

	public Inimigo(int _tipo,int _objX,int _objY) {
		objX=_objX;
		objY=_objY;
		tipo=_tipo;
		larguraMapa=CanvasGame.tela.Largura*16;
		alturaMapa=CanvasGame.tela.Altura*16;
		 colidiuObstaculo = false;
		X=(GamePanel.rnd.nextInt(alturaMapa));//+alturaMapa*(-2));
		Y=(GamePanel.rnd.nextInt(larguraMapa));//+alturaMapa*(-2));
		
		dano = 10;

		vivo=(true);
		estado=0;
		
		campoDeVisao=Constantes.INIMIGO_CAMPO_VISAO1;
		
		switch (_tipo) {
			case 1:
				img = Imagem.inimigoUm;
				frameX=0;
				frameY=1;
				sizeX=32;
				sizeY=32;
				break;
			case 2:
				img = Imagem.inimigoDois;
				frameX=0;
				frameY=0;
				sizeX=32;
				sizeY=32;
			default:
				break;
		}

	}

public void SimulaSe(int DiffTime) {

			oldx=(X);
			oldy=(Y);
			
			velx=(int) (Math.cos(ang)*vel);
			vely=(int) (Math.sin(ang)*vel);

			X+= (velx*DiffTime/1000.0f);
			Y+= (vely*DiffTime/1000.0f);


			calculaIA(DiffTime);
			if (GerenciadorObstaculos.largura*32>X&&X>0 && GerenciadorObstaculos.altura*32>Y&&Y>0 ) {
				verificaSeparacao();
				trataColisaoMapa() ;
			}
			
			if (life<0) {
				vivo=(false);	
			}			
	}

	
	@Override
public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		//dbg.drawOval((int)getX()-getSizeX()/2-XMundo,(int)getY()-getSizeY()/2-YMundo,(int)getSizeX(),(int)getSizeY());

		
		dbg.drawRect((int)X-5-sizeX/2-XMundo, (int)Y-17-sizeY/2-YMundo, 30, 10);
		dbg.setColor(Color.green);
		int px =(int) (X-XMundo);
		int py = (int)(Y-YMundo);
		AffineTransform trans = dbg.getTransform();
		dbg.translate(px, py);
		dbg.rotate(ang+Math.PI/2);
		dbg.drawImage(img, -sizeX/2,-sizeY/2,sizeX/2,sizeY/2,sizeX*frameX,sizeY*frameY,sizeX*frameX+sizeX,sizeY*frameY+sizeY,null);
		dbg.setTransform(trans);
		dbg.setColor(Color.black);
		dbg.drawRect((int)px-sizeX/2-5, (int)py-sizeY/2-17, 30, 10);
		dbg.setColor(Color.green);
		dbg.fillRect((int)px-sizeX/2-5+1, (int)py-16-sizeY/2, (int)(life*30/maximoVida)-1, 9);
	
//		if(Constantes.colidecircular(getX(), getY(),getSizeX(),target.getX()+target.sizeX/2,target.getY()+target.sizeY/2,target.getSizeX()/2)){
		if (target!=null&&Constantes.menuDeObstaculos) {
			
			dbg.drawOval((int)X-sizeX/2-XMundo,(int) Y-sizeY/2-YMundo, sizeX, sizeX);
			dbg.drawOval((int)target.getX()-target.sizeX/2-XMundo,(int)target.getY()-target.sizeY/2-YMundo, target.sizeX, target.sizeX);
		}
		
	
	}
	
	int iatimer = 0;
private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub
//		System.out.println("vel"+vel);
//		
//		System.out.println("velx"+velx);
		tempoEntreAtaque+=DiffTime;
		iatimer+=DiffTime;
		
		if(iatimer>100){
			maquinaEstados();
			trataEstado();
			iatimer  = 0;
//System.out.println("estado" + estado);
		}


		
	}
		
	
 private void maquinaEstados() {
		
		
		switch (estado) {
		case TRATAR_COLISAO:
			//if (!verificaColisaoObstaculo()||!verificaSeparacao()||verificaColisaoTorre())
//				if (!verificaColisao())
					estado=ATRAS_BASE;
			break;	
			
		case ATRAS_BASE:
		//	if (verificaColisaoObstaculo()||verificaSeparacao()||verificaColisaoTorre()||verificaColisaoBase())
//			if (verificaColisao())
//				estado=TRATAR_COLISAO;
//			else 
				if(Constantes.colidecircular(X, Y,campoDeVisao,CanvasGame.heroi.X,CanvasGame.heroi.Y,CanvasGame.heroi.sizeX/2)){
			
					estado=ATRAS_HEROI;
				}
//			}
			break;
		case ATRAS_HEROI:
			//if (verificaColisaoObstaculo()||verificaSeparacao()||verificaColisaoTorre()||verificaColisaoBase())
//			if (verificaColisao())
//	
//				estado=TRATAR_COLISAO;
//			else {
				if(!Constantes.colidecircular(X, Y,campoDeVisao,CanvasGame.heroi.X,CanvasGame.heroi.Y,CanvasGame.heroi.sizeX/2))
					estado=ATRAS_BASE;
//			}

			
			break;

		}	
}

private void trataEstado() {
			switch (estado) {
			case TRATAR_COLISAO:
				
				
				
				//trataColisaoMapa();
				
			//	System.out.println("tratacolisaoMapa");
				
				
				break;
			case ATRAS_HEROI:
				
				 estadoFollowPlayer();
				 break;
			case ATRAS_BASE:
				
				 estadoFollowBase();
				 break;
			}
		
		}
	private void trataColisaoMapa() {
		
			boolean aux = true;
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
				
					aux=false;
				}
		}
	//return aux;			
	}
	private boolean verificaColisao() {
		int bx = (int)(X/32); 
		int by = (int)(Y/32);
		if (GerenciadorObstaculos.largura*32>X&&X>0 && GerenciadorObstaculos.altura*32>Y&&Y>0 ) {
			
			System.out.println(GerenciadorObstaculos.mapa[by][bx]);
			
			System.out.println("bx " + bx);
			System.out.println("by " + by);
			
			if(GerenciadorObstaculos.mapa[by][bx]==1){
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return  false;
		}
//return aux;			
}

	private void estadoFollowBase() {

		procuraCaminho(); // trata do caminho e ainda retorna se ja pode atacar a base ou nao que eh o proximo estado
	
	}

	private void estadoFollowPlayer() {
		// TODO ir Atras do player
		
		if(!GerenciadorRespawn.isRespawn()){
			
			if(Constantes.colidecircular(X, Y,campoDeVisao,CanvasGame.heroi.X,CanvasGame.heroi.Y,CanvasGame.heroi.sizeX/2)){
					irAtrasDoHeroi();
					objSecundarioX=(int)CanvasGame.heroi.X;
					objSecundarioY=(int)CanvasGame.heroi.Y;
					target=null;
					
				}else {
					objSecundarioX=-1;
					objSecundarioY=-1;
				} 
			} 
	

	}


	private boolean verificaColisaoBase() {
		

				if (Constantes.colidecircular(X, Y, sizeX/2, CanvasGame.base.X, CanvasGame.base.Y,CanvasGame.base.sizeX/2)) {
//					if (objX!=(int)CanvasGame.base.X&&objY!=(int)CanvasGame.base.Y)
					System.out.println("colidiu base");	

					return true;
//				sysou//
				}			
	
					return false;
		}

	private boolean verificaSeparacao() {
		return (verificaColisaoComInimigo());
	}


	private boolean verificaColisaoComInimigo() {

			for (int i =0; i < Constantes.inimigos.size();i++) {
				Inimigo ob = Constantes.inimigos.get(i);
				if (ob !=this) {
				if (Constantes.colidecircular(X, Y, sizeX/4, ob.X, ob.Y, ob.sizeX/4)) {
//					double dx =  X-ob.X;
//					double  dy = Y-ob.Y;

						double aux = X;
						X=oldx;
						
						if (Constantes.colidecircular(X, Y, sizeX/4, ob.X, ob.Y, ob.sizeX/4)) {
						
							X=aux;
							double auxY =Y;
							Y=oldy;
						
							if (Constantes.colidecircular(X, Y, sizeX/4, ob.X, ob.Y, ob.sizeX/4)) {
								
								//X=oldx;
								Y=auxY;
								if (estado !=ATRAS_HEROI&&tipo==ob.tipo) {
								
									ang = ob.ang-Math.PI;
								}

								return true;
							}
							
							
						}
						vel=maxVel;
				}

				}
			}

		return false;		
	}

	public void recebeuDano(int dano,int _tipo) {
		tipoAssasino=_tipo;
		if (life>0)
			
			GerenciadorEfeitos.ativaSangue(X,Y,ang,(int)dano,tipo);
			
		if (life-dano<=0) {
			
			morreu(_tipo);
		}else  {
			life-= dano;
		}
	

		System.out.println(dano);
				
	}

	public void morreu(int _tipo) {
		// TODO Auto-generated method stub
		
		life=0;	
		
		tipoAssasino=_tipo;
		vivo=false;
	}

		
	private void procuraCaminho(){

		if (target==null ) {
			if (primeiraVez) {
				carregaPrimeiroTarget();
				primeiraVez=false;
				
			}else
				carregaTargetProximo();
			
		}else {
						
			verificaTarget();
		}

	}


private void verificaTarget() {
	
	double difX = target.X - X;
	double difY = target.Y - Y;
	 ang =  Math.atan2(difY, difX);
	 angAux = Math.atan2(difY, difX);

	if(Constantes.colideQuadrado((int)X-sizeX/2, (int)Y-sizeY/2,sizeX,sizeY,(int)target.X-target.sizeX/2,(int)target.Y-target.sizeY/2,target.sizeX,target.sizeY)){		

		trataColisaoTarget(target);
			
	}
	

	   
	}
private void trataColisaoTarget(WayPoint target2) {

	if ((int)target.X!=objX||(int)target.Y!=objY) {

		int newI = target2.indexNextTarget; 

		if (newI !=-1) {
			target = Constantes.wayPoints.get(newI);
			
		

//			if (newI == 1) 
			
			
		

			
		}else {
			target = new WayPoint(objX,objY,objSizeX,objSizeY);
		}	

	}else {
		X=oldx;
		Y=oldy;
		atacaBase();
	}


}

private void carregaTargetProximo() {
	vel=maxVel;
	WayPoint _target= new WayPoint(0, 0, 0, 0);
		int dist = 99999999;
		for (int i = 0; i<Constantes.wayPoints.size();i++ ) {
	
			   int dx = (int)(Constantes.wayPoints.get(i).X - X);
			   int dy = (int)(Constantes.wayPoints.get(i).Y - Y);
			
			   int _d = dx*dx+dy*dy;
	

			   if (_d<dist) {
				   
				   _target =   Constantes.wayPoints.get(i);
				   dist=_d;
			   }
		}
	
			target=_target;
		
		
			   int dx = (int)(target.X - X);
			   int dy = (int)(target.Y - Y);
			   
			   ang = Math.atan2(dy, dx);
				 angAux = Math.atan2(dy, dx);

			
		
}
private void carregaPrimeiroTarget() {
		WayPoint _target= new WayPoint(0, 0, 0, 0);
			int dist = 99999999;
			for (int i = 0; i<Constantes.NUM_WAYPOINT_PRINCIPAL;i++ ) {
		
				   int dx = (int)(Constantes.wayPoints.get(i).X - X);
				   int dy = (int)(Constantes.wayPoints.get(i).Y - Y);
				
				   int _d = dx*dx+dy*dy;


				   if (_d<dist) {
					   
					   _target =   Constantes.wayPoints.get(i);
					   dist=_d;
				   }

			}

			if (_target !=null) {
				target=_target;
			}
			if (target !=null){
				   int dx = (int)(target.X - X);
				   int dy = (int)(target.Y - Y);
				   
				   ang = Math.atan2(dy, dx);
					 angAux = Math.atan2(dy, dx);

				
			}
			
			
	}

private void atacaBase() {
	
		if (tempoEntreAtaque>500) {
			CanvasGame.base.life=(CanvasGame.base.life - dano);
			tempoEntreAtaque=0;
		}
		
		X=(oldx);
		Y=(oldy);
		
	}
			
private void irAtrasDoHeroi(){
		double difX = CanvasGame.heroi.X - X;
		double difY = CanvasGame.heroi.Y - Y;
		 ang =  Math.atan2(difY, difX);
		 angAux = Math.atan2(difY, difX);


		if(Constantes.colidecircular(X, Y,sizeX/2,CanvasGame.heroi.X,CanvasGame.heroi.Y,CanvasGame.heroi.sizeX/2)){
			if (tempoEntreAtaque>500) {
				CanvasGame.heroi.life=(CanvasGame.heroi.life - dano);
				tempoEntreAtaque=0;
			}
			
			vel=(0); /// variavel para o inimigo nao atravessar o player....
			
			}
		else vel=(maxVel);
	}

}
