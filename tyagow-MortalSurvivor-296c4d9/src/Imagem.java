import java.awt.image.BufferedImage;


public class Imagem {

	public static BufferedImage miraMenu;
	public static BufferedImage tileset;
	public static BufferedImage mira1;
	public static BufferedImage inimigoUm;
	public static BufferedImage logo;
	public static BufferedImage he;
	
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

	
	public Imagem() {
		
		
			logo=Constantes.LoadImage("logo.png");
			miraMenu= Constantes.LoadImage("miraMenu.png");
			inimigoUm =Constantes.LoadImage("zombie.png");				
			tileset = Constantes.LoadImage("Bridge.png");
			mira1= Constantes.LoadImage("mira1.png");	
			explosao = Constantes.LoadImage("explosao.png");
			explosao2 = Constantes.LoadImage("explosao2.png");

			TORRE_UM_ANIMESET = Constantes.LoadImage("torre.png");
			TORRE_DOIS_ANIMESET = Constantes.LoadImage("torre2.png");
			TORRE_TRES_ANIMESET = Constantes.LoadImage("torre3.png");

			deagle= Constantes.LoadImage("deagle.png");
			m4=Constantes.LoadImage("m4a.png");
			deagle_hud=Constantes.LoadImage("img/deagle_hud.png");
			m4_hud=Constantes.LoadImage("img/m4_hud.png");
			he = Constantes.LoadImage("he.png");
			he_hud = Constantes.LoadImage("img/he_hud.png");
			faca =Constantes.LoadImage("knife.png");
			
			
			heroiUm=Constantes.LoadImage("t1.png");
	}
 
}
