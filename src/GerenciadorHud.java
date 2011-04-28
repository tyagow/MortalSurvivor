import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.LinkedList;

public class GerenciadorHud extends Objeto {

	private Font oldFont;
	private Font fontXp = Constantes.big;
	private static int xpHud;
	Torre torreAtivaHud;
	private boolean rangeAtivo=false;
	
	private static LinkedList<Botao> botoes=new LinkedList<Botao>();
	
	
	int xHudArmas,yHudArmas,sizeXHudArmas,sizeYHudArmas;
	private SlotTorre[] slotsTorre;
	
	
	
	public GerenciadorHud() {
		carregaHudArmas();
		
	}
	private void carregaHudArmas() {
		// TODO Auto-generated method stub
		
		
		xHudArmas=200;
		sizeXHudArmas=300;
		sizeYHudArmas=70;
		yHudArmas=GamePanel.PHEIGHT-sizeYHudArmas-1;
		
		
		
		setSlotsTorre(new SlotTorre[4]);
		getSlotsTorre()[0] = new SlotTorre();
		getSlotsTorre()[0].setX(xHudArmas+40);
		getSlotsTorre()[0].setY(yHudArmas+10);
		getSlotsTorre()[0].setTorre(Heroi.getArmaMelee().imagem, new Faca());
		getSlotsTorre()[0].setAtivo(true);


		
		getSlotsTorre()[1] = new SlotTorre();
		getSlotsTorre()[1].setX(slotsTorre[0].getX()+5+slotsTorre[0].getSizeX());
		getSlotsTorre()[1].setY(yHudArmas+10);
		getSlotsTorre()[1].setTorre(Heroi.getArmaSecundaria().imagem_hud, new Deagle());
		getSlotsTorre()[1].setAtivo(false);
		
		
		getSlotsTorre()[2] = new SlotTorre();
		getSlotsTorre()[2].setX(slotsTorre[1].getX()+5+slotsTorre[1].getSizeX());
		getSlotsTorre()[2].setY(yHudArmas+10);
		getSlotsTorre()[2].setTorre(Heroi.getArmaPrimaria().imagem_hud, new Deagle());
		getSlotsTorre()[2].setAtivo(false);
		
		
		getSlotsTorre()[3] = new SlotTorre();
		getSlotsTorre()[3].setX(slotsTorre[2].getX()+5+slotsTorre[2].getSizeX());
		getSlotsTorre()[3].setY(yHudArmas+10);
		getSlotsTorre()[3].setTorre(Heroi.getArmaGranada().imagem_hud, new He());
		getSlotsTorre()[3].setAtivo(false);
//		
//		getSlotsTorre()[1] = new SlotTorre();
//		getSlotsTorre()[1].setX(Constantes.SLOT_DOIS_X);
//		getSlotsTorre()[1].setY(Constantes.SLOT_DOIS_Y);
//		getSlotsTorre()[1].setTorre(Imagem.TORRE_DOIS_ANIMESET, new ArmaDoisTorre(Imagem.TORRE_DOIS_ANIMESET));
//		getSlotsTorre()[1].setAtivo(false);
//
//		getSlotsTorre()[2] = new SlotTorre();
//		getSlotsTorre()[2].setX(Constantes.SLOT_TRES_X);
//		getSlotsTorre()[2].setY(Constantes.SLOT_TRES_Y);
//		getSlotsTorre()[2].setTorre(Imagem.TORRE_TRES_ANIMESET, new ArmaTresTorre(Imagem.TORRE_TRES_ANIMESET));
//		getSlotsTorre()[2].setAtivo(false);
//
//		getSlotsTorre()[3] = new SlotTorre();
//		getSlotsTorre()[3].setX(Constantes.SLOT_QUATRO_X);
//		getSlotsTorre()[3].setY(Constantes.SLOT_QUATRO_Y);
//		getSlotsTorre()[3].setTorre(Imagem.TORRE_UM_ANIMESET, new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET));
//		getSlotsTorre()[3].setAtivo(false);
		
		

	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
//		if (torreAtivaHud!=null) {
//			
//			if (torreAtivaHud.select==false)
//				torreAtivaHud=null;
//			
//			else {
//				if (Constantes.colidecircular(CanvasGame.mousex+CanvasGame.MAPA.MapX, CanvasGame.mousey+CanvasGame.MAPA.MapY, 10,torreAtivaHud.getX()- Constantes.HUD_TORRE_STARTX+Constantes.HUD_TORRE_SIZEX/2, torreAtivaHud.getY()- Constantes.HUD_TORRE_STARTY+Constantes.HUD_TORRE_SIZEY/2, Constantes.HUD_TORRE_SIZEY/2)) {
//					
//					torreAtivaHud.setTimerSelect();
//				}
//			}
//			
//			
//			
//			}
//		  
		
		
	}
	

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
//		if (torreAtivaHud!=null) {
//			desenhaHudTorre(dbg,XMundo,YMundo);
//			
//		}

		Stroke stk = dbg.getStroke();
		    
		dbg.setStroke(new BasicStroke(1.5f));
	
		dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		dbg.fillRect(xHudArmas, yHudArmas, sizeXHudArmas, sizeYHudArmas);
	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

		for(int i = 0; i < getSlotsTorre().length; i++){
			getSlotsTorre()[i].DesenhaSe(dbg, XMundo, YMundo);
		}
		
	    dbg.setStroke(new BasicStroke(1.0f));


		//hud XP
		oldFont=dbg.getFont();
		dbg.setFont(fontXp);
		dbg.setColor(Color.red);
		dbg.drawString("XP: "+getXpHud(), GamePanel.PWIDTH/2, 20);
		dbg.setColor(Color.black);
		dbg.drawString("XP: "+getXpHud(), GamePanel.PWIDTH/2-2, 20-2);
		dbg.setFont(oldFont);	
		
		
		
		
		
		
	}
//	private void desenhaHudTorre(Graphics2D dbg, int xMundo, int yMundo) {
//		// TODO Auto-generated method stub
//
//			dbg.setColor(Color.blue);
//			dbg.drawRect((int)torreAtivaHud.getX()-Constantes.HUD_TORRE_STARTX-xMundo,(int)torreAtivaHud.getY()-Constantes.HUD_TORRE_STARTY-yMundo, Constantes.HUD_TORRE_SIZEX, Constantes.HUD_TORRE_SIZEY);
//		
//			desenhaBotaoHud(dbg, xMundo, yMundo);
//			
//			
//			if(rangeAtivo) {
//				dbg.setColor(Color.red);
//				dbg.drawOval((int)torreAtivaHud.getX()-torreAtivaHud.getRange()/2-xMundo, (int)torreAtivaHud.getY()-torreAtivaHud.getRange()/2-yMundo, torreAtivaHud.getRange(), torreAtivaHud.getRange());
//
//			}
//		
//		
//	}
//	private void desenhaBotaoHud(Graphics2D dbg, int xMundo, int yMundo) {
//		// TODO Auto-generated method stub
//		
//		//botao 1
//		dbg.drawOval((int)torreAtivaHud.getX()-Constantes.HUD_TORRE_STARTX-xMundo+5,(int)torreAtivaHud.getY()-Constantes.HUD_TORRE_STARTY-yMundo+8, 20, 10);
//		//botao 2
//		dbg.drawOval((int)torreAtivaHud.getX()-Constantes.HUD_TORRE_STARTX-xMundo+5,(int)torreAtivaHud.getY()-Constantes.HUD_TORRE_STARTY-yMundo+30, 20, 10);
//		//botao 3
//		dbg.drawOval((int)torreAtivaHud.getX()-Constantes.HUD_TORRE_STARTX-xMundo+5,(int)torreAtivaHud.getY()-Constantes.HUD_TORRE_STARTY-yMundo+55, 20, 10);
//		
//	}
	public static void setXpHud(int _xpHud) {
		xpHud = _xpHud;
	}
	public int getXpHud() {
		return xpHud;
	}
	public void setSlotsTorre(SlotTorre[] slotsTorre) {
		this.slotsTorre = slotsTorre;
	}
	public SlotTorre[] getSlotsTorre() {
		return slotsTorre;
	}
	
//	public void desativaHudTorre() {
//		// TODO Auto-generated method stub
//		torreAtivaHud=null;
//	}
//	
//	public void ativaHudTorre(Torre torre) {
//		// TODO Auto-generated method stub
//		torreAtivaHud=torre;
//		
//		
//	}

}
