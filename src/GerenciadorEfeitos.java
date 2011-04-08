import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;


public class GerenciadorEfeitos extends Objeto {
	
	
	public static LinkedList<Particula> particulas = new LinkedList<Particula>();
	public static LinkedList<Particula> particulasEstatica = new LinkedList<Particula>();
	
	public static LinkedList<Objeto> efeitos = new LinkedList<Objeto>();
	
	private static BufferedImage manchasSangue;
	private static Graphics2D manchas;
	public GerenciadorEfeitos() {
		// TODO Auto-generated constructor stub
	
		manchasSangue= new BufferedImage(CanvasGame.MAPA.Largura*16, CanvasGame.MAPA.Altura*16, BufferedImage.TYPE_INT_ARGB);
		manchas = manchasSangue.createGraphics();
		
//		new Thread(){
//			 @Override
//			public void run() {
//				// TODO Auto-generated method stub
//				//super.run();
//				
//				 
//				 GerenciadorEfeitos.verificaParticulas();
//				 
//				 
//			 }
//
//			
//		 }.start();
		
		
	}

	
	

	public static void verificaParticulas() {
		// TODO Auto-generated method stub
		if (particulas.size()!=0) {
			
		
//				Iterator<Particula> it = particulas.iterator();
//				
//				while(it.hasNext()){
//					Particula part = it.next();
					for (int i=0;i<=particulas.size();i++) {
						Particula part = particulas.get(i);
						if (part.vivo==false) {
	//						it.remove();
							particulas.remove(i);
							GerenciadorEfeitos.desenhaSangue(part);
						}
				}
			}
		
	}



	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
//			while(particulasEstatica.size()>Constantes.MAXIMO_PARTICULAS_ESTATICAS){
//				particulasEstatica.removeFirst();
//				
//			}
			
			for(int i = 0; i < efeitos.size();i++){
				Objeto part =  efeitos.get(i);
				part.SimulaSe((int)DiffTime);
				if(part.vivo==false) {
					efeitos.remove();
				}
			}
		
		
		Iterator<Particula> it = particulas.iterator();
		while(it.hasNext()){
			Particula part = it.next();
			part.SimulaSe((int)DiffTime);
			if(part.vivo==false) {
				it.remove();
				desenhaSangue(part);
			}
		}
		

	}

	private static void desenhaSangue(Particula part) {
		// TODO Auto-generated method stub
		
		
		manchas.setColor(new Color(255,0,0,part.getAlpha()-50));
		manchas.fillOval((int)part.X,(int) part.Y, part.sizeX, part.sizeY);

		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub


		dbg.drawImage(manchasSangue, -XMundo,-YMundo, manchasSangue.getWidth()-XMundo, manchasSangue.getHeight()-YMundo, 0, 0, CanvasGame.largura, CanvasGame.altura, null);
		
		for(int i = 0; i < particulas.size();i++){
			
			Particula proj = (Particula) particulas.get(i);
			proj.DesenhaSe(dbg,XMundo,YMundo);

		}

		for(int i = 0; i < efeitos.size();i++){
			
			Objeto proj = (Objeto) efeitos.get(i);
			proj.DesenhaSe(dbg,XMundo,YMundo);
	
		}
	}
	
	public void ganhouXp(double x,double y,int _xp) {
		
		efeitos.add(new Texto(_xp,x,y) );
		
	}
	
	public void ativaSangue (double x, double y, double ang, int dano) {
		Color cor;
		int velx =(int) Math.cos(ang)*800;
		int vely=(int) Math.sin(ang)*800;

			int totalParticulas= dano+20;
			for(int B = 0; B < totalParticulas;B++){
				int modv = GamePanel.rnd.nextInt(600)-200;
				
				int pvx = 0;
				int pvy = 0;
				if(GamePanel.rnd.nextInt(4)==0){
					pvx = (velx + modv)/-2;
					pvy = (vely - modv)/-2;
				}else{
					pvx = velx + modv;
					pvy = vely - modv;
				}
				
				pvx = (int)(pvx*(0.01+0.25*GamePanel.rnd.nextFloat()));
				pvy = (int)(pvy*(0.01+0.25*GamePanel.rnd.nextFloat()));
				
				if(GamePanel.rnd.nextBoolean()){
					cor = Color.red;
				}else{
					cor = Color.red;
				}
			
				Particula part = (Particula)new Sangue(x,y,pvx,pvy,GamePanel.rnd.nextInt(400)+200,cor);
				
				particulas.add(part);
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

}
