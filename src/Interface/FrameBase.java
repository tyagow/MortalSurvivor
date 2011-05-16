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
	LinkedList<FrameBase>menuAtivo= new LinkedList<FrameBase>();

	public int r,g,b,alpha;
	
	public ArrayList<Botao> botoes = new ArrayList<Botao>();
	public boolean selecionado;
	
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
		
		dbg.setColor(new Color(r,g,b,alpha));
		
		dbg.fillRect((int)getX()+1-XMundo,(int)getY()-YMundo+1, getSizeX()-2, getSizeY()-2);
		
		Iterator<Botao> it = botoes.iterator();
		while(it.hasNext()){
			Botao bot= it.next();
			
			bot.DesenhaSe(dbg, XMundo, YMundo);
			
	
		
		}
		Iterator<FrameBase> it2 = menuAtivo.iterator();

		while(it2.hasNext()){
			FrameBase _frame= it2.next();
			
			_frame.DesenhaSe(dbg, XMundo, YMundo);
			
	
		
		}
		
		
		

	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		for (int x=0;x<botoes.size();x++) {
			Botao b= botoes.get(x);
			
			b.SimulaSe((int)DiffTime);			
			if (b.ativo==true) {
				
				trataBotao(b);
				botoes.get(x).ativo=false;
			}		
		}
		
		Iterator<FrameBase> it3 = menuAtivo.iterator();
		while(it3.hasNext()){
			FrameBase _frame= it3.next();
			
			_frame.SimulaSe(DiffTime);
		}
		
		}
protected abstract void trataBotao(Botao b2);


public void mouseClicked(MouseEvent e) {
	for (int i=0;i<botoes.size();i++) {
		
		botoes.get(i).mouseClicked(e);
	}
	Iterator<FrameBase> it3 = menuAtivo.iterator();
	while(it3.hasNext()){
		FrameBase _frame= it3.next();
		
		_frame.mouseClicked(e);
	}
	
		
	}
	public void mousePressed(MouseEvent e) {
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mousePressed(e);
		}
		Iterator<FrameBase> it3 = menuAtivo.iterator();
		while(it3.hasNext()){
			FrameBase _frame= it3.next();
			
			_frame.mousePressed(e);
		}
			
	}
	public void mouseReleased(MouseEvent e) {
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mouseReleased(e);
		}
		Iterator<FrameBase> it3 = menuAtivo.iterator();
		while(it3.hasNext()){
			FrameBase _frame= it3.next();
			
			_frame.mouseReleased(e);
		}	
	}


public void mouseMoved(MouseEvent e) {
		
		if (Constantes.colideQuadrado((int)getX(),(int)getY(),getSizeX(),getSizeY(), (int)e.getX(),(int)e.getY() ,2,2 )) {
			selecionado=true;
			
		}
		else {
			selecionado=false;
		}
		
		for (int i=0;i<botoes.size();i++) {
			
			botoes.get(i).mouseMoved(e);
		}
		
		Iterator<FrameBase> it3 = menuAtivo.iterator();
		while(it3.hasNext()){
			FrameBase _frame= it3.next();
			
			_frame.mouseMoved(e);
		}	
		
		
		
	
	}
	


public
void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	
	
	int keyCode = e.getKeyCode();
	if(keyCode == KeyEvent.VK_0){
	}		
	if(keyCode == KeyEvent.VK_8){
	
	}	

	Iterator<FrameBase> it3 = menuAtivo.iterator();
	while(it3.hasNext()){
		FrameBase _frame= it3.next();
		
		_frame.keyPressed(e);
	}	
	
	

}


public void keyReleased(KeyEvent e ) {
	int keyCode = e.getKeyCode();
	
	if(keyCode == KeyEvent.VK_F6){
		
	
	}
	
	Iterator<FrameBase> it3 = menuAtivo.iterator();
	while(it3.hasNext()){
		FrameBase _frame= it3.next();
		
		_frame.keyReleased(e);
	}	
}

	

}
