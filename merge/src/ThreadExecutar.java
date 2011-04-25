import javax.sound.sampled.FloatControl;

import sun.audio.AudioPlayer;

public class ThreadExecutar extends Som {  
  
    public synchronized static void executarThread(String tipoSom) {  
        try {  
        	
        	AudioPlayer.player.start(Som.Sm(tipoSom));
<<<<<<< HEAD
        
=======
>>>>>>> f4be26e53949e1179bc18113b3ca7e59d38ee32e
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }  
}  