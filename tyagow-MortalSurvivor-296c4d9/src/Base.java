import java.awt.Color;
import java.awt.Graphics2D;


public class Base extends Objeto{

	boolean auraAtiva;
	double timerAura;
	int xAura, yAura;
	
	public Base(int _x, int _y, int sizeX, int sizeY){
//		setX(_x);
//		setY(_y);
		setSizeX(sizeX);
		setSizeY(sizeY);
		setX(_x/16*16);
		setY(_y/16*16);	
	

		setVivo(true);
		setLife(Constantes.BASE_LIFE_1);
		GerenciadorObstaculos.addObstaculos((int)getX(),(int)getY(),getSizeX(),getSizeY() );
		
		auraAtiva = true;
		timerAura = 0;
		xAura = (int)getX() - Constantes.AURA_BASE_DIAMETRO/2;
		yAura = (int)getY() - Constantes.AURA_BASE_DIAMETRO/2;
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		if(!auraAtiva){
			timerAura += DiffTime;
		}
		
		if(timerAura >= Constantes.AURA_BASE_TIMER){
			auraAtiva = true;
			timerAura = 0;
		}
		
		if(auraAtiva && Constantes.colidecircular(xAura + Constantes.AURA_BASE_DIAMETRO/2, yAura + Constantes.AURA_BASE_DIAMETRO/2, Constantes.AURA_BASE_DIAMETRO/2, CanvasGame.heroi.getX(), CanvasGame.heroi.getY(), CanvasGame.heroi.getSizeX()/2)){
			System.out.println("colidiu é noiz");
			CanvasGame.heroi.recarregaArmas();
			auraAtiva = false;
		}
			
		System.out.println(timerAura);
		
		if (getLife() <0)
			setLife(0);
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		//AURA
		if(auraAtiva){
			dbg.setColor(Color.blue);
			dbg.drawOval(xAura - XMundo, yAura - YMundo, Constantes.AURA_BASE_DIAMETRO, Constantes.AURA_BASE_DIAMETRO);
		}
		
		//LIFE
		dbg.drawRect((int)getX()-getSizeX()/2-XMundo, (int)getY()-getSizeY()/2-17-YMundo, 30, 10);
		dbg.setColor(Color.lightGray);
		dbg.fillRect((int)getX()-getSizeX()/2+1-XMundo, (int)getY()-getSizeY()/2-16-YMundo, (int)(getLife()*30/Constantes.BASE_LIFE_1)-1, 9);
		//BASE
		dbg.setColor(Color.ORANGE);
		dbg.fillOval((int)getX()-getSizeX()/2-XMundo,(int) (getY()-getSizeY()/2-YMundo), getSizeX(),getSizeY());
		
		

	}

}
