import java.net.Socket;
import java.util.ArrayList;

public class ValidaJokenpo {

    int valorJogador1, valorJogador2;
    Socket socketJogador1, socketJogador2;
    ArrayList<Socket> ganhador = new ArrayList<>();

    public void setJogador1(Socket socketJogador1, int valorJogador1) {
        this.socketJogador1 = socketJogador1;
        this.valorJogador1 = valorJogador1;

    }

    public void setJogador2(Socket socketJogador2, int valorJogador2) {
        this.socketJogador2 = socketJogador2;
        this.valorJogador2 = valorJogador2;
    }

    public ArrayList<Socket> getGanhador() {

        switch (valorJogador1) {
            // Pedra
            case 1:
                if (valorJogador2 == 2) {
                    ganhador.add(socketJogador2);
                    return ganhador;
                } else {
                    ganhador.add(socketJogador1);
                    return ganhador;
                }

                // Papel
            case 2:
                if (valorJogador2 == 1) {
                    ganhador.add(socketJogador1);
                    return ganhador;
                } else {
                    ganhador.add(socketJogador2);
                    return ganhador;
                }

                // Tesoura
            case 3:
                if (valorJogador2 == 2) {
                    ganhador.add(socketJogador1);
                    return ganhador;
                } else {
                    ganhador.add(socketJogador2);
                    return ganhador;
                }

            default:
                ganhador.add(socketJogador1);
                ganhador.add(socketJogador2);
                return ganhador;
        }

    }

}
