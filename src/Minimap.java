import java.awt.Color;
import java.awt.Graphics2D;


public class Minimap extends Objeto{


	public Minimap(){
		setX(0);
		setY(0);
		setSizeX((int) CanvasGame.largura/6);
		setSizeY((int)CanvasGame.altura/6);
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		System.out.println();
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(Color.GREEN);
		dbg.fillRect((int)getX(), (int)getY(), (int)getSizeX(), (int)getSizeY());
		dbg.setColor(Color.RED);
		dbg.drawLine((int)getX() + getSizeX()/2, (int)getY(),(int)getX() + getSizeX()/2,(int)getY() + getSizeY());
		dbg.drawLine((int)getX(), (int)getY() + getSizeY()/2,(int)getX() + getSizeX(),(int)getY() + getSizeY()/2);
		
		for(int j = 0; j < GerenciadorDeRaids.getRaids().size(); j++){
			for(int i = 0; i < GerenciadorDeRaids.getRaids().get(j).inimigos.size(); i++){
				Inimigo inim = (Inimigo) GerenciadorDeRaids.getRaids().get(j).inimigos.get(i);
				
				if(inim.getX() >= 0 && inim.getX() <= 80*16 && inim.getY() >= 0 && inim.getY() < 60*16){
					int xM = (int) (inim.getX()/6 + this.getX());
					int yM = (int) (inim.getY()/6 + this.getY());
					int lM = inim.getSizeX()/6;
					int aM = inim.getSizeY()/6;
					
				
					dbg.fillOval(xM-lM/2, yM-aM/2, lM, aM);
				}
			}
		}
		dbg.setColor(Color.white);
		dbg.fillOval(((int)CanvasGame.heroi.getX()-CanvasGame.heroi.getSizeX()/2)/6,((int)CanvasGame.heroi.getY()-CanvasGame.heroi.getSizeY()/2)/6,(int)CanvasGame.heroi.getSizeX()/6,CanvasGame.heroi.getSizeY()/6);
		

	}

}
