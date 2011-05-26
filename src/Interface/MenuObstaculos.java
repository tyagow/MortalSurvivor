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


public class MenuObstaculos extends FrameBase {

	
BufferedImage img;
	
int mX =-1;
int mY =-1;
int sizeMx=32;
int sizeMy=32;

private boolean moveMenu;

private Obstaculo obs;

private Color corBotaoMove = Color.white;

private String botaoSaveWay="Save wayPoint";

private String botaoSaveObs="Save Obstaculos";

private String botaoEditWay="Edit way";

private String botaoEditObs="Edit Obs";

private String botaoResetWay="Reset way";

private String botaoResetObs="Reset Obs";

private static BotaoTela SaveWay;

private static BotaoTela SaveObs;
private static BotaoTela EditWay;

private static BotaoTela EditObs;

private static BotaoTela resetWay;

private static BotaoTela resetObs;



	public MenuObstaculos(int x, int y, int sizeX, int sizeY, Color cor,int _tempoVida,BufferedImage charset) {
		super(x-sizeX/2-5, y-sizeY-5, sizeX, sizeY, cor, _tempoVida);
		img = charset;
		// TODO Auto-generated constructor 
		tempoVida=(_tempoVida);
//			botoes=(criaBotoesStatusTorre());
		criaBotoes();
		
	}
	
	
	@Override
	public void SimulaSe(int DiffTime) {
		super.SimulaSe(DiffTime);
		
		SaveWay.X=X;
		SaveWay.Y=Y-60;
		
		resetWay.X=X+320;
		resetWay.Y=Y-60;

		EditWay.X=X;
		EditWay.Y=Y-100;
		

		EditObs.X=X+160;
		EditObs.Y=Y-100;
		
		resetObs.X=X+480;
		resetObs.Y=Y-60;
		
		SaveObs.X=X+160;
		SaveObs.Y=Y-60;
		Constantes.miraDoJogo = false;
//		System.out.println("numero de obstaculos" + GerenciadorObstaculos.obstaculos.size());

		
	}

	

	@Override
	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {
		// TODO Auto-generated method stub
		
		//desenha menu
	
	
		dbg.setColor(new Color(r,g,b,150));
		
		dbg.fillRect((int)getX()-25,(int) (getY())-25, getSizeX()+50,getSizeY()+50);
		dbg.drawImage(img, (int)X, (int)Y, null);
		dbg.setColor(corBotaoMove );
		dbg.drawRect((int)getX()-10,(int) (getY()-10), 8,8);
		
		if (mX!=-1 &&mY!=-1) {
			dbg.setColor(Color.white);
			dbg.drawRect((int)X+mX*32, (int)Y+mY*32, 32, 32);
		}
		
		// range
			dbg.setColor(Color.red);
			//dbg.drawOval((int)getX()-torrePai.getRange()/2-xMundo, (int)getY()-torrePai.getRange()/2-yMundo, torrePai.getRange(), torrePai.getRange());

//		desenha botoes
//			dbg.setColor(new Color(r,g,b,alpha));
//			dbg.fillRect((int)X+1,(int)Y+1, sizeX-2, sizeY-2);
		
			Iterator<Botao> it = botoes.iterator();
			while(it.hasNext()){
				Botao bot= it.next();
				
				bot.DesenhaSe(dbg, 0, 0);
				
		
			
			}
			
			
		Iterator<Objeto> it3 = objetos.iterator();
			
			while(it3.hasNext()){
				Objeto ob= it3.next();
				
				ob.DesenhaSe(dbg, 0, 0);
				
		
			
			}
			if(frameAtivo!=null) 
				frameAtivo.DesenhaSe(dbg, xMundo, yMundo);

			
			Iterator<FrameBase> it2 = frames.iterator();
	
			while(it2.hasNext()){
				FrameBase _frame= it2.next();
				
				_frame.DesenhaSe(dbg, 0, 0);
				
		
			
			}
		dbg.setColor(Color.black);
		
		if (obs !=null&&Constantes.editarObstaculo) {
			obs.DesenhaSe(dbg, xMundo, yMundo);
			dbg.setColor(Color.white);
			dbg.drawRect((int)((obs.X-10)/16)*16-xMundo,(int) ((obs.Y-10)/16)*16-yMundo, obs.sizeX, obs.sizeY);
		}
		dbg.drawString(Constantes.wayPoints.size()+"", (int)X+16, (int)Y+32);
		
		//dbg.drawString(torrePai., x, y)
		
	}
	
	private void trataColisaoMenu(MouseEvent e) {
		
		
//		int auxX = (int)(e.getX()-X );
//		int auxY = (int)(e.getY()-Y );
		
		mX = ((e.getX()-(int)X)/32);
		mY = ((e.getY()-(int)Y)/32);
		if (mY>1)
			mY=1;
		
		
		}


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
		SaveWay = (new BotaoTela(null,botaoSaveWay,(int)X,(int)Y-80,160,16,false));
		SaveWay.sizeY=30;
		
		SaveObs = (new BotaoTela(null,botaoSaveObs,(int)X+160,(int)Y-80,160,16,false));
		SaveObs.sizeY=30;
		
		resetObs = (new BotaoTela(null,botaoResetObs,(int)X+320,(int)Y-60,160,16,false));
		resetObs.sizeY=30;
		
		
		
		
		EditWay=new BotaoTela(null,botaoEditWay,(int)X,(int)Y-80,160,16,false);
		EditWay.sizeY=30;
		EditObs=new BotaoTela(null,botaoEditObs,(int)X,(int)Y-80,160,16,false);
		EditObs.sizeY=30;
		
		resetWay = (new BotaoTela(null,botaoResetWay,(int)X+480,(int)Y-60,160,16,false));
		resetWay.sizeY=30;
		
		
		
		botoes.add(EditObs);
		botoes.add(EditWay);
		botoes.add(SaveObs);
		botoes.add(SaveWay);
		botoes.add(resetObs);
		botoes.add(resetWay);
		
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {

		
		if (Constantes.colideQuadrado((int)getX(),(int)getY(),getSizeX()-1,getSizeY()-1,(int)e.getX(),(int) e.getY(),1,1 )) {
			selecionado=true;
//			trataColisaoMenu(e);
		}
		else {
			
			selecionado=false;
		}
	
			
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mouseMoved(e);

			
		
		}

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		boolean aux =true;

		for (int i=0;i<botoes.size();i++) {
			if (botoes.get(i).selecionado) {
				aux = false;
				break;
			}			
		}
		if (aux) {
			moveMenu=false;
			
			if (Constantes.editarObstaculo) {
				tentaCriarObstaculo(e);
			}
		}
	
	}

	private void tentaCriarObstaculo(MouseEvent e) {
		moveMenu=false;
		corBotaoMove=Color.white;
		if (obs !=null) {
			if (mX == 0 && mY == 0) {
				
				int i = Constantes.wayPoints.size();
				
				WayPoint  aux  = new WayPoint(e.getX()+Constantes.XTela+16, e.getY()+Constantes.YTela+16, 32, 32);
				aux.index=i;
				aux.indexNextTarget=-1;
				aux.X = ((int)(aux.X+5)/16)*16;

				aux.Y = ((int)(aux.Y+5)/16)*16;
				
				Constantes.wayPoints.add(aux);
			
				
			}else {
				obs.X = ((int)(obs.X+5)/16)*16;

				obs.Y = ((int)(obs.Y+5)/16)*16;
				GerenciadorObstaculos.obstaculos.add (obs );
			}
			
			obs=null;
		}		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mouseClicked(e);

			
		
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		trataMousePressed(e);
		
	}

	private void trataMousePressed(MouseEvent e) {
		boolean aux = true;
		for (int i=0;i<botoes.size();i++) {
			if (botoes.get(i).selecionado) {
				aux = false;
				break;
			}			
		}
		if (aux) {
			
			if (Constantes.colidecircular(X-10, Y-10, 4, e.getX(),e.getY(), 4)) {
				moveMenu=true;
				corBotaoMove=Color.blue;
			}else if (selecionado) {
				trataColisaoMenu(e);
			}else {
				
				if (mX>=0&&mY>=0&&Constantes.editarObstaculo) {
				
					if (e.getButton() == MouseEvent.BUTTON1)
						obs = new Obstaculo(e.getX()+Constantes.XTela, e.getY()+Constantes.YTela, 32, 32, mX, mY);
					else {
						
					if (tentaApagarObstaculo(e.getX(),e.getY())) {
						System.out.println("Obstaculo Apagado");
					}else {
						System.out.println("Obstaculo nao Apagado");
	
					}
					
					}
				}
			}
		
		}
	}

	private boolean tentaApagarObstaculo(int x, int y) {

		for (int i = 0 ;i<GerenciadorObstaculos.obstaculos.size();i++) {
			
			if (Constantes.colidecircular(x+Constantes.XTela, y+Constantes.YTela, 2,(int)GerenciadorObstaculos.obstaculos.get(i).X ,(int)GerenciadorObstaculos.obstaculos.get(i).Y,GerenciadorObstaculos.obstaculos.get(i).sizeX/2)) {
				
				GerenciadorObstaculos.obstaculos.remove(i);
				return true;
			}
			
			
			
		}
		return false;
	
	}
	


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (moveMenu) {
			X=e.getX();
			Y=e.getY();
		}
		if (obs!=null) {
			obs.X = e.getX()+obs.sizeX/2+Constantes.XTela;
			obs.Y = e.getY()+obs.sizeY/2+Constantes.YTela;
		}
			
	}


	@Override
	protected void trataBotao(Botao b) {

		if (b.name.contains(botaoSaveObs) ) {
			GerenciadorObstaculos.saveObstaculosInFile();
	
		}
		else if (b.name.contains(botaoSaveWay) ) {
			GerenciadorObstaculos.saveWayPointInFile();

			
		}	else if (b.name.contains(botaoEditObs) ) {
			Constantes.editarObstaculo=true;
			Constantes.editarWay=false;
			
	
			
			
		}	else if (b.name.contains(botaoEditWay) ) {
			Constantes.editarObstaculo=false;
			Constantes.editarWay=true;


		}else if (b.name.contains(botaoResetWay) ) {
			Constantes.wayPoints.clear();

		}else if (b.name.contains(botaoResetObs) ) {
			GerenciadorObstaculos.obstaculos.clear();


		}
		
	}


}
