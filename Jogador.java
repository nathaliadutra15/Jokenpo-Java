import java.io.Serializable;

public class Jogador implements Serializable {

    private int modalidadeJogo;

    public Jogador(int modalidadeJogo){

        this.modalidadeJogo = modalidadeJogo;
    }

    public int getModalidadeJogo() {
        return this.modalidadeJogo;
    }


}
