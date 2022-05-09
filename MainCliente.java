import java.net.Socket;
import java.util.Scanner;

public class MainCliente {
    public static void main(String[] args) {
        // Elementos para gerar o socket
        final String IP = "127.0.0.1";
        final int PORTA = 12345;

        // Guarda o socket gerado
        Socket socket;

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

            // Recebe modalidade escolhida pelo jogador
            ModalidadeJogador novoJogador = new ModalidadeJogador(modalidadeJogo);

            // Abre canal de comunicacao e enviar a resposta da modalidade do jogador para a
            // thread do jogo
            CanalComunicacao comunicacao = new CanalComunicacao(socket);
            comunicacao.send(novoJogador);

            // Recebe a resposta da liberação do jogo (A liberação é dada quando o jogador
            // deseja jogar contra o computador ou quando é encontrado um outro jogador
            // disponível na lista)
            LiberarJogo receberLiberacao = (LiberarJogo) comunicacao.receive();

            System.out.println("Pronto para jogar!");
            Thread.sleep(2000);

            switch (modalidadeJogo) {
                case 1:


                    break;
                case 2:


                

                    break;

                default:
                    break;
            }

        } catch (Exception e) {
            System.out.println("Não foi possível conectar ao servidor.");
            teclado.close();
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
