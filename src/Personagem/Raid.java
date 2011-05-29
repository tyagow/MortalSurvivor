package Personagem;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.sun.corba.se.impl.orbutil.closure.Constant;

import Constantes.Constantes;
import Data.Imagem;
import GameState.GamePanel;
import Map.TileMap;


public class Raid {
	
	public boolean ativo = false;
	
	public ArrayList<Inimigo> inimigos = new ArrayList<Inimigo>();

	private int tempoEntreInimigos=0;
	
	int numInimigosAdicionados=0;

	private int tipoInimigoAtual=-1;

	private int respaw=0;
	
	public Raid(){
		
inimigos.clear();
//		InputStream in = Data.Imagem.class.getResourceAsStream(filename);
//		
//		
//		BufferedReader bf = new BufferedReader(new InputStreamReader(in));
//		
//		String str = "";
//		
//	
//		ativo=true;
//		int _tipo=0;
//		int respaw =0;
//		try {
//			while((str = bf.readLine())!=null){
//				if(str.charAt(0)!='#'){
//					String strs[] = str.split(";");
//			
//					int codigo = Integer.parseInt(strs[0]); 
//					int tipo = Integer.parseInt(strs[1]);
//					int ix = Integer.parseInt(strs[2])*16;
//					int iy = Integer.parseInt(strs[3])*16;
//					int iv = Integer.parseInt(strs[4]);
//					int ilife = Integer.parseInt(strs[5]);
//					
////					Inimigo inim = new Inimigo(Imagem.inimigoUm,(int)CanvasGame.base.getX()/16-20,(int)CanvasGame.base.getY()/16-20);
//					Inimigo inim = new Inimigo(tipo,Constantes.BASE_X,Constantes.BASE_Y);
//					
//					if (_tipo!=tipo) {
//						
//						if (respaw+1 <Constantes.quantidadeRespawInimigo) {
//							respaw++;
//							
//						}else {
//							respaw=0;
//						}
//						
//					}
//					
//					inim.X=(ix);
//					inim.Y=(iy);
//					inim.vel=(iv);
//					inim.maxVel=iv;
//					inim.maximoVida=ilife;
//					inim.life=(ilife);
//
////					inim.setTipoAssasino(tipo);
////					Inimigo inim2 = new Inimigo(Imagem.inimigoUm/*,(int)CanvasGame.base.getX()/16,(int)CanvasGame.base.getY()/16*/);
//					
//					//Constantes.inimigos.add(inim);
//				}
//			}
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}


	}
	
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo) {
//		for(int i = 0; i < inimigos.size();i++){
//			inimigos.get(i).DesenhaSe(dbg, CanvasGame.MAPA.MapX, CanvasGame.MAPA.MapY);
//
//			
//		}
	}
	
	public void SimulaSe(int DiffTime){
		
		//System.out.println("inimigos"+numInimigosAdicionados);
		//System.out.println("size"+inimigos.size());
//		System.out.println(ativo);
//		
			
			tempoEntreInimigos+=DiffTime;
		
			if (numInimigosAdicionados<=inimigos.size()&&tempoEntreInimigos>1000) {
				
				Inimigo aux = inimigos.get(numInimigosAdicionados);
						
				
				if (tipoInimigoAtual!=aux.tipo) {
					tipoInimigoAtual=aux.tipo;
					if (respaw+1 <Constantes.quantidadeRespawInimigo) {
						respaw++;
						
					}else {
						respaw=0;
					}
					
				}
				
				
				aux.X=Constantes.wayPoints.get(respaw).X;
				aux.Y=Constantes.wayPoints.get(respaw).Y;
				
				Constantes.inimigos.add(aux);
				
				numInimigosAdicionados++;
				
				tempoEntreInimigos=0;
			}
			
			if (numInimigosAdicionados>=inimigos.size()) {
				
				ativo=false;
				numInimigosAdicionados=0;
				
			}
			
	
//		
//		for(int i = 0; i < inimigos.size();i++){
//			Inimigo inim = inimigos.get(i);
//			
//			inim.SimulaSe((int)DiffTime);
//			
//			if(!inim.isVivo()){
//				inimigos.remove(i);
//				GerenciadorXP.ganhouXp(inim.getX(), inim.getY(),inim.getTipoAssasino() );
//			}
//			if(inimigos.size() == 0)
//				this.ativo = false;
//		}
	}

}
