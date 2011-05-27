package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import Canvas.CanvasGame;
import Data.Imagem;
import Efeitos.Texto;
import GameState.GamePanel;
import Gerenciadores.GerenciadorJogo;


public class FrameChooseLevel extends FrameBase {

	
	public FrameChooseLevel(int _x, int _y, int sizeX, int sizeY, Color cor, int _tempoVida) {
		super(_x, _y, sizeX, sizeY, cor, _tempoVida);

		criaBotoes();
		alpha=100;
		
		
	}
	

		



	@Override
	protected void trataBotao(Botao b) {
		// TODO Auto-generated method stub

		if (b.name.contains("Mapa Um") ) {
			
			GamePanel.canvasAtivo=(CanvasGame.instance);
//		
//			if (!frames.contains(frameVideo)) {
//				frameVideo.ativo=true;
//				frames.add(frameVideo);
//			}	
//				else {
//					frameVideo.ativo=false;
//				}
				
			
	
		}
		else if (b.name.contains("Mapa Dois") ) {
			
			GamePanel.canvasAtivo=(CanvasGame.instance);

			
		}
		else if (b.name.contains("Game") ) {
			

//			if (!frames.contains(frameGame)) {
//				frameGame.ativo=true;
//				frames.add(frameGame);
//			}	
//				else {
//					frameGame.ativo=false;
//				}
				
			
		}
		
	}

	private void criaBotoes() {
		// TODO Auto-generated method stubB
//
		botoes.add(new Botao(Imagem.fundoCidade, "Mapa Um",(int)X+165,(int)Y+80,180,18,false));
		botoes.add(new Botao(Imagem.fundoCidade, "Mapa Dois",(int)X+165,(int)Y+330,180,18,false));

//		botoes.add(new Botao(null,"Som",(int)X+130+5,(int)Y+30,90,18,false));
//		botoes.add(new Botao(null,"Game",(int)X+230+5,(int)Y+30,90,18,false));
//		getBotoes().add(new Botao(null,"",(int)getX()+10,(int)getY()+110,120,25,false));
//		getBotoes().add(new Botao(null,"Options",(int)getX()+10,(int)getY()+160,120,25,false));
//		getBotoes().add(new Botao(null,"Exit",(int)getX()+10,(int)getY()+210,120,25,false));
		
		
	}






}
