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

import Canvas.CanvasGame;
import Constantes.Constantes;
import Gerenciadores.GerenciadorObstaculos;


public class MenuObstaculos extends FrameBase {

	private int timerSelecionado;
	
BufferedImage img;
	
int mX =-1;
int mY =-1;
int sizeMx=32;
int sizeMy=32;

private boolean moveMenu;

private Obstaculo obs;

private Color corBotaoMove = Color.white;
	
	public MenuObstaculos(int x, int y, int sizeX, int sizeY, Color cor,int _tempoVida,BufferedImage charset) {
		super(x-sizeX/2-5, y-sizeY-5, sizeX, sizeY, cor, _tempoVida);
		img = charset;
		// TODO Auto-generated constructor 
		timerSelecionado=(0);
		tempoVida=(_tempoVida);
//			botoes=(criaBotoesStatusTorre());
		
	}
	
	
	@Override
	public void SimulaSe(int DiffTime) {
	
		timerSelecionado += DiffTime;
		CanvasGame.setMiraMenu();
		if (selecionado) {
			timerSelecionado=0;
		}
		
		if (tempoVida != -1 && timerSelecionado >= tempoVida+10000) {
			
			vivo=(false);
			timerSelecionado=(0);
		}
		Iterator<Botao> it = botoes.iterator();
		while(it.hasNext()){
			Botao bt = it.next();
			bt.SimulaSe((int)DiffTime);


			if (bt.ativo==true) {
//				trataBotao(bt);
			}
		}
		
	
		
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

		//desenha botoes
		Iterator<Botao> it = botoes.iterator();
		while(it.hasNext()){
			Botao bt = it.next();
			bt.DesenhaSe(dbg, xMundo, yMundo);
		

		}
		dbg.setColor(Color.black);
		if (obs !=null) {
			obs.DesenhaSe(dbg, xMundo, yMundo);
			dbg.setColor(Color.white);
			dbg.drawRect((int)((obs.X-10)/16)*16-xMundo,(int) ((obs.Y-10)/16)*16-yMundo, obs.sizeX, obs.sizeY);
		}
	
		
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


	private ArrayList<Botao> criaBotoesStatusTorre() {
		// TODO Auto-generated method stub
		ArrayList<Botao>aux = new ArrayList<Botao>();
		
	
		aux.add(new Botao(null,"range",(int)getX()+10,(int)getY()+10,40,12,false));
		aux.add(new Botao(null,"fire",(int)getX()+10,(int)getY()+35,40,12,false));
		aux.add(new Botao(null,"dano",(int)getX()+10,(int)getY()+60,40,12,false));
		
		return aux;
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
			
			botoes.get(i).mousex = e.getX()+Constantes.XTela;

			botoes.get(i).mousey = e.getY()+Constantes.YTela;
			
		
		}

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		moveMenu=false;
		corBotaoMove=Color.white;
		if (obs !=null) {
			if (mX == 0 && mY == 0) {
				Constantes.wayPoints.add(new WayPoint(e.getX()+Constantes.XTela, e.getY()+Constantes.YTela, 16, 16));
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

		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		trataMousePressed(e);
		
	}



	



	private void trataMousePressed(MouseEvent e) {
 
		
		if (Constantes.colidecircular(X-10, Y-10, 4, e.getX(),e.getY(), 4)) {
			moveMenu=true;
			corBotaoMove=Color.blue;
		}else if (selecionado) {
			trataColisaoMenu(e);
		}else {
			if (mX>=0&&mY>=0) {
			
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
	protected void trataBotao(Botao b2) {
		// TODO Auto-generated method stub
		
	}


}
