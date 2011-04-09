import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GerenciadorHud extends Objeto {

	private Font oldFont;
	private Font fontXp = Constantes.big;
	private static int xpHud;
	Torre torreAtivaHud;
	public GerenciadorHud() {
		
		
	}
	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		if (torreAtivaHud!=null) {
			
			if (torreAtivaHud.select==false)
				torreAtivaHud=null;
			else {
				if (Constantes.colidecircular(CanvasGame.mousex+CanvasGame.MAPA.MapX, CanvasGame.mousey+CanvasGame.MAPA.MapY, 10,torreAtivaHud.X- Constantes.HUD_TORRE_STARTX+Constantes.HUD_TORRE_SIZEX/2, torreAtivaHud.Y- Constantes.HUD_TORRE_STARTY+Constantes.HUD_TORRE_SIZEY/2, Constantes.HUD_TORRE_SIZEY/2)) {
					
					torreAtivaHud.resetTimerSelect();
				}
			}
			
			
			
			}
		  
		
		
	}
	

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		if (torreAtivaHud!=null) {
			desenhaHudTorre(dbg,XMundo,YMundo);
	
			
		}
		
		
		
		//hud XP
		oldFont=dbg.getFont();
		dbg.setFont(fontXp);
		dbg.setColor(Color.red);
		dbg.drawString("XP: "+getXpHud(), GamePanel.PWIDTH/2, 20);
		dbg.setColor(Color.black);
		dbg.drawString("XP: "+getXpHud(), GamePanel.PWIDTH/2-2, 20-2);
		dbg.setFont(oldFont);	
		
		
		
	}
	private void desenhaHudTorre(Graphics2D dbg, int xMundo, int yMundo) {
		// TODO Auto-generated method stub

			dbg.setColor(Color.blue);
			dbg.drawRect((int)torreAtivaHud.X-Constantes.HUD_TORRE_STARTX-xMundo,(int)torreAtivaHud.Y-Constantes.HUD_TORRE_STARTY-yMundo, Constantes.HUD_TORRE_SIZEX, Constantes.HUD_TORRE_SIZEY);
		
			dbg.setColor(Color.red);
			dbg.drawOval((int)torreAtivaHud.X-torreAtivaHud.getRange()/2-xMundo, (int)torreAtivaHud.Y-torreAtivaHud.getRange()/2-yMundo, torreAtivaHud.getRange(), torreAtivaHud.getRange());


		
		
	}
	public static void setXpHud(int _xpHud) {
		xpHud = _xpHud;
	}
	public int getXpHud() {
		return xpHud;
	}
	
	public void desativaHudTorre() {
		// TODO Auto-generated method stub
		torreAtivaHud=null;
	}
	
	public void ativaHudTorre(Torre torre) {
		// TODO Auto-generated method stub
		torreAtivaHud=torre;
		
		
	}

}
