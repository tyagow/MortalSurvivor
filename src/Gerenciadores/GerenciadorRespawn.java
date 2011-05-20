package Gerenciadores;

import java.awt.Color;
import java.awt.Graphics2D;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;
import Constantes.Constantes;
import GameState.GamePanel;
import Map.Obstaculo;


public class GerenciadorRespawn extends Objeto {

	int timerRespawn=0;
	int tempoDeRespawn=2000;
	private static boolean respawn=false;
	public boolean verificaColisaoObstaculo() {
		for (int i =0; i < GerenciadorObstaculos.obstaculos.size();i++) {
			Obstaculo ob = GerenciadorObstaculos.obstaculos.get(i);
			if (Constantes.colidecircular(X, Y, sizeX/2, ob.X, ob.Y, ob.sizeX/2)) {
				return true;
			
			}			
		} 
				return false;
		
	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		if (CanvasGame.heroi.isVivo()==false) {
			setRespawn(true);
			CanvasGame.heroi.setX(9999);
			CanvasGame.heroi.setY(9999);
			timerRespawn+=DiffTime;
			
			if (timerRespawn>=tempoDeRespawn) {
				
				
				
				CanvasGame.heroi.respaw(260,400);
//				while (verificaColisaoObstaculo()) {
//					CanvasGame.heroi.respaw(GamePanel.rnd.nextInt(GamePanel.PWIDTH), GamePanel.rnd.nextInt(GamePanel.PHEIGHT));
//
//				}
				
				timerRespawn=0;
				setRespawn(false);
			}
			
		}
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		if (isRespawn()) {
	
			dbg.setColor(Color.black);
			dbg.fillRect(300, 300, timerRespawn*50/tempoDeRespawn, 20); // life*30/maximoVida
		}
			
			
	}

	public static void setRespawn(boolean respawn) {
		GerenciadorRespawn.respawn = respawn;
	}

	public static boolean isRespawn() {
		return respawn;
	}

}
