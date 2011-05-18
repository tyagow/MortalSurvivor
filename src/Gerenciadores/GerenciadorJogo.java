package Gerenciadores;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;
import Constantes.Constantes;
import GameState.GamePanel;
import Interface.FramePause;

public class GerenciadorJogo extends Objeto  {

	FramePause framePause;
	public static float velocidadeJogo;
	
	public GerenciadorJogo(){
		
		inicializaFramePause();
		
		
		
		
	}
	
	private void inicializaFramePause() {
		int _sizeX= GamePanel.PWIDTH/2;
		int _sizeY= GamePanel.PHEIGHT;
		
		int _x= GamePanel.PWIDTH/2 + Constantes.XTela - _sizeX/2;
		int _y= 0;
		
		
		framePause= new FramePause(_x, _y, _sizeX, _sizeY, Color.DARK_GRAY, -1);		
	}

	public void SimulaSe(int DiffTime, float _velocidadeJogo2) {
		velocidadeJogo=_velocidadeJogo2;
		if (velocidadeJogo == 0) {
			framePause.SimulaSe(DiffTime);
			
		}
		trataMiraMouse();
		
		
	}

	private void trataMiraMouse() {
		
		if (Constantes.miraDoJogo)
			CanvasGame.setMiraJogo();
		else {
			CanvasGame.setMiraMenu();

		}
		Constantes.miraDoJogo=true;
			
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		if (velocidadeJogo == 0) {
			framePause.DesenhaSe(dbg, 0, 0);
		}
		
		
	}



	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE){
			
			CanvasGame.velocidadeJogo = 0;
			//inicializaFramePause();
			
		}
		
		if (velocidadeJogo == 0) {
			framePause.keyPressed(e);
		}
	}

	
	public void keyReleased(KeyEvent e) {
		if (velocidadeJogo == 0) {
			framePause.keyReleased(e);
		}
		
	}

	public void mouseMoved(MouseEvent e) {
		if (velocidadeJogo == 0) {
			framePause.mouseMoved(e);
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (velocidadeJogo == 0) {
			framePause.mouseDragged(e);
		}		
	}

	
	public void mouseReleased(MouseEvent e) {
		if (velocidadeJogo == 0) {
			framePause.mouseReleased(e);
		}			
	}

	
	public void mousePressed(MouseEvent e) {
		if (velocidadeJogo == 0) {
			framePause.mousePressed(e);
		}		
	}

	

	
	public void mouseClicked(MouseEvent e) {
		if (velocidadeJogo == 0) {
			framePause.mouseClicked(e);
		}		
	}

	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
