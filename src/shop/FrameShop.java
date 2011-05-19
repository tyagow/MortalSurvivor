package shop;

import java.awt.Color;

import Interface.Botao;
import Interface.BotaoTela;
import Interface.FrameBase;


public class FrameShop extends FrameBase {
	FrameShopArma frameShopArma;
	FrameShopTorre frameShopTorre;
	
	public FrameShop(int _x, int _y, int sizeX, int sizeY, Color cor, int _tempoVida) {
		super(_x, _y, sizeX, sizeY, cor, _tempoVida);
		frameShopArma=new  FrameShopArma(_x, _y+80, sizeX, sizeY-80, cor, _tempoVida);
		frameShopTorre=new FrameShopTorre(_x, _y+80, sizeX, sizeY-80, cor, _tempoVida);
		criaBotoes();
		alpha=100;
	}
	


	@Override
	protected void trataBotao(Botao b) {
		// TODO Auto-generated method stub

		if (b.name.contains("Armas") ) {
//			frameAtivo=frameVideo;
//		
//			if (!frames.contains(frameVideo)) {
//				frameVideo.ativo=true;
//				frames.add(frameVideo);
//			}	
//				else {
//					frameVideo.ativo=false;
//				}
				
			
	
		}
		else if (b.name.contains("Torres") ) {
			
			frameAtivo=frameShopTorre;
		}
		else if (b.name.contains("voltar") ) {
			
		
//			if (!frames.contains(frameGame)) {
//				frameGame.ativo=true;
//				frames.add(frameGame);
//			}	
//				else {
//					frameGame.ativo=false;
//				}
				
			
		}
		
	}

	private void criaBotoes() {
		// TODO Auto-generated method stubB

		botoes.add(new BotaoTela(null,"Armas",(int)X+30,(int)Y+30,90,18,false));
		botoes.add(new BotaoTela(null,"Torres",(int)X+130+5,(int)Y+30,90,18,false));
		botoes.add(new BotaoTela(null,"voltar",(int)X+230+5,(int)Y+30,90,18,false));

	}






}
