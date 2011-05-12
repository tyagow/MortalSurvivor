package Base;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.EventListener;

import org.w3c.dom.events.Event;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;
import Constantes.Constantes;
import GameState.GamePanel;
import Gerenciadores.GerenciadorObstaculos;


public class Base extends Objeto {

	boolean auraAtiva;
	double timerAura;
	int xAura, yAura, alpha;
	boolean soma = true;

	BufferedImage img;
	
	public Base(int _x, int _y, BufferedImage _img){

	
		setSizeX((_img.getWidth()*16)/16);
		setSizeY((_img.getHeight()*16)/16);
		X=(_x/16)*16;
		Y=(_y/16)*16;	
	
		img = _img;
		alpha = 0;

		vivo=true;
		life=Constantes.BASE_LIFE_1;
		GerenciadorObstaculos.addObstaculos((int)(X),(int)(Y),getSizeX(),getSizeY(),0,0 );
		
		auraAtiva = true;
		timerAura = 0;
		xAura = (int)X - Constantes.AURA_BASE_DIAMETRO/2;
		yAura = (int)Y - Constantes.AURA_BASE_DIAMETRO/2;
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		if(!auraAtiva){
			timerAura += DiffTime;
		}
		
		if(timerAura >= Constantes.AURA_BASE_TIMER){
			auraAtiva = true;
			timerAura = 0;
		}
		
		if(auraAtiva && Constantes.colidecircular(xAura + Constantes.AURA_BASE_DIAMETRO/2, yAura + Constantes.AURA_BASE_DIAMETRO/2, Constantes.AURA_BASE_DIAMETRO/2, CanvasGame.heroi.X, CanvasGame.heroi.Y, CanvasGame.heroi.getSizeX()/2)){
			//System.out.println("colidiu � noiz");
			CanvasGame.heroi.recarregaArmas();
			auraAtiva = false;
		}
					
		if (getLife() <0)
			setLife(0);
	}
	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		
		
		Color corAnterior = dbg.getColor();
		//LIFE
		dbg.drawRect((int)GamePanel.PWIDTH/2-150, (int)5, 300, 25);
		dbg.setColor(Color.lightGray);
		dbg.fillRect((int)GamePanel.PWIDTH/2-149, (int)6, (int)(life*300/Constantes.BASE_LIFE_1)-2, 23);
		//BASE
		dbg.setColor(Color.ORANGE);
			//dbg.fillOval((int)X-getSizeX()/2-XMundo,(int) (-getSizeY()/2-YMundo), getSizeX(),getSizeY());
		dbg.drawImage(img, null, (int)X-getSizeX()/2-XMundo,(int) (Y-getSizeY()/2-YMundo));
		//AURA
		if(auraAtiva){
			Color c = new Color(0, 0, 255, alpha);
			dbg.setColor(c);
			dbg.drawOval(xAura - XMundo, yAura - YMundo, Constantes.AURA_BASE_DIAMETRO, Constantes.AURA_BASE_DIAMETRO);
						
			if(alpha >= 255){
				soma = false;
			}
			
			if(alpha <= 0){
				soma = true;
			}
			
			if(soma){
				alpha++;
			}else{
				alpha--;
			}
			
			
		}
		dbg.setColor(corAnterior);
		

	}


}