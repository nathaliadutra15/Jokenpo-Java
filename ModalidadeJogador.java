import java.io.Serializable;

public class ModalidadeJogador implements Serializable {

    private int modalidadeJogo;

    public ModalidadeJogador(int modalidadeJogo){

        this.modalidadeJogo = modalidadeJogo;
    }

    public int getModalidadeJogo() {
        return this.modalidadeJogo;
    }


}
