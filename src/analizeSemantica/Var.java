package src.analizeSemantica;

import src.simbolos.TabelaDeSimbolos;
import utils.VetorDinamico;

public class Var {
    /**
     * TODO: add a gramatica do var seguido essa gramatica
     * <VAR> -> "VAR" = INTERGER = "END" | "=" <EXPRESAO>
     * <EXPRESAO> -> String "END" | numero "END" | variavel "END"
     * @param tb
     * @param i
     * @param vt
     * @return index
     */
    public static int start(Config args){
        VetorDinamico vt = args.vt;
        int index = args.index;

        index++;
        if(!vt.getElemento(index).equals("IDENTIFIER") || vt.getElemento(index).equals("EOF")){
            System.out.println("ERROR tem que colocar um nome na variavel ");
            System.exit(1);
        }
        index++;
        if(!vt.getElemento(index).equals("END") || ! vt.getElemento(index).equals("IGUAL") || vt.getElemento(index).equals("EOF")) {
            System.out.println("Error voce precisa ou atribuir o valor a variavel ou colocar um end nela ");
            System.exit(1);
        }else if (vt.getElemento(index).equals("END")){
            return index;
        }

        index++;

        return index;
    }

}
