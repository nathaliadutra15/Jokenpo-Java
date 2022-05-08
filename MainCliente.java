import java.net.Socket;
import java.util.Scanner;

public class MainCliente {
    public static void main(String[] args) {
        // Elementos para gerar o socket
        final String IP = "127.0.0.1";
        final int PORTA = 12345;

        // Guarda o socket gerado
        Socket socket;

        //PrintStream output = null;
        Scanner teclado = new Scanner(System.in);
        int modalidadeJogo;

        // Criar socket e pedir conexão
        try {
            socket = new Socket(IP, PORTA);

            System.out.println("*********************************");
            System.out.println("             JOKENPO             ");
            System.out.println("*********************************");
            System.out.println("Informe com quem deseja jogar: ");
            System.out.println("[1] Com um adversário conectado       [2] Com o computador");
            modalidadeJogo = teclado.nextInt();

            ModalidadeJogador novoJogador = new ModalidadeJogador(modalidadeJogo);

            CanalComunicacao comunicacao = new CanalComunicacao(socket);
            comunicacao.send(novoJogador);


        } catch (Exception e) {
            System.out.println("Não foi possível conectar ao servidor.");
            return;
        }

        // Encerrar conexão
        try {
            socket.close();
            teclado.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
