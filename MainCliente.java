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
        int valorJokenpo;

        // Criar socket e pedir conexão
        try {
            socket = new Socket(IP, PORTA);
            CanalComunicacao comunicacao = new CanalComunicacao(socket);
            System.out.println("*********************************");
            System.out.println("             JOKENPO             ");
            System.out.println("*********************************");
            System.out.println("Informe com quem deseja jogar: ");
            System.out.println("[1] Com um adversário conectado       [2] Com o computador");
            modalidadeJogo = teclado.nextInt();

            // Recebe modalidade escolhida pelo jogador
            // ModalidadeJogador modalidadeJogador = new ModalidadeJogador(modalidadeJogo);

            // Envia a resposta da modalidade do jogador para a thread do jogo
            comunicacao.send(modalidadeJogo);

            // Recebe a resposta da liberação do jogo (A liberação é dada quando o jogador
            // deseja jogar contra o computador ou quando é encontrado um outro jogador
            // disponível na lista)
            LiberarJogo receberLiberacao = (LiberarJogo) comunicacao.receive();

            while (receberLiberacao.getRespostaLiberacao() == null) {
                System.out.println("Aguardando jogador...");
                Thread.sleep(6000);
            }

            System.out.println("Pronto para jogar!");
            Thread.sleep(2000);

            System.out.println("**************************************************************************");
            System.out.println("                                  JOKENPO                                 ");
            System.out.println("**************************************************************************");
            System.out.println("Você é: " + socket.toString());
            System.out.println("**************************************************************************");

            System.out.println("Escolha um dos seguintes valores:");
            System.out.println("[1] Pedra   [2] Papel   [3] Tesoura");
            valorJokenpo = teclado.nextInt();

            ValoresJokenpo valor = new ValoresJokenpo(valorJokenpo);

            comunicacao.send(valor);

            Thread.sleep(6000);
            Integer resultado = (Integer)comunicacao.receive();

            switch (resultado) {
                case 0:
                    System.out.println("Você perdeu :( ");
                    break;
                case 1:
                    System.out.println("Você ganhou! :) ");
                    break;
                case 2:
                    System.out.println("Empate!");
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            System.out.println("Não foi possível conectar ao servidor.");
            System.out.println(e.getMessage());
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
