import javax.sound.sampled.FloatControl;

import sun.audio.AudioPlayer;

public class ThreadExecutar extends Som {  
  
    public synchronized static void executarThread(String tipoSom) {  
        try {  
        	
        	AudioPlayer.player.start(Som.Sm(tipoSom));
        
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }  
    
    

}  