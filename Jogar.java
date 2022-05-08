import java.net.Socket;
import java.util.ArrayList;

public class Jogar extends Thread {
    Socket socket;
    CanalComunicacao comunicacao;
    ArrayList<Socket> listaConectados = new ArrayList<>();
    ModalidadeJogador getModalidadeJogador;
    
   
    public Jogar(Socket socket) {
        this.socket = socket;
        this.comunicacao = new CanalComunicacao(socket);
    }

    public void receberLista(ArrayList<Socket> listaConectados){
        this.listaConectados = listaConectados;
    }

    @Override
    public void run() {
        this.getModalidadeJogador = (ModalidadeJogador) comunicacao.receive();

        switch (getModalidadeJogador.getModalidadeJogo()) {
            case 1:
                //Procura jogador enquanto não houver jogador na lista
                try {
                    while (!verificaDisponibilidade()) {
                        System.out.println("Procurando um jogador...");
    
                        sleep(6000);
                    }

                    System.out.println("Achou alguem para jogar!");
    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
               
                break;
            
            case 2:
                //Remove o jogador da lista de espera para jogar
                for (int i = 0; i < listaConectados.size(); i++) {
                    if (this.socket == listaConectados.get(i)) {
                        listaConectados.remove(i);
                    }
                }    
            default:
                break;
        }
 
    }

   public boolean verificaDisponibilidade() {
       //Procura na lista de conectados se há jogadores diferentes desejando a mesma modalidade de jogo
       try {
        for (int i = 0; i < listaConectados.size(); i++) {
            if (this.socket != listaConectados.get(i) && getModalidadeJogador.getModalidadeJogo() == 1) {
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