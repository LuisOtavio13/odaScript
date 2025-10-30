package src.analizeSemantica;

import src.a.Eu;
import src.simbolos.TabelaDeSimbolos;
import utils.VetorDinamico;

public class Main {
    public static void main(TabelaDeSimbolos tb,VetorDinamico vt ){
        for (int i = 0; i < vt.tamanhoVetor(); i++) {
            if(Eu.DEBUG_ALL || Eu.DEBUG_ANALIZE_SEMANTICA){
                System.out.println("[DEBUG] estamos no i = "+i);
            }
            switch (vt.getElemento(i)) {
                case Luis.VAR:
                     i = Var.start(new Config(tb, i, vt));
                    break;
            
                default:
                   System.out.println("Não conheço ...");
            }
            
        }
    }
}
