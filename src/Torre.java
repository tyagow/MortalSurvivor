import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Torre extends Objeto{

	BufferedImage AnimeSet;
	
	int frame;
	int timeranimacao;
	int animacao;
	int tempoentreframes;
	int timertiro = 0;
	public boolean select=false;
	int range;
	int tipotiro=0;
	int potencia=10;
	int velocidade = 0;
	int velx,vely;
	boolean atirando = false;
	double ang = 0;
	int intervaloTiro;
	int sizeX = 23;
	int sizeY = 32;
	
	int pmy;
	int pmx;
	
	double oldx;
	double oldy; 

	public int direcao;
		
	int pontoDestino = 0;

	public boolean click = false;
	int tipo;
	
	public int UpgradeRange;
	public int UpgradeTiro;
	public int UpgradePotecia;
	Color cor;
	public int QuantidadeUpRange=0;
	public int QuantidadeUpTiro=0;
	public int QuantidadeUpPotencia=0;
	int start;

	public boolean Super=false;

	private int startY;

	private int slowtime;
	public Torre(BufferedImage _AnimeSet, int tipo){ 
		// TODO Auto-generated constructor stub
		AnimeSet = _AnimeSet;
		frame = 0;
		animacao = 0;
		timeranimacao = 0;
		this.tipo=tipo;
		direcao = 0;
		int _x=((CanvasGame.mousex+4)/16)*16; 
		X=_x;
		int _y=((CanvasGame.mousey-5)/16)*16; 
		Y=_y+8;		
		start = sizeX*10;
		tempoentreframes = 200;
		startY = (tipo-1)*sizeY;
		vivo = true;
		switch (tipo) {
		case 1:
			intervaloTiro=1000;
			range = 100;
			UpgradeRange=10;
			UpgradeTiro=10;
			UpgradePotecia=10;
			cor = Color.blue;
			
			break;
			
		case 2:
			intervaloTiro=700;
			range = 80;
			UpgradeRange=10;
			UpgradeTiro=10;
			UpgradePotecia=10;
			cor = Color.red;
			break;
		case 3:
			intervaloTiro=1500;
			range = 100;
			UpgradeRange=10;
			UpgradeTiro=10;
			potencia = 40;
			UpgradePotecia=20;
			cor = Color.green;
			slowtime=2000;
			break;
		
		}
		
		
	}
	
	
	public void DesenhaSe(Graphics2D dbg,int XMundo,int YMundo) {
		// TODO Auto-generated method stub
//		AffineTransform trans = dbg.getTransform();
//		dbg.translate(X+16-XMundo, Y+16-YMundo);
//		dbg.rotate(ang);
//		int px = (int)X-XMundo;
//		int py = (int)Y-YMundo;
//
//
//		dbg.setColor(cor);
//
//
//
//		dbg.drawImage(AnimeSet,-14,-18,sizeX-10,sizeY-14,sizeX*frame+start,startY,(sizeX*frame)+sizeX+start,(startY)+sizeY,null);
//
//		if(select&&CanvasGame.Torre==0) {
//			dbg.setColor(Color.blue);
//
//			dbg.drawOval((int)-range, (int)-range, range*2, range*2);
//
//
//
//		}
//
//		dbg.setTransform(trans);
//		if(select&&CanvasGame.Torre==0 ) {
//			String rag = Integer.toString(range);
//			String tmtiro = Integer.toString(intervaloTiro);
//			String pote = Integer.toString(potencia);
//
//
//			String UpRange = Integer.toString(UpgradeRange);
//			String UpTiro= Integer.toString(UpgradeTiro);
//			String QntRange = Integer.toString(QuantidadeUpRange);
//			String QntTiro = Integer.toString(QuantidadeUpTiro);
//
//			String UpPo= Integer.toString(UpgradePotecia);
//			String QntPo= Integer.toString(QuantidadeUpPotencia);
//
//
//			dbg.setColor(Color.BLUE );
//			
//
//			
//			//dbg.drawRect(Constantes.MENU_STATUS_X, Constantes.MENU_STATUS_Y, Constantes.MENU_STATUS_SIZEX, Constantes.MENU_STATUS_SIZEY);
//			//dbg.setFont(CanvasGame.fonte);
//			//range
//			if (CanvasGame.Dinheiro >= UpgradeRange&&QuantidadeUpRange<20) {
//				dbg.setColor(Color.blue );
//
//				if (Constantes.colidecircular(CanvasGame.mousex, CanvasGame.mousey, 10, Constantes.MENU_STATUS_X+250, Constantes.MENU_STATUS_Y+18, 6))
//				{
//					dbg.fillRect(Constantes.MENU_STATUS_X+236, Constantes.MENU_STATUS_Y+10, 30, 20); // botao
//					if (CanvasGame.click) {
//
//						CanvasGame.Dinheiro-=UpgradeRange;
//						UpgradeRange*=3;
//
//						QuantidadeUpRange++;
//						range+=10;
//
//					}
//
//
//				}
//
//
//			dbg.drawRect(Constantes.MENU_STATUS_X+236, Constantes.MENU_STATUS_Y+10, 30, 20); // botao
//			dbg.setColor(Color.white );
//			dbg.drawString("Upg", Constantes.MENU_STATUS_X+240, Constantes.MENU_STATUS_Y+25);
//
//			}
//			dbg.setColor(Color.white );
//
//
//			dbg.drawString("(lvl:"+QntRange+")"+" Range: " + rag + "  UP $"+ UpRange, Constantes.MENU_STATUS_X+10, Constantes.MENU_STATUS_Y+25) ;
//
////TEMPO ENTRE TIROS
//
//			if (CanvasGame.Dinheiro >= UpgradeTiro&&QuantidadeUpTiro<10) {
//				dbg.setColor(Color.blue );
//
//				if (Constantes.colidecircular(CanvasGame.mousex, CanvasGame.mousey, 10, Constantes.MENU_STATUS_X+250, Constantes.MENU_STATUS_Y+55, 6))
//				{
//					dbg.fillRect(Constantes.MENU_STATUS_X+236, Constantes.MENU_STATUS_Y+45, 30, 20); // botao
//					if (CanvasGame.click) {
//
//						CanvasGame.Dinheiro-=UpgradeTiro;
//						UpgradeTiro*=2;
//
//						QuantidadeUpTiro++;
//						intervaloTiro-=50;
//
//					}
//
//				}
//
//
//			dbg.drawRect(Constantes.MENU_STATUS_X+236, Constantes.MENU_STATUS_Y+45, 30, 20); // botao
//			dbg.setColor(Color.white );
//			dbg.drawString("Upg", Constantes.MENU_STATUS_X+240, Constantes.MENU_STATUS_Y+58);
//
//			}
//			dbg.setColor(Color.white );
//
//
//			dbg.drawString("(lvl:"+QntTiro+")"+" V(ms): " + tmtiro + " UP $"+ UpTiro, Constantes.MENU_STATUS_X+10, Constantes.MENU_STATUS_Y+58) ;
//			//Potencia do tiro
//
//
//
//			if (CanvasGame.Dinheiro >= UpgradePotecia&&QuantidadeUpPotencia<9) {
//				dbg.setColor(Color.blue );
//
//				if (Constantes.colidecircular(CanvasGame.mousex, CanvasGame.mousey, 10, Constantes.MENU_STATUS_X+250,  Constantes.MENU_STATUS_Y+88, 6))
//				{
//					dbg.fillRect(Constantes.MENU_STATUS_X+236,  Constantes.MENU_STATUS_Y+78, 30, 20); // botao
//					if (CanvasGame.click) {
//
//						CanvasGame.Dinheiro-=UpgradePotecia;
//						UpgradePotecia*=3;
//
//						QuantidadeUpPotencia++;
//						slowtime+=1000;
//						potencia+=10;
//
//					}
//
//
//				}
//
//
//			dbg.drawRect(Constantes.MENU_STATUS_X+236, Constantes.MENU_STATUS_Y+77, 30, 20); // botao
//			dbg.setColor(Color.white );
//			dbg.drawString("Upg", Constantes.MENU_STATUS_X+240, Constantes.MENU_STATUS_Y+90);
//
//			}
//			dbg.setColor(Color.white );
//
//
//			if (tipo==3)
//				dbg.drawString("(lvl:"+QntPo+")"+" T. Slow: " + pote + " UP $"+ UpPo, Constantes.MENU_STATUS_X+10, Constantes.MENU_STATUS_Y+90) ;
//				else
//					dbg.drawString("(lvl:"+QntPo+")"+" Pot: " + pote + "     UP $"+ UpPo, Constantes.MENU_STATUS_X+10, Constantes.MENU_STATUS_Y+90) ;
//
//
//
//		dbg.setFont(CanvasGame.fonte2);
//
//	}
}
	@Override
	public void SimulaSe(int DiffTime) {
//		// TODO Auto-generated method stub	
//		timeranimacao += DiffTime;
//		timertiro += DiffTime;
//		frame=0;
//		if (Super)  
//			startY=3*sizeY;
//		else 
//			startY=(tipo-1)*sizeY;
//		for (int i = 0;i<GerenciadorDeRaids.raids.size();i++) {
//			Raid ra = GerenciadorDeRaids.raids.get(i);
//		
//			for (int j = 0;j<ra.inimigos.size();j++) {
//				Inimigo in = ra.inimigos.get(j);
//				if (Constantes.colidecircular(in.X, in.Y, 1, X, Y, range)) {
//					
//					//Constantes.somTiro.run();
//					Projetil proj = new Projetil(this, X+sizeX/2, Y+sizeY/2,GerenciadorDeRaids.raids.get(i).inimigos.get(j).X+12 ,GerenciadorDeRaids.raids.get(i).inimigos.get(j).Y+16,tipo);
//					
//					if (timertiro > intervaloTiro  ) {
//					//Constantes.tiro.play();
//					
//					timertiro =0;
//					ang=proj.ang;
//			
//
//					CanvasGame.projeteis.add(proj);
//					if (tipo==1||tipo==2) 
//						
//						GerenciadorDeRaids.raids.get(i).inimigos.get(j).life -=potencia;
//					
//					
//					else if (tipo==3) 
//					GerenciadorDeRaids.raids.get(i).inimigos.get(j).tiroSlow=true;
//					GerenciadorDeRaids.raids.get(i).inimigos.get(j).slowtime=slowtime;
//					
//					
//						
//						timertiro=0;
//					}
//					if (timertiro < intervaloTiro  ) {
//						frame = (timeranimacao/tempoentreframes)%2;
//					}
//			
//				}
//			}
//		}
//
//		
//				
//		
//	}
	}
}
