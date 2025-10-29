package analizeSemantica;

import simbolos.TabelaDeSimbolos;
import utils.VetorDinamico;

public class Main {
    public static void main(TabelaDeSimbolos tb,VetorDinamico vt ){
        for (int i = 0; i < vt.tamanhoVetor(); i++) {
            if(Config.DEBUG_MODE){
                System.out.println("[DEBUG] estamos no i = "+i);
            }
            switch (vt.getElemento(i)) {
                case Config.VAR:
                     i = Var.start(tb,i, vt);                    
                    break;
            
                default:
                   System.out.println("Não conheço ...");
            }
            
        }
    }
}
