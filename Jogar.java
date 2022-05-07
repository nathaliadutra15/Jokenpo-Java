import java.net.Socket;
import java.util.ArrayList;

public class Jogar extends Thread {
    Socket socket;
    CanalComunicacao comunicacao;
    //ArrayList<Socket> listaEspera = new ArrayList<>();
    ArrayList<Socket> listaConectados = new ArrayList<>();
    
   
    public Jogar(Socket socket) {
        this.socket = socket;
        this.comunicacao = new CanalComunicacao(socket);
    }

    public void receberLista(ArrayList<Socket> listaConectados){
        this.listaConectados = listaConectados;
    }

    @Override
    public void run() {

        Jogador novoJogador = (Jogador) comunicacao.receive();

        System.out.println("====================================================");
        System.out.println("LAÇO");
        for (Socket socketAguardando : listaConectados) {
            
            System.out.println(socketAguardando.toString());
        }
        

        /* switch (novoJogador.getModalidadeJogo()) {
            case 1:
            this.listaEspera.add(this.socket);
           
                try {
                    while (!verificaDisponibilidade()) {
                        System.out.println("Procurando um jogador...");
    
                        sleep(5000);
                    }

                    System.out.println("Achou alguem!");
    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
               
                break;

            default:
                break;
        }
 */
    }

   /*  public boolean verificaDisponibilidade() {
        for (Socket socketAguardando : listaEspera) {
            System.out.println(socketAguardando.toString());
        }
        return false;
    } */
}