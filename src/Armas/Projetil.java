package Armas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Iterator;
import java.util.List;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;
import Constantes.Constantes;
import Personagem.Inimigo;


public class Projetil extends Objeto {
	
	int vel=Constantes.VELOCIDADE_TIRO;
	double ang;
	int tipo;
	Arma pai;
	protected int dano;
	private int penetration;
	
	public Projetil(Arma pai,double ang,int _tipo){
		// TODO Auto-generated constructor stub
		this.pai = pai;
		this.ang = ang;
	
		this.setX(pai.getX());
		this.setY(pai.getY());
		penetration=Arma.penetration;
		trataTipo(_tipo);
		
		this.setDano(pai.dano);
		setVivo(true);
		setSizeX(4);
		setSizeY(2);
	
	
	}
	public Projetil(Arma pai,double ang,int _tipo,int _x,int _y){
		// TODO Auto-generated constructor stub
		this.pai = pai;
		this.ang = ang;

		this.setX(_x);
		this.setY(_y);
		penetration=Arma.penetration;
		trataTipo(_tipo);
		
		this.setDano(pai.getDano());
		setVivo(true);
		setSizeX(4);
		setSizeY(2);
	
	
	}
	
	
	
	private void trataTipo(int _tipo) {
		tipo=_tipo;
		

	}



	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		oldx=((int)getX());
		oldy=((int)getY());
		
		setX(getX() + (Math.cos(ang)*vel*DiffTime/1000.0f));
		setY(getY() + (Math.sin(ang)*vel*DiffTime/1000.0f));
		
		if((int)getX()<0||(int)getX()>=(CanvasGame.largura)|| (int)getY()<0||(int)getY()>=(CanvasGame.altura)) {
			
			X=(oldx);
			Y=(oldy);
			vivo=(false);
			
		}
//		for (int i = 0;i<GerenciadorDeRaids.getRaids().size();i++) {
//			Raid ra = GerenciadorDeRaids.getRaids().get(i);
//		
			for (int j = 0;j<Constantes.inimigos.size();j++) {
				Inimigo in = Constantes.inimigos.get(j);
				

				if (Constantes.colidecircular(getX(), getY(),getSizeX()/2,in.getX(),in.getY(),in.getSizeX()/2)) {
				
					penetration--;
					
					Constantes.inimigos.get(j).recebeuDano(dano,tipo);
				
					if (penetration<=0) {
						setVivo(false);
						break;
					}
					
					
				}
			}
			
//		}
	}
//
//	private List<Inimigo> extracted() {
//		return ((List<Inimigo>) CanvasGame.gerenciadorDeRaids);
//	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		dbg.setColor(Color.yellow);
	
		AffineTransform trans = dbg.getTransform();
		dbg.translate(getX()-XMundo, getY()-YMundo);
		dbg.rotate(ang);
		dbg.fillOval(-getSizeX()/2,-getSizeY()/2, getSizeX(),getSizeY());

		dbg.setTransform(trans);		
		
		
	}
//	
//	public void ativaParticulas() {
//		Color cor;
//		double velx = Math.cos(ang)*1000;
//		double vely= Math.sin(ang)*1000;
//		for(int B = 0; B < 100;B++){
//			int modv = GamePanel.rnd.nextInt(600)+50;
//			
//			double pvx = 0;
//			double pvy = 0;
//		
//				pvx = velx + modv;
//				pvy = vely - modv;
//
//			
//			pvx = (int)(pvx*(0.21+0.25*GamePanel.rnd.nextFloat()));
//			pvy = (int)(pvy*(0.21+0.25*GamePanel.rnd.nextFloat()));
//			
//			if(GamePanel.rnd.nextBoolean()){
//				cor = Color.red;
//			}else{
//				cor = Color.red;
//			}
//		
//			Particula part = (Particula)new Sangue(X,Y,-pvx/6,-pvy/6,GamePanel.rnd.nextInt(400)+100,cor);
//			
//			//CanvasGame.particulas.add(part);
//			
//		}
//
//	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public int getDano() {
		return dano;
	}





}
