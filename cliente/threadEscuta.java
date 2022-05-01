package cliente;

import java.net.Socket;
import java.util.Scanner;

import servidor.ConexaoServidor;
import servidor.threadAtendeCliente;

public class threadEscuta extends Thread {
    private Socket socket;
    int modalidadeJogo;
    int valorJokenpo;

    public threadEscuta(Socket socket, int modalidadeJogo) {
        this.socket = socket;
        this.modalidadeJogo = modalidadeJogo;
    }

    @Override
    public void run() {

        

        switch (modalidadeJogo) {
            case 1:
            threadAtendeCliente atendeCliente = new threadAtendeCliente(socket, modalidadeJogo);
            atendeCliente.start();

                break;
        
            default:
                break;
        }

    }
}