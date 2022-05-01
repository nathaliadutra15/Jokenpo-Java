package servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import util.Jogador;

public class ConexaoServidor {
    ArrayList<Jogador> esperaJogadores = new ArrayList<>();
    private int PortaServidor;
    private int modalidadeJogo;
    private Socket socketCliente = null;
    private ServerSocket socketServidor;

    public void ConexaoServidor(int PORTA) {
        this.PortaServidor = PORTA;

        try {
            socketServidor = new ServerSocket(PortaServidor);
        } catch (Exception e) {
            System.out.println("Porta " + PORTA + " já está em uso.");
            return;
        }

        AguardarJogador();

    }

    public void ConexaoServidor() {}

    public void AdicionaJogadorServer(Jogador jogador){
        this.esperaJogadores.add(jogador);
    }

    public void AguardarJogador() {
        try {
            while (true) {
                System.out.println("Aguardando a conexão do jogador...");

                this.socketCliente = socketServidor.accept();
                System.out.println("Jogador conectado!");
            }
        } catch (Exception e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }

    }
}
