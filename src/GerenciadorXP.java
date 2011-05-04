import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;


public class GerenciadorXP extends Objeto {
	private static int xp;
	private float alpha;
	private static int fastKill=0;
	private static double timer;
	
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		timer+=DiffTime;
		//alpha-=DiffTime;
		alpha = (float) (1.0f-(timer/Constantes.tempoEntreKill));
		if (timer>=Constantes.tempoEntreKill) {
			
			fastKill =0;
		}
		if (alpha <0) {
			alpha=0;
		}

		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub

		if (Constantes.QUANTIDADE_FAST_KILL> fastKill && fastKill > 0) {
		    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

			
			Stroke stk = dbg.getStroke();
		    
		    dbg.setStroke(new BasicStroke(1.5f));
			dbg.setColor(Color.cyan);
			
			dbg.drawRect(GamePanel.PWIDTH/2-25,20, Constantes.QUANTIDADE_FAST_KILL*10, 30);
			dbg.fillRect(GamePanel.PWIDTH/2-25,20 , fastKill*10, 30);
			dbg.setColor(Color.red);
			dbg.setFont(Constantes.fonteBig);
			dbg.drawString("Fast Kill", GamePanel.PWIDTH/2-20,45 );
			
			dbg.setStroke(stk);

		    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			
		}
		
	}
	
	
	public static void kill() {
		fastKill++;
		timer=0;
	}

	public static void ganhouXp(double x,double y,int tipoAssasino) {
		int _xp;
		boolean extraXp=false;
		if (tipoAssasino==Constantes.TIPO_ASSASINO_PLAYER) {
			_xp=Constantes.GANHO_XP_PLAYER;
			kill();
		}else {
			_xp=+Constantes.GANHO_XP_TORRE;
		}
		
			if (trataXp(_xp)==true) {
				
				extraXp=true;
			}
		
			trataEfeito(extraXp,_xp,x,y);
		
	
	
		
		
	}
	public void reset() {
		System.out.println("teste");
		xp=0;
		fastKill=0;
		timer=0;
		
	}
	private static void trataEfeito(boolean extraXp, int _xp, double x, double y) {
		// TODO Auto-generated method stub
		
		if (extraXp){
			GerenciadorEfeitos.efeitos.add(new TextoExtraXp(_xp,x,y) );
		}else {
			GerenciadorEfeitos.efeitos.add(new Texto(_xp,x,y) );

		}
	}

	private static boolean trataXp(int _xp) {
		boolean fast=false;
		if (fastKill>Constantes.QUANTIDADE_FAST_KILL) {
			xp+=(_xp*3);
			fast=true;
		}else {
			xp+=_xp;

		}
		
		GerenciadorHud.setXpHud(xp);
		return fast;
		
	}
}
