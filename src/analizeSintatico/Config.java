package src.analizeSintatico;

import src.simbolos.TabelaDeSimbolos;
import utils.VetorDinamico;

public class Config {
    TabelaDeSimbolos tb;
    int index;
    VetorDinamico vt;

    public Config(TabelaDeSimbolos tb, int index, VetorDinamico vt){
        this.vt = vt;
        this.index = index;
        this.tb = tb;
    }
}
