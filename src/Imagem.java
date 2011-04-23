import java.awt.image.BufferedImage;


public class Imagem {

	public static BufferedImage miraMenu;
	public static BufferedImage tileset;
	public static BufferedImage mira1;
	public static BufferedImage TORRE_UM_ANIMESET;
	public static BufferedImage inimigoUm;
	public static BufferedImage logo;

	
	public Imagem() {
		
			logo=Constantes.LoadImage("logo.png");
			miraMenu= Constantes.LoadImage("miraMenu.png");
			inimigoUm =Constantes.LoadImage("zombie.png");				
			tileset = Constantes.LoadImage("Bridge.png");
			mira1= Constantes.LoadImage("mira1.png");	
			TORRE_UM_ANIMESET = Constantes.LoadImage("torre.png");

	}
 
}
