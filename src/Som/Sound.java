package Som;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;


public class Sound {

	static public int timeSom = 0;
	static public boolean cont = false;

	static AudioPlayer MGP = AudioPlayer.player;


	public static void music(String som,boolean repete) {
		
		AudioStream BGM;
		AudioData MD;
		InputStream _Som = null;
		
		if (!repete) {
			
			try {
				BGM = new AudioStream(new FileInputStream(som));
				MD = BGM.getData();
				_Som = new AudioDataStream(MD);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			MGP.start(_Som);
			
		} else {
		
				 ContinuousAudioDataStream loop = null;
				
				 try {
				 BGM = new AudioStream(new FileInputStream(som));
				 MD = BGM.getData();
				 loop = new ContinuousAudioDataStream(MD);
				
				 } catch (IOException error) {
				 }
				
				 if (cont == false) {
				 MGP.start(loop);
				 cont = true;
				 //System.out.print(cont);
				 } else {
				 MGP.isInterrupted();
				 }
			}
		}
}
