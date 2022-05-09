import java.io.Serializable;

public class LiberarJogo implements Serializable {
    private boolean liberacaoJogo;

    public void setRespostaLiberacao(Boolean respostaThread) {
        this.liberacaoJogo = respostaThread;
    }

    public Boolean getRespostaLiberacao() {
        return this.liberacaoJogo;
    }
}
