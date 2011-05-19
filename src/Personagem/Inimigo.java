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
import Gerenciadores.GerenciadorObstaculos;
import Gerenciadores.GerenciadorRespawn;
import Map.Obstaculo;
import Map.WayPoint;

public class Inimigo extends Objeto {
	
	private static final int ATRAS_BASE = 2;
	private static final int DESVIAR_OBSTACULO = 0;
	private static final int ATRAS_HEROI = 1;
	int objX,objY;
	double angAux;
	int dano;
	public double vel;
	private int tempoEntreAtaque;
	public int maximoVida;
	int maxVel=100;
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
	private int tipo;
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
		
		
		
		// TODO Auto-generated constructor stub
	}

public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub	
	
			oldx=((int)X);
			oldy=((int)Y);
	
			X+= (velx*DiffTime/1000.0f);
			Y+= (vely*DiffTime/1000.0f);
			 velx=(int) (Math.cos(ang)*vel);
			 vely=(int) (Math.sin(ang)*vel);
		
			calculaIA(DiffTime);
	
			
			if (life<0) {
				setVivo(false);	
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
			dbg.drawOval((int)getX()-sizeX/2-XMundo,(int) getY()-sizeY/2-YMundo, sizeX, sizeX);
			dbg.drawOval((int)target.getX()-target.sizeX/2-XMundo,(int)target.getY()-target.sizeY/2-YMundo, target.sizeX, target.sizeX);
		}
		
	
	}
	
private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub
//		System.out.println("vel"+vel);
//		
//		System.out.println("velx"+velx);
		tempoEntreAtaque+=DiffTime;
		
		
		maquinaEstados();
		trataEstado();

//		estado=trataEstado(estado);
//		if(estado == 0){
//			
//			estado=trataEstado(estado);
//			//System.out.println("1");
//		}
//		
//		if (estado == 1) {
//			//System.out.println("2");
//
//			estado=trataEstado(estado);
//		}
//		if (estado == 2) {
//			//System.out.println("3");
//
//			estado=trataEstado(estado);
//		}
//		if (estado == 3) {
//			//System.out.println("4");
//
//			estado=trataEstado(estado);
//		}
//		
//		estado=0;
		
	}
		
	
 private void maquinaEstados() {
		
		
		switch (estado) {
		case DESVIAR_OBSTACULO:
			if (!verificaColisaoObstaculo())
				estado=ATRAS_BASE;
			break;	
			
		case ATRAS_BASE:
			if (verificaColisaoObstaculo())
				estado=DESVIAR_OBSTACULO;
			if(Constantes.colidecircular(X, Y,campoDeVisao,CanvasGame.heroi.X,CanvasGame.heroi.Y,CanvasGame.heroi.sizeX/2)){
			
				estado=ATRAS_HEROI;
			}
			break;
		case ATRAS_HEROI:
			if (verificaColisaoObstaculo())
				estado=DESVIAR_OBSTACULO;
			else {
				if(!Constantes.colidecircular(X, Y,campoDeVisao,CanvasGame.heroi.X,CanvasGame.heroi.Y,CanvasGame.heroi.sizeX/2))
					estado=ATRAS_BASE;
			}

			
			break;

		}	
}

private void trataEstado() {
			switch (estado) {
			case DESVIAR_OBSTACULO:
				
				estadoDesviaObstaculo();
				break;
			case ATRAS_HEROI:
				
				 estadoFollowPlayer();
				 break;
			case ATRAS_BASE:
				
				 estadoFollowBase();
				 break;
			}
		
		}


	private void estadoFollowBase() {
		// TODO ir atras da base
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

	private void estadoDesviaObstaculo() {

	
	
			colidiuObstaculo=verificaColisaoObstaculo();
		 
			
			if(verificaColisaoObstaculo()){
				double tempx = X;
				double tempy = Y;
				X = oldx;
				
				
				colidiuObstaculo=verificaColisaoObstaculo();

				if(verificaColisaoObstaculo()){
					Y = oldy;
					X = tempx;
					
					colidiuObstaculo=verificaColisaoObstaculo();

					if(verificaColisaoObstaculo()){
						X = oldx;
						Y = oldy;
						ang=angAux;
						 velx=(int) (Math.cos(ang)*vel);
						 vely=(int) (Math.sin(ang)*vel);
						 vel=maxVel;
			
					}else{
						 velx = (int)vel;
						 vely = 0;
						 vel=maxVel;
					}
				}else{
					 velx = 0;
					 vely =(int)vel;
					 vel=maxVel;
				}
				
			}else{
				 velx=(int) (Math.cos(ang)*vel);
				 vely=(int) (Math.sin(ang)*vel);
				 vel=maxVel;
			}


		}



	private boolean verificaColisaoObstaculo() {
		for (int i =0; i < GerenciadorObstaculos.obstaculos.size();i++) {
			Obstaculo ob = GerenciadorObstaculos.obstaculos.get(i);
			if (Constantes.colidecircular(X, Y, sizeX/2, ob.X, ob.Y, ob.sizeX/2)) {
				return true;
			
			}			
		} 
				return false;
	}

	public void recebeuDano(int dano,int _tipo) {
		tipoAssasino=_tipo;
		if (life>0)
			
			CanvasGame.gerenciadorEfeitos.ativaSangue(X,Y,ang,(int)dano,tipo);
		
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
	 angAux = Math.atan2(difY, difX)-Math.PI/2;

	if(Constantes.colideQuadrado((int)X-sizeX/2, (int)Y-sizeY/2,sizeX,sizeY,(int)target.X-target.sizeX/2,(int)target.Y-target.sizeY/2,target.sizeX,target.sizeY)){		

		trataColisaoTarget(target);
			
	}
	

	   
	}
private void trataColisaoTarget(WayPoint target2) {
//	System.out.println("x t " + target.X);
//	System.out.println("y t " + target.Y);
//	System.out.println("x  " + objX);
//	System.out.println("Y  " + objY);
//	
//	System.out.println("Y target colidida"+target2.Y);	
//	System.out.println("X target colidida"+target2.X);	
//
//	System.out.println("X obj"+objX);	
//	System.out.println("Y obj"+objY);	
//	System.out.println("vel"+vel);
	if ((int)target.X!=objX||(int)target.Y!=objY) {

		int newI = target2.indexNextTarget; 

		if (newI !=-1) {
			target = Constantes.wayPoints.get(newI);
			
		

//			if (newI == 1) 
			
			
		

			
		}else {
			target = new WayPoint(objX,objY,objSizeX,objSizeY);
		}	

	}else {
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
//			System.out.println(_d + "i " + i);
		}
//		System.out.println(target.indexNextTarget);
	
			target=_target;
		
		
			   int dx = (int)(target.X - X);
			   int dy = (int)(target.Y - Y);
			   
			   ang = Math.atan2(dy, dx);
				 angAux = Math.atan2(dy, dx)+Math.PI/2;

			
		
}
private void carregaPrimeiroTarget() {
		WayPoint _target= new WayPoint(0, 0, 0, 0);
			int dist = 99999999;
			for (int i = 0; i<Constantes.NUM_WAYPOINT_PRINCIPAL;i++ ) {
		
				   int dx = (int)(Constantes.wayPoints.get(i).X - X);
				   int dy = (int)(Constantes.wayPoints.get(i).Y - Y);
				
				   int _d = dx*dx+dy*dy;
//				   System.out.println(Constantes.wayPoints.get(i).X + "   "+Constantes.wayPoints.get(i).Y  );
//				   System.out.println("x" +X);
//				   System.out.println("y" +Y);

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
					 angAux = Math.atan2(dy, dx)+Math.PI/2;

				
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
		 angAux = Math.atan2(difY, difX)+Math.PI/2;


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
