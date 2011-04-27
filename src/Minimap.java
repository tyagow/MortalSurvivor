import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;


public class Minimap extends Objeto{


	private int tamanhoMiniMap = 6;
	public static float resizeX;
	public static float resizeY;
	
	public Minimap(){
		
		resizeX = (float) 70/(CanvasGame.MAPA.Largura);
		resizeY = (float) 60/(CanvasGame.MAPA.Altura);

		setX(0);
		setY(GamePanel.PHEIGHT - (int)((resizeY * CanvasGame.altura)/tamanhoMiniMap));
		
		setSizeX((int) (resizeX * CanvasGame.largura/tamanhoMiniMap));
		setSizeY((int) (resizeY * CanvasGame.altura/tamanhoMiniMap));
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
	//System.out.println(GerenciadorObstaculos.getObstaculos().size());

	}  

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		dbg.setColor(Color.GREEN);
		dbg.fillRect((int)getX(), (int)getY(), (int)getSizeX(), (int)getSizeY());
	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		dbg.setColor(Color.RED);
		dbg.drawLine((int)getX() + getSizeX()/2, (int)getY(),(int)getX() + getSizeX()/2,(int)getY() + getSizeY());
		dbg.drawLine((int)getX(), (int)getY() + getSizeY()/2,(int)getX() + getSizeX(),(int)getY() + getSizeY()/2);
		
		for(int j = 0; j < GerenciadorDeRaids.getRaids().size(); j++){
			for(int i = 0; i < GerenciadorDeRaids.getRaids().get(j).inimigos.size(); i++){
				Inimigo inim = (Inimigo) GerenciadorDeRaids.getRaids().get(j).inimigos.get(i);
				
				if(inim.getX() >= 0 && inim.getX() <= 80*16 && inim.getY() >= 0&& inim.getY() < 60*16){
					double xM = (resizeX * inim.getX()/tamanhoMiniMap) + this.getX();
					double yM = (resizeY * inim.getY()/tamanhoMiniMap) + this.getY();
					int lM = inim.getSizeX()/tamanhoMiniMap;
					int aM = inim.getSizeY()/tamanhoMiniMap;
					
				
					dbg.fillOval((int) xM-lM/2, (int) yM-aM/2, lM, aM);
				}
			}
		}

			
			dbg.setColor(Color.yellow);
		for (int k=0;k<GerenciadorObstaculos.getObstaculos().size();k++)
		{
			Obstaculo inim = GerenciadorObstaculos.getObstaculos().get(k);
			double xM = (resizeX * inim.getX()*16/tamanhoMiniMap + this.getX());
			double yM = (resizeY * inim.getY()*16/tamanhoMiniMap + this.getY());
			int lM = inim.getSizeX()/tamanhoMiniMap;
			int aM = inim.getSizeY()/tamanhoMiniMap;
			dbg.fillOval((int)xM-2, (int)yM-2, 4, 4);

///			dbg.fillRect((int)inim.getX()*16/tamanhoMiniMap+ this.getX(), (int)inim.getY()*16/tamanhoMiniMap+ this.getY(), inim.getSizeX()/tamanhoMiniMap, inim.getSizeY()/tamanhoMiniMap);
			
		}
		dbg.setColor(Color.black);
	
		dbg.drawOval((int)(resizeX * CanvasGame.getMiraAtiva().getXMundo()/6+getX()), (int)(resizeY * CanvasGame.getMiraAtiva().getYMundo()/6+getY()), 2, 2);
		dbg.setColor(Color.red);
//		dbg.fillOval(((int)CanvasGame.heroi.getX()-CanvasGame.heroi.getSizeX()/2)/6,((int)CanvasGame.heroi.getY()-CanvasGame.heroi.getSizeY()/2)/6,(int)CanvasGame.heroi.getSizeX()/6,CanvasGame.heroi.getSizeY()/6);
//		int x[] = new int[4];
//		int y[] = new int[4];
//		
//		x[0]=(int) ((int)(CanvasGame.heroi.getX()-CanvasGame.heroi.getSizeX()/2)/6+getX());
//		x[1]=(int) ((int)(CanvasGame.heroi.getX()-CanvasGame.heroi.getSizeX()/2)/6+getX());
//		x[2]=(int) (((int)CanvasGame.heroi.getX()+CanvasGame.heroi.getSizeX()/2)/6+getX());
//		x[3]=(int) (((int)CanvasGame.heroi.getX()+CanvasGame.heroi.getSizeX()/2)/6+getX());
//		
//		y[0]=(int) (((int)CanvasGame.heroi.getY()-CanvasGame.heroi.getSizeY()/2)/6+getY());
//		y[1]=(int) (((int)CanvasGame.heroi.getY()+CanvasGame.heroi.getSizeY()/2)/6+getY());
//		y[2]=(int) (((int)CanvasGame.heroi.getY()+CanvasGame.heroi.getSizeY()/2)/6+getY());
//		y[3]=(int) (((int)CanvasGame.heroi.getY()-CanvasGame.heroi.getSizeY()/2)/6+getY());
		
		dbg.fillOval((int)(resizeX * CanvasGame.heroi.getX()/6+getX()), (int)(resizeY * CanvasGame.heroi.getY()/6+getY()), 4, 4);
		
		//dbg.fillPolygon(x,y,4);
		
	}

}
