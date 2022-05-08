import java.net.ServerSocket;
import java.net.Socket;

public class MainServidor {
public static void main(String[] args) {
    int PortaServidor = 12345;
    ServerSocket socketServidor;
    Socket socketCliente;
    GerenciaJogadores lista = new GerenciaJogadores();

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
            lista.addLista(socketCliente);
            Jogar iniciarJogo = new Jogar(socketCliente);
            iniciarJogo.receberLista(lista.getLista());
            iniciarJogo.start();
        }
    } catch (Exception e) {
        System.out.println("Erro na conexão: " + e.getMessage());
    }
}
    
}