package Interface;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import AbstractClasses.Objeto;
import Constantes.Constantes;


public abstract class FrameBase extends Objeto {

	public boolean ativo;
	public int tempoVida;
	public Color cor;
	public ArrayList<FrameBase>frames= new ArrayList<FrameBase>();

	public int r,g,b,alpha;
	
	public ArrayList<Botao> botoes = new ArrayList<Botao>();
	public ArrayList<Objeto> objetos = new ArrayList<Objeto>();

	public boolean selecionado;
	public boolean frameDeTela=true;
	public FrameBase frameAtivo;
	
	public FrameBase (int _x,int _y,int _sizeX,int _sizeY, Color _cor, int _tempoVida) {
		
		X=(_x);
		Y=(_y);
		sizeX=(_sizeX);
		sizeY=(_sizeY);
		cor =_cor;
		vivo=(true);
		tempoVida=(_tempoVida);
		r=(cor.getRed());
		g=(cor.getGreen());
		b=(cor.getBlue());
		alpha=(100);
	}


	//private abstract  void trataBotao(Botao part);
	
	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
	
		dbg.setColor(Color.white);
	//	dbg.drawRect((int)getX()-XMundo,(int)getY()-YMundo, getSizeX(), getSizeY());

		if (!frameDeTela) {
			dbg.setColor(new Color(r,g,b,alpha));
			dbg.fillRect((int)X+1-XMundo,(int)Y-YMundo+1, sizeX-2, sizeY-2);
		
			Iterator<Botao> it = botoes.iterator();
			while(it.hasNext()){
				Botao bot= it.next();
				
				bot.DesenhaSe(dbg, XMundo, YMundo);
				
		
			
			}
			Iterator<Objeto> it3 = objetos.iterator();
			
			while(it3.hasNext()){
				Objeto ob= it3.next();
				
				ob.DesenhaSe(dbg, 0, 0);
				
		
			
			}
			if (frameAtivo!=null)
				frameAtivo.DesenhaSe(dbg, XMundo, YMundo);

			Iterator<FrameBase> it2 = frames.iterator();
	
			while(it2.hasNext()){
				FrameBase _frame= it2.next();
				
				_frame.DesenhaSe(dbg, XMundo, YMundo);
				
		
			
			}
		
		}else {
			
			
			dbg.setColor(new Color(r,g,b,alpha));
			dbg.fillRect((int)X+1,(int)Y+1, sizeX-2, sizeY-2);
		
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
				frameAtivo.DesenhaSe(dbg, XMundo, YMundo);

			
			Iterator<FrameBase> it2 = frames.iterator();
	
			while(it2.hasNext()){
				FrameBase _frame= it2.next();
				
				_frame.DesenhaSe(dbg, 0, 0);
				
		
			
			}
		
			
		}
		

	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		if(frameAtivo!=null) 
			frameAtivo.SimulaSe(DiffTime);
		
		for (int x=0;x<botoes.size();x++) {
			Botao b= botoes.get(x);
			
			b.SimulaSe((int)DiffTime);			
			if (b.ativo==true) {
				
				trataBotao(b);
				botoes.get(x).ativo=false;
			}		
		}
		
		for (int x=0;x<objetos.size();x++) {
			
			Objeto b= objetos.get(x);	
			b.SimulaSe((int)DiffTime);			
		
		}
		
		Iterator<FrameBase> it3 = frames.iterator();
		while(it3.hasNext()){
			FrameBase _frame= it3.next();
			
			_frame.SimulaSe(DiffTime);
			if (!_frame.ativo) {
				it3.remove();
				
			}
		}
		
		Constantes.miraDoJogo=false;
		frameDeTela=false;
		
		calculaIA(DiffTime);
		}
	
	
public void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub
		
	}


protected abstract void trataBotao(Botao b2);


public void mouseClicked(MouseEvent e) {
	if (frameAtivo!=null)
		frameAtivo.mouseClicked(e);
	
	for (int i=0;i<botoes.size();i++) {
		
		botoes.get(i).mouseClicked(e);
	}
	Iterator<FrameBase> it3 = frames.iterator();
	while(it3.hasNext()){
		FrameBase _frame= it3.next();
		
		_frame.mouseClicked(e);
	}
	
		
	}
	public void mousePressed(MouseEvent e) {
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mousePressed(e);
		}
		Iterator<FrameBase> it3 = frames.iterator();
		while(it3.hasNext()){
			FrameBase _frame= it3.next();
			
			_frame.mousePressed(e);
		}
			
	}
	public void mouseReleased(MouseEvent e) {
		
		if (frameAtivo!=null)
			frameAtivo.mouseReleased(e);
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mouseReleased(e);
		}
		Iterator<FrameBase> it3 = frames.iterator();
		while(it3.hasNext()){
			FrameBase _frame= it3.next();
			
			_frame.mouseReleased(e);
		}	
	}


public void mouseMoved(MouseEvent e) {
		if (frameAtivo!=null)
			frameAtivo.mouseMoved(e);
		
		if (Constantes.colideQuadrado((int)getX(),(int)getY(),getSizeX(),getSizeY(), (int)e.getX(),(int)e.getY() ,2,2 )) {
			selecionado=true;
			
		}
		else {
			selecionado=false;
		}
		
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mouseMoved(e);
		}
		
		Iterator<FrameBase> it3 = frames.iterator();
		while(it3.hasNext()){
			FrameBase _frame= it3.next();
			
			_frame.mouseMoved(e);
		}	
		
		
		
	
	}

public void mouseDragged(MouseEvent e) {
	// TODO Auto-generated method stub
	
}


public
void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	if (frameAtivo!=null)
		frameAtivo.keyPressed(e);
	
	int keyCode = e.getKeyCode();
	if(keyCode == KeyEvent.VK_0){
	}		
	if(keyCode == KeyEvent.VK_8){
	
	}	

	Iterator<FrameBase> it3 = frames.iterator();
	while(it3.hasNext()){
		FrameBase _frame= it3.next();
		
		_frame.keyPressed(e);
	}	
	
	

}


public void keyReleased(KeyEvent e ) {
	if (frameAtivo!=null)
		frameAtivo.keyReleased(e);
	int keyCode = e.getKeyCode();
	
	if(keyCode == KeyEvent.VK_F6){
		
	
	}
	
	Iterator<FrameBase> it3 = frames.iterator();
	while(it3.hasNext()){
		FrameBase _frame= it3.next();
		
		_frame.keyReleased(e);
	}	
}

	

}
