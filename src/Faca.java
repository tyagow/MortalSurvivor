import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class Faca extends Arma {
	
	
	private int estado;
	private boolean soltouTiro;
	private double oldAng;
	private double alcanceAtaque=50;
	private boolean atacou;

	public Faca() {
		atirou=false;
		dano=Constantes.FACA_dano;
		peso=Constantes.FACA_peso;
		round=Constantes.FACA_round;
		tempoEntreTiros=0;
		tempoRecarrega=0;
		valor=Constantes.FACA_valor;
		sizeX=50;
		sizeY=5;
		
		
	}
 
	@Override
	public void definePosicaoArma(double ang,double startX,double startY) {
		// TODO Auto-generated method stub
		
		angulo=ang;
		oldAng=ang;
		X=startX;
		Y=startY;

	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
			dbg.setColor(Color.black);
			AffineTransform trans = dbg.getTransform();
			dbg.translate(X, Y);
			if(estado==1)
				dbg.rotate(oldAng);
			else 
				dbg.rotate(angulo);
			
			dbg.drawLine(0, 0, (int)alcanceAtaque/2, 0);

			dbg.setTransform(trans);
			
			if (estado==1) {
//				dbg.setColor(Color.LIGHT_GRAY);
//				dbg.fillRect(GamePanel.PWIDTH/2-50, GamePanel.PHEIGHT/2-205,(int)(tempoRecarrega*100/Constantes.PISTOLA_tempoRecarrega) , 20);

				dbg.setColor(Color.black);
				dbg.drawOval((int)(X-alcanceAtaque/2), (int)(Y-alcanceAtaque/2), (int)alcanceAtaque,(int)alcanceAtaque);
				
			}
//			
//			dbg.drawString("Round: "+round,5 , 20);
//			dbg.drawString("mag: "+mag,5 , 30);
			
		
		
	}

	@Override
	public void SimulaSe(int Difftime) {
		// TODO Auto-generated method stub
		
		
		tempoEntreTiros+=Difftime;
		tempoRecarrega+=Difftime;
		

			
		if (estado==0) {
			oldAng=angulo;
		
			if (tempoEntreTiros>=Constantes.FACA_tempoEntreTiros) {
				
				if (atirou&&soltouTiro) {	
					soltouTiro=false;
					atira();
					tempoEntreTiros=0;
					atacou=false;
					
				}
				
				if (!atirou)
					soltouTiro=true;
			}
			
		}
		
		if (estado==1) {
			
			
			oldAng+=Math.PI*Difftime/Constantes.FACA_tempoAtaque;
				
				for (int i = 0; i < CanvasGame.inimigos.size(); i++) {
					if (Constantes.colidecircular(X, Y, alcanceAtaque, CanvasGame.inimigos.get(i).X, CanvasGame.inimigos.get(i).Y, CanvasGame.inimigos.get(i).sizeX/2)) {
						CanvasGame.inimigos.get(i).life-=dano;
						
					}
		
				}
				
			if (tempoRecarrega>=Constantes.FACA_tempoAtaque) {
				tempoRecarrega=0;
				estado=0;	
			}
				
			
			
		}
//		if (estado==2) {
//			
//			if (round>0) 
//				estado=0;
//			
//			
//			}
	
		
	}
	private void atira() {
		// TODO Auto-generated method stub
		angulo-=Math.PI/2;
		estado=1;
	}

	@Override
	public void recarrega() {
		// TODO Auto-generated method stub

	}

}
