import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;


public class GerenciadorTorre extends Objeto {
	
	
	
	public static ArrayList<Torre> torres = new ArrayList<Torre>();
	private static int rangeMouse=20;
	private static long tempoUltimaTorre=0;
	 public GerenciadorTorre() {
		 
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
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		for(int i = 0; i < torres.size();i++){
			
			Torre proj = (Torre) torres.get(i);
			proj.DesenhaSe(dbg, CanvasGame.MAPA.MapX,CanvasGame.MAPA.MapY);
			
		}
	}

	public void click(int mousex, int mousey) {
		// TODO Auto-generated method stub
		
		//System.out.println(mousex);
//
//		System.out.println(mousey);
		boolean aux=true;
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
				
				torres.add(new Torre(new ArmaUmTorre(),x,y) );
			}
	
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
	
}
