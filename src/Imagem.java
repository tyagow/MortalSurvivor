import java.awt.image.BufferedImage;


public class Imagem {

	public static BufferedImage miraMenu;
	public static BufferedImage tileset;
	
	public Imagem() {
		
			miraMenu= Constantes.LoadImage("miraMenu.png");
			Constantes.inimigoUm =Constantes.LoadImage("zombie.png");	
			
			tileset = Constantes.LoadImage("Bridge.png");

			Constantes.mira1= Constantes.LoadImage("mira1.png");	

			Constantes.TORRE_UM_ANIMESET = Constantes.LoadImage("torre.png");
	}
 
}
