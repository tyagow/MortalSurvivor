package Interface;

import java.awt.Color;

import java.awt.event.MouseEvent;

import Constantes.Constantes;
import Gerenciadores.GerenciadorObstaculos;


public class FrameEditorWayPoint extends FrameBase {

private String botaoDelLast="Delete";
private String botaoSave="Save";
private String botaoEdit="Edit";
private String botaoReset="Reset";

private BotaoTela delLast;
private static BotaoTela Save;
private static BotaoTela edit;
private static BotaoTela reset;



	public FrameEditorWayPoint(int x, int y, int sizeX, int sizeY, Color cor,int _tempoVida) {
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
			
		Save.X=X;
		Save.Y=Y+5;
		
		edit.X=X;
		edit.Y=Y+20;
		
		reset.X=X;
		reset.Y=Y+35;
		
		delLast.X=X;
		delLast.Y=Y+50;

		frameDeTela=true;

		
	}


	private void criaBotoes() {

		reset = (new BotaoTela(null,botaoReset,(int)X,(int)Y+35,60,10,false));
		reset.sizeY=15;
			
		edit = (new BotaoTela(null,botaoEdit,(int)X,(int)Y+20,60,10,false));
		edit.sizeY=15;
		
		Save = (new BotaoTela(null,botaoSave,(int)X,(int)Y+5,60,10,false));
		Save.sizeY=15;
	
		delLast= (new BotaoTela(null,botaoDelLast,(int)X,(int)Y+50,60,10,false));
		delLast.sizeY=15;
	
		botoes.add(delLast);
		botoes.add(Save);
		botoes.add(edit);
		botoes.add(reset);
		
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

		if (b.name.contains(botaoSave) ) {
			GerenciadorObstaculos.saveWayPointInFile();
	
		}
		else if (b.name.contains(botaoEdit) ) {
			Constantes.editarObstaculo=false;
			Constantes.editarWay=true;
			
	
			
			
		}else if (b.name.contains(botaoReset) ) {
			Constantes.wayPoints.clear();


		}
		else if (b.name.contains(botaoDelLast) ) {
			Constantes.wayPoints.remove(Constantes.wayPoints.size()-1);

		}
		
	}
}