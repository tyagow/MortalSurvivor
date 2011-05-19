package Gerenciadores;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import shop.FrameShop;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;
import Constantes.Constantes;
import GameState.GamePanel;
import Interface.FrameBase;
import Interface.FramePause;

public class GerenciadorJogo extends Objeto  {

	static FramePause framePause;
	static FrameShop frameShop;
	public static FrameBase frameAtivo;
		
	public static float velocidadeJogo;
	
	public GerenciadorJogo(){
		
		inicializaFrames();
		
		
		
		
	}
	
	private void inicializaFrames() {
		int _sizeX= GamePanel.PWIDTH/2;
		int _sizeY= GamePanel.PHEIGHT;
		
		int _x= GamePanel.PWIDTH/2 + Constantes.XTela - _sizeX/2;
		int _y= 0;
		
		
		framePause= new FramePause(_x, _y, _sizeX, _sizeY, Color.DARK_GRAY, -1);	
		
		
		 _sizeX= GamePanel.PWIDTH;
		 _sizeY= GamePanel.PHEIGHT;
		
		 _x= 0;
		 _y= 0;
		
		
		 frameShop= new FrameShop(_x, _y, _sizeX, _sizeY, Color.darkGray, -1);	
		
		
		
	}

	public void SimulaSe(int DiffTime, float _velocidadeJogo2) {
		velocidadeJogo=_velocidadeJogo2;
		if (frameAtivo!=null) {
			frameAtivo.SimulaSe(DiffTime);
			
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
		if (frameAtivo!=null) {
			frameAtivo.DesenhaSe(dbg, 0, 0);
		}
		
		
	}



	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE){
			
			frameAtivo=framePause;
			CanvasGame.velocidadeJogo = 0;
			//inicializaFramePause();
			
		}
		
//		if(keyCode == KeyEvent.VK_SHIFT){
//			
//			frameAtivo=frameShop;
//			CanvasGame.velocidadeJogo = 0;
//			//inicializaFramePause();
//			
//		}
		
		if (frameAtivo!=null) {
			frameAtivo.keyPressed(e);
		}
	}

	
	public void keyReleased(KeyEvent e) {
		
		int keyCode = e.getKeyCode();

		if (frameAtivo!=null) {
			frameAtivo.keyReleased(e);
		}
		if(keyCode == KeyEvent.VK_SHIFT){
			if (frameAtivo==(FrameBase)frameShop) {
				frameAtivo=null;
				CanvasGame.velocidadeJogo = 1;
				velocidadeJogo=1;
			}else {
				frameAtivo=frameShop;
				CanvasGame.velocidadeJogo = 0;
				velocidadeJogo=0;
			}
				
		}
	}

	public void mouseMoved(MouseEvent e) {
		if (frameAtivo!=null) {
			frameAtivo.mouseMoved(e);
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (frameAtivo!=null) {
			frameAtivo.mouseDragged(e);
		}		
	}

	
	public void mouseReleased(MouseEvent e) {
		if (frameAtivo!=null) {
			frameAtivo.mouseReleased(e);
		}			
	}

	
	public void mousePressed(MouseEvent e) {
		if (frameAtivo!=null) {
			frameAtivo.mousePressed(e);
		}		
	}

	

	
	public void mouseClicked(MouseEvent e) {
		if (frameAtivo!=null) {
			frameAtivo.mouseClicked(e);
		}		
	}

	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
