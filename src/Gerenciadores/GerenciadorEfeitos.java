package Gerenciadores;


import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;
import Constantes.Constantes;
import Data.Imagem;
import Efeitos.Explosao;
import Efeitos.Particula;
import Efeitos.Sangue;
import GameState.GamePanel;



public class GerenciadorEfeitos extends Objeto implements Runnable {
	
	private Thread tPart;

	private static int xp;
	private static BufferedImage manchasSangue;
	private static Graphics2D manchas;
	private static long diffTimeParticulas;
	
	private boolean running=false;
		private int timer=0;
	private long DiffTime;
	private long TempoAnterior;
	private int segundo;
	
	

	long tempoinicial = 0;
	long tempototal = 0;

	static int FPS;
	int SFPS;
	int fpscount;
	//private LinkedList<Particula> particulasParaDesenhas;
	//private  LinkedList<Particula> particulasParaDesenhas= new LinkedList<Particula>();
	
	public GerenciadorEfeitos() {
		// TODO Auto-generated constructor stub
		diffTimeParticulas=0;
		manchasSangue= new BufferedImage(CanvasGame.tela.Largura*16, CanvasGame.tela.Altura*16, BufferedImage.TYPE_INT_ARGB);
		manchas = manchasSangue.createGraphics();
		Constantes.particulas.clear();
		Constantes.particulasDesenha.clear();
		Constantes.particulasExplosao.clear();
		Constantes.efeitos.clear();

		
//		startGame();
	}
	public void reset() {
		xp=0;
		manchasSangue= new BufferedImage(CanvasGame.tela.Largura*16, CanvasGame.tela.Altura*16, BufferedImage.TYPE_INT_ARGB);
		manchas = manchasSangue.createGraphics();

		
	}

	public synchronized void setParticulasDesenha(LinkedList<Particula> aux ) {
		
		Constantes.particulasDesenha=aux;
		
	}


	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		timer+=DiffTime;
		diffTimeParticulas+=DiffTime;
//			while(particulasEstatica.size()>Constantes.MAXIMO_PARTICULAS_ESTATICAS){
//				particulasEstatica.removeFirst();
//				
//			}
			
			for(int i = 0; i < Constantes.efeitos.size();i++){
				Objeto part =  Constantes.efeitos.get(i);
				part.SimulaSe((int)DiffTime);
				if(part.isVivo()==false) {
					Constantes.efeitos.remove(i);
				}
			}
			
			Iterator<Particula> itP = Constantes.particulas.iterator();
			while(itP.hasNext()){
				Particula part= itP.next();
			
				part.SimulaSe((int)DiffTime);
				
				if (!part.isVivo()) {
					desenhaSangue(part);
					itP.remove();
				}
		
			}
			
			Iterator<Particula> itP2 = Constantes.particulasExplosao.iterator();
			while(itP2.hasNext()){
				Particula part= itP2.next();
			
				part.SimulaSe((int)DiffTime);
				
				if (!part.isVivo()) {
					//desenhaSangue(part);
					itP2.remove();
				}
		
			}
		
	
//		
//		if (timer>50) {
//			chamaThreadParticulas();
//		timer=0;
//		}

	}
	public void startGame()
	// initialise and start the thread
	{
		if (tPart == null || !running) {
			tPart = new Thread(this);
			tPart.start();
			tPart.setPriority(Thread.MIN_PRIORITY);
		}
	} // end of startGame()
	public void run()
	/* Repeatedly update, render, sleep */
	{
		running = true;
		segundo=0;
		DiffTime=0;
		
		while(running) {
				
			for(int i = 0; i < Constantes.particulas.size();i++){
				Particula part =  Constantes.particulas.get(i);
				part.SimulaSe((int)DiffTime);
				if(part.isVivo()==false) {
					
					Constantes.particulas.remove(i);
					desenhaSangue(part);
				}
			}
			
			setParticulasDesenha(Constantes.particulas);

				
			try {
				Thread.sleep(10); // sleep a bit
			}	
			catch(InterruptedException ex){}
			
			DiffTime = System.currentTimeMillis() - TempoAnterior;
			TempoAnterior = System.currentTimeMillis();
			
			if(segundo!=((int)(TempoAnterior/1000))){
				FPS = SFPS;
				SFPS = 1;
				segundo = ((int)(TempoAnterior/1000));
			}else{
				SFPS++;
			}
		
		}
	System.exit(0); // so enclosing JFrame/JApplet exits
	} // end of run()
	
	
	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub


		dbg.drawImage(manchasSangue, -XMundo,-YMundo, manchasSangue.getWidth()-XMundo, manchasSangue.getHeight()-YMundo, 0, 0, CanvasGame.largura, CanvasGame.altura, null);
		
		Iterator<Particula> itP = Constantes.particulasDesenha.iterator();
		while(itP.hasNext()){
			Particula part= itP.next();
			part.DesenhaSe(dbg,XMundo,YMundo);
			
		}	
		
		Iterator<Particula> itP2 = Constantes.particulasExplosao.iterator();
		while(itP2.hasNext()){
			Particula part= itP2.next();
			part.DesenhaSe(dbg,XMundo,YMundo);
			
		}
		
		//dbg.drawString(""+FPS, 50, 10);

		for(int i = 0; i < Constantes.efeitos.size();i++){
			
			Objeto proj = (Objeto) Constantes.efeitos.get(i);
			proj.DesenhaSe(dbg,XMundo,YMundo);
	
		}
		
	
	}
	
	

	public synchronized static void verificaParticulas() {
		// TODO Auto-generated method stub
		//System.out.println("oi");
//		Iterator<Particula> it = particulas.iterator();
//		while(it.hasNext()){
		for(int i = 0; i < Constantes.particulas.size();i++){
			Particula part =  Constantes.particulas.get(i);
//			part.SimulaSe(50/*(int)DiffTime*/);
//			if(part.isVivo()==false) {
//				efeitos.remove();
//			}
//		}
//			Particula part = it.next();
			
//			part.SimulaSe((int)(diffTimeParticulas-System.currentTimeMillis()));
			//System.out.println(diffTimeParticulas+ "   ");
			if(part.isVivo()==false) {
				
				Constantes.particulas.remove(i);
				desenhaSangue(part);
//				//chamaThreadDesenhaSangue(part);
			}
		}
	}


	private static void chamaThreadDesenhaSangue(final Particula part) {
		// TODO Auto-generated method stub
		new Thread(){
			 @Override
			public void run() {
				// TODO Auto-generated method stub
				//super.run();
				
				
				 desenhaSangue(part);
				 
				 
			 }

			
		 }.start();
	}




	private  static void chamaThreadParticulas() {
		
		new Thread(){
			 @Override
			public void run() {
				// TODO Auto-generated method stub
				//super.run();
//				while (CanvasGame.ContiuaJogo) 
				 
				 GerenciadorEfeitos.verificaParticulas();
				 
				 
//			 }

			 }
		 }.start();
	}
	private  static void desenhaSangue(Particula part) {
		// TODO Auto-generated method stub
		int alpha;
		if (part.getAlpha()-40<0)
			alpha = 0;
		else alpha= part.getAlpha()-40;
		
		manchas.setColor(new Color(255,0,0,alpha));
		manchas.fillOval((int)part.getX(),(int) part.getY(), part.getSizeX(), part.getSizeY());

		
	}

	public static void criaEfeitoFaca(double x,double y,double ang) {
	
		

	
		
		
	}
	
	public void ativaSangue (double x, double y, double ang, int dano) {
		Color cor;
		int velx =(int) (Math.cos(ang)*800);
		int vely=(int) (Math.sin(ang)*800);

			int totalParticulas= dano+10;
			for(int B = 0; B < totalParticulas;B++){
				int modv = GamePanel.rnd.nextInt(500)-100;
				
				int pvx = 0;
				int pvy = 0;
				
				switch (GamePanel.rnd.nextInt(12)) {
				case	0:
					pvx = (velx + modv);
					pvy = (vely - modv);
					break;
				case	1:
					pvx = (velx - modv);
					pvy = (vely - modv);
					break;
				case	2:
					pvx = (velx + modv);
					pvy = (vely - modv);
					break;
				case	3:
					pvx = (velx - modv);
					pvy = (vely + modv);
					break;
				case	4:
					pvx = (velx + modv)/-2;
					pvy = (vely - modv)/-2;
					break;
				case	5:
					pvx = (velx - modv)/-2;
					pvy = (vely - modv)/-2;
					break;
				case	6:
					pvx = (velx + modv)/-2;
					pvy = (vely - modv)/-2;
					break;
				case	7:
					pvx = (velx - modv)/-2;
					pvy = (vely + modv)/-2;
					break;
				}

				
				pvx = (int)(pvx*(0.01+0.25*GamePanel.rnd.nextFloat()));
				pvy = (int)(pvy*(0.01+0.25*GamePanel.rnd.nextFloat()));
				
	
					cor = Color.red;
					
				switch (GamePanel.rnd.nextInt(8)) {
					case 0:
						pvx = -pvx;
						break;
					
					case 1:
						pvy=-pvy;
						break;
						
					case 2:
						pvy=-pvy;
						pvx=-pvy;
					   break;
					   
				}
				
				Constantes.particulas.add((Particula)new Sangue(x,y,-pvx,-pvy,GamePanel.rnd.nextInt(100)+100,cor));
			}
//			int modv = GamePanel.rnd.nextInt(300);
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
//			Particula part;
//			switch (B%4) {
//			case 0:
//				part = (Particula)new Sangue(x,y,-pvx/2,-pvy/2,GamePanel.rnd.nextInt(400)+100,cor);
//
//				break;
//			case 1:
//				part = (Particula)new Sangue(x,y,-pvx/6,-pvy/6,GamePanel.rnd.nextInt(400)+100,cor);
//
//				break;
//			case 2:
//				part = (Particula)new Sangue(x,y,-pvx/2,pvy/2,GamePanel.rnd.nextInt(400)+100,cor);
//
//				break;
//			case 3:
//				part = (Particula)new Sangue(x,y,pvx/2,-pvy/2,GamePanel.rnd.nextInt(400)+100,cor);
//
//				break;
//
//			default:
//				break;
//			}
//			
//			if (B%4==0)
//				part = (Particula)new Sangue(x,y,-pvx/2,-pvy/2,GamePanel.rnd.nextInt(400)+100,cor);
//			else
//				part = (Particula)new Sangue(x,y,pvx/6d,pvy/6,GamePanel.rnd.nextInt(400)+100,cor);
//
//				
//			particulas.add(part);
//			
		
	}




	public static void setXp(int xp) {
		GerenciadorEfeitos.xp = xp;
	}




	public static int getXp() {
		return xp;
	}


public void explosao(double x, double y, int velx, int vely) {
		

			Particula part=null;
			for(int B = 0; B < 50;B++){
				int modv = GamePanel.rnd.nextInt(200)+50;

				int pvx = 0;
				int pvy = 0;
			
					int a =B%4;
					switch (a) {
					
					case 0:
						pvx = velx + modv;
						pvy = vely - modv;
						break;
					case 1:
						pvx = -velx - modv;
						pvy = vely + modv;
						break;
					case 2:
						pvx = velx + modv;
						pvy = -vely + modv;
						break;
					case 3:
						pvx = -velx - modv;
						pvy = -vely - modv;
						break;
					
					
					}
				
				pvx = (int)(pvx*(0.1+0.25*GamePanel.rnd.nextFloat()));
				pvy = (int)(pvy*(0.1+0.25*GamePanel.rnd.nextFloat()));
				part = (Particula)new Explosao(x,y,pvx,pvy,GamePanel.rnd.nextInt(500)+200,Imagem.explosao,Imagem.explosao2);
				
			
				Constantes.particulasExplosao.add(part);

//				switch (B%4) {
//				case 0:
//					part = (Particula)new Explosao(x+pvx/40,y+pvy/40,pvx/5,pvy/5,GamePanel.rnd.nextInt(600)+300,Constantes.explosao,Constantes.explosao2);
	//
//					break;	
//				case 1:
//					part = (Particula)new Explosao(x,y,-pvx/5,pvy/5,GamePanel.rnd.nextInt(600)+300,Constantes.explosao,Constantes.explosao2);
	//
//					break;
//				case 2:
//					part = (Particula)new Explosao(x,y,pvx/5,-pvy/5,GamePanel.rnd.nextInt(600)+300,Constantes.explosao,Constantes.explosao2);
	//
//					break;
//				case 3:
//					part = (Particula)new Explosao(x,y,-pvx/5,-pvy/5,GamePanel.rnd.nextInt(600)+300,Constantes.explosao,Constantes.explosao2);
	//
//					break;
//				default:
//					break;
//				}
//				if (B%4==0) {
//					part = (Particula)new Explosao(x,y,pvx/5,pvy/5,GamePanel.rnd.nextInt(300)+300,Constantes.explosao,Constantes.explosao2);
//				}
//				if (B%4==1) {
//					part = (Particula)new Explosao(x,y,-pvx/6,-pvy/6,GamePanel.rnd.nextInt(300)+300,Constantes.explosao,Constantes.explosao2);
//				}
//				if (B%4==2) {
//					part = (Particula)new Explosao(x,y,-pvx/6,-pvy/6,GamePanel.rnd.nextInt(300)+300,Constantes.explosao,Constantes.explosao2);
//				}
			
			}
			
		}
		
	}










