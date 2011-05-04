import java.awt.Graphics2D;
import java.util.ArrayList;


public class GerenciadorDeRaids {
	
	private static ArrayList<Raid> raids = new ArrayList<Raid>();
	
	int numRaid = 1;
	public static int tempoEntreRaids;
	public boolean fim=false;
	
	public GerenciadorDeRaids(){
		
		getRaids().clear();
		Raid raid1 = new Raid("raid1.csv");
		raid1.ativo = true;
		getRaids().add(raid1);
		tempoEntreRaids=0;
	}
	
	public void DesenhaSe(Graphics2D dbg, int XMundo, int YMundo){
		for(int i = 0; i < getRaids().size(); i++){
			if(getRaids().get(i).ativo == true){
				getRaids().get(i).DesenhaSe(dbg, XMundo, YMundo);
			}
		}
	}
	
	public void SimulaSe(int DiffTime){
		tempoEntreRaids+=DiffTime;
		
		if (tempoEntreRaids > Constantes.TEMPO_ENTRE_RAIDS&&!CanvasGame.isEndGame()){
			
				trataInicializacaoRaid(numRaid);
				tempoEntreRaids=0;
				
			
			
		}else for(int i = 0; i < getRaids().size(); i++){
				
				Raid raid = getRaids().get(i);
				if(raid.ativo == true){
					raid.SimulaSe(DiffTime);
				}
				
				if(raid.ativo == false){
					getRaids().remove(i);
				}
					
				
				
			}
		
	}

	private void trataInicializacaoRaid(int numRaid2) {
		switch(numRaid){
		
		case 1:
			Raid raid2 = new Raid("raid2.csv");
			raid2.ativo = true;
			getRaids().add(raid2);
			numRaid++;
		

			
		break;
			
		case 2:
			Raid raid3 = new Raid("raid3.csv");
			raid3.ativo = true;
			getRaids().add(raid3);
			numRaid++;
		break;
		
		case 3:
			Raid raid4 = new Raid("raid4.csv");
			raid4.ativo = true;
			getRaids().add(raid4);
			numRaid++;
			break;
		case 4:
			Raid raid5 = new Raid("raid5.csv");
			raid5.ativo = true;
			getRaids().add(raid5);
			numRaid++;
		break;
		case 5:
			Raid raid6 = new Raid("raid6.csv");
			raid6.ativo = true;
			getRaids().add(raid6);
			numRaid++;
		break;
		case 6:
			Raid raid7 = new Raid("raid7.csv");
			raid7.ativo = true;
			getRaids().add(raid7);
			numRaid++;
		break;
		case 7:
			Raid raid8 = new Raid("raid8.csv");
			raid8.ativo = true;
			getRaids().add(raid8);
			numRaid++;
		break;
		case 8:
			Raid raid9 = new Raid("raid9.csv");
			raid9.ativo = true;
			getRaids().add(raid9);
			numRaid++;
		break;
		case 9:
			Raid raid10 = new Raid("raid10.csv");
			raid10.ativo = true;
			getRaids().add(raid10);
			numRaid++;
		break;
		case 10:
			Raid raid11 = new Raid("raid11.csv");
			raid11.ativo = true;
			getRaids().add(raid11);
			numRaid++;
		break;
		case 11:
			Raid raid12 = new Raid("raid12.csv");
			raid12.ativo = true;
			getRaids().add(raid12);
			numRaid++;
		break;
		case 12:
			Raid raid13 = new Raid("raid13.csv");
			raid13.ativo = true;
			getRaids().add(raid13);
			numRaid++;
		break;
		case 13:
			Raid raid14 = new Raid("raid14.csv");
			raid14.ativo = true;
			getRaids().add(raid14);
			numRaid++;
		break;
		case 14:
			Raid raid15 = new Raid("raid15.csv");
			raid15.ativo = true;
			getRaids().add(raid15);
			numRaid++;
		break;
		case 15:
			Raid raid16 = new Raid("raid16.csv");
			raid16.ativo = true;
			getRaids().add(raid16);
			numRaid++;
		break;
		case 16:
			Raid raid17 = new Raid("raid17.csv");
			raid17.ativo = true;
			getRaids().add(raid17);
			numRaid++;
		break;
		case 17:
			Raid raid18 = new Raid("raid18.csv");
			raid18.ativo = true;
			getRaids().add(raid18);
			numRaid++;
		break;
		case 18:
			Raid raid19 = new Raid("raid19.csv");
			raid19.ativo = true;
			getRaids().add(raid19);
			numRaid++;
		break;
		case 19:
			Raid raid20 = new Raid("raid20.csv");
			raid20.ativo = true;
			getRaids().add(raid20);
			numRaid++;

			CanvasGame.setEndGame(true);
		break;
		
		
		
	}
	
	}

	public static void setRaids(ArrayList<Raid> raids) {
		GerenciadorDeRaids.raids = raids;
	}

	public static ArrayList<Raid> getRaids() {
		return raids;
	}

	


}
