package Interface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import Canvas.CanvasGame;
import GameState.GamePanel;


public class FrameStart extends FrameBase {
	FrameOptions frameOptions;
	public FrameStart(int _x, int _y, int sizeX, int sizeY, Color cor, int _tempoVida) {
		super(_x, _y, sizeX, sizeY, cor, _tempoVida);
		frameOptions= new FrameOptions(250, 0, GamePanel.PWIDTH-250,GamePanel.PHEIGHT, Color.darkGray, 9999);
	
		criaBotoes();
		alpha=100;
	}
	



	private ArrayList<Botao> getBotoesMenu() {
		// TODO Auto-generated method stub
		return botoes;
	}


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
//		
		dbg.setColor(Color.white);
		//dbg.drawRect((int)getX()-XMundo,(int)getY()-YMundo, getSizeX(), getSizeY());
		
		dbg.setColor(new Color(r,g,b,alpha));

		dbg.fillRect((int)0,(int)0,250, GamePanel.PHEIGHT);

		Iterator<Botao> it = getBotoesMenu().iterator();

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


	private void criaBotoes() {
		// TODO Auto-generated method stubB

		
		botoes.add(new Botao(null,"Play",50,100,120,25,false));
		botoes.add(new Botao(null,"Score",50,150,120,25,false));
		botoes.add(new Botao(null,"Help",50,200,120,25,false));
		botoes.add(new Botao(null,"Options",50,250,120,25,false));
		botoes.add(new Botao(null,"Exit",50,300,120,25,false));
//		getBotoesMenu().add(new Botao(null,"Video",(int)getX()+30,(int)getY()+30,90,18,false));
//		getBotoesMenu().add(new Botao(null,"Som",(int)getX()+130+5,(int)getY()+30,90,18,false));
//		getBotoes().add(new Botao(null,"",(int)getX()+10,(int)getY()+110,120,25,false));
//		getBotoes().add(new Botao(null,"Options",(int)getX()+10,(int)getY()+160,120,25,false));
//		getBotoes().add(new Botao(null,"Exit",(int)getX()+10,(int)getY()+210,120,25,false));
		
		
	}
@Override
protected void trataBotao(Botao b) {
	// TODO Auto-generated method stub
	if (b.name.contains("Play") ) {
		

		GamePanel.setCanvasAtivo(CanvasGame.instance);
	

		
	}else if (b.name.contains("Options") ) {
		if (!menuAtivo.contains(frameOptions)) {
			
			menuAtivo.add(frameOptions);
	}	
		else {
			
			menuAtivo.remove(frameOptions);
		}
		
	}else if (b.name.contains("Exit") ) {
		
		System.exit(0);
		
	}else if (b.name.contains("Play") ) {
		
		GamePanel.setCanvasAtivo(CanvasGame.instance);
		
	}
	
	
}


	public void setBotoesMenu(ArrayList<Botao> botoesMenu) {
		this.botoes = botoesMenu;
	}
}
