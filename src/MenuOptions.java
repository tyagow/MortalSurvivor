import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;


public class MenuOptions extends Menu {

	public MenuOptions(int _x, int _y, int sizeX, int sizeY, Color cor, int _tempoVida) {
		super(_x, _y, sizeX, sizeY, cor, _tempoVida);
		// TODO Auto-generated constructor stub
		
		criaBotoes();
	}
	


	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		Iterator<Botao> it = getBotoes().iterator();
		while(it.hasNext()){
			Botao bot= it.next();
			bot.SimulaSe(DiffTime);
			trataBotao(bot);
		}
		

	}
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		dbg.setColor(Color.white);
		dbg.drawRect((int)getX()-XMundo,(int)getY()-YMundo, getSizeX(), getSizeY());
		
		dbg.setColor(new Color(50,50,50,200));
		
		dbg.fillRect((int)getX()+1-XMundo,(int)getY()-YMundo+1, getSizeX()-2, getSizeY()-2);
		
		Iterator<Botao> it = getBotoes().iterator();
		while(it.hasNext()){
			Botao bot= it.next();
			
			bot.DesenhaSe(dbg, XMundo, YMundo);
			
			//trataBotao(bot);
		
		}
		

	}
	private void trataBotao(Botao b) {
		// TODO Auto-generated method stub
		
		
		
	}

	private void criaBotoes() {
		// TODO Auto-generated method stubB

		getBotoes().add(new Botao(null,"Video",(int)getX()+10,(int)getY()+10,60,14,false));
		getBotoes().add(new Botao(null,"Som",(int)getX()+10,(int)getY()+35,60,14,false));
//		getBotoes().add(new Botao(null,"",(int)getX()+10,(int)getY()+110,120,25,false));
//		getBotoes().add(new Botao(null,"Options",(int)getX()+10,(int)getY()+160,120,25,false));
//		getBotoes().add(new Botao(null,"Exit",(int)getX()+10,(int)getY()+210,120,25,false));
		
		
	}
}
