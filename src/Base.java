import java.awt.Color;
import java.awt.Graphics2D;


public class Base extends Objeto{

	public Base(int _x, int _y){
		setX(_x);
		setY(_y);
		setSizeX(Constantes.BASE_SIZEX_1);
		setSizeY(Constantes.BASE_SIZEY_1);
		setVivo(true);
		setLife(Constantes.BASE_LIFE_1);

	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		//LIFE
		dbg.drawRect((int)getX()-getSizeX()/2-XMundo, (int)getY()-getSizeY()/2-17-YMundo, 30, 10);
		dbg.setColor(Color.lightGray);
		dbg.fillRect((int)getX()-getSizeX()/2+1-XMundo, (int)getY()-getSizeY()/2-16-YMundo, (int)(getLife()*30/Constantes.BASE_LIFE_1)-1, 9);
		//BASE
		dbg.setColor(Color.ORANGE);
		dbg.fillOval((int)getX()-getSizeX()/2-XMundo,(int) (getY()-getSizeY()/2-YMundo), getSizeX(),getSizeY());
		
		//dbg.drawRect((int)(getX()/16)*16-getSizeX()/2-XMundo,(int) ((getY())/16*16-getSizeY()/2-YMundo), getSizeX(),getSizeY());

	}

}
