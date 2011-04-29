public class ThreadSom extends Thread {  
    private String tipoSom;  
  
    public ThreadSom(String tipoSom) {  
        this.tipoSom = tipoSom;  
    }  
  
    @Override  
    public void run() {  
        ThreadExecutar.executarThread(this.tipoSom);  
    }  
}  