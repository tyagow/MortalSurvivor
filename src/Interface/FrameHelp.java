package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import Efeitos.Texto;
import GameState.GamePanel;


public class FrameHelp extends FrameBase {

	
	public FrameHelp(int _x, int _y, int sizeX, int sizeY, Color cor, int _tempoVida) {
		super(_x, _y, sizeX, sizeY, cor, _tempoVida);

		criaBotoes();
		alpha=100;
		criaTexto();
		
		
		
		
	}
	

	private void criaTexto() {
		// TODO Auto-generated method stub
		
		Texto aux[] = new Texto[20] ;
		aux[0]=new Texto();
		aux[0].tipo=-1;
		aux[0].big= new Font("SansSerif", Font.BOLD, (int) 14);
		aux[0].cor=Color.yellow;
		aux[0].text = "<W> Movimenta personagem para cima";
		aux[0].X=X+100;
		aux[0].Y=Y+130;
		objetos.add(aux[0]);
		
		
		Botao au = new BotaoTela(null, "Teclas",(int) X+100,(int) Y+50,(int)(GamePanel.PWIDTH-X)/2 , 20, false);
		au.sizeY=30;
		au.cor = new Color (150,150,150,100);
		au.corText= new Color (250,200,0,255);
		objetos.add(au);
//
		aux[1]=new Texto();
		aux[1].tipo=-1;
		aux[1].big= new Font("SansSerif", Font.BOLD, (int) 14);
		aux[1].cor=Color.yellow;
		aux[1].text = "<S> Movimenta personagem para baixo";
		aux[1].X=X+100;
		aux[1].Y=Y+150;
		objetos.add(aux[1]);
		
		
		aux[2]=new Texto();
		aux[2].tipo=-1;
		aux[2].big= new Font("SansSerif", Font.BOLD, (int) 14);
		aux[2].cor=Color.yellow;
		aux[2].text = "<A> Movimenta personagem para esquerda";
		aux[2].X=X+100;
		aux[2].Y=Y+170;
		objetos.add(aux[2]);
		
		
		aux[3]=new Texto();
		aux[3].tipo=-1;
		aux[3].big= new Font("SansSerif", Font.BOLD, (int) 14);
		aux[3].cor=Color.yellow;
		aux[3].text = "<D> Movimenta personagem para direita";
		aux[3].X=X+100;
		aux[3].Y=Y+190;
		objetos.add(aux[3]);
		
		aux[4]=new Texto();
		aux[4].tipo=-1;
		aux[4].big= new Font("SansSerif", Font.BOLD, (int) 14);
		aux[4].cor=Color.yellow;
		aux[4].text = "<Mouse1> Atira / Seleciona Torre";
		aux[4].X=X+100;
		aux[4].Y=Y+230;
		objetos.add(aux[4]);
		
		
		aux[5]=new Texto();
		aux[5].tipo=-1;
		aux[5].big= new Font("SansSerif", Font.BOLD, (int) 14);
		aux[5].cor=Color.yellow;
		aux[5].text = "<Mouse2> Seleciona Arma Primaria";
		aux[5].X=X+100;
		aux[5].Y=Y+250;
		objetos.add(aux[5]);
		
		aux[6]=new Texto();
		aux[6].tipo=-1;
		aux[6].big= new Font("SansSerif", Font.BOLD, (int) 14);
		aux[6].cor=Color.yellow;
		aux[6].text = "<G> Seleciona Granada";
		aux[6].X=X+100;
		aux[6].Y=Y+270;
		objetos.add(aux[6]);
		
		aux[7]=new Texto();
		aux[7].tipo=-1;
		aux[7].big= new Font("SansSerif", Font.BOLD, (int) 14);
		aux[7].cor=Color.yellow;
		aux[7].text = "<1> Seleciona Faca";
		aux[7].X=X+100;
		aux[7].Y=Y+290;
		objetos.add(aux[7]);
		
		aux[8]=new Texto();
		aux[8].tipo=-1;
		aux[8].big= new Font("SansSerif", Font.BOLD, (int) 14);
		aux[8].cor=Color.yellow;
		aux[8].text = "<2> Seleciona Pistola";
		aux[8].X=X+100;
		aux[8].Y=Y+310;
		objetos.add(aux[8]);
		
		
		aux[9]=new Texto();
		aux[9].tipo=-1;
		aux[9].big= new Font("SansSerif", Font.BOLD, (int) 14);
		aux[9].cor=Color.yellow;
		aux[9].text = "<3> Cria Torre";
		aux[9].X=X+100;
		aux[9].Y=Y+330;
		objetos.add(aux[9]);
		
		
	
	}


	@Override
	protected void trataBotao(Botao b) {
		// TODO Auto-generated method stub

		if (b.name.contains("Video") ) {

//		
//			if (!frames.contains(frameVideo)) {
//				frameVideo.ativo=true;
//				frames.add(frameVideo);
//			}	
//				else {
//					frameVideo.ativo=false;
//				}
				
			
	
		}
		else if (b.name.contains("Options") ) {
			
			
		}
		else if (b.name.contains("Game") ) {
			

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
//
//		botoes.add(new Botao(null,"Video",(int)X+30,(int)Y+30,90,18,false));
//		botoes.add(new Botao(null,"Som",(int)X+130+5,(int)Y+30,90,18,false));
//		botoes.add(new Botao(null,"Game",(int)X+230+5,(int)Y+30,90,18,false));
//		getBotoes().add(new Botao(null,"",(int)getX()+10,(int)getY()+110,120,25,false));
//		getBotoes().add(new Botao(null,"Options",(int)getX()+10,(int)getY()+160,120,25,false));
//		getBotoes().add(new Botao(null,"Exit",(int)getX()+10,(int)getY()+210,120,25,false));
		
		
	}






}
