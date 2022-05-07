import java.net.Socket;
import java.util.ArrayList;

public class GerenciaJogadores {

    public ArrayList<Socket> jogadoresConectados = new ArrayList<>();

    public void addLista(Socket socket){
        this.jogadoresConectados.add(socket);
    }

    public ArrayList<Socket> getLista(){
        return jogadoresConectados;
    }
    
}
