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

	
	public Imagem() {
		
		
			logo=Constantes.LoadImage("logo.png");
			miraMenu= Constantes.LoadImage("miraMenu.png");
			inimigoUm =Constantes.LoadImage("zombie.png");				
			tileset = Constantes.LoadImage("Bridge.png");
			mira1= Constantes.LoadImage("mira1.png");	
			he = Constantes.LoadImage("he.png");
			explosao = Constantes.LoadImage("explosao.png");
			explosao2 = Constantes.LoadImage("explosao2.png");

			TORRE_UM_ANIMESET = Constantes.LoadImage("torre.png");
			TORRE_DOIS_ANIMESET = Constantes.LoadImage("torre2.png");
			TORRE_TRES_ANIMESET = Constantes.LoadImage("torre3.png");



	}
 
}
