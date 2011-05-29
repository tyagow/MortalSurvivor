package Interface;

import java.awt.Color;
import java.awt.event.MouseEvent;
import Constantes.Constantes;
import Gerenciadores.GerenciadorObstaculos;


public class FrameEditorObstaculos extends FrameBase {

	

private String botaoSaveObs="Save";
private String botaoEditObs="Edit";
private String botaoResetObs="Reset";
private String botaoDelLast="Delete";

private static BotaoTela saveObs;
private static BotaoTela editObs;
private static BotaoTela resetObs;
private static BotaoTela delLast;

public FrameEditorObstaculos(int x, int y, int sizeX, int sizeY, Color cor,int _tempoVida) {
		super(x-sizeX/2-5, y-sizeY-5, sizeX, sizeY, cor, _tempoVida);
		
		tempoTotalVida=(_tempoVida);
		criaBotoes();
		frameDeTela=true;
		
	}
	
	
	@Override
	public void SimulaSe(int DiffTime) {
			super.SimulaSe(DiffTime);
		
		if (selecionado)
			timerVida=0;
		
		saveObs.X=X;
		saveObs.Y=Y+5;
				
		editObs.X=X;
		editObs.Y=Y+20;
		
		resetObs.X=X;
		resetObs.Y=Y+35;
		
		delLast.X=X;
		delLast.Y=Y+50;
		
		frameDeTela=true;

	}

	private void criaBotoes() {

		resetObs = (new BotaoTela(null,botaoResetObs,(int)X,(int)Y+35,60,10,false));
		resetObs.sizeY=15;
		
		
		editObs = (new BotaoTela(null,botaoEditObs,(int)X,(int)Y+20,60,10,false));
		editObs.sizeY=15;
	
		saveObs = (new BotaoTela(null,botaoSaveObs,(int)X,(int)Y+5,60,10,false));
		saveObs.sizeY=15;
	
		delLast= (new BotaoTela(null,botaoDelLast,(int)X,(int)Y+50,60,10,false));
		delLast.sizeY=15;
	
		botoes.add(delLast);
		botoes.add(saveObs);
		botoes.add(editObs);
		botoes.add(resetObs);
		

	}
	@Override
	public void mouseMoved(MouseEvent e) {

		
		if (Constantes.colideQuadrado((int)getX(),(int)getY(),getSizeX()-1,getSizeY()-1,(int)e.getX(),(int) e.getY(),1,1 )) {
			selecionado=true;
		}
		else {
			
			selecionado=false;
		}
	
			
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mouseMoved(e);


		}

	}

@Override
	protected void trataBotao(Botao b) {

		if (b.name.contains(botaoSaveObs) ) {
			GerenciadorObstaculos.saveObstaculosInFile();
	
		}
		else if (b.name.contains(botaoEditObs) ) {
			Constantes.editarObstaculo=true;
			Constantes.editarWay=false;
			
	
			
			
		}else if (b.name.contains(botaoResetObs) ) {
			GerenciadorObstaculos.obstaculos.clear();


		}
		else if (b.name.contains(botaoDelLast) ) {
			GerenciadorObstaculos.obstaculos.remove(GerenciadorObstaculos.obstaculos.size()-1);

		}
	
	}
		

}
