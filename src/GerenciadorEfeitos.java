import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;


public class GerenciadorEfeitos extends Objeto {
	
	
	public static LinkedList<Particula> particulas = new LinkedList<Particula>();
	public static LinkedList<Particula> particulasEstatica = new LinkedList<Particula>();
	
	public static LinkedList<Objeto> efeitos = new LinkedList<Objeto>();
	
	public GerenciadorEfeitos() {
		// TODO Auto-generated constructor stub
	
		
		
	}

	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
			while(particulasEstatica.size()>Constantes.MAXIMO_PARTICULAS_ESTATICAS){
				particulasEstatica.removeFirst();
				
			}
			
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
				if (GamePanel.rnd.nextBoolean())
					if (GamePanel.rnd.nextBoolean())
					particulasEstatica.add(part);
			}
		}
		

	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub

		for(int i = 0; i < particulas.size();i++){
			
			Particula proj = (Particula) particulas.get(i);
			proj.DesenhaSe(dbg,XMundo,YMundo);

		}

		for(int i = 0; i < particulasEstatica.size();i++){
				
				Particula proj = (Particula) particulasEstatica.get(i);
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
			
				Particula part = (Particula)new Sangue(x+CanvasGame.MAPA.MapX,y+CanvasGame.MAPA.MapY,pvx,pvy,GamePanel.rnd.nextInt(400)+200,cor);
				
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
