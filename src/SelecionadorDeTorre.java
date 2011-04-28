import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.lang.reflect.Array;

public class SelecionadorDeTorre extends Objeto {

	private SlotTorre[] slotsTorre;
	private boolean oldIsMousePressed;
	public Torre torreAtiva;

	public SelecionadorDeTorre(){
		setX(Constantes.SELECIONADOR_DE_TORRE_X);
		setY(Constantes.SELECIONADOR_DE_TORRE_Y);
		setSizeX(Constantes.SELECIONADOR_DE_TORRE_SIZEX);
		setSizeY(Constantes.SELECIONADOR_DE_TORRE_SIZEY);
		setVivo(true);
		
		
		
		
		setSlotsTorre(new SlotTorre[4]);
		getSlotsTorre()[0] = new SlotTorre();
		getSlotsTorre()[0].setX(Constantes.SLOT_UM_X);
		getSlotsTorre()[0].setY(Constantes.SLOT_UM_Y);
		getSlotsTorre()[0].setTorre(Imagem.TORRE_UM_ANIMESET, new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET));
		getSlotsTorre()[0].setAtivo(true);
		
		getSlotsTorre()[1] = new SlotTorre();
		getSlotsTorre()[1].setX(Constantes.SLOT_DOIS_X);
		getSlotsTorre()[1].setY(Constantes.SLOT_DOIS_Y);
		getSlotsTorre()[1].setTorre(Imagem.TORRE_DOIS_ANIMESET, new ArmaDoisTorre(Imagem.TORRE_DOIS_ANIMESET));
		getSlotsTorre()[1].setAtivo(false);

		getSlotsTorre()[2] = new SlotTorre();
		getSlotsTorre()[2].setX(Constantes.SLOT_TRES_X);
		getSlotsTorre()[2].setY(Constantes.SLOT_TRES_Y);
		getSlotsTorre()[2].setTorre(Imagem.TORRE_TRES_ANIMESET, new ArmaTresTorre(Imagem.TORRE_TRES_ANIMESET));
		getSlotsTorre()[2].setAtivo(false);

		getSlotsTorre()[3] = new SlotTorre();
		getSlotsTorre()[3].setX(Constantes.SLOT_QUATRO_X);
		getSlotsTorre()[3].setY(Constantes.SLOT_QUATRO_Y);
		getSlotsTorre()[3].setTorre(Imagem.TORRE_UM_ANIMESET, new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET));
		getSlotsTorre()[3].setAtivo(false);
		
		
	//	System.out.println(new Torre(slotsTorre[0].getAnimeSet(),slotsTorre[0].getArmaAtiva(),0,0));
	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
//		System.out.println(slotsTorre[0].isAtivo());

		for(int i = 0; i < 4; i++){
			if (Constantes.colideQuadrado((int)getSlotsTorre()[i].getX(), (int)getSlotsTorre()[i].getY(), getSlotsTorre()[i].getSizeX(), getSlotsTorre()[i].getSizeY(), (int)CanvasGame.getMiraAtiva().getX(), (int)CanvasGame.getMiraAtiva().getY(), 1, 1 )) {
				if (!getSlotsTorre()[i].isAtivo()) {
					if (CanvasGame.getMiraAtiva().isPressed() && !oldIsMousePressed) {
							getSlotsTorre()[i].setAtivo(true);
							//torreAtiva=new Torre(getSlotsTorre()[i].getAnimeSet(),getSlotsTorre()[i].getArmaAtiva(),0,0);
						
							
								//System.out.println(new Torre(slotsTorre[0].getAnimeSet(),slotsTorre[0].getArmaAtiva(),0,0));

							//System.out.println(getSlotsTorre()[i].getArmaAtiva());
							for(int j = 0; j < 4; j++){
								if(i != j){
									getSlotsTorre()[j].setAtivo(false);
								}
							}
					}
				}
				getSlotsTorre()[i].setSelecionado(true);
			}else {
				getSlotsTorre()[i].setSelecionado(false);
			}
			
		}
				
		oldIsMousePressed = CanvasGame.getMiraAtiva().isPressed();		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
		 Stroke stk = dbg.getStroke();
		    
		    dbg.setStroke(new BasicStroke(1.5f));
	
		
		
		    dbg.setColor(new Color(1,1,1,150));
		dbg.fillRect((int)getX(), (int)getY(), getSizeX(), getSizeY());
		dbg.setColor(Color.black);

		for(int i = 0; i < 4; i++){
			getSlotsTorre()[i].DesenhaSe(dbg, XMundo, YMundo);
		}
	    dbg.setStroke(stk);

	}

	public void setSlotsTorre(SlotTorre[] slotsTorre) {
		this.slotsTorre = slotsTorre;
	}

	public SlotTorre[] getSlotsTorre() {
		return slotsTorre;
	}
	
	
}
