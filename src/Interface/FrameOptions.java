package Interface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;


public class FrameOptions extends FrameBase {
	FrameOptionsVideo frameVideo;
	FrameOptionsGame frameGame;
	public FrameOptions(int _x, int _y, int sizeX, int sizeY, Color cor, int _tempoVida) {
		super(_x, _y, sizeX, sizeY, cor, _tempoVida);
		frameGame=new  FrameOptionsGame(_x, _y+80, sizeX, sizeY-80, cor, _tempoVida);
		frameVideo=new FrameOptionsVideo(_x, _y+80, sizeX, sizeY-80, cor, _tempoVida);
		criaBotoes();
		alpha=100;
	}
	

	@Override
	protected void trataBotao(Botao b) {

		if (b.name.contains("Video") ) {
			frameAtivo=frameVideo;

	
		}
		else if (b.name.contains("Options") ) {
			
			
		}
		else if (b.name.contains("Game") ) {
			
			frameAtivo=frameGame;
	
			
		}
		
	}

	private void criaBotoes() {

		botoes.add(new Botao(null,"Video",(int)X+30,(int)Y+30,90,18,false));
		botoes.add(new Botao(null,"Som",(int)X+130+5,(int)Y+30,90,18,false));
		botoes.add(new Botao(null,"Game",(int)X+230+5,(int)Y+30,90,18,false));
	
	}






}
