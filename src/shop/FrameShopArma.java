package shop;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import com.sun.accessibility.internal.resources.accessibility;

import Data.Imagem;
import Gerenciadores.GerenciadorArma;
import Gerenciadores.GerenciadorHud;
import Interface.Botao;
import Interface.BotaoTela;
import Interface.FrameBase;
import Personagem.Heroi;


public class FrameShopArma extends FrameBase {

	public static final int PRIMARIA = 0;
	public static final int SECUNDARIA = 1;
	public static final int ESPECIAL = 2;
	
	ArrayList<Botao> armas = new ArrayList<Botao>();
	
	public FrameShopArma(int _x, int _y, int sizeX, int sizeY, Color cor, int _tempoVida) {
		super(_x, _y, sizeX, sizeY, cor, _tempoVida);

//		criaBotoes();
		alpha=200;
		
		for (int i=0; i < GerenciadorArma.armas.size(); i++){
			
			armas.add(new BotaoTela(Imagem.ak47_hud, "ak47", (int) X + 100, (int) Y + 50, Imagem.ak47_hud.getWidth()*2, Imagem.ak47_hud.getHeight()*2, false));
			armas.add(new BotaoTela(Imagem.m4_hud, "m4", (int) X + 250, (int) Y + 50, Imagem.ak47_hud.getWidth()*2, Imagem.ak47_hud.getHeight()*2, false));
			armas.add(new BotaoTela(Imagem.deagle_hud, "deagle", (int) X + 100, (int) Y + 150, Imagem.ak47_hud.getWidth()*2, Imagem.ak47_hud.getHeight()*2, false));
			armas.add(new BotaoTela(Imagem.he_hud, "he", (int) X + 100, (int) Y + 250, Imagem.ak47_hud.getWidth()*2, Imagem.ak47_hud.getHeight()*2, false));
			armas.add(new BotaoTela(Imagem.mp5_hud, "mp5", (int) X + 400, (int) Y + 50, Imagem.ak47_hud.getWidth()*2, Imagem.ak47_hud.getHeight()*2, false));

		}
		
		for(int i = 0; i < armas.size(); i++){
			botoes.add(armas.get(i));
		}
	
		
	}
	
	
//	@Override
//	public void SimulaSe(int DiffTime) {
//		// TODO Auto-generated method stub
//		for (int x=0;x<getBotoesMenu().size();x++) {
//			Botao b= getBotoesMenu().get(x);
//			
//			b.SimulaSe((int)DiffTime);			
//			if (b.ativo==true) {
//				trataBotao(b);
//				getBotoesMenu().get(x).ativo=false;
//			}		
//		}
//		
//		if (menuAtivo!=null) 
//			menuAtivo.SimulaSe(DiffTime);
//		
//
//	}
//	private ArrayList<Botao> getBotoesMenu() {
//		// TODO Auto-generated method stub
//		return botoes;
//	}
//
//
//
//	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
//		// TODO Auto-generated method stub
//		
//		dbg.setColor(Color.white);
//		//dbg.drawRect((int)getX()-XMundo,(int)getY()-YMundo, getSizeX(), getSizeY());
//		
//		dbg.setColor(new Color(r,g,b,alpha));
//		
//		dbg.fillRect((int)getX()+1-XMundo,(int)getY()-YMundo+1, getSizeX()-2, getSizeY()-2);
//		
//		Iterator<Botao> it = getBotoesMenu().iterator();
//		while(it.hasNext()){
//			Botao bot= it.next();
//			
//			bot.DesenhaSe(dbg, XMundo, YMundo);
//			
//			//trataBotao(bot);
//		
//		}
//		if (menuAtivo!=null) 
//			menuAtivo.DesenhaSe(dbg, XMundo, YMundo);
//		
//
//	}
	@Override
	protected void trataBotao(Botao b) {
		// TODO Auto-generated method stub

		if(b.name.contains("mp5")){
			trocaArma(GerenciadorArma.MP5, PRIMARIA);
		}
		
		if(b.name.contains("ak47")){
			trocaArma(GerenciadorArma.AK47, PRIMARIA);
		}
		
		else if(b.name.contains("m4")){
			trocaArma(GerenciadorArma.M4, PRIMARIA);
		}
		
		else if(b.name.contains("deagle")){
			trocaArma(GerenciadorArma.DEAGLE, SECUNDARIA);
		}
		
		else if(b.name.contains("he")){
			trocaArma(GerenciadorArma.HE, ESPECIAL);
		}
		
	}

	private void criaBotoes() {
		// TODO Auto-generated method stubB

		botoes.add(new Botao(null,"Armas",(int)X+30,(int)Y+30,90,18,false));
		botoes.add(new Botao(null,"Torres",(int)X+130+5,(int)Y+30,90,18,false));
		botoes.add(new Botao(null,"voltar",(int)X+230+5,(int)Y+30,90,18,false));
//		getBotoes().add(new Botao(null,"",(int)getX()+10,(int)getY()+110,120,25,false));
//		getBotoes().add(new Botao(null,"Options",(int)getX()+10,(int)getY()+160,120,25,false));
//		getBotoes().add(new Botao(null,"Exit",(int)getX()+10,(int)getY()+210,120,25,false));
		
		
	}

	private void trocaArma(int _arma, int _categoria){
		
		switch (_categoria) {
			case 0:
				GerenciadorArma.primariaAtiva = GerenciadorArma.armas.get(_arma);
				Heroi.armaPrimaria = GerenciadorArma.primariaAtiva;
				Heroi.PRIMARIA = true;
				Heroi.SECUNDARIA = false;
				Heroi.MELEE = false;
				Heroi.ESPECIAL = false;
				GerenciadorHud.hudArma[2].setSlot(Heroi.getArmaPrimaria());

				break;
			case 1:
				GerenciadorArma.secundariaAtiva = GerenciadorArma.armas.get(_arma);
				Heroi.armaSecundaria = GerenciadorArma.secundariaAtiva;
				Heroi.PRIMARIA = false;
				Heroi.SECUNDARIA = true;
				Heroi.MELEE = false;
				Heroi.ESPECIAL = false;
				GerenciadorHud.hudArma[1].setSlot(Heroi.getArmaSecundaria());
				break;
			case 2:
				GerenciadorArma.especialAtiva = GerenciadorArma.armas.get(_arma);
				Heroi.armaEspecial = GerenciadorArma.especialAtiva;
				Heroi.PRIMARIA = false;
				Heroi.SECUNDARIA = false;
				Heroi.MELEE = false;
				Heroi.ESPECIAL = true;
				GerenciadorHud.hudArma[3].setSlot(Heroi.getArmaGranada());
				break;
				
			default:
				break;
		}			
		
	}




}
