package Gerenciadores;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Canvas.CanvasGame;
import Constantes.Constantes;
import Data.Imagem;
import Personagem.Inimigo;
import Personagem.Raid;


public class GerenciadorDeRaids {
	
	private static ArrayList<Raid> raids = new ArrayList<Raid>();
	
	
	
	
	static int numRaid = 0;
	public static int tempoEntreRaids;
	public boolean fim=false;

	static int _tipo=0;
	static int respaw =0;

	private boolean primeiraVez;




	private static int tempQuantidadeInimigos;
	
	public GerenciadorDeRaids(){
		
		
		for (int i = 0;i<50;i++) {
			raids.add(new Raid());
		}
		primeiraVez=true;
	
	}
	
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo){
//		for(int i = 0; i < raids.size(); i++){
//			if(raids.get(i).ativo == true){
//				raids.get(i).DesenhaSe(dbg, XMundo, YMundo);
//			}
//		}
		for(int i = 0; i < Constantes.inimigos.size();i++){
			Constantes.inimigos.get(i).DesenhaSe(dbg, CanvasGame.tela.XTela, CanvasGame.tela.YTela);

			
		}
	}
	
	public void SimulaSe(int DiffTime){
		tempoEntreRaids+=DiffTime;

		if (tempoEntreRaids > Constantes.TEMPO_ENTRE_RAIDS&&!CanvasGame.endGame){
			System.out.println("inicializa Raid");
				trataInicializacaoRaid(numRaid);
				tempoEntreRaids=0;
		}		
			
			
		
			for(int i = 0; i < raids.size(); i++){
				
				Raid raid = raids.get(i);
				if(raid.ativo == true){
					raid.SimulaSe(DiffTime);
				}
				
//				if(raid.ativo == false){
//					raids.remove(i);
//				}
					

				
			}
		
		
		for(int i = 0; i < Constantes.inimigos.size();i++){
			Inimigo inim = Constantes.inimigos.get(i);
			
			inim.SimulaSe((int)DiffTime);
			
			if(!inim.vivo){
				Constantes.inimigos.remove(i);
				GerenciadorXP.ganhouXp(inim.getX(), inim.getY(),inim.tipoAssasino );
			}
		
		}
	}




	private void trataInicializacaoRaid(int numRaid2) {

		
		
		raids.get(numRaid2).ativo = true;
	}

	public static void loadInimigos() {
		
		//raids.clear();
		numRaid=0;


	
	
	}
public static  void loadInimigos(String filename) {
	
		loadInimigos();
		InputStream in = Data.Imagem.class.getResourceAsStream(filename);
		
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(in));
		
		String str = "";
		
		
		//ativo=true;

		try {
			while((str = bf.readLine())!=null){
				if(str.charAt(0)!='#'){
					
					if (str.charAt(0)!='@') {
						
						carregaRaid(numRaid,str);
					
						
						
					}else {
						System.out.println("muda raid");
						if (str.contains("muda raid")) {
							numRaid++;
							 _tipo=0;
							 respaw =0;
							 tempQuantidadeInimigos=0;
							
						}
						
					}
					
						
						
						
						
//					carregaInimigo(str);
		
		//			inim.setTipoAssasino(tipo);
		//			Inimigo inim2 = new Inimigo(Imagem.inimigoUm/*,(int)CanvasGame.base.getX()/16,(int)CanvasGame.base.getY()/16*/);
					
					//Constantes.inimigos.add(inim);
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static void carregaRaid(int _numRaid, String str) {
		
		Inimigo aux = carregaInimigo(str);
		if (aux !=null) {
		
//			raids.get(_numRaid).inimigos.get()
		
			raids.get(_numRaid).inimigos.add(aux);
		
		
		}else {
			System.out.println("nao carregou inimigo");
		}
		System.out.println(str);
		System.out.println("raid " + _numRaid);
		System.out.println("raid inimigos size" + raids.get(0).inimigos.size());
		
	}

	private static Inimigo carregaInimigo(String str) {
		String strs[] = str.split(";");
		
		int codigo = Integer.parseInt(strs[0]); 
		int tipo = Integer.parseInt(strs[1]);
		int ix = Integer.parseInt(strs[2])*16;
		int iy = Integer.parseInt(strs[3])*16;
		int iv = Integer.parseInt(strs[4]);
		int ilife = Integer.parseInt(strs[5]);
	
	//			Inimigo inim = new Inimigo(Imagem.inimigoUm,(int)CanvasGame.base.getX()/16-20,(int)CanvasGame.base.getY()/16-20);
		Inimigo inim = new Inimigo(tipo,Constantes.BASE_X,Constantes.BASE_Y);
		

		
		
		inim.X=(ix);
		inim.Y=(iy);
		inim.vel=(iv);
		inim.maxVel=iv;
		inim.maximoVida=ilife;
		inim.life=(ilife);
		
		tempQuantidadeInimigos++;
		return inim;
		
	}
}
