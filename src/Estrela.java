import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Estrela {
 int[][] mapa;

	ArrayList<Nodo> nodosAbertos = new ArrayList<Nodo>();
	ArrayList<Nodo> nodosFechados = new ArrayList<Nodo>();
	static ArrayList<Nodo> caminho = new ArrayList<Nodo>();

	int objetivox = 0;
	int objetivoy = 0;
	int largura,altura;
	public Estrela(int[][] _mapa) {
		// TODO Auto-generated constructor stub
		mapa = _mapa;
		largura = GerenciadorObstaculos.getLargura();
		altura=GerenciadorObstaculos.getAltura();
	}

	public List<Nodo> MontaEstrela(int x, int y, int objx, int objy) {
		caminho.clear();
		objetivox = objx;
		objetivoy = objy;
		
		nodosAbertos.clear();
		nodosFechados.clear();

		Nodo selecionado = new Nodo(null, x, y, 0);

		while (abreNodo(selecionado) == false) {
			//System.out.println(selecionado.x + " - " +selecionado.y);
/*			Collections.sort(nodosAbertos, new Comparator<Nodo>() {
				public int compare(Nodo o1, Nodo o2) {
					// TODO Auto-generated method stub
					return (int) (o1.h - o2.h);
				}
			});*/
			
			
			
			double menorHeuristica = nodosAbertos.get(0).h;
			int indiceMenor = 0;
			
			for (int i = 1; i < nodosAbertos.size()-1; i++) {
				Nodo nodo2 = nodosAbertos.get(i);
				if(nodo2.h < menorHeuristica){
					menorHeuristica = nodo2.h;
					indiceMenor = i;
				}
			}
			
			selecionado = nodosAbertos.get(indiceMenor);
			nodosAbertos.remove(indiceMenor);

		}

		Nodo nodo = nodosFechados.get(nodosFechados.size() - 1);
		caminho.add(nodo);

		while (nodo.pai != null) {
			nodo = nodo.pai;
			caminho.add(nodo);
		}

		return caminho;
	}

	public boolean abreNodo(Nodo _nodo) {
		nodosFechados.add(_nodo);

		ArrayList<Nodo> nodos = new ArrayList<Nodo>();

		nodos.add(0, new Nodo(_nodo, _nodo.x + 1, _nodo.y, _nodo.g + 1));
		nodos.add(1, new Nodo(_nodo, _nodo.x, _nodo.y + 1, _nodo.g + 1));
		nodos.add(2, new Nodo(_nodo, _nodo.x - 1, _nodo.y, _nodo.g + 1));
		nodos.add(3, new Nodo(_nodo, _nodo.x, _nodo.y - 1, _nodo.g + 1));

		for (int i = 0; i < 4; i++) {
			Nodo nodo = nodos.get(i);

			if (nodo.x == objetivox && nodo.y == objetivoy) {
				nodosFechados.add(nodo);
				return true;
			} else {
				if (!(nodo.x < 0 || nodo.y < 0 || nodo.x >= largura || nodo.y >= altura)) {
					if (!(mapa[nodo.x][nodo.y] == 1)) {
						boolean ToNoFechado = false;
						boolean TonoAberto = false;
						for (int z = 0; z < nodosFechados.size(); z++) {
							Nodo nodo2 = nodosFechados.get(z);
							if (nodo.x == nodo2.x && nodo.y == nodo2.y) {
								ToNoFechado = true;
							}
						}

						for (int z = 0; z < nodosAbertos.size(); z++) {
							Nodo nodo2 = nodosAbertos.get(z);
							if (nodo.x == nodo2.x && nodo.y == nodo2.y) {
								TonoAberto = true;
							}
						}
						if (!TonoAberto && !ToNoFechado) {
							nodo.h = CalculaHeuristica(nodo.x, nodo.y,
									objetivox, objetivoy);
							nodosAbertos.add(nodo);
						}
					}
				}
			}
		}

		return false;
	}

	public double CalculaHeuristica(int x, int y, int objx, int objy) {
		double difx = objx - x;
		double dify = objy - y;
		return Math.sqrt(difx * difx + dify * dify);
	}

	public ArrayList<Nodo> getNodosAbertos() {
		return nodosAbertos;
	}

	public ArrayList<Nodo> getNodosFechados() {
		return nodosFechados;
	}
}
