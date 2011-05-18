package Torre;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;


import AbstractClasses.Objeto;
import Armas.ArmaDoisTorre;
import Armas.ArmaTresTorre;
import Armas.ArmaUmTorre;
import Canvas.CanvasGame;
import Constantes.Constantes;
import Data.Imagem;

public class SelecionadorDeTorre extends Objeto {

	public SlotTorre[] slotsTorre;
	private boolean oldIsMousePressed;
	public Torre torreAtiva;

	public SelecionadorDeTorre(){
		setX(Constantes.SELECIONADOR_DE_TORRE_X);
		setY(Constantes.SELECIONADOR_DE_TORRE_Y);
		setSizeX(Constantes.SELECIONADOR_DE_TORRE_SIZEX);
		setSizeY(Constantes.SELECIONADOR_DE_TORRE_SIZEY);
		setVivo(true);
		
		
		
		
		slotsTorre = new SlotTorre[4];
		slotsTorre[0] = new SlotTorre();
		slotsTorre[0].setX(Constantes.SLOT_UM_X);
		slotsTorre[0].setY(Constantes.SLOT_UM_Y);
		slotsTorre[0].setSlot(new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET));
		slotsTorre[0].ativo=(true);
		
		slotsTorre[1] = new SlotTorre();
		slotsTorre[1].setX(Constantes.SLOT_DOIS_X);
		slotsTorre[1].setY(Constantes.SLOT_DOIS_Y);
		slotsTorre[1].setSlot( new ArmaDoisTorre(Imagem.TORRE_DOIS_ANIMESET));
		slotsTorre[1].ativo=(false);

		slotsTorre[2] = new SlotTorre();
		slotsTorre[2].setX(Constantes.SLOT_TRES_X);
		slotsTorre[2].setY(Constantes.SLOT_TRES_Y);
		slotsTorre[2].setSlot(new ArmaTresTorre(Imagem.TORRE_TRES_ANIMESET));
		slotsTorre[2].ativo=(false);

		slotsTorre[3] = new SlotTorre();
		slotsTorre[3].setX(Constantes.SLOT_QUATRO_X);
		slotsTorre[3].setY(Constantes.SLOT_QUATRO_Y);
		slotsTorre[3].setSlot( new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET));
		slotsTorre[3].ativo=(false);
		
		
	//	System.out.println(new Torre(slotsTorre[0].getAnimeSet(),slotsTorre[0].getArmaAtiva(),0,0));
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
//		System.out.println(slotsTorre[0].isAtivo());

//		for(int i = 0; i < 4; i++){
//			if (Constantes.colideQuadrado((int)slotsTorre[i].getX(), (int)slotsTorre[i].getY(), slotsTorre[i].getSizeX(), slotsTorre[i].getSizeY(), (int)CanvasGame.getMiraAtiva().getX(), (int)CanvasGame.getMiraAtiva().getY(), 1, 1 )) {
//				if (!slotsTorre[i].isAtivo()) {
//					if (CanvasGame.miraAtiva.pressed && !oldIsMousePressed) {
//							slotsTorre[i].setAtivo(true);
//							//torreAtiva=new Torre(slotsTorre[i].getAnimeSet(),slotsTorre[i].getArmaAtiva(),0,0);
//						
//							
//								//System.out.println(new Torre(slotsTorre[0].getAnimeSet(),slotsTorre[0].getArmaAtiva(),0,0));
//
//							//System.out.println(slotsTorre[i].getArmaAtiva());
//							for(int j = 0; j < 4; j++){
//								if(i != j){
//									slotsTorre[j].setAtivo(false);
//								}
//							}
//					}
//				}
//				slotsTorre[i].setSelecionado(true);
//			}else {
//				slotsTorre[i].setSelecionado(false);
//			}
//			
//		}
//				
//		oldIsMousePressed = CanvasGame.getMiraAtiva().isPressed();		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		 Stroke stk = dbg.getStroke();
		    
		    dbg.setStroke(new BasicStroke(1.5f));
	
		
		
		    dbg.setColor(new Color(100,100,100,150));
		dbg.fillRect((int)getX(), (int)getY(), getSizeX(), getSizeY());
		dbg.setColor(Color.black);

		for(int i = 0; i < 4; i++){
			slotsTorre[i].DesenhaSe(dbg, XMundo, YMundo);
		}
	    dbg.setStroke(stk);

	}



	public void mouseClicked(MouseEvent e) {
		
		for(int i = 0; i < 4; i++){
			if (Constantes.colideQuadrado((int)slotsTorre[i].getX(), (int)slotsTorre[i].getY(), slotsTorre[i].getSizeX(), slotsTorre[i].getSizeY(), e.getX(), e.getY(), 1, 1 )) {
				if (!slotsTorre[i].ativo) {
							slotsTorre[i].ativo=(true);
							//torreAtiva=new Torre(slotsTorre[i].getAnimeSet(),slotsTorre[i].getArmaAtiva(),0,0);
						
							
								//System.out.println(new Torre(slotsTorre[0].getAnimeSet(),slotsTorre[0].getArmaAtiva(),0,0));

							//System.out.println(slotsTorre[i].getArmaAtiva());
							for(int j = 0; j < 4; j++){
								if(i != j){
									slotsTorre[j].ativo=(false);
								}
							}
					}
				
				slotsTorre[i].selecionado=(true);
			}else {
				slotsTorre[i].selecionado=(false);
			}
			
		}
				
		
//		for(int i = 0; i < 4; i++){
//			slotsTorre[i].mouseClicked(e);
//		}
	}

	public void mouseReleased(MouseEvent e) {
		for(int i = 0; i < 4; i++){
			slotsTorre[i].mouseReleased(e);
		}
	}

	public void mouseMoved(MouseEvent e) {
		
		for(int i = 0; i < 4; i++){
			if (Constantes.colideQuadrado((int)slotsTorre[i].getX(), (int)slotsTorre[i].getY(), slotsTorre[i].getSizeX(), slotsTorre[i].getSizeY(), e.getX(), e.getY(), 1, 1 )) {
					
				slotsTorre[i].selecionado=(true);
			}else {
				slotsTorre[i].selecionado=(false);
			}
			
		}
		
		for(int i = 0; i < 4; i++){
			slotsTorre[i].mouseMoved(e);
		}
	}
	
	
}
