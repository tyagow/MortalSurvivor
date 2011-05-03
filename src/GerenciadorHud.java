import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.LinkedList;

public class GerenciadorHud extends Objeto {

	private Font oldFont;
	private Font fontXp = Constantes.fonteBig;
	private static int xpHud;
	Torre torreAtivaHud;
	
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
	

	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
	  
			atualizaHudArma();
		
	}
	

	private void atualizaHudArma() {
	// TODO Auto-generated method stub
		
		switch (Heroi.getArma()) {
		case Heroi.IDX_ARMA_MELEE:
			setHudAtivo(0);
			break;
		case Heroi.IDX_ARMA_PRIMARIA:
			setHudAtivo(2);

			break;
		case Heroi.IDX_ARMA_SECUNDARIA:
			setHudAtivo(1);

			break;
		case Heroi.IDX_ARMA_GRANADA:
			setHudAtivo(3);

			break;

		default:
			break;
		}
		
	}
	private void setHudAtivo(int i) {
		// TODO Auto-generated method stub
		
		
		for (int j =0;j<slotsTorre.length;j++) {
			
			if (j!=i)	{
				slotsTorre[j].setAtivo(false);
			}
			else {
				slotsTorre[j].setAtivo(true);

			}
		
		}
	}
	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub

		Stroke stk = dbg.getStroke();
		    
		dbg.setStroke(new BasicStroke(1.5f));
		dbg.setColor(Color.DARK_GRAY);
		dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		dbg.fillRect(xHudArmas, yHudArmas, sizeXHudArmas, sizeYHudArmas);
	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

		for(int i = 0; i < getSlotsTorre().length; i++){
			getSlotsTorre()[i].DesenhaSe(dbg, XMundo, YMundo);
		}
		
	    dbg.setStroke(stk);


		//hud XP
		oldFont=dbg.getFont();
		dbg.setFont(fontXp);
		dbg.setColor(Color.red);
		dbg.drawString("XP: "+getXpHud(), GamePanel.PWIDTH/2, 20);
		dbg.setColor(Color.black);
		dbg.drawString("XP: "+getXpHud(), GamePanel.PWIDTH/2-2, 20-2);
		dbg.setFont(oldFont);	
		
	
		
	}

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
	public static void setBotoes(LinkedList<Botao> botoes) {
		GerenciadorHud.botoes = botoes;
	}
	public static LinkedList<Botao> getBotoes() {
		return botoes;
	}
	


}
