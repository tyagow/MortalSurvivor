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

	
	private Botao botaoMapaUm;
	private Botao botaoMapaDois;

	public FrameChooseLevel(int _x, int _y, int sizeX, int sizeY, Color cor, int _tempoVida) {
		super(_x, _y, sizeX, sizeY, cor, _tempoVida);

		criaBotoes();
		alpha=100;
		
		
	}

	@Override
	protected void trataBotao(Botao b) {
		// TODO Auto-generated method stub

		if (b.name.contains("Mapa Um") ) {
			GerenciadorJogo.mapaSelecionado=0;

			
			
			GamePanel.canvasAtivo=(new CanvasGame());
			GerenciadorJogo.carregaMapa(0);
			

			
	
		}
		else if (b.name.contains("Mapa Dois") ) {
			GerenciadorJogo.mapaSelecionado=1;
			GamePanel.canvasAtivo=(new CanvasGame());
			GerenciadorJogo.carregaMapa(1);

			
		}
		else if (b.name.contains("Game") ) {
					
			
		}
		
	}

	private void criaBotoes() {
		// TODO Auto-generated method stubB
//
		botaoMapaUm=new Botao(Imagem.mapaUm, "Mapa Um",(int)X+70,(int)Y+60,150,18,false);
		botaoMapaUm.sizeY=120;
		botoes.add(botaoMapaUm);
		
		botaoMapaDois=new Botao(Imagem.mapaDois, "Mapa Dois",(int)X+70,(int)Y+250,150,18,false);
		botaoMapaDois.sizeY=120;

		botoes.add(botaoMapaDois);

	}






}
