package Mouse;


public class  MiraDoJogoEstatica extends MiraJogo {


	public MiraDoJogoEstatica() {
		atualizaMira();
		
	}
	
	@Override
	public void SimulaSe(int DiffTime) {

		timerIA +=DiffTime;
		
		if (timerIA>200)
			calculaIA();
				
	}
}
