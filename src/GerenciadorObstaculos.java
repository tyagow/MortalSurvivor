import java.awt.Graphics2D;
import java.util.LinkedList;


public class GerenciadorObstaculos extends Objeto {

	private static LinkedList<Obstaculo> obstaculos = new LinkedList<Obstaculo>();
	
	public GerenciadorObstaculos() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub

	}

	public static void setObstaculos(LinkedList<Obstaculo> obstaculos) {
		GerenciadorObstaculos.obstaculos = obstaculos;
	}

	public static LinkedList<Obstaculo> getObstaculos() {
		return obstaculos;
	}

}
