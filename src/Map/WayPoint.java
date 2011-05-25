package Map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.sun.org.apache.xml.internal.security.encryption.AgreementMethod;

import AbstractClasses.Objeto;
import Canvas.CanvasGame;
import Constantes.Constantes;

public class WayPoint extends Objeto  {

	public int indexNextTarget;
	public int index;
	private double lineX=0;
	private double lineY=0;
	private boolean mouseOver;
	private boolean ativo;
	private int mX;
	private int mY;
	private int rangeAgruparMouse=20;
	private int rangeAgrupar=100;
	private boolean tentaAgrupar=false;
	
	public WayPoint(int _x,int _y,int _sizeX,int _sizeY) {
		X=_x;
		Y=_y;
		sizeX=_sizeX;
		sizeY=_sizeY;
		
		
		
	}
	@Override
	public void SimulaSe(int DiffTime) {
		if (indexNextTarget!=-1) {	
			lineX=Constantes.wayPoints.get(indexNextTarget).X;
			lineY=Constantes.wayPoints.get(indexNextTarget).Y;
		}
		if (ativo) {
			lineX=mX;
			lineY=mY;
		}
		
	}
	

	@Override
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
		
		
		dbg.setColor(Color.white);

		if (mouseOver)
			dbg.setColor(Color.green);
		if (ativo) {
			dbg.setColor(Color.cyan);
			dbg.drawOval(mX-rangeAgruparMouse/2-XMundo, mY-rangeAgruparMouse/2-YMundo, rangeAgruparMouse, rangeAgruparMouse);
			
			
			dbg.drawOval((int)X-rangeAgrupar/2-XMundo, (int)Y-rangeAgrupar/2-YMundo, rangeAgrupar, rangeAgrupar);

		}
		if (tentaAgrupar) {
			dbg.drawOval((int)X-rangeAgrupar/2-XMundo, (int)Y-rangeAgrupar/2-YMundo, rangeAgrupar, rangeAgrupar);

		}
		
		dbg.fillRect((int)X-XMundo-sizeX/2, (int)Y-YMundo-sizeY/2, sizeX, sizeY);
		dbg.setColor(Color.red);
		dbg.drawString(""+indexNextTarget,(int)X-XMundo, (int)Y-YMundo);
		dbg.drawString("i:"+index,(int)X-10-XMundo, (int)Y-10-YMundo);
		
		if (Constantes.wayPoints.size()>(indexNextTarget)&&indexNextTarget!=-1) {
				dbg.drawLine((int)X-XMundo,(int) Y-YMundo,(int)lineX-XMundo,(int) lineY-YMundo);
		}
		
		
	}
	public void mouseMoved(MouseEvent e) {
		if (Constantes.editarWay) {

			if (Constantes.colidecircular(X, Y, sizeX, e.getX()+Constantes.XTela, e.getY()+Constantes.YTela, 1)) {
				mouseOver = true;
			}else {
				mouseOver=false;
			}
			
			if (Constantes.colidecircular(X, Y, rangeAgrupar, e.getX()+Constantes.XTela, e.getY()+Constantes.YTela,rangeAgruparMouse )) {
				tentaAgrupar = true;
			}else {
				tentaAgrupar=false;
			}
		}
		mX=e.getX()+Constantes.XTela;
		mY=e.getY()+Constantes.YTela;
	}
	
	public void mouseReleased(MouseEvent e) {
		if (Constantes.editarWay) {
			if (!Constantes.editandoWayPoint) {
				if (mouseOver) {
					if (ativo == true) {

						ativo=false;
					}else {
						Constantes.editandoWayPoint=true;
						Constantes.editandoWayPointIndex=index;
						ativo =true;
					}
				}
				
			} else {
//				Constantes.editandoWayPoint=false;
				
				if (ativo){
					if (Constantes.colidecircular(X, Y, rangeAgruparMouse, e.getX()+Constantes.XTela, e.getY()+Constantes.YTela,rangeAgruparMouse ))
					{
						ativo=false;
						Constantes.editandoWayPoint=false;
//						Constantes.editandoWayPointIndex=-1;
					}else {
						ativo=false;
					}
						
//						WayPoint wayAtivo = Constantes.wayPoints.get(Constantes.editandoWayPointIndex);
//					if (Constantes.colidecircular((int)wayAtivo.mX,(int)wayAtivo.mY,(int)wayAtivo.rangeAgruparMouse,X,Y,rangeAgrupar)) {
//						
//						
//					}
					
					
//					}
					
				}else {
					
					WayPoint wayAtivo = Constantes.wayPoints.get(Constantes.editandoWayPointIndex);
					if (Constantes.colidecircular((int)wayAtivo.mX+Constantes.XTela,(int)wayAtivo.mY+Constantes.YTela,(int)wayAtivo.rangeAgruparMouse,X,Y,rangeAgrupar)) {
						Constantes.editandoWayPoint=false;
						
						 Constantes.wayPoints.get(Constantes.editandoWayPointIndex).indexNextTarget= index;
						
					}
					
					
					
				}
				
//				if (mouseOver) {
//					if (Constantes.colidecircular(X, Y, rangeAgruparMouse, e.getX(), e.getY(),rangeAgruparMouse ))
//					{
//					
//						
//					}
//				}
				
			}
				
		}
			
	}
	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseDragged(MouseEvent e) {

		mX=e.getX()+Constantes.XTela;
		mY=e.getY()+Constantes.YTela;
	}
			
	

}
