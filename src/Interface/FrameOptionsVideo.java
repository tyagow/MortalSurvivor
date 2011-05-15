package Interface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import Constantes.Constantes;


public class FrameOptionsVideo extends FrameBase {

	private String botaoSangue= "Sangue";



	public FrameOptionsVideo(int _x, int _y, int sizeX, int sizeY, Color cor, int _tempoVida) {
		super(_x, _y, sizeX, sizeY, cor, _tempoVida);
		
		criaBotoes();
		alpha=100;
	}
	

//@Override
//	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
//		// TODO Auto-generated method stub
//		System.out.println("desenha");
//		dbg.setColor(Color.white);
//		//dbg.drawRect((int)getX()-XMundo,(int)getY()-YMundo, getSizeX(), getSizeY());
//		
//		dbg.setColor(new Color(r,g,b,alpha));
//		
//		dbg.fillRect((int)getX()+1-XMundo,(int)getY()-YMundo+1, getSizeX()-2, getSizeY()-2);
//		
//		Iterator<Botao> it = botoes.iterator();
//		while(it.hasNext()){
//			Botao bot= it.next();
//			
//			bot.DesenhaSe(dbg, XMundo, YMundo);
//			
//			//trataBotao(bot);
//		
//		}
//		
//
//	}
	@Override
	protected void trataBotao(Botao b) {
		// TODO Auto-generated method stub

		if (b.name.contains("Sangue") ) {
			Constantes.totalParticulasSangue =0;
			
		}else if (b.name.contains("Options") ) {
	
		}
		
	}

	private void criaBotoes() {
		// TODO Auto-generated method stubB

		botoes.add(new Botao(null,"Sangue",(int)getX()+30,(int)getY()+30,90,18,false));

		
		
	}



}
