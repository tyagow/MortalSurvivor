import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Constantes {
	


	//public static final int MAXIMO_PARTICULAS_ESTATICAS = 1000;

	//public static final int PERSONAGEM_TEMPO_TIRO = 100;
	
	public static BufferedImage mira1 ;



	public static final ThreadSom ak=new ThreadSom("/ak47.wav");
	//Pistola
	public static final int PISTOLA_mag = 3;
	public static final int PISTOLA_dano=66;
	public static final int PISTOLA_peso=30;
	public static final int PISTOLA_round=6;
	public static final int PISTOLA_tempoEntreTiros=100;
	public static final int PISTOLA_tempoRecarrega=1000;
	public static final int PISTOLA_valor=10;

	//Faca
	public static final int FACA_dano = 70;
	public static final int FACA_peso = 0;
	public static final int FACA_round = 5;
	public static final int FACA_valor = 50;
	public static final int FACA_tempoAtaque = 200;
	public static final int FACA_tempoEntreTiros = 400;

	//Sangue
	public static final int SANGUE_SIZE_X = 5;
	public static final int SANGUE_SIZE_Y = 5;
	
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
	


}
