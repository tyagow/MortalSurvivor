import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Constantes {
	


	//public static final int MAXIMO_PARTICULAS_ESTATICAS = 1000;

	//public static final int PERSONAGEM_TEMPO_TIRO = 100;
	
	//public static BufferedImage mira1 ;


	public static final int BASE_SIZEX_1 = 80;
	public static final int BASE_SIZEY_1 = 80;
	public static final int BASE_LIFE_1 = 1000;


	//public static  BufferedImage inimigoUm ;


	public static BufferedImage TORRE_UM_ANIMESET;
	public static double HE_RANGE=40;

	//Pistola
	public static final int PISTOLA_mag = 3;
	public static final int PISTOLA_dano=66;
	public static final int PISTOLA_peso=30;
	public static final int PISTOLA_round=6;
	public static final int PISTOLA_tempoEntreTiros=100;
	public static final int PISTOLA_tempoRecarrega=1000;
	public static final int PISTOLA_valor=10;
	
	//metralhadora
	public static final int METRALHADORA_mag = 3;
	public static final int METRALHADORA_dano=33;
	public static final int METRALHADORA_peso=60;
	public static final int METRALHADORA_round=30;
	public static final int METRALHADORA_tempoEntreTiros=100;
	public static final int METRALHADORA_tempoRecarrega=1500;
	public static final int METRALHADORA_valor=50;

	//Faca
	public static final int FACA_dano = 70;
	public static final int FACA_peso = 0;
	public static final int FACA_round = 5;
	public static final int FACA_valor = 50;
	public static final int FACA_tempoAtaque = 200;
	public static final int FACA_tempoEntreTiros = 400;
	
	//he
	public static final int HE_mag = 10;
	public static final int HE_dano=100;
	public static final int HE_peso=10;
	public static final int HE_round=1;
	public static final int HE_tempoEntreTiros=100;
	public static final int HE_tempoRecarrega=1500;
	public static final int HE_valor=500;

	//Sangue
	public static final int SANGUE_SIZE_X = 10;
	public static final int SANGUE_SIZE_Y = 10;
	
	//Fonte
	public static final Font big = new Font("SansSerif", Font.BOLD, (int) 24);


	// Torre 
	public static final int TIPO_ASSASINO_PLAYER = 1;
	public static final int TEMPO_ENTRE_ADD_TORRES = 500;
	public static final int TEMPO_TORRE_CONSTRUCAO = 2000;
	public static final int TEMPO_TORRE_SELECIONADA = 2000;
	//torre Arma
	public static final int TORRE_ARMA_UM_dano = 34;
	public static final int TORRE_ARMA_UM_mag = 5;
	public static final int TORRE_ARMA_UM_peso = 0;
	public static final int TORRE_ARMA_UM_round = 100;
	public static final int TORRE_ARMA_UM_valor=200;
	public static final int TORRE_ARMA_UM_tempoEntreTiros = 400;
	public static final int TORRE_ARMA_UM_tempoRecarrega=1000; 
	
	//torre arma 2
	public static final int TORRE_ARMA_DOIS_dano = 34;
	public static final int TORRE_ARMA_DOIS_mag = 5;
	public static final int TORRE_ARMA_DOIS_peso = 0;
	public static final int TORRE_ARMA_DOIS_round = 100;
	public static final int TORRE_ARMA_DOIS_valor=200;
	public static final int TORRE_ARMA_DOIS_tempoEntreTiros = 200;
	public static final int TORRE_ARMA_DOIS_tempoRecarrega=1000; 
	//torre arma 3
	public static final int TORRE_ARMA_TRES_dano = 34;
	public static final int TORRE_ARMA_TRES_mag = 5;
	public static final int TORRE_ARMA_TRES_peso = 0;
	public static final int TORRE_ARMA_TRES_round = 100;
	public static final int TORRE_ARMA_TRES_valor=200;
	public static final int TORRE_ARMA_TRES_tempoEntreTiros = 130;
	public static final int TORRE_ARMA_TRES_tempoRecarrega=1000; 


	// HUD Torre
	public static final int HUD_TORRE_STARTX = 40;
	public static final int HUD_TORRE_STARTY = 100;
	public static final int HUD_TORRE_SIZEX = 80;
	public static final int HUD_TORRE_SIZEY = 80;


	// XP
	public static final int GANHO_XP_PLAYER = 10;
	public static final int GANHO_XP_TORRE = 5;


	// Mouse
	public static final int MOUSE_SIZEY = 40;
	public static final int MOUSE_SIZEX = 40;

	public static final double INIMIGO_CAMPO_VISAO1 = 200;


	public static final double TEMPO_ENTRE_RAIDS = 5000;


	public static final double MINIMAP_X = 50;
	public static final double MINIMAP_Y = 50;
	public static final double MINIMAP_SIZEX = 100;
	public static final double MINIMAP_SIZEY = 100;


	public static final double SELECIONADOR_DE_TORRE_X = GamePanel.PWIDTH - 260;
	public static final double SELECIONADOR_DE_TORRE_Y = GamePanel.PHEIGHT - 70;
	public static final int SELECIONADOR_DE_TORRE_SIZEX = 250;
	public static final int SELECIONADOR_DE_TORRE_SIZEY = 70;


	public static final int SLOT_SIZEX = 50;
	public static final int SLOT_SIZEY = 50;


	public static final double SLOT_UM_X = SELECIONADOR_DE_TORRE_X + 10;
	public static final double SLOT_UM_Y = SELECIONADOR_DE_TORRE_Y + 10;
	public static final double SLOT_DOIS_X = SLOT_UM_X + SLOT_SIZEX + 10;
	public static final double SLOT_DOIS_Y = SLOT_UM_Y;
	public static final double SLOT_TRES_X = SLOT_DOIS_X + SLOT_SIZEX + 10;
	public static final double SLOT_TRES_Y = SLOT_UM_Y;
	public static final double SLOT_QUATRO_X = SLOT_TRES_X + SLOT_SIZEX + 10;
	public static final double SLOT_QUATRO_Y = SLOT_UM_Y;
	
//efeito triple XP	
	public static final int QUANTIDADE_FAST_KILL = 10;
	public static double tempoEntreKill=2500;

	//aura base
	public static final int AURA_BASE_DIAMETRO = 175;
	public static final int AURA_BASE_TIMER = 3000;
	

	public static BufferedImage LoadImage(String filename){
		BufferedImage image = null;
		
		
		BufferedImage imgtmp;
		try {
			imgtmp = ImageIO.read( GamePanel.instance.getClass().getResourceAsStream(filename));
			image = new BufferedImage(imgtmp.getWidth(), imgtmp.getHeight(), BufferedImage.TYPE_INT_ARGB);
			image.getGraphics().drawImage(imgtmp, 0, 0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return image;
	}


	public static boolean colidecircular(double X1,double Y1,double R1,double X2,double Y2,double R2){
		
		double dx = X1-X2;
		double dy = Y1-Y2;
		double sr = R1+R2;
		double d2 = dx*dx + dy*dy;
		
		if(d2<(sr*sr)){
			return true;
		}
		
		return false;
	}
	
	public static boolean colideQuadrado(int x1, int y1, int sizeX1, int sizeY1, int x2, int y2, int sizeX2, int sizeY2){
		if(((x1+sizeX1<x2 || x1>x2+sizeX2)) || ((y1+sizeY1<y2 || y1>y2+sizeY2)))
			return false;
		return true;
	}



}