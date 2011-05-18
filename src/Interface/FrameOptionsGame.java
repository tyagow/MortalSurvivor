package Interface;

import java.awt.Color;


import Constantes.Constantes;
import GameState.GamePanel;
import Mouse.Mira;
import Mouse.MiraDoJogoEstatica;
import Mouse.MiraJogo;


public class FrameOptionsGame extends FrameBase {

	Color[] corMira = new Color[6];
	int indCor=0;
	
	int sizeMira=Constantes.DADOS_miraSize;
	
	String mira = "Mira";
	static Mira tipoMira;
//	static MiraQuadrada miraQuadrada;
	static MiraJogo miraJogo;
	
	public FrameOptionsGame(int _x, int _y, int sizeX, int sizeY, Color cor, int _tempoVida) {
		super(_x, _y, sizeX, sizeY, cor, _tempoVida);
		corMira[0] = Color.black;
		corMira[1] = Color.yellow;
		corMira[2] = Color.red;
		corMira[3] = Color.blue;
		corMira[4] = Color.cyan;
		corMira[5] = Color.green;
		
		criaBotoes();
		alpha=100;
//		Texto aux = new Texto();
//		aux.X=X+50;
//		aux.Y=Y+30;
//		aux.size=18;
//		aux.text=mira;
//		aux.tipo=-1;
//		aux.big= new Font("SansSerif", Font.BOLD, (int) aux.size);

//		objetos.add(aux);
		
		//"interface texto mira "
		Botao au = new BotaoTela(null, "Mira",(int) X,(int) Y,(int)(GamePanel.PWIDTH-X)/2 , 20, false);
		au.sizeY=30;
		au.cor = new Color (150,150,150,100);
		au.corText= new Color (200,0,0,250);
		objetos.add(au);
		
		
		//mira estatica
		miraJogo= new MiraDoJogoEstatica();
		miraJogo.X = X+200;
		miraJogo.Y = Y+100;
		
		objetos.add(miraJogo);

	}
	
	
	@Override
	protected void trataBotao(Botao b) {

		if (b.name.contains("Tipo") ) {
			
			if (Constantes.DADOS_miraDoJogoOval)
				Constantes.DADOS_miraDoJogoOval=false;
			else 
				Constantes.DADOS_miraDoJogoOval=true;
			
			Constantes.EVENT_atualizaMira=true;
		}
		else if (b.name.contains("Cor") ) {
			if (indCor<5) {
				indCor++;
			}else {
				indCor =0;
			}
			Constantes.DADOS_miraCor=corMira[indCor];
			Constantes.EVENT_atualizaMira=true;
			
			
		}
		else if (b.name.contains("Tamanho") ) {
			if (sizeMira<40)
				sizeMira+=5;
			else 
				sizeMira=10;
			
			Constantes.DADOS_miraSize=sizeMira;

			Constantes.EVENT_atualizaMira=true;
		}
		
	}

	private void criaBotoes() {
		Botao b =new BotaoTexto(null,"Tipo",(int)X+40,(int)Y+60,80,16,false);
	
		botoes.add(b);
		
		botoes.add(new BotaoTexto(null,"Cor",(int)X+30,(int)Y+90,80,16,false));
		botoes.add(new BotaoTexto(null,"Tamanho",(int)X+30,(int)Y+120,80,16,false));
		
		
	}






}
