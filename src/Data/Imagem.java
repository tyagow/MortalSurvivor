package Data;
import java.awt.image.BufferedImage;

import Constantes.Constantes;



public class Imagem {

	public static BufferedImage miraMenu;
	public static BufferedImage tileSetMapaUm;	
	public static BufferedImage tileSetMapaDois;
	public static BufferedImage obstaculos;

	public static BufferedImage mira1;
	public static BufferedImage inimigoUm;
	public static BufferedImage logo;
	public static BufferedImage fundoCidade;
	public static BufferedImage TORRE_UM_ANIMESET;
	public static BufferedImage TORRE_DOIS_ANIMESET;
	public static BufferedImage TORRE_TRES_ANIMESET;
	public static BufferedImage explosao;
	public static BufferedImage explosao2;
	
//*********************************ARMAS ****************************************
	public static BufferedImage mp5;
	public static BufferedImage mp5_hud;
	public static BufferedImage m4;
	public static BufferedImage m4_hud;
	public static BufferedImage ak47;
	public static BufferedImage ak47_hud;
	public static BufferedImage deagle;
	public static BufferedImage deagle_hud;
	public static BufferedImage faca;
	public static BufferedImage machete;
	
	public static BufferedImage he;
	public static BufferedImage he_hud;
//************************************************************************************
	
	public static BufferedImage heroiUm;
	public static BufferedImage botaoUm;
	public static BufferedImage base;
	public static BufferedImage efeitoFaca;
	public static BufferedImage inimigoDois;

	public static BufferedImage legs;

	public static Imagem instance;
	
	// *************************************** MAPA *********************************
	
	public static BufferedImage mapaUm;
	public static BufferedImage mapaDois;
	
	public Imagem() {
			efeitoFaca=Constantes.LoadImage("img/efeitoFaca.png");
			fundoCidade=Constantes.LoadImage("img/fundoCidade.png");
			logo=Constantes.LoadImage("img/logo.png");
			miraMenu= Constantes.LoadImage("img/miraMenu.png");
			inimigoUm =Constantes.LoadImage("img/zombie.png");
			inimigoDois =Constantes.LoadImage("img/vortigaunt.png");				

			legs =Constantes.LoadImage("img/legs.png");				

			
			tileSetMapaUm = Constantes.LoadImage("img/tileSetMapaUm.png");
			tileSetMapaDois = Constantes.LoadImage("img/tileSetMapaDois.png");

			mira1= Constantes.LoadImage("img/mira1.png");	
			explosao = Constantes.LoadImage("img/explosao.png");
			explosao2 = Constantes.LoadImage("img/explosao2.png");
			obstaculos = Constantes.LoadImage("img/obstaculos.png");
			TORRE_UM_ANIMESET = Constantes.LoadImage("img/torre.png");
			TORRE_DOIS_ANIMESET = Constantes.LoadImage("img/torre2.png");
			TORRE_TRES_ANIMESET = Constantes.LoadImage("img/torre3.png");

			deagle= Constantes.LoadImage("img/deagle.png");
			mp5=Constantes.LoadImage("img/mp5.png");
			mp5_hud=Constantes.LoadImage("img/mp5_hud.png");

			m4=Constantes.LoadImage("img/m4a.png");
			m4_hud=Constantes.LoadImage("img/m4_hud.png");
			ak47=Constantes.LoadImage("img/ak47.png");
			ak47_hud=Constantes.LoadImage("img/ak47_hud.png");

			deagle_hud=Constantes.LoadImage("img/deagle_hud.png");
			he = Constantes.LoadImage("img/he.png");
			he_hud = Constantes.LoadImage("img/he_hud.png");
			faca =Constantes.LoadImage("img/knife.png");
			machete =Constantes.LoadImage("img/machete.png");
			
			
			heroiUm=Constantes.LoadImage("img/t1.png");
			
			//botaoUm=Constantes.LoadImage("img/botoes/botaoUm.png");
			base = Constantes.LoadImage("img/base.png");
			instance = this;

			
			mapaUm=Constantes.LoadImage("img/mapaUm.png");;
			mapaDois=Constantes.LoadImage("img/mapaDois.png");;
			
	}
 
}
