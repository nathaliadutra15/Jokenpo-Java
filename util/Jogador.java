package util;

import java.net.Socket;

public class Jogador {
    private Socket socket;
    private int modalidadeJogo;

    public Jogador(Socket socket, int modalidadeJogo){
        this.socket = socket;
        this.modalidadeJogo = modalidadeJogo;
    }

    public int getModalidadeJogo() {
        return this.modalidadeJogo;
    }

    public Socket getSocket() {
        return this.socket;
    }

    
}
