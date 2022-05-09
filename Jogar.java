import java.net.Socket;
import java.util.ArrayList;

public class Jogar extends Thread {
    Socket socket;
    CanalComunicacao comunicacao;
    ArrayList<Socket> listaConectados = new ArrayList<>();
    Socket jogadorLiberado;
    ModalidadeJogador getModalidadeJogador;
    ValoresJokenpo jogador1, jogador2;
    Socket ganhador;
    

    public Jogar(Socket socket) {
        this.socket = socket;
        this.comunicacao = new CanalComunicacao(socket);
    }

    public void receberLista(ArrayList<Socket> listaConectados) {
        this.listaConectados = listaConectados;
    }

    @Override
    public void run() {
        this.getModalidadeJogador = (ModalidadeJogador) comunicacao.receive();

        LiberarJogo liberacao = new LiberarJogo();

        switch (getModalidadeJogador.getModalidadeJogo()) {
            case 1:
                // Procura jogador enquanto não houver jogador na lista
                try {
                    while (!verificaDisponibilidade()) {
                        System.out.println("Procurando um jogador...");
                        sleep(6000);
                    }

                    liberacao.setRespostaLiberacao(true);
                    comunicacao.send(liberacao);

                    CanalComunicacao jogadorAnterior = new CanalComunicacao(this.jogadorLiberado);
                    jogadorAnterior.send(liberacao);
                    
                    //Receber os valores do jokenpo
                    this.jogador1 = (ValoresJokenpo)jogadorAnterior.receive();

                    comunicacao = new CanalComunicacao(this.socket);

                    this.jogador2 = (ValoresJokenpo) comunicacao.receive();

                    System.out.println(jogador1.getValorJokenpo() + " + " + jogador2.getValorJokenpo());


                    ValidaJokenpo validarGanhador = new ValidaJokenpo();
                    validarGanhador.setJogador1(this.jogadorLiberado,jogador1.getValorJokenpo());
                    validarGanhador.setJogador2(this.socket, jogador2.getValorJokenpo());

                    for (Socket ganhador : validarGanhador.getGanhador()) {
                        System.out.println(ganhador);
                        CanalComunicacao enviarGanhador = new CanalComunicacao(ganhador);
                        enviarGanhador.send("VOCÊ GANHOU!");
                    }
                    

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                break;

            case 2:
                // Remove o jogador da lista de espera para jogar
                for (int i = 0; i < listaConectados.size(); i++) {
                    if (this.socket == listaConectados.get(i)) {
                        listaConectados.remove(i);
                    }
                }

                liberacao.setRespostaLiberacao(true);
                comunicacao.send(liberacao);

            break;

            default:
                break;
           
        }
        
    }

    public boolean verificaDisponibilidade() {
        // Procura na lista de conectados se há jogadores diferentes desejando a mesma
        // modalidade de jogo
        try {
            for (int i = 0; i < listaConectados.size(); i++) {
                if (this.socket != listaConectados.get(i)) {
                    if (getModalidadeJogador.getModalidadeJogo() == 1) {
                        this.jogadorLiberado = listaConectados.get(i);
                        //Esvazia a lista
                        listaConectados.clear();
                        return true;
                    }
                }
            }
            return false;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}