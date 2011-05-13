package Personagem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;
import Constantes.Constantes;
import GameState.GamePanel;
import Gerenciadores.GerenciadorRespawn;
import Map.WayPoint;

public class Inimigo extends Objeto {
	
	int dano;
	private double vel;
	private int tempoEntreAtaque;
	private int maximoVida=50;
	private int maxVel=100;
	public int tipoAssasino;
	private int larguraMapa;
	private int alturaMapa;
	private int estado;
	private double ang;
	private double campoDeVisao;
	private int frameX=0;
	private int frameY=1;
	BufferedImage img;
	
	
	WayPoint target  = null;
	public Inimigo(BufferedImage img) {
		
		this.img=img;
		larguraMapa=CanvasGame.tela.Largura*16;
		alturaMapa=CanvasGame.tela.Altura*16;
		
		setX(GamePanel.rnd.nextInt(alturaMapa));//+alturaMapa*(-2));
		setY(GamePanel.rnd.nextInt(larguraMapa));//+alturaMapa*(-2));
		
		setSizeX(img.getWidth()/2);
		setSizeY(img.getHeight()/3);
		dano = 10;
		setVel(100);
		setLife(maximoVida);
		setVivo(true);
		estado=0;
		campoDeVisao=Constantes.INIMIGO_CAMPO_VISAO1;

		// TODO Auto-generated constructor stub
	}

	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub	
			calculaIA(DiffTime);
		
			double velx=(int) (Math.cos(ang)*getVel());
			double vely=(int) (Math.sin(ang)*getVel());
			
			X=(X + (velx*DiffTime/1000.0f));
			Y=(Y + (vely*DiffTime/1000.0f));
			
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
dbg.drawOval((int)getX()-sizeX/2-XMundo,(int) getY()-sizeY/2-YMundo, sizeX, sizeX);
if (target!=null)
dbg.drawOval((int)target.getX()-target.sizeX/2-XMundo,(int)target.getY()-target.sizeY/2-YMundo, target.sizeX, target.sizeX);

	
	}
	
	private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub
		
		tempoEntreAtaque+=DiffTime;
		
		//System.out.println(estado);
		
		if (estado ==0) {// ir atras da base

				if(!Constantes.colidecircular(getX(), getY(),campoDeVisao,CanvasGame.heroi.getX(),CanvasGame.heroi.getY(),CanvasGame.heroi.getSizeX()/2)&&!GerenciadorRespawn.isRespawn()){
					irAtrasDaBase();
				} else {
					estado = 1;
				}
		}
		
		if (estado ==1) { //ir atras do heroi

			if(Constantes.colidecircular(getX(), getY(),campoDeVisao,CanvasGame.heroi.getX(),CanvasGame.heroi.getY(),CanvasGame.heroi.getSizeX()/2)){
					irAtrasDoHeroi();
				} else {
					estado = 0;
					carregaTargetProximo();
				}
		
		}
	}

	public void recebeuDano(int dano,int tipo) {
		// TODO Auto-generated method stub
		if (getLife()>0)
			
			CanvasGame.gerenciadorEfeitos.ativaSangue(getX(),getY(),ang,(int)dano);
		
		if (life-dano<=0) {
			
			morreu(tipo);
		}else  {
			life=- dano;
		}
	


				
	}

	public void morreu(int tipo) {
		// TODO Auto-generated method stub
		
		life=0;	
		tipoAssasino=tipo;
		vivo=false;
	}

		
	private void irAtrasDaBase(){
		
		
		if (target==null) {
			carregaPrimeiroTarget();
		}else {
			verificaTarget();
		}
//		
//
//		double difX = CanvasGame.base.getX() - getX();
//		double difY = CanvasGame.base.getY() - getY();
//		 ang =  Math.atan2(difY, difX);
//		 
//
//atacaBase();
	}


private void verificaTarget() {
	
	double difX = target.X - X;
	double difY = target.Y - Y;
	 ang =  Math.atan2(difY, difX);


	if(Constantes.colidecircular(getX(), getY(),getSizeX()/2,target.getX(),target.getY(),target.getSizeX()/2)){
		int newI = target.indexNextTarget;
		target = Constantes.wayPoints.get(newI);
		ang = Math.atan2(difY, difX);
		System.out.println("TROCA TARGET");
		}

//	   int dx = (int)(target.X - X);
//	   int dy = (int)(target.Y - Y);
//System.out.println(target.X);
//		if(Constantes.colidecircular(X, Y,sizeX/2,target.X,target.Y,target.sizeX)){
//
//		   target = Constantes.wayPoints.get(target.indexNextTarget);
////		   System.out.println(target.indexNextTarget);
//
//		   ang = Math.atan2(dy, dx);
//	   }
	   
	}
private void carregaTargetProximo() {
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
		if (_target !=null) {
			target=_target;
		}
		if (target !=null){
			   int dx = (int)(target.X - X);
			   int dy = (int)(target.Y - Y);
			   
			   ang = Math.atan2(dy, dx);
			   
			
		}
}
private void carregaPrimeiroTarget() {
		WayPoint _target= new WayPoint(0, 0, 0, 0);
			int dist = 99999999;
			for (int i = 0; i<Constantes.NUM_WAYPOINT_PRINCIPAL;i++ ) {
		
				   int dx = (int)(Constantes.wayPoints.get(i).X - X);
				   int dy = (int)(Constantes.wayPoints.get(i).Y - Y);
				
				   int _d = dx*dx+dy*dy;
				   System.out.println(Constantes.wayPoints.get(i).X + "   "+Constantes.wayPoints.get(i).Y  );
				   System.out.println("x" +X);
				   System.out.println("y" +Y);

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
		CanvasGame.base.setLife(CanvasGame.base.getLife() - dano);
		tempoEntreAtaque=0;
	}
	setX(getOldx());
	setY(getOldy());
	setVel(0); /// variavel para o inimigo nao atravessar o player....
	
	}
else setVel(maxVel);		
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
