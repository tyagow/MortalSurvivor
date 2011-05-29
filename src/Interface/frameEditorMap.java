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


public class frameEditorMap extends FrameBase {

	
BufferedImage img;
	
int mX =-1;
int mY =-1;
int sizeMx=32;
int sizeMy=32;

private boolean moveMenu;

private Obstaculo obs;

private Color corBotaoMove = Color.white;

private String botaoFile="file";

private String botaoWayPoint="WayPoint";

private String botaoObstaculo="Obstaculo";

private double frameX;

private static BotaoTela file,wayPoint,obstaculo;

private static FrameEditorFile frameFile;
private static FrameEditorObstaculos frameObstaculos;
private static FrameEditorWayPoint frameWayPoint;


	public frameEditorMap(int x, int y, int sizeX, int sizeY, Color cor,int _tempoVida,BufferedImage charset) {
		super(x-sizeX/2-5, y-sizeY-5, sizeX, sizeY, cor, _tempoVida);
		img = charset;
		tempoTotalVida=(_tempoVida);
		
		criaBotoes();
		
		frameFile = new FrameEditorFile((int)X+65, (int)Y, 60, 55, Color.darkGray, 800);
		frameFile.frameDeTela=false;
		
		frameObstaculos = new FrameEditorObstaculos((int)X+185, (int)Y-50, 60, 70, Color.darkGray, 800);
		frameObstaculos.alpha=200;
		
		frameWayPoint = new FrameEditorWayPoint((int)X+125, (int)Y-50, 60, 70, Color.darkGray, 800);
		frameWayPoint.alpha=200;

	}
	
	
	@Override
	public void SimulaSe(int DiffTime) {
		super.SimulaSe(DiffTime);

		file.X=X+30;
		file.Y=Y-20;	
		wayPoint.X=X+90;
		wayPoint.Y=Y-20;	
		obstaculo.X=X+150;
		obstaculo.Y=Y-20;

		Constantes.miraDoJogo = false;
		
		if (frameAtivo!=null) {

	
			
			frameAtivo.X=X+frameX;
			frameAtivo.Y=Y;
			
			if (frameAtivo.timerVida>=frameAtivo.tempoTotalVida) {
				frameAtivo.timerVida=0;
				frameAtivo=null;
			}
		}

	}

	

	@Override
	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {

		dbg.setColor(new Color(r,g,b,150));
		
		dbg.fillRect((int)getX()-25,(int) (getY())-25, getSizeX()+50,getSizeY()+50);
		dbg.drawImage(img, (int)X, (int)Y, null);
		dbg.setColor(corBotaoMove );
		dbg.drawRect((int)getX()-10,(int) (getY()-10), 8,8);
		
		if (mX!=-1 &&mY!=-1) {
			dbg.setColor(Color.white);
			dbg.drawRect((int)X+mX*32, (int)Y+mY*32, 32, 32);
		}

			dbg.setColor(Color.red);
	
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

	}
	
	private void trataColisaoMenu(MouseEvent e) {
		
		mX = ((e.getX()-(int)X)/32);
		mY = ((e.getY()-(int)Y)/32);
		if (mY>img.getHeight()/32)
			mY=img.getHeight()/32;
		
		
		}


	private void criaBotoes() {
	
		file = (new BotaoTela(null,botaoFile,(int)X+30,(int)Y+5,60,10,false));
		file.sizeY=15;
		
		wayPoint = (new BotaoTela(null,botaoWayPoint,(int)X+110,(int)Y+5,60,10,false));
		wayPoint.sizeY=15;
		
		obstaculo = (new BotaoTela(null,botaoObstaculo,(int)X+190,(int)Y+5,60,10,false));
		obstaculo.sizeY=15;

		botoes.add(file);
		botoes.add(obstaculo);
		botoes.add(wayPoint);

	}
	@Override
	public void mouseMoved(MouseEvent e) {

		if (Constantes.colideQuadrado((int)getX(),(int)getY(),getSizeX()-1,getSizeY()-1,(int)e.getX(),(int) e.getY(),1,1 )) {
			selecionado=true;

		}
		else {
			
			selecionado=false;
		}
	
		if (frameAtivo!=null)
			frameAtivo.mouseMoved(e);
			
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mouseMoved(e);
	
		
		}

	}
	@Override
	public void mouseReleased(MouseEvent e) {
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
		
		if (frameAtivo!=null)
			frameAtivo.mouseReleased(e);
	
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

	for (int i=0;i<botoes.size();i++) {	
			botoes.get(i).mouseClicked(e);	
		}
	
	if (frameAtivo!=null)
		frameAtivo.mouseClicked(e);
	
	}

	@Override
	public void mousePressed(MouseEvent e) {

		trataMousePressed(e);
		
		if (frameAtivo!=null)
			frameAtivo.mousePressed(e);

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
	
		return	tentaApagarWay(x,y);
	
	}
	
	
	private boolean tentaApagarWay(int x, int y) {

		for (int i = 0 ;i<Constantes.wayPoints.size();i++) {
			
			if (Constantes.colidecircular(x+Constantes.XTela, y+Constantes.YTela, 2,(int)Constantes.wayPoints.get(i).X ,(int)Constantes.wayPoints.get(i).Y,Constantes.wayPoints.get(i).sizeX/2)) {
				
				Constantes.wayPoints.remove(i);
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
			obs.X = e.getX()+obs.sizeX/2-16+Constantes.XTela;
			obs.Y = e.getY()+obs.sizeY/2-16+Constantes.YTela;
		}
		if (frameAtivo!=null)
			frameAtivo.mouseDragged(e);
	}


	@Override
	protected void trataBotao(Botao b) {

		if (b.name.contains(botaoFile) ) {

				if (frameAtivo!=null)
					frameAtivo=null;
				else {

					frameAtivo=frameFile;
					frameX=frameAtivo.X-X;
				}
				
			
		}
		else if (b.name.contains(botaoWayPoint) ) {
			if (frameAtivo!=null)
				frameAtivo=null;
			else {

				frameAtivo=frameWayPoint;
				frameX=frameAtivo.X-X;

			}
			
		}	else if (b.name.contains(botaoObstaculo) ) {

			if (frameAtivo!=null)
				frameAtivo=null;
			else {

				frameAtivo=frameObstaculos;
				frameX=frameAtivo.X-X;

			}
	
			
		}
	}


}