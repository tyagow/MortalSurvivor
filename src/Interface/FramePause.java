package Interface;

import java.awt.Color;

import Canvas.CanvasGame;
import Constantes.Constantes;
import GameState.GamePanel;
import Gerenciadores.GerenciadorJogo;

public class FramePause extends FrameBase {

	public FramePause(int _x, int _y, int _sizeX, int _sizeY, Color _cor,
			int _tempoVida) {
		super(_x, _y, _sizeX, _sizeY, _cor, _tempoVida);

		criaBotoes();
		alpha=200;
	}

	
	@Override
	public void calculaIA(int DiffTime) {
		frameDeTela=true;
//		X=Constantes.XTela+GamePanel.PWIDTH/2-sizeX/2;
//		Y=Constantes.YTela;
		
		
	}

	private void criaBotoes() {
		// TODO Auto-generated method stubB
		
		int sizeButton = 200;
		
		botoes.add(new BotaoMenuPause(null,"recomecar",(int)X+sizeX/2-sizeButton,(int)Y+100,200,24,false));
		
		botoes.add(new BotaoMenuPause(null,"voltar",(int)X+sizeX/2-sizeButton,(int)Y+210,200,24,false));
		
		botoes.add(new BotaoMenuPause(null,"main",(int)X+sizeX/2-sizeButton,(int)Y+320,200,24,false));
		
		botoes.add(new BotaoMenuPause(null,"exit",(int)X+sizeX/2-sizeButton,(int)Y+420,200,24,false));

		
		
	}
	@Override
	protected void trataBotao(Botao b) {
		
		if (b.name.contains("voltar") ) {
				CanvasGame.velocidadeJogo = 1;
				GerenciadorJogo.velocidadeJogo = 1;
	
		}
		else if (b.name.contains("recomecar") ) {
			GamePanel.canvasAtivo=(new CanvasGame());			
		}
		else if (b.name.contains("main") ) {
			System.out.println("main");

			
		}
		else if (b.name.contains("exit") ) {
			
			System.out.println("exit");
			System.exit(0);

		}
	}


}
