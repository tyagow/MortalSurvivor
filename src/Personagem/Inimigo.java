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
	
	int objX,objY;
	int dano;
	public double vel;
	private int tempoEntreAtaque;
	public int maximoVida;
	private int maxVel=100;
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
		vel=100;
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
	

		calculaIA(DiffTime);
			X+= (velx*DiffTime/1000.0f);
			Y+= (vely*DiffTime/1000.0f);
			
			if (life<0) {
				setVivo(false);
				
			}			
		
			oldx=((int)X);
			oldy=((int)Y);
		
	
			//verificaColisaoTiros();
	}


	private void verificaColisaoTiros() {
		// TODO Auto-generated method stub

//		for (int i=0;i<CanvasGame.projeteis.size();i++) {
//			Projetil proj= CanvasGame.projeteis.get(i);
//			if (Constantes.colidecircular(getX(), getY(),getSizeX()/2,proj.getX(),proj.getY(),proj.getSizeX()/2)) {
//				CanvasGame.projeteis.get(i).setVivo(false);
//				recebeuDano(proj.getDano(),proj.tipo);
//				CanvasGame.gerenciadorEfeitos.ativaSangue(getX(),getY(),ang,(int)proj.getDano());
//			}
//		}
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		//dbg.drawOval((int)getX()-getSizeX()/2-XMundo,(int)getY()-getSizeY()/2-YMundo,(int)getSizeX(),(int)getSizeY());
//		System.out.println("INIMIGO"+X);
//		System.out.println("INIMIGO"+Y);
		dbg.drawRect((int)getX()-5-getSizeX()/2-XMundo, (int)getY()-17-getSizeY()/2-YMundo, 30, 10);
		dbg.setColor(Color.green);
		int px =(int) (getX()-XMundo);
		int py = (int)(getY()-YMundo);
		AffineTransform trans = dbg.getTransform();
		dbg.translate(px, py);
		dbg.rotate(ang+Math.PI/2);
		dbg.drawImage(img, -getSizeX()/2,-getSizeX()/2,getSizeX()/2,getSizeY()/2,getSizeX()*frameX,getSizeY()*frameY,getSizeX()*frameX+getSizeX(),getSizeY()*frameY+getSizeY(),null);
		dbg.setTransform(trans);
		dbg.setColor(Color.black);
		dbg.drawRect((int)px-getSizeX()/2-5, (int)py-getSizeY()/2-17, 30, 10);
		dbg.setColor(Color.green);
		dbg.fillRect((int)px-getSizeX()/2-5+1, (int)py-16-getSizeY()/2, (int)(getLife()*30/maximoVida)-1, 9);
	
//		if(Constantes.colidecircular(getX(), getY(),getSizeX(),target.getX()+target.sizeX/2,target.getY()+target.sizeY/2,target.getSizeX()/2)){
		if (target!=null&&Constantes.menuDeObstaculos) {
			dbg.drawOval((int)getX()-sizeX/2-XMundo,(int) getY()-sizeY/2-YMundo, sizeX, sizeX);
			dbg.drawOval((int)target.getX()-target.sizeX/2-XMundo,(int)target.getY()-target.sizeY/2-YMundo, target.sizeX, target.sizeX);
		}
		
	
	}
	
private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub
		
		tempoEntreAtaque+=DiffTime;
		if(estado == 0){
			
			estado=trataEstado(estado);
			//System.out.println("1");
		}
		
		if (estado == 1) {
			//System.out.println("2");

			estado=trataEstado(estado);
		}
		if (estado == 2) {
			//System.out.println("3");

			estado=trataEstado(estado);
		}
		if (estado == 3) {
			//System.out.println("4");

			estado=trataEstado(estado);
		}
		
		estado=0;
		
	}
		
//		
//		//System.out.println(estado);
//		
//		if (estado ==0) {// ir atras da base
//		
//				if(!Constantes.colidecircular(getX(), getY(),campoDeVisao,CanvasGame.heroi.getX(),CanvasGame.heroi.getY(),CanvasGame.heroi.getSizeX()/2)||	GerenciadorRespawn.isRespawn() ){
//					irAtrasDaBase();
//				} else {
//				
//						estado = 1;
//				}
//		}
//		
//		if (estado ==1) {
//			if(!GerenciadorRespawn.isRespawn()){
//
//				if(Constantes.colidecircular(getX(), getY(),campoDeVisao,CanvasGame.heroi.getX(),CanvasGame.heroi.getY(),CanvasGame.heroi.getSizeX()/2)){
//					irAtrasDoHeroi();
//				} else {
//					estado = 0;
//					carregaTargetProximo();
//				}
//			} else {
//				estado = 0;
//				carregaTargetProximo();
//			}
//		}
	
	 private int trataEstado(int _estado) {
			
			switch (_estado) {
			case 0:
				
				return estadoDesviaObstaculo(_estado);
			case 1:
				
				return estadoFollowPlayer(_estado);
			case 2:
				
				return estadoFollowBase(_estado);
			
			case 3:
			
			return estadoAtacaBase(_estado);
		

			default:
				break;
			}
			
			return 0;
		}
	private int estadoAtacaBase(int _estado) {
		// TODO Auto-generated method stub
		atacaBase();
		return 0;
	}

	private int estadoFollowBase(int _estado) {
		// TODO ir atras da base
	
		return procuraCaminho(_estado); // trata do caminho e ainda retorna se ja pode atacar a base ou nao que eh o proximo estado
		
	
	}

	private int estadoFollowPlayer(int _estado) {
		// TODO ir Atras do player
		
		if(!GerenciadorRespawn.isRespawn()){
			
			if(Constantes.colidecircular(getX(), getY(),campoDeVisao,CanvasGame.heroi.X,CanvasGame.heroi.Y,CanvasGame.heroi.sizeX/2)){
					irAtrasDoHeroi();
					objSecundarioX=(int)CanvasGame.heroi.X;
					objSecundarioY=(int)CanvasGame.heroi.Y;
					target=null;
					return _estado;
				}else {
					objSecundarioX=-1;
					objSecundarioY=-1;
				} 
			} 
		return _estado+1;

	}

	private int estadoDesviaObstaculo(int _estado) {
		// TODO desviar de obstaculos

		
		for (int i =0; i < GerenciadorObstaculos.obstaculos.size();i++) {
			Obstaculo ob = GerenciadorObstaculos.obstaculos.get(i);
			if (Constantes.colidecircular(X, Y, sizeX/2, ob.X, ob.Y, ob.sizeX/2)) {
				colidiuObstaculo=true;
					trataColisaoObstaculo(ob);
				break;
			}else {
				velx=(int) (Math.cos(ang)*vel);
				 vely=(int) (Math.sin(ang)*vel);
			}
			
			
		} 
//		if (!colidiuObstaculo) {
			 velx=(int) (Math.cos(ang)*vel);
			 vely=(int) (Math.sin(ang)*vel);
				vel=maxVel;

				return _estado+1;
//		}else {
////			atualizaAngulo();
//			colidiuObstaculo=false;
//			
//		}
			
			
			
//		
//		return _estado+1;
	}

	private void atualizaAngulo() {

//		if (objSecundarioX != -1 &&objSecundarioY != -1 ) {
//			double difX = objSecundarioX - X;
//			double difY = objSecundarioY - Y;
//			ang =  Math.atan2(difY, difX);
//			
//		}else {
//			double difX = target.X - X;
//			double difY = target.Y - Y;
//			ang =  Math.atan2(difY, difX);
//		}
		
	}

	private void trataColisaoObstaculo(Obstaculo ob) {
//		if(((x1+sizeX1<x2 || x1>x2+sizeX2)) || ((y1+sizeY1<y2 || y1>y2+sizeY2)))
		
		if (X+sizeX>ob.X && X < ob.X+ob.sizeX) {
			X=oldx;
		}
		if (Y+sizeY>ob.Y && Y < ob.Y+ob.sizeY) {
			Y=oldy;
		}
//		
//		
//		if (velx!=0) {
//			if (vely!=0) {
//				vely=0;
//				velx=(int) (Math.cos(ang)*vel);
//				X=oldx;
//				Y=oldy;
//			
//				
//			}else {
//				 vely=(int) (Math.sin(ang)*vel);
//				 velx=0;
//				X=oldx;
//				Y=oldy;
//
//				
//			}		
//		}
//		else {
//			if (vely==0) {
//				vely = -(int)(Math.sin(ang)*vel);
//				X=oldx;
//				Y=oldy;
//				
//					
//			}
//			else { 
//				vely=0;
//				velx=-(int) (Math.cos(ang)*vel);
//				X=oldx;
//				Y=oldy;
//				
//			}
//
//		}		
	}



	public void recebeuDano(int dano,int _tipo) {
		tipoAssasino=_tipo;
		if (getLife()>0)
			
			CanvasGame.gerenciadorEfeitos.ativaSangue(getX(),getY(),ang,(int)dano);
		
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

		
	private int procuraCaminho(int _estado){

		
		if (target==null ) {
			if (primeiraVez) {
				carregaPrimeiroTarget();
				primeiraVez=false;
				return _estado;
			}else
				carregaTargetProximo();
			return _estado;
		}else {
						
			return verificaTarget(_estado);
		}

	}


private int verificaTarget(int _estado) {
	
	double difX = target.X - X;
	double difY = target.Y - Y;
	 ang =  Math.atan2(difY, difX);


	if(Constantes.colideQuadrado((int)X-sizeX/2, (int)Y-sizeY/2,sizeX,sizeY,(int)target.X-target.sizeX/2,(int)target.Y-target.sizeY/2,target.sizeX,target.sizeY)){		

		return  trataColisaoTarget(target,_estado);
			
		}
	return _estado;

	   
	}
private int trataColisaoTarget(WayPoint target2,int _estado) {
//	System.out.println("x t " + target.X);
//	System.out.println("y t " + target.Y);
//	System.out.println("x  " + objX);
//	System.out.println("Y  " + objY);
//	
//	System.out.println("Y target colidida"+target2.Y);	
//	System.out.println("X obj"+objX);	
//	System.out.println("Y obj"+objY);	
	if ((int)target.X!=objX||(int)target.Y!=objY) {

		int newI = target2.indexNextTarget; 

		if (newI !=-1) {
			
			target = Constantes.wayPoints.get(newI);
			
		

			return _estado;
//			if (newI == 1) 
			
			
		

			
		}else {
			target = new WayPoint(objX,objY,objSizeX,objSizeY);
		}	

	}
	return _estado+1; // vai atras da base
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
//				System.out.println(_d + "i " + i);
			}
//			System.out.println(target.indexNextTarget);
			if (_target !=null) {
				target=_target;
			}
			if (target !=null){
				   int dx = (int)(target.X - X);
				   int dy = (int)(target.Y - Y);
				   
				   ang = Math.atan2(dy, dx);
				   
				
			}
	}

private void atacaBase() {
	if(Constantes.colidecircular(getX(), getY(),getSizeX()/2,CanvasGame.base.getX(),CanvasGame.base.getY(),CanvasGame.base.getSizeX()/2)){
	if (tempoEntreAtaque>500) {
		CanvasGame.base.life=(CanvasGame.base.life - dano);
		tempoEntreAtaque=0;
	}
//	X(getOldx());
//	Y(getOldy());
	vel =0; /// variavel para o inimigo nao atravessar o player....
	
	}else {
		vel = maxVel;
	}
		
	}

private void irAtrasDoHeroi(){
		double difX = CanvasGame.heroi.getX() - getX();
		double difY = CanvasGame.heroi.getY() - getY();
		 ang =  Math.atan2(difY, difX);


		if(Constantes.colidecircular(getX(), getY(),getSizeX()/2,CanvasGame.heroi.getX(),CanvasGame.heroi.getY(),CanvasGame.heroi.getSizeX()/2)){
			if (tempoEntreAtaque>500) {
				CanvasGame.heroi.setLife(CanvasGame.heroi.getLife() - dano);
				tempoEntreAtaque=0;
			}
			
			setVel(0); /// variavel para o inimigo nao atravessar o player....
			
			}
		else setVel(maxVel);
}

public void setVel(double vel) {
	this.vel = vel;
}

public double getVel() {
	return vel;
}
}
