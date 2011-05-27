package Interface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import Canvas.CanvasGame;
import GameState.GamePanel;


public class FrameStart extends FrameBase {
	static FrameOptions  frameOptions;
	static FrameHelp frameHelp;
	static FrameChooseLevel frameChooseLevel;
	
	public final String botaoPlay = "Play";
	public final String botaoHelp = "Help";
	public final String botaoOptions = "Options";
	public final String botaoScore = "Score";
	public final String botaoExit = "Exit";
	
	
	
	public FrameStart(int _x, int _y, int sizeX, int sizeY, Color cor, int _tempoVida) {
		super(_x, _y, sizeX, sizeY, cor, _tempoVida);
		frameOptions= new FrameOptions(250, 0, GamePanel.PWIDTH-250,GamePanel.PHEIGHT, Color.darkGray, -1);
		frameHelp= new FrameHelp(250, 0, GamePanel.PWIDTH-250,GamePanel.PHEIGHT, Color.darkGray, -1);
		frameChooseLevel = new FrameChooseLevel(250, 0, GamePanel.PWIDTH-250,GamePanel.PHEIGHT, Color.darkGray, -1);
		ativo=true;
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
		Iterator<FrameBase> it2 = frames.iterator();
		while(it2.hasNext()){
			FrameBase _frame= it2.next();
			
			_frame.DesenhaSe(dbg, XMundo, YMundo);
			
		
		
		}
	

	}


	private void criaBotoes() {
		// TODO Auto-generated method stubB

		
		botoes.add(new Botao(null,botaoPlay,50,100,120,25,false));
		botoes.add(new Botao(null,botaoScore,50,150,120,25,false));
		botoes.add(new Botao(null,botaoHelp,50,200,120,25,false));
		botoes.add(new Botao(null,botaoOptions,50,250,120,25,false));
		botoes.add(new Botao(null,botaoExit,50,300,120,25,false));
//		getBotoesMenu().add(new Botao(null,"Video",(int)getX()+30,(int)getY()+30,90,18,false));
//		getBotoesMenu().add(new Botao(null,"Som",(int)getX()+130+5,(int)getY()+30,90,18,false));
//		getBotoes().add(new Botao(null,"",(int)getX()+10,(int)getY()+110,120,25,false));
//		getBotoes().add(new Botao(null,"Options",(int)getX()+10,(int)getY()+160,120,25,false));
//		getBotoes().add(new Botao(null,"Exit",(int)getX()+10,(int)getY()+210,120,25,false));
		
		
	}
@Override
protected void trataBotao(Botao b) {
	// TODO Auto-generated method stub
	if (b.name.contains(botaoPlay) ) {
		
		if (!frames.contains(frameChooseLevel)) {
			frameChooseLevel.ativo=true;
			frames.add(frameChooseLevel);
		}	
		else {
			frameChooseLevel.ativo=false;
		}
		
		//GamePanel.canvasAtivo=(CanvasGame.instance);
	

		
	}else if (b.name.contains(botaoOptions) ) {
		if (!frames.contains(frameOptions)) {
			frameOptions.ativo=true;
			frames.add(frameOptions);
	}	
		else {
			frameOptions.ativo=false;
		}
		
	}else if (b.name.contains(botaoExit) ) {
		
		System.exit(0);
		
	}else if (b.name.contains(botaoHelp) ) {
		
		if (!frames.contains(frameHelp)) {
			frameHelp.ativo=true;
			frames.add(frameHelp);
	}	
		else {
			frameHelp.ativo=false;
		}
	}
	
	
}


	public void setBotoesMenu(ArrayList<Botao> botoesMenu) {
		this.botoes = botoesMenu;
	}
}
