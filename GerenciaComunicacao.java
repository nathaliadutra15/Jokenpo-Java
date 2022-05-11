import java.net.Socket;

public class GerenciaComunicacao {
    private Socket socket;
    private CanalComunicacao comunicacao;

    public GerenciaComunicacao(Socket socket, CanalComunicacao comunicacao){
        this.socket = socket;
        this.comunicacao = comunicacao;
    }

    public Socket getSocket(){
        return this.socket;
    }
    
    public CanalComunicacao getCanalComunicacao(){
        return this.comunicacao;
    }
}
