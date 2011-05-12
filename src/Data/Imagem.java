package Data;
import java.awt.image.BufferedImage;

import Constantes.Constantes;



public class Imagem {

	public static BufferedImage miraMenu;
	public static BufferedImage tileset;
	public static BufferedImage obstaculos;

	public static BufferedImage mira1;
	public static BufferedImage inimigoUm;
	public static BufferedImage logo;
	public static BufferedImage he;
	public static BufferedImage fundoCidade;
	public static BufferedImage TORRE_UM_ANIMESET;
	public static BufferedImage TORRE_DOIS_ANIMESET;
	public static BufferedImage TORRE_TRES_ANIMESET;
	public static BufferedImage explosao;
	public static BufferedImage explosao2;
	public static BufferedImage deagle;
	public static BufferedImage m4;
	public static BufferedImage deagle_hud;
	public static BufferedImage m4_hud;
	public static BufferedImage faca;
	public static BufferedImage heroiUm;
	public static BufferedImage he_hud;
	public static BufferedImage botaoUm;
	public static BufferedImage base;
	public static BufferedImage efeitoFaca;

	public static Imagem instance;
	
	public Imagem() {
			efeitoFaca=Constantes.LoadImage("img/efeitoFaca.png");
			fundoCidade=Constantes.LoadImage("img/fundoCidade.png");
			logo=Constantes.LoadImage("img/logo.png");
			miraMenu= Constantes.LoadImage("img/miraMenu.png");
			inimigoUm =Constantes.LoadImage("img/zombie.png");				
			tileset = Constantes.LoadImage("img/Bridge.png");
			mira1= Constantes.LoadImage("img/mira1.png");	
			explosao = Constantes.LoadImage("img/explosao.png");
			explosao2 = Constantes.LoadImage("img/explosao2.png");
			obstaculos = Constantes.LoadImage("img/obstaculos.png");
			TORRE_UM_ANIMESET = Constantes.LoadImage("img/torre.png");
			TORRE_DOIS_ANIMESET = Constantes.LoadImage("img/torre2.png");
			TORRE_TRES_ANIMESET = Constantes.LoadImage("img/torre3.png");

			deagle= Constantes.LoadImage("img/deagle.png");
			m4=Constantes.LoadImage("img/m4a.png");
			deagle_hud=Constantes.LoadImage("img/deagle_hud.png");
			m4_hud=Constantes.LoadImage("img/m4_hud.png");
			he = Constantes.LoadImage("img/he.png");
			he_hud = Constantes.LoadImage("img/he_hud.png");
			
			faca =Constantes.LoadImage("img/knife.png");
			
			
			heroiUm=Constantes.LoadImage("img/t1.png");
			
			//botaoUm=Constantes.LoadImage("img/botoes/botaoUm.png");
			base = Constantes.LoadImage("img/base.png");
			instance = this;

	}
 
}
