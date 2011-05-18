package Gerenciadores;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Canvas.CanvasGame;
import Constantes.Constantes;
import Data.Imagem;
import Personagem.Inimigo;
import Personagem.Raid;


public class GerenciadorDeRaids {
	
	private static ArrayList<Raid> raids = new ArrayList<Raid>();
	
	
	
	int numRaid = 1;
	public static int tempoEntreRaids;
	public boolean fim=false;



	private boolean primeiraVez;
	
	public GerenciadorDeRaids(){
		
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
		if (primeiraVez) {
			carregaPrimeiraRaid();
			
		}
//		if (tempoEntreRaids > Constantes.TEMPO_ENTRE_RAIDS&&!CanvasGame.isEndGame()){
//			
//				trataInicializacaoRaid(numRaid);
//				tempoEntreRaids=0;
//				
//			
//			
//		}else for(int i = 0; i < raids.size(); i++){
//				
//				Raid raid = raids.get(i);
//				if(raid.ativo == true){
//					raid.SimulaSe(DiffTime);
//				}
//				
//				if(raid.ativo == false){
//					raids.remove(i);
//				}
//					
//
//				
//			}
		
		
		for(int i = 0; i < Constantes.inimigos.size();i++){
			Inimigo inim = Constantes.inimigos.get(i);
			
			inim.SimulaSe((int)DiffTime);
			
			if(!inim.isVivo()){
				Constantes.inimigos.remove(i);
				GerenciadorXP.ganhouXp(inim.getX(), inim.getY(),inim.tipoAssasino );
			}
		
		}
	}

	private void carregaPrimeiraRaid() {
		Raid raid1 = new Raid("data/raid1.csv");
		raid1.ativo = true;
		getRaids().add(raid1);
		tempoEntreRaids=0;
		primeiraVez=false;		
	}

	private void trataInicializacaoRaid(int numRaid2) {
		switch(numRaid){
		
		case 1:
			Raid raid2 = new Raid("data/raid2.csv");
			raid2.ativo = true;
			raids.add(raid2);
			numRaid++;

		break;
			
		case 2:
			Raid raid3 = new Raid("data/raid3.csv");
			raid3.ativo = true;
			raids.add(raid3);
			numRaid++;
		break;
		
		case 3:
			Raid raid4 = new Raid("data/raid4.csv");
			raid4.ativo = true;
			raids.add(raid4);
			numRaid++;
			break;
		case 4:
			Raid raid5 = new Raid("data/raid5.csv");
			raid5.ativo = true;
			raids.add(raid5);
			numRaid++;
		break;
		case 5:
			Raid raid6 = new Raid("data/raid6.csv");
			raid6.ativo = true;
			raids.add(raid6);
			numRaid++;
		break;
		case 6:
			Raid raid7 = new Raid("data/raid7.csv");
			raid7.ativo = true;
			raids.add(raid7);
			numRaid++;
		break;
		case 7:
			Raid raid8 = new Raid("data/raid8.csv");
			raid8.ativo = true;
			raids.add(raid8);
			numRaid++;
		break;
		case 8:
			Raid raid9 = new Raid("data/raid9.csv");
			raid9.ativo = true;
			raids.add(raid9);
			numRaid++;
		break;
		case 9:
			Raid raid10 = new Raid("data/raid10.csv");
			raid10.ativo = true;
			raids.add(raid10);
			numRaid++;
		break;
		case 10:
			Raid raid11 = new Raid("data/raid11.csv");
			raid11.ativo = true;
			raids.add(raid11);
			numRaid++;
		break;
		case 11:
			Raid raid12 = new Raid("data/raid12.csv");
			raid12.ativo = true;
			raids.add(raid12);
			numRaid++;
		break;
		case 12:
			Raid raid13 = new Raid("data/raid13.csv");
			raid13.ativo = true;
			raids.add(raid13);
			numRaid++;
		break;
		case 13:
			Raid raid14 = new Raid("data/raid14.csv");
			raid14.ativo = true;
			raids.add(raid14);
			numRaid++;
		break;
		case 14:
			Raid raid15 = new Raid("data/raid15.csv");
			raid15.ativo = true;
			raids.add(raid15);
			numRaid++;
		break;
		case 15:
			Raid raid16 = new Raid("data/raid16.csv");
			raid16.ativo = true;
			raids.add(raid16);
			numRaid++;
		break;
		case 16:
			Raid raid17 = new Raid("data/raid17.csv");
			raid17.ativo = true;
			raids.add(raid17);
			numRaid++;
		break;
		case 17:
			Raid raid18 = new Raid("data/raid18.csv");
			raid18.ativo = true;
			raids.add(raid18);
			numRaid++;
		break;
		case 18:
			Raid raid19 = new Raid("data/raid19.csv");
			raid19.ativo = true;
			raids.add(raid19);
			numRaid++;
		break;
		case 19:
			Raid raid20 = new Raid("data/raid20.csv");
			raid20.ativo = true;
			raids.add(raid20);
			numRaid++;

			CanvasGame.endGame =(true);
		break;
		
		
		
	}
	
	}

	public static void setRaids(ArrayList<Raid> raids) {
		GerenciadorDeRaids.raids = raids;
	}

	public static ArrayList<Raid> getRaids() {
		return raids;
	}

	

	public void reset() {
		raids.clear();
		numRaid=1;
		primeiraVez=true;
	}


}
