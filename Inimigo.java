import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Inimigo extends Objeto {
	
	int dano;
	private double vel;
	private int tempoEntreAtaque;
	private int maximoVida=50;
	private int maxVel=100;
	private int tipoAssasino;
	private int larguraMapa;
	private int alturaMapa;
	private int estado;
	private double ang;
	private double campoDeVisao;
	private int frameX=0;
	private int frameY=1;
	BufferedImage img;
	
	Estrela aestrela ;
	ArrayList<Nodo> caminho= new ArrayList<Nodo>();
	private int objx;
	private int objy;
	
	private int passo =0;
	private int XTela;
	private int YTela;
	public Inimigo(BufferedImage img,int _objx,int _objy) {
		//aestrela= new Estrela(GerenciadorObstaculos.getMapa());
		this.img=img;
		larguraMapa=CanvasGame.MAPA.Largura*16;
		alturaMapa=CanvasGame.MAPA.Altura*16;
		objx=_objx;
		objy=_objy;
		setX(GamePanel.rnd.nextInt(alturaMapa));
		setY(GamePanel.rnd.nextInt(larguraMapa));
		
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
			
			setX(getX() + (velx*DiffTime/1000.0f));
			setY(getY() + (vely*DiffTime/1000.0f));
			
			if (getLife()<0) {
				setVivo(false);
				
			}			
			
			setOldx((int)getX());
			setOldy((int)getY());
			
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
		
		XTela=XMundo;
		YTela=YMundo;	
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
	}
	
	private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub
		
		tempoEntreAtaque+=DiffTime;

		
		if (estado ==0) {

				if(GerenciadorRespawn.isRespawn()||!Constantes.colidecircular(getX(), getY(),campoDeVisao,CanvasGame.heroi.getX(),CanvasGame.heroi.getY(),CanvasGame.heroi.getSizeX()/2)){
					irAtrasDaBase();
					verificarAtaqueBase();
				} else {
					estado = 1;
					caminho=null;
				}
		}
		
		if (estado ==1) {
	

				if(!GerenciadorRespawn.isRespawn()||Constantes.colidecircular(getX(), getY(),campoDeVisao,CanvasGame.heroi.getX(),CanvasGame.heroi.getY(),CanvasGame.heroi.getSizeX()/2)){
					irAtrasDoHeroi();
					verificarAtaqueHeroi();
				} else {
					estado = 0;
				}
			}
	}

	private void verificarAtaqueHeroi() {
		// TODO Auto-generated method stub
		if(Constantes.colidecircular(getX(), getY(),getSizeX()/2,CanvasGame.heroi.getX(),CanvasGame.heroi.getY(),CanvasGame.heroi.getSizeX()/2)){
			atacaHeroi();
			
			}
		else setVel(maxVel);
	}

	private void atacaHeroi() {
		// TODO Auto-generated method stub
		if (tempoEntreAtaque>500) {
			CanvasGame.heroi.setLife(CanvasGame.heroi.getLife() - dano);
			tempoEntreAtaque=0;
		}
		
		setVel(0); 
	}

	private void verificarAtaqueBase() {
		// TODO Auto-generated method stub
		if(Constantes.colidecircular(getX(), getY(),getSizeX()/2,CanvasGame.base.getX(),CanvasGame.base.getY(),CanvasGame.base.getSizeX()/2)){
			atacaBase();
			
			}
		else setVel(maxVel);
	}

	public void recebeuDano(int dano,int tipo) {
		// TODO Auto-generated method stub
		
		if (getLife()>0)
			
			CanvasGame.gerenciadorEfeitos.ativaSangue(getX(),getY(),ang,(int)dano);
		
		if (getLife()-dano<=0) {
			morreu(tipo);
		}else  {
			setLife(getLife() - dano);
		}
	


				
	}

	public void morreu(int tipo) {
		// TODO Auto-generated method stub
		
		setLife(0);	
		setTipoAssasino(tipo);
		setVivo(false);
			
	}

	public void setTipoAssasino(int tipoAssasino) {
		this.tipoAssasino = tipoAssasino;
	}

	public int getTipoAssasino() {
		return tipoAssasino;
	}
		
	private void irAtrasDaBase(){
		if (aestrela==null)
			aestrela = new Estrela(new Nodo(null,(int)getX()/16,(int)getY()/16,0, objx-16, objy-16),objx-16,objy-16);
		
		//System.out.println(caminho);
		if (caminho==null) {
//			System.out.println("oi");
		
		
		}
		
		else {
			
			if (chegouPasso())
			ang = achaProximoPasso();
	
		
		}
	}


private boolean chegouPasso() {
		// TODO Auto-generated method stub
		System.out.println(caminho.size());
	if (caminho.size()>0) { 
		if (caminho.get(passo).X!=objx&&caminho.get(passo).Y!=objy) {
			boolean chegouPassoX=((getX())/16==caminho.get(passo).X);
			boolean chegouPassoY=((getY())/16==caminho.get(passo).Y);
			return (chegouPassoX&&chegouPassoY);
		}else {
			return false;
		}
	}
	return false;
	}

private void atacaBase() {
		// TODO Auto-generated method stub
	if (tempoEntreAtaque>500) {
		CanvasGame.base.setLife(CanvasGame.base.getLife() - dano);
		tempoEntreAtaque=0;
	}
	setX(getOldx());
	setY(getOldy());
	setVel(0); /// variavel para o inimigo nao atravessar o player....
	}

private double achaProximoPasso() {
		// TODO Auto-generated method stub
	double difX =  caminho.get(passo).X*16  - getX();
	double difY = caminho.get(passo).Y*16 - getY();
	passo++;
	return Math.atan2(difY, difX);

	}

private void irAtrasDoHeroi(){
		double difX = CanvasGame.heroi.getX() - getX();
		double difY = CanvasGame.heroi.getY() - getY();
		 ang =  Math.atan2(difY, difX);


}

public void setVel(double vel) {
	this.vel = vel;
}

public double getVel() {
	return vel;
}
}
