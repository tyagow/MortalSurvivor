import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.reflect.Array;

public class SelecionadorDeTorre extends Objeto {

	private SlotTorre[] slotsTorre;
	private boolean oldIsMousePressed;

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
		slotsTorre[0].setTorre(Imagem.TORRE_UM_ANIMESET, new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET));
		slotsTorre[0].setAtivo(true);
		
		slotsTorre[1] = new SlotTorre();
		slotsTorre[1].setX(Constantes.SLOT_DOIS_X);
		slotsTorre[1].setY(Constantes.SLOT_DOIS_Y);
		slotsTorre[1].setTorre(Imagem.TORRE_UM_ANIMESET, new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET));
		slotsTorre[1].setAtivo(false);

		slotsTorre[2] = new SlotTorre();
		slotsTorre[2].setX(Constantes.SLOT_TRES_X);
		slotsTorre[2].setY(Constantes.SLOT_TRES_Y);
		slotsTorre[2].setTorre(Imagem.TORRE_UM_ANIMESET, new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET));
		slotsTorre[2].setAtivo(false);

		slotsTorre[3] = new SlotTorre();
		slotsTorre[3].setX(Constantes.SLOT_QUATRO_X);
		slotsTorre[3].setY(Constantes.SLOT_QUATRO_Y);
		slotsTorre[3].setTorre(Imagem.TORRE_UM_ANIMESET, new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET));
		slotsTorre[3].setAtivo(false);

	}
	
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < 4; i++){
			if (Constantes.colideQuadrado((int)slotsTorre[i].getX(), (int)slotsTorre[i].getY(), slotsTorre[i].getSizeX(), slotsTorre[i].getSizeY(), (int)CanvasGame.getMiraAtiva().getX(), (int)CanvasGame.getMiraAtiva().getY(), 1, 1 )) {
				if (!slotsTorre[i].isAtivo()) {
					if (CanvasGame.getMiraAtiva().isPressed() && !oldIsMousePressed) {
							slotsTorre[i].setAtivo(true);
							for(int j = 0; j < 4; j++){
								if(i != j){
									slotsTorre[j].setAtivo(false);
								}
							}
					}
				}
				slotsTorre[i].setSelecionado(true);
			}else {
				slotsTorre[i].setSelecionado(false);
			}
			
		}
				
		oldIsMousePressed = CanvasGame.getMiraAtiva().isPressed();		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		dbg.setColor(Color.black);
		dbg.drawRect((int)getX(), (int)getY(), getSizeX(), getSizeY());
		
		for(int i = 0; i < 4; i++){
			slotsTorre[i].DesenhaSe(dbg, XMundo, YMundo);
		}
		
	}
	
	
}
