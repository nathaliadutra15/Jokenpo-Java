import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Jogar extends Thread {
    Socket socket;
    CanalComunicacao comunicacao;
    GerenciaComunicacao infoJogador;
    ArrayList<GerenciaComunicacao> listaConectados = new ArrayList<>();
    GerenciaComunicacao jogadorLiberado;
    int getModalidadeJogador;
    ValoresJokenpo valorJokenpo;
    private ValoresJokenpo valorJogador1, valorJogador2;

    public Jogar(GerenciaComunicacao infoJogador) {
        this.infoJogador = infoJogador;
        this.socket = infoJogador.getSocket();
        this.comunicacao = infoJogador.getCanalComunicacao();
        this.getModalidadeJogador = (int) comunicacao.receive();
    }

    public void receberLista(ArrayList<GerenciaComunicacao> listaConectados) {
        this.listaConectados = listaConectados;
    }

    @Override
    public void run() {
        System.out.println("RECEBIDO" + getModalidadeJogador);

        LiberarJogo liberacao = new LiberarJogo();

        switch (getModalidadeJogador) {
            case 1:
                // Procura jogador enquanto não houver jogador na lista
                try {
                    while (!verificaDisponibilidade()) {
                        System.out.println("Procurando um jogador...");
                        sleep(6000);
                    }

                    liberacao.setRespostaLiberacao(true);

                    infoJogador.getCanalComunicacao().send(liberacao);
                    jogadorLiberado.getCanalComunicacao().send(liberacao);

                    // Receber os valores do jokenpo

                    sleep(6000);
                    System.out.println((ValoresJokenpo) infoJogador.getCanalComunicacao().receive());
                    sleep(6000);
                    System.out.println((ValoresJokenpo) jogadorLiberado.getCanalComunicacao().receive());
                    sleep(6000);

                    /*
                     * sleep(2000);
                     * this.valorJogador1 = (ValoresJokenpo)
                     * infoJogador.getCanalComunicacao().receive();
                     * sleep(2000);
                     * this.valorJogador2 = (ValoresJokenpo)
                     * jogadorLiberado.getCanalComunicacao().receive();
                     * sleep(2000);
                     * 
                     * final int status_ganhador = 1;
                     * final int status_perdedor = 0;
                     * final int status_empate = 2;
                     * 
                     * switch (valorJogador1.getValorJokenpo()) {
                     * case 1:
                     * if (valorJogador2.getValorJokenpo() == 2) {
                     * sleep(2000);
                     * this.jogadorLiberado.getCanalComunicacao().send(status_ganhador);
                     * sleep(2000);
                     * this.infoJogador.getCanalComunicacao().send(status_perdedor);
                     * sleep(2000);
                     * }
                     * 
                     * if (valorJogador2.getValorJokenpo() == 3) {
                     * sleep(2000);
                     * this.jogadorLiberado.getCanalComunicacao().send(status_perdedor);
                     * sleep(2000);
                     * this.infoJogador.getCanalComunicacao().send(status_ganhador);
                     * sleep(2000);
                     * }
                     * 
                     * break;
                     * case 2:
                     * if (valorJogador2.getValorJokenpo() == 1) {
                     * sleep(2000);
                     * this.jogadorLiberado.getCanalComunicacao().send(status_perdedor);
                     * sleep(2000);
                     * this.infoJogador.getCanalComunicacao().send(status_ganhador);
                     * sleep(2000);
                     * }
                     * 
                     * if (valorJogador2.getValorJokenpo() == 3) {
                     * sleep(2000);
                     * this.jogadorLiberado.getCanalComunicacao().send(status_ganhador);
                     * sleep(2000);
                     * this.infoJogador.getCanalComunicacao().send(status_perdedor);
                     * sleep(2000);
                     * }
                     * 
                     * break;
                     * 
                     * case 3:
                     * if (valorJogador2.getValorJokenpo() == 1) {
                     * sleep(2000);
                     * this.jogadorLiberado.getCanalComunicacao().send(status_ganhador);
                     * sleep(2000);
                     * this.infoJogador.getCanalComunicacao().send(status_perdedor);
                     * sleep(2000);
                     * 
                     * }
                     * 
                     * if (valorJogador2.getValorJokenpo() == 2) {
                     * sleep(2000);
                     * this.jogadorLiberado.getCanalComunicacao().send(status_perdedor);
                     * sleep(2000);
                     * this.infoJogador.getCanalComunicacao().send(status_ganhador);
                     * sleep(2000);
                     * }
                     * 
                     * break;
                     * 
                     * default:
                     * sleep(2000);
                     * this.jogadorLiberado.getCanalComunicacao().send(status_empate);
                     * sleep(2000);
                     * this.infoJogador.getCanalComunicacao().send(status_empate);
                     * sleep(2000);
                     * 
                     * break;
                     * }
                     */

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2:
                // Remove o jogador da lista de espera para jogar
                for (int i = 0; i < listaConectados.size(); i++) {
                    if (this.socket == listaConectados.get(i).getSocket()) {
                        listaConectados.remove(i);
                    }
                }

                try {
                    liberacao.setRespostaLiberacao(true);
                    comunicacao.send(liberacao);

                    sleep(6000);
                    this.valorJogador1 = (ValoresJokenpo) infoJogador.getCanalComunicacao().receive();
                    System.out.println(valorJogador1.getValorJokenpo());

                    Random gerarValorPC = new Random();
                    int valorJokenpoPC = gerarValorPC.nextInt(3) + 1;
                    this.valorJogador2 = new ValoresJokenpo(valorJokenpoPC);
                    System.out.println(valorJogador2.getValorJokenpo());

                    switch (valorJogador1.getValorJokenpo()) {
                        case 1:
                            if (valorJogador2.getValorJokenpo() == 2) {
                                sleep(2000);
                                this.comunicacao.send("VOCÊ PERDEU! Valor do PC: " + this.valorJogador2.getValorJokenpo());
                            }
                            if (valorJogador2.getValorJokenpo() == 3) {
                                sleep(2000);
                                this.comunicacao.send("VOCÊ GANHOU! Valor do PC: " + this.valorJogador2.getValorJokenpo());
                            }

                            if (valorJogador2.getValorJokenpo() == 1) {
                                sleep(2000);
                            this.comunicacao.send("EMPATE! Valor do PC: " + this.valorJogador2.getValorJokenpo());
                                
                            }

                            break;
                        case 2:
                            if (valorJogador2.getValorJokenpo() == 1) {
                                sleep(2000);
                                this.comunicacao.send("VOCÊ GANHOU! Valor do PC: " + this.valorJogador2.getValorJokenpo());
                            }

                            if (valorJogador2.getValorJokenpo() == 3) {
                                sleep(2000);
                                this.comunicacao.send("VOCÊ PERDEU! Valor do PC: " + this.valorJogador2.getValorJokenpo());
                            }

                            if (valorJogador2.getValorJokenpo() == 2) {
                                sleep(2000);
                            this.comunicacao.send("EMPATE! Valor do PC: " + this.valorJogador2.getValorJokenpo());
                                
                            }

                            break;

                        case 3:
                            if (valorJogador2.getValorJokenpo() == 1) {
                                sleep(2000);
                                this.comunicacao.send("VOCÊ PERDEU! Valor do PC: " + this.valorJogador2.getValorJokenpo());

                            }

                            if (valorJogador2.getValorJokenpo() == 2) {
                                sleep(2000);
                                this.comunicacao.send("VOCÊ GANHOU! Valor do PC: " + this.valorJogador2.getValorJokenpo());
                            }

                            if (valorJogador2.getValorJokenpo() == 3) {
                                sleep(2000);
                            this.comunicacao.send("EMPATE! Valor do PC: " + this.valorJogador2.getValorJokenpo());
                                
                            }

                            break;

                        default:
                            

                            break;
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                break;

            default:
                break;
        }
    }

    public boolean verificaDisponibilidade() {
        // Procura na lista de conectados se há jogadores diferentes desejando a mesma
        // modalidade de jogo
        try {
            for (GerenciaComunicacao jogador : listaConectados) {
                if (this.socket != jogador.getSocket()) {
                    this.jogadorLiberado = jogador;
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}