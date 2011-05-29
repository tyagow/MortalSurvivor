package Gerenciadores;


import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import AbstractClasses.Objeto;
import Constantes.Constantes;
import Efeitos.Texto;
import Efeitos.TextoExtraXp;
import GameState.GamePanel;


public class GerenciadorReward extends Objeto {
	private static int xp;
	private float alpha;
	private static int fastKill=0;
	private static double timer;
	public  static int cash=0;
	int  larguraHudFastKill =200;

	
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
			dbg.fillRect(GamePanel.PWIDTH/2-larguraHudFastKill/2,GamePanel.PHEIGHT-larguraHudFastKill/2 , larguraHudFastKill*fastKill/Constantes.QUANTIDADE_FAST_KILL, 20);
			dbg.setColor(Color.black);

			dbg.drawRect(GamePanel.PWIDTH/2-larguraHudFastKill/2,GamePanel.PHEIGHT-larguraHudFastKill/2, larguraHudFastKill, 20);

			dbg.setColor(Color.red);
			dbg.setFont(Constantes.fonteBig);
			dbg.drawString("Fast Kill", GamePanel.PWIDTH/2-40,GamePanel.PHEIGHT-larguraHudFastKill/2-20 );
			
			dbg.setStroke(stk);

		    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			
		}
		
	}
	
	
	public static void trataFastKill() {
		fastKill++;
		timer=0;
	}

	public static void trataReward(double x,double y,int tipoAssasino) {
		int _xp;
		boolean extraXp=false;
		
		
		if (tipoAssasino==Constantes.TIPO_ASSASINO_PLAYER) {
			_xp=Constantes.GANHO_XP_PLAYER;
			cash+=Constantes.GANHO_CASH_PLAYER;
			trataFastKill();
			
		}else {
			cash+=Constantes.GANHO_CASH_TORRE;

			_xp=Constantes.GANHO_XP_TORRE;
		}
		
		
		
		if (trataXp(_xp)==true) {
				
			extraXp=true;
		}
	
		
		
		trataEfeito(extraXp,_xp,x,y);
		
	
	
		
		
	}
	

	private static void ganhaCash() {
		
	}

	public void reset() {
		xp=0;
		fastKill=0;
		timer=0;
		
	}
	private static void trataEfeito(boolean extraXp, int _xp, double x, double y) {
		// TODO Auto-generated method stub
		
		if (extraXp){
			Constantes.efeitos.add(new TextoExtraXp(_xp,x,y) );
		}else {
			Constantes.efeitos.add(new Texto(_xp,x,y) );

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
		
		GerenciadorHud.xpHud=(xp);
		return fast;
		
	}
}
