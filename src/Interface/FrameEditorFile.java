package Interface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.ConsoleHandler;

import Map.Obstaculo;
import Map.WayPoint;
import Torre.Torre;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;
import Constantes.Constantes;
import Gerenciadores.GerenciadorObstaculos;


public class FrameEditorFile extends FrameBase {

	
private Color corBotaoMove = Color.white;

private String botaoSaveWay="Save wayPoint";

private String botaoSaveObs="Save Obstaculos";


private static BotaoTela SaveWay;
private static BotaoTela SaveObs;



	public FrameEditorFile(int x, int y, int sizeX, int sizeY, Color cor,int _tempoVida) {
		super(x-sizeX/2-5, y-sizeY-5, sizeX, sizeY, cor, _tempoVida);
		// TODO Auto-generated constructor 
		tempoTotalVida=(_tempoVida);
//			botoes=(criaBotoesStatusTorre());
		criaBotoes();
		frameDeTela=true;
		
	}
	
	
	@Override
	public void SimulaSe(int DiffTime) {
			super.SimulaSe(DiffTime);
			if (selecionado)
				timerVida=0;
			
//		SaveWay.X=X;
//		SaveWay.Y=Y+5;
//
//		SaveObs.X=X;
//		SaveObs.Y=Y+30;
		frameDeTela=true;

//		System.out.println("numero de obstaculos" + GerenciadorObstaculos.obstaculos.size());

		
	}
//@Override
//public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
//		super.DesenhaSe(dbg, XMundo, YMundo);
//}	

	private void criaBotoes() {
//		resetWay.X=X+320;
//		resetWay.Y=X-60;
//
//		EditWay.X=X;
//		EditWay.Y=Y-100;
//		
//
//		EditObs.X=X+160;
//		EditObs.Y=Y-100;
//		
//		resetObs.X=X+320;
//		resetObs.Y=X-60;
//		
		SaveWay = (new BotaoTela(null,botaoSaveWay,(int)X,(int)Y+5,60,10,false));
		SaveWay.sizeY=30;
		
		SaveObs = (new BotaoTela(null,botaoSaveObs,(int)X,(int)Y+35,60,10,false));
		SaveObs.sizeY=30;
//	
//
//		botoes.add(SaveObs);
//		botoes.add(SaveWay);

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
		else if (b.name.contains(botaoSaveWay) ) {
			GerenciadorObstaculos.saveWayPointInFile();

		

		}
		
	}


}
