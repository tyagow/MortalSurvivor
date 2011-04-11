import java.awt.Color;
import java.awt.Graphics2D;


public class RaidSkipper extends Objeto{
	
	boolean ativo, select;

	public RaidSkipper(){
		
		setX(590); 
		setY(30);
		
		setSizeX(45);
		setSizeY(37);
			
		ativo = false;
		select = false;
		
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
		if(ativo){
			CanvasGame.timer = Constantes.TEMPO_ENTRE_RAIDS;
			ativo = false;
		}
		
		if (Constantes.colidecircular(CanvasGame.getMiraAtiva().getX(), CanvasGame.getMiraAtiva().getX(), 12, getX()+getSizeX()/2, getY()+getSizeY()/2, 12)) {
			select = true;
		}else select = false;
		
		if(select && CanvasGame.click){
			ativo = true;
		}
		
		
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		if (!select) 
			dbg.setColor(Color.blue); 
		else 
			dbg.setColor(Color.green);
		
		int px = (int)getX()-XMundo;
		int py = (int)getY()-YMundo;
		
		dbg.drawOval((int)getX()-XMundo-getSizeX()/2, (int)getY()-YMundo-getSizeY()/2, (int)getSizeX(), (int)getSizeY());
		dbg.drawString("NEXT", (int) getX()+6, (int) getY()+22);

	
	}
	

}
