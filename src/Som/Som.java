package Som;

import java.io.*;

import GameState.GamePanel;


import sun.audio.AudioStream;

public class Som  {  
  
    public static AudioStream Sm(String caminho) throws Exception {  
        InputStream in = Data.Imagem.class.getResourceAsStream(caminho);  
        AudioStream audioStream = new AudioStream(in);  
      
        return audioStream;  
    }  
} 