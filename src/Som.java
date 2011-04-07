import java.io.*;

import sun.audio.AudioStream;

public class Som  {  
  
    public static AudioStream Sm(String caminho) throws Exception {  
        InputStream in = GamePanel.instance.getClass().getResourceAsStream(caminho);  
        AudioStream audioStream = new AudioStream(in);  
      
        return audioStream;  
    }  
} 