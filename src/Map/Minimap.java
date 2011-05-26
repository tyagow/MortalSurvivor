package Map;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;
import Constantes.Constantes;
import GameState.GamePanel;
import Gerenciadores.GerenciadorObstaculos;
import Gerenciadores.GerenciadorTorre;
import Personagem.Inimigo;
import Torre.Torre;


public class Minimap extends Objeto{


	private int tamanhoMiniMap = 6;
	public static float resizeX;
	public static float resizeY;
	
	public Minimap(){
		
		resizeX = (float) 40/(CanvasGame.tela.Largura);
		resizeY = (float) 40/(CanvasGame.tela.Altura);
//mapa em cima direita 
//		X= GamePanel.PWIDTH- (int)((resizeX * CanvasGame.largura)/tamanhoMiniMap);
//		Y=0;
		// mapa em baixo esquerda
		X=(GamePanel.PWIDTH - (int)((resizeX * CanvasGame.largura)/tamanhoMiniMap));
		//Y=(GamePanel.PHEIGHT - (int)((resizeY * CanvasGame.altura)/tamanhoMiniMap));
		Y=0;
		
		sizeX=((int) (resizeX * CanvasGame.largura/tamanhoMiniMap));
		sizeY=((int) (resizeY * CanvasGame.altura/tamanhoMiniMap));
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
	//System.out.println(GerenciadorObstaculos.getObstaculos().size());

	}  

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		dbg.setColor(Color.GREEN);
		dbg.fillRect((int)getX(), (int)getY(), (int)getSizeX(), (int)getSizeY());
	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		dbg.setColor(Color.RED);
		dbg.drawLine((int)getX() + getSizeX()/2, (int)getY(),(int)getX() + getSizeX()/2,(int)getY() + getSizeY());
		dbg.drawLine((int)getX(), (int)getY() + getSizeY()/2,(int)getX() + getSizeX(),(int)getY() + getSizeY()/2);
		
//		for(int j = 0; j < GerenciadorDeRaids.getRaids().size(); j++){
			for(int i = 0; i < Constantes.inimigos.size(); i++){
				Inimigo inim = (Inimigo) Constantes.inimigos.get(i);
				
				if(inim.getX() >= 0 && inim.getX() <= CanvasGame.largura && inim.getY() >= 0 && inim.getY() < CanvasGame.altura){
					double xM = (resizeX * inim.getX()/tamanhoMiniMap) + this.getX();
					double yM = (resizeY * inim.getY()/tamanhoMiniMap) + this.getY();
					int lM = inim.getSizeX()/tamanhoMiniMap;
					int aM = inim.getSizeY()/tamanhoMiniMap;
					
				
					dbg.fillOval((int) xM-lM/2, (int) yM-aM/2, lM, aM);
				}
			}
//		}

			
			dbg.setColor(Color.yellow);
		for (int k=0;k<GerenciadorObstaculos.obstaculos.size();k++)
		{
			Obstaculo obs = GerenciadorObstaculos.obstaculos.get(k);
			double xM = (resizeX * obs.X/tamanhoMiniMap + this.X);
			double yM = (resizeY * obs.Y/tamanhoMiniMap + this.Y);
			int lM = (int)(resizeY * obs.sizeX/tamanhoMiniMap );
			int aM = (int)(resizeY * obs.sizeY/tamanhoMiniMap );

			dbg.fillRect((int)(xM-lM/2), (int)(yM-aM/2), lM+1, aM+1);
			
		}
		
		dbg.setColor(Color.green);

		for (int k=0;k<GerenciadorTorre.torres.size();k++)
		{
			Torre obs = GerenciadorTorre.torres.get(k);
			double xM = (resizeX * obs.X/tamanhoMiniMap + this.X);
			double yM = (resizeY * obs.Y/tamanhoMiniMap + this.Y);
			int lM = (int)(resizeY * obs.sizeX/tamanhoMiniMap );
			int aM = (int)(resizeY * obs.sizeY/tamanhoMiniMap );

			dbg.fillRect((int)(xM-lM/2), (int)(yM-aM/2), lM+1, aM+1);
			
		}
		
		
		dbg.setColor(Color.black);
		if ( CanvasGame.miraAtiva!=null)
			dbg.drawOval((int)(resizeX * CanvasGame.miraAtiva.getXMundo()/6+getX()), (int)(resizeY * CanvasGame.getMiraAtiva().getYMundo()/6+getY()), 2, 2);
		dbg.setColor(Color.red);

		if (CanvasGame.base!=null)
			dbg.fillRect((int)(resizeX * CanvasGame.base.X/6+X-CanvasGame.base.sizeX/2/6), (int)(resizeY * CanvasGame.base.Y/6+Y-CanvasGame.base.sizeY/2/6), CanvasGame.base.sizeX/6, CanvasGame.base.sizeY/6);
		
		
		dbg.fillOval((int)(resizeX * CanvasGame.heroi.X/6+X), (int)(resizeY * CanvasGame.heroi.Y/6+Y), 4, 4);
		
	}

}
