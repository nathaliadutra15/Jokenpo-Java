package servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import util.Jogador;

public class threadAtendeCliente extends Thread {
    //private ArrayList<Jogador> esperaJogadores = new ArrayList<>();
    private Socket socketCliente;
    //private Scanner input = null;
    int modalidadeJogo;
    Jogador novoJogador;
    ConexaoServidor JogadoresServer;

    // Receber o cliente
    public threadAtendeCliente(Socket socket, int modalidadeJogo) {
        this.socketCliente = socket;
        this.modalidadeJogo = modalidadeJogo;
        this.novoJogador = new Jogador(socket, modalidadeJogo);
        JogadoresServer = new ConexaoServidor();
    }

    @Override
    public void run() {
        confereModalidade();

    }

    public boolean confereModalidade() {
        if (JogadoresServer.esperaJogadores.size() > 0) {
            for (Jogador jogador : JogadoresServer.esperaJogadores) {
                if (jogador.getModalidadeJogo() == this.modalidadeJogo
                        && jogador.getSocket() != this.socketCliente)
                    return true;
                System.out.println(jogador.getSocket().toString());
            }
        } else {
            JogadoresServer.AdicionaJogadorServer(novoJogador);
        }


        /*switch (this.modalidadeJogo) {
            case 1:
                if (addJogadorServer.esperaJogadores.size() > 0) {
                    for (Jogador jogador : addJogadorServer.esperaJogadores) {
                        if (jogador.getModalidadeJogo() == this.modalidadeJogo
                                && jogador.getSocket() != this.socketCliente)
                            return true;
                        System.out.println(jogador.getSocket().toString());
                    }
                }
                break;

            default:
                break;
        }

        addJogadorServer.ConexaoServidor(novoJogador);*/

        return false;
    }

}
