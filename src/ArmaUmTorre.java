import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class ArmaUmTorre extends Arma {

	
	private boolean soltouTiro=true;
	private boolean semMunicao=false;

		
	public int estado=0;
	public ArmaUmTorre() {
		
		atirou=false;
		soltouTiro=true;
		dano=Constantes.TORRE_ARMA_UM_dano;
		mag=Constantes.TORRE_ARMA_UM_mag;
		round=Constantes.TORRE_ARMA_UM_round;
		tempoEntreTiros=0;
		tempoRecarrega=0;
		valor=Constantes.TORRE_ARMA_UM_valor;
		sizeX=20;
		sizeY=5;
		estado=0;
		
	}
	


	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(Color.black);
		AffineTransform trans = dbg.getTransform();
		dbg.translate(X-XMundo, Y-YMundo);
		dbg.rotate(angulo);
		dbg.drawLine(0, 0, sizeX, 0);

		dbg.setTransform(trans);
		
		if (estado==1) {
			dbg.setColor(Color.LIGHT_GRAY);
			dbg.fillRect(GamePanel.PWIDTH/2-50, GamePanel.PHEIGHT/2-205,(int)(tempoRecarrega*100/Constantes.PISTOLA_tempoRecarrega) , 20);

			dbg.setColor(Color.black);
			dbg.drawRect(GamePanel.PWIDTH/2-51, GamePanel.PHEIGHT/2-206, 103, 21);
		}
		
		dbg.drawString("Round: "+round,5 , 60);
		dbg.drawString("mag: "+mag,5 , 70);
		
	}
	


	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
		calculaIA(DiffTime);
	

		
		
	}
	
	private void calculaIA(int DiffTime) {
		// TODO Auto-generated method stub
		tempoEntreTiros+=DiffTime;
	
		
		if (round>0) 
			estado=0;
		else estado=1;
			
		if (estado==0) {
	

			if (tempoEntreTiros>=Constantes.TORRE_ARMA_UM_tempoEntreTiros) {
				if (atirou) {	
					atira();
					tempoEntreTiros=0;
					
				}
			}
		}
		
		if (estado==1) {
			
			
			tempoRecarrega+=DiffTime;
			
			
			if (mag<1)
				estado=2;
				
			if (tempoRecarrega>=Constantes.TORRE_ARMA_UM_tempoRecarrega) {
				
				if (mag >=1) {
					tempoRecarrega=0;
					round=Constantes.TORRE_ARMA_UM_round;
					mag--;
					estado=0;
				}
			}
			
		}
		if (estado==2) {
			
			if (round>0) 
				estado=0;
			
			
		}
	}



	@Override
	public void definePosicaoArma(double ang,double startX,double startY) {
		// TODO Auto-generated method stub
		angulo=ang;
		X=startX;
		Y=startY;

	}
	
	@Override
	public void recarrega() {
		mag=Constantes.TORRE_ARMA_UM_mag;
		round=Constantes.TORRE_ARMA_UM_round;
	}

	
	private void atira() {
//		System.out.println("atira");
		// TODO Auto-generated method stub
		
//		if (temMunicao()) {
			round--;
			
			CanvasGame.projeteis.add( new Projetil (this,angulo,2 ));
			Constantes.ak.run();
			
//		}
		
	}



	@Override
	public void atirou() {
		// TODO Auto-generated method stub

			atirou=true;
			
		
	}



	@Override
	public void naoAtirou() {
		// TODO Auto-generated method stub
		atirou=false;

	}


//	private boolean temMunicao() {
//		// TODO Auto-generated method stub
//		
//		if (round<1) {
//			semMunicao=true;
//			return false;
//			
//		}else 
//			semMunicao=false;
//		
//		
//		return true;
//	}



}
