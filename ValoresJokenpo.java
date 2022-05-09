import java.io.Serializable;

public class ValoresJokenpo implements Serializable {
    private int valorJokenpo;

    public ValoresJokenpo(int valorJokenpo){
        this.valorJokenpo = valorJokenpo;
    }

    public int getValorJokenpo(){
        return this.valorJokenpo;
    }


}
