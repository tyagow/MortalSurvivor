import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;


public class GerenciadorTorre extends Objeto {
	
	
	public static SelecionadorDeTorre selecionadorDeTorre;
	public static ArrayList<Torre> torres = new ArrayList<Torre>();
	
	private static boolean hitMiraMenu = false;
	public static boolean hitMiraSelecionador = false;
	
	private static int rangeMouse=20;
	private static long tempoUltimaTorre=0;
	
	public GerenciadorTorre() {
		 selecionadorDeTorre = new SelecionadorDeTorre();
		 torres.clear();
	}


	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
//			Iterator<Torre> it = torres.iterator();
//			while(it.hasNext()){
//				Torre torre = it.next();
//				torre.SimulaSe((int)DiffTime);
//				if(torre.isVivo()==false){
//					it.remove();
//					}
//			}
		
		for(int i = 0; i < torres.size();i++){
			Torre proj = (Torre)torres.get(i);
			proj.SimulaSe((int)DiffTime);
			if(!proj.isVivo()){
				torres.remove(i);
			}
		}
		
		selecionadorDeTorre.SimulaSe(DiffTime);
		
		trataMouse();
	}




	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		for(int i = 0; i < torres.size();i++){
			
			Torre proj = (Torre) torres.get(i);
			proj.DesenhaSe(dbg, CanvasGame.MAPA.MapX,CanvasGame.MAPA.MapY);
			
		}
		
		selecionadorDeTorre.DesenhaSe(dbg, XMundo, YMundo);

	}

	public void click(int mousex, int mousey) {
		// TODO Auto-generated method stub
		
		//System.out.println(mousex);
//
//		System.out.println(mousey);
		Iterator<Torre> it = torres.iterator();
		while(it.hasNext()){
			Torre torre = it.next();

			if (Constantes.colidecircular(mousex, mousey,rangeMouse/2,torre.getX(),torre.getY(),torre.getSizeX()/2)) {
				torre.seleciona();
				break;
			}	
		
		}
	
		
			
	}

	public static void adicionaTorre(int x, int y) {
		// TODO Auto-generated method stub
		
		if (posicaoValida(x,y))
			if (System.currentTimeMillis()-tempoUltimaTorre >Constantes.TEMPO_ENTRE_ADD_TORRES) {
				tempoUltimaTorre=System.currentTimeMillis();
				
				
			//	selecionadorDeTorre.torreAtiva.setX(x);
				//selecionadorDeTorre.torreAtiva.setY(y);
				
		
				for (int i=0;i<4;i++) {
				
					if (selecionadorDeTorre.getSlotsTorre()[i].isAtivo()){
						
						switch (i) {
						case 0: // torre um
							
							torres.add(new Torre(Imagem.TORRE_UM_ANIMESET,new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET),x,y));

								break;	
						case 1: // torre dois
									
							torres.add(new Torre(Imagem.TORRE_DOIS_ANIMESET,new ArmaDoisTorre(Imagem.TORRE_DOIS_ANIMESET),x,y));

						break;
						case 2: // torre tres
							
							torres.add(new Torre(Imagem.TORRE_TRES_ANIMESET,new ArmaTresTorre(Imagem.TORRE_TRES_ANIMESET),x,y));

								break;	
						case 3: // torre um
							
							torres.add(new Torre(Imagem.TORRE_UM_ANIMESET,new ArmaUmTorre(Imagem.TORRE_UM_ANIMESET),x,y));

								break;	

						default:
							break;
						}
						
						
						//Torre aux =  new Torre(selecionadorDeTorre.getSlotsTorre()[i].getAnimeSet(),selecionadorDeTorre.getSlotsTorre()[i].getArmaAtiva(),x,y);
						
						//torres.add(aux);
					
					}
				}

				
			}
				//Imagem.TORRE_DOIS_ANIMESET
	}
	
	

	private static boolean posicaoValida(int x, int y) {
		// TODO Auto-generated method stub
		boolean aux=true;
		Iterator<Torre> it = torres.iterator();
		while(it.hasNext()&&aux){
			Torre torre = it.next();
			

			if (Constantes.colidecircular(x, y,rangeMouse/2,torre.getX(),torre.getY(),torre.getSizeX())) {
				aux= false;
				}
		
			}
		if (GerenciadorObstaculos.colidiuObstaculo(x,y)) {
			aux=false;
		}
	
		

		return aux;

	}
	
	private void trataMouse() {
		// TODO Auto-generated method stub
		if (Constantes.colideQuadrado((int) selecionadorDeTorre.getX(),(int) selecionadorDeTorre.getY(), selecionadorDeTorre.getSizeX(), selecionadorDeTorre.getSizeY(), (int) CanvasGame.getMiraAtiva().getX(),(int) CanvasGame.getMiraAtiva().getY(), 1, 1)) {
			hitMiraSelecionador = true;
		}else {
			hitMiraSelecionador = false;
		}
		
		for(int i = 0; i < torres.size(); i++){
			MenuTorre m = torres.get(i).getMenuAtivo();
			if(m != null){
				if (Constantes.colideQuadrado((int)m.getX(),(int) m.getY(),(int) m.getSizeX(),(int) m.getSizeY(), (int)CanvasGame.getMiraAtiva().getXMundo(),(int) CanvasGame.getMiraAtiva().getYMundo(), 1, 1)) {
					setHitMiraMenu(true);
					break;
				}
			}
			setHitMiraMenu(false);
		}
		
		if(isHitMiraMenu() || hitMiraSelecionador){
			CanvasGame.setMiraMenu();
			
		}else {
			CanvasGame.setMiraJogo();
		}
	}


	public static void setHitMiraMenu(boolean hitMiraMenu) {
		GerenciadorTorre.hitMiraMenu = hitMiraMenu;
	}


	public static boolean isHitMiraMenu() {
		return hitMiraMenu;
	}
}
