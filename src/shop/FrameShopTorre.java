package shop;

import java.awt.Color;
import Armas.ArmaDoisTorre;
import Armas.ArmaTresTorre;
import Armas.ArmaUmTorre;
import Constantes.Constantes;
import Data.Imagem;
import Interface.Botao;
import Interface.BotaoTela;
import Interface.BotaoTexto;
import Interface.FrameBase;
import Torre.SlotTorre;


public class FrameShopTorre extends FrameBase {
	
	private SlotTorre slotsTorre;
	private SlotTorre slotsTorreDois;
	private SlotTorre slotsTorreTres;

	public FrameShopTorre(int _x, int _y, int sizeX, int sizeY, Color cor, int _tempoVida) {
		super(_x, _y, sizeX, sizeY, cor, _tempoVida);

		criaBotoes();
		alpha=200;
		

		
		slotsTorre = new SlotTorre();
		slotsTorre.X=(X+50);
		slotsTorre.Y=(Y+100);
		slotsTorre.setSlot(new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET));
		slotsTorre.ativo=(true);
		objetos.add(slotsTorre);
		
		slotsTorreDois = new SlotTorre();
		slotsTorreDois.X=(X+50);
		slotsTorreDois.Y=(Y+200);
		slotsTorreDois.setSlot( new ArmaDoisTorre(Imagem.TORRE_DOIS_ANIMESET));
		slotsTorreDois.ativo=(false);
		objetos.add(slotsTorreDois);
		
		slotsTorreTres = new SlotTorre();
		slotsTorreTres.X=(X+50);
		slotsTorreTres.Y=(Y+300);
		slotsTorreTres.setSlot(new ArmaTresTorre(Imagem.TORRE_TRES_ANIMESET));
		slotsTorreTres.ativo=(false);
		objetos.add(slotsTorreTres);
		

		
		
	}
	

	@Override
	protected void trataBotao(Botao b) {
		// TODO Auto-generated method stub

		if (b.name.contains("Armas") ) {

			
	
		}
		else if (b.name.contains("Torres") ) {
			

		}
		else if (b.name.contains("voltar") ) {
	
			
		}
		
	}

	private void criaBotoes() {
		// TODO Auto-generated method stubB

		botoes.add(new BotaoTexto(null,"Comprar",(int)X+130,(int)Y+110,90,16,false));
//		botoes.add(new Botao(null,"Torres",(int)X+130+5,(int)Y+30,90,18,false));
//		botoes.add(new Botao(null,"voltar",(int)X+230+5,(int)Y+30,90,18,false));
		
		
	}






}
