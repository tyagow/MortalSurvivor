package Gerenciadores;


import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.LinkedList;

import AbstractClasses.Objeto;
import Constantes.Constantes;
import GameState.GamePanel;
import Interface.Botao;
import Personagem.Heroi;
import Torre.SlotTorre;
import Torre.Torre;

public class GerenciadorHud extends Objeto {

	private Font oldFont;
	private Font fontXp = Constantes.fonteBig;
	private static int xpHud;
	Torre torreAtivaHud;
	
	private static LinkedList<Botao> botoes=new LinkedList<Botao>();
	
	
	int xHudArmas,yHudArmas,sizeXHudArmas,sizeYHudArmas;
	public static SlotTorre[] hudArma;
	
	
	
	public GerenciadorHud() {
		carregaHudArmas();
		
	}
	private void carregaHudArmas() {
		// TODO Auto-generated method stub
		
		
		xHudArmas=100;
		sizeXHudArmas=300;
		sizeYHudArmas=70;
		yHudArmas=GamePanel.PHEIGHT-sizeYHudArmas-1;
		
		
		
		hudArma=(new SlotTorre[4]);
		hudArma[0] = new SlotTorre();
		hudArma[0].setX(xHudArmas+40);
		hudArma[0].setY(yHudArmas+10);
		hudArma[0].setSlot(Heroi.getArmaMelee());
		hudArma[0].ativo=(true);


		
		hudArma[1] = new SlotTorre();
		hudArma[1].setX(hudArma[0].getX()+5+hudArma[0].getSizeX());
		hudArma[1].setY(yHudArmas+10);
		hudArma[1].setSlot(Heroi.getArmaSecundaria());
		hudArma[1].ativo=(false);
		
		
		hudArma[2] = new SlotTorre();
		hudArma[2].setX(hudArma[1].getX()+5+hudArma[1].getSizeX());
		hudArma[2].setY(yHudArmas+10);
		hudArma[2].setSlot(Heroi.getArmaPrimaria());
		hudArma[2].ativo=(false);
		
		
		hudArma[3] = new SlotTorre();
		hudArma[3].setX(hudArma[2].getX()+5+hudArma[2].getSizeX());
		hudArma[3].setY(yHudArmas+10);
		hudArma[3].setSlot(Heroi.getArmaGranada());
		hudArma[3].ativo=(false);
	

	}
	@Override
	public void SimulaSe(int DiffTime) {
	  
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
		
		
		for (int j =0;j<hudArma.length;j++) {
			
			if (j!=i)	{
				hudArma[j].ativo=(false);
			}
			else {
				hudArma[j].ativo=(true);

			}
		
		}
	}
	@Override
	public void DesenhaSe(Graphics2D dbg, int XTela, int YTela) {
		// TODO Auto-generated method stub

		Stroke stk = dbg.getStroke();
		    
		dbg.setStroke(new BasicStroke(1.5f));
		dbg.setColor(Color.LIGHT_GRAY);
		dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		dbg.fillRect(xHudArmas, yHudArmas, sizeXHudArmas, sizeYHudArmas);
	    dbg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	    dbg.setStroke(stk);
		for(int i = 0; i < hudArma.length; i++){
			hudArma[i].DesenhaSe(dbg, XTela, YTela);
			desenhaQuantidadeTiros(dbg,hudArma[i]);
		}
		
	  
	    

		//hud XP
		oldFont=dbg.getFont();
		dbg.setFont(fontXp);
		dbg.setColor(Color.red);
		dbg.drawString("XP: "+xpHud, GamePanel.PWIDTH/2-30, 50);
		dbg.setColor(Color.black);
		dbg.drawString("XP: "+xpHud, GamePanel.PWIDTH/2-32, 50-2);
		dbg.setFont(oldFont);	
		//LIFE
		dbg.drawRect((int)GamePanel.PWIDTH/2-150, (int)5, 300, 25);
		dbg.setColor(Color.lightGray);
		dbg.fillRect((int)GamePanel.PWIDTH/2-149, (int)6, (int)(Constantes.baseLife*300/Constantes.BASE_LIFE_1)-2, 23);
		
	
		
	}

	private void desenhaQuantidadeTiros(Graphics2D dbg,SlotTorre slotHudArma) {
		
		Font f =  dbg.getFont();
		dbg.setFont(Constantes.FonteNormal);
		
		int round = slotHudArma.armaAtiva.round;
		int mag = slotHudArma.armaAtiva.mag;
		int _x = (int)slotHudArma.getX()+slotHudArma.getSizeX()-25;
		int _y = (int)slotHudArma.getY()+slotHudArma.getSizeY()-35;
		dbg.setColor(Color.black);
		dbg.drawString("r:"+round, _x-23, _y);
		dbg.drawString("m:"+mag, _x, _y);
		
		
	}
	public static void setXpHud(int _xpHud) {
		xpHud = _xpHud;
	}
	public int getXpHud() {
		return xpHud;
	}
	public void setHudArma(SlotTorre[] _hudArma) {
		this.hudArma = _hudArma;
	}

	public static void setBotoes(LinkedList<Botao> botoes) {
		GerenciadorHud.botoes = botoes;
	}
	public static LinkedList<Botao> getBotoes() {
		return botoes;
	}
	public void reset() {
		xpHud=0;
	}
	


}
