package Efeitos;
import java.awt.Graphics2D;

import AbstractClasses.Objeto;


public class Particula extends Objeto{

	int velx = 0;
	int vely = 0;
	int alpha=1;
	int tempototal = 0;
	int tempodevida = 0;

	@Override
	public void SimulaSe(int DiffTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		// TODO Auto-generated method stub
		
	}
	public int getAlpha() {
		return alpha;
	}
	
}
