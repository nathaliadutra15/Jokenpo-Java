import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServidor {
    public static ArrayList<GerenciaComunicacao> jogadoresConectados = new ArrayList<>();
public static void main(String[] args) {
    int PortaServidor = 12345;
    ServerSocket socketServidor;
    Socket socketCliente;
    

    try {
        socketServidor = new ServerSocket(PortaServidor);
    } catch (Exception e) {
        System.out.println("Porta " + PortaServidor + " já está em uso.");
        return;
    }

    try {
        while (true) {
            System.out.println("Aguardando a conexão do jogador...");

            socketCliente = socketServidor.accept();
            
            System.out.println("Jogador conectado!");
            
            CanalComunicacao comunicacao = new CanalComunicacao(socketCliente);

            GerenciaComunicacao infoJogador = new GerenciaComunicacao(socketCliente, comunicacao);
            jogadoresConectados.add(infoJogador);

            Jogar iniciarJogo = new Jogar(infoJogador);
            iniciarJogo.receberLista(jogadoresConectados);
            iniciarJogo.start();
        }
    } catch (Exception e) {
        System.out.println("Erro na conexão: " + e.getMessage());
    }
}
    
}