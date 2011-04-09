import java.util.ArrayList;
import java.util.LinkedList;

public class Estrela {
	
	public static ArrayList<Nodo> listaDeAbertos = new ArrayList<Nodo>();
	public static ArrayList<Nodo> listaDeFechados = new ArrayList<Nodo>();
	public static LinkedList<Nodo> Caminho = new LinkedList<Nodo>();
	boolean caminhoEncontrado = false;
	boolean caminhoNaoExiste = false;
	int objetivoX;
	int objetivoY;
	int indice=0;
	Nodo nodoAtual=null;
	double tempoFinal=0;
	double tempoInicial=0;
	

	public Estrela( Nodo nodo, int objX, int objY) {
		// TODO Auto-generated constructor stub
		tempoInicial=System.currentTimeMillis();
		listaDeAbertos.add(new Nodo(null, nodo.X,nodo.Y,0, objX, objY));
		
		this.objetivoX = objX;
		this.objetivoY = objY;
		listaDeFechados.add(nodo);
		listaDeAbertos.remove(0);
		nodoAtual = nodo;
		ExpandeNodo(nodo);
		procuraCaminho(nodo);
		
	}
	
	void procuraCaminho(Nodo nodo){		
				
				
		while((caminhoEncontrado==false)){
			int menorEuristica = 999999999;
			for(int i =0; i<listaDeAbertos.size();i++){
				Nodo nodolista = listaDeAbertos.get(i);
				if(nodolista.Euristica<menorEuristica){
					nodoAtual=nodolista;
					menorEuristica= nodolista.Euristica;
					indice = i;
				}
			}
			listaDeFechados.add(nodoAtual);
			listaDeAbertos.remove(indice);
			ExpandeNodo(nodoAtual);
		}
		Nodo NC =  nodoAtual;
		
			
		while(NC.pai!=null){

			System.out.println("NC "+NC);
			Caminho.addFirst(NC);
			NC = NC.pai;
		}
	}
	
	void ExpandeNodo(Nodo nodo){
		
		nodo.Energia++;
		Nodo nodoUP = new Nodo( nodo, nodo.X, nodo.Y-1, nodo.Energia, objetivoX, objetivoY);
		Nodo nodoDown = new Nodo( nodo, nodo.X, nodo.Y+1, nodo.Energia, objetivoX, objetivoY);
		Nodo nodoRight =  new Nodo( nodo, nodo.X-1, nodo.Y, nodo.Energia, objetivoX, objetivoY);
		Nodo nodoLeft =  new Nodo( nodo, nodo.X+1, nodo.Y, nodo.Energia, objetivoX, objetivoY);
		
		if(testaNodo(nodoUP)){
			if(EstaAberto(nodoUP)==false){
				listaDeAbertos.add(nodoUP);				
			}
		}
		
		if(testaNodo(nodoDown)){
			if(EstaAberto(nodoDown)==false){
				listaDeAbertos.add(nodoDown);
			}
		}
		if(testaNodo(nodoRight)==true){
			if(EstaAberto(nodoRight)==false){		
				listaDeAbertos.add(nodoRight);
			}
		}
		if(testaNodo(nodoLeft)){
			if(EstaAberto(nodoLeft)==false){	
				listaDeAbertos.add(nodoLeft);
			}
		}
		
		
	}
	
	
	
	boolean testaNodo(Nodo nodo){	
		boolean teste=false;							
			if(EstaFechado(nodo)==false){
				if(ColideParede(nodo)==false){
					if(dentroDoMapa(nodo)==true){
						if(ColideMapa(nodo)){
							
							chegouObjetivo(nodo);
							teste=true;
						}
					}
				}
			}		
		return teste;
	}
	
	

	void chegouObjetivo(Nodo nodo){
		if(nodo.X == objetivoX && nodo.Y == objetivoY){
			tempoFinal=System.currentTimeMillis();
			caminhoEncontrado=true;
			
		}
	}
	
	boolean dentroDoMapa(Nodo nodo){
		boolean dentro =false;
		int Xmax = CanvasGame.MAPA.Largura;
		int Ymax = CanvasGame.MAPA.Altura;		
		if((nodo.X>=0) && (nodo.Y>=0) && (nodo.X<Xmax) && (nodo.Y<Ymax)){
			dentro = true;
		}
		
		return dentro;
	}
	
	boolean EstaFechado(Nodo nodo){
		boolean fechado=false;
		for(int i=0; i<listaDeFechados.size();i++){
			Nodo nodoAberto = listaDeFechados.get(i);
			if(nodo.X==nodoAberto.X && nodo.Y==nodoAberto.Y){
				fechado=true;			
			}
		}
		return fechado;		
	}
	
	boolean EstaAberto(Nodo nodo){
		boolean aberto=false;
		for(int i=0; i<listaDeAbertos.size();i++){
			Nodo nodoAberto = listaDeAbertos.get(i);
			if((nodo.X==nodoAberto.X && nodo.Y==nodoAberto.Y)){
				aberto=true;				
			}
		}
		return aberto;		
	}
	
	boolean ColideParede(Nodo nodo){
		boolean colide = false;
//		for(int i=0; i<GamePanel.listaDeObstaculos.size();i++){
//			Parede parede = GamePanel.listaDeObstaculos.get(i);
//			if(nodo.X == parede.X && nodo.Y== parede.Y){
//				colide = true;				
//			}
//		}
		return colide;
	}
	
	boolean ColideMapa(Nodo nodo){
		
		if(CanvasGame.MAPA.mapa[1][nodo.Y][nodo.X]==1){
			return false;
		}else{
			return true;
		}
	}

}
