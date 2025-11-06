package src.analizeSemantica;


import utils.VetorDinamico;

public class Var {
    /**
     * TODO: add a gramatica do var seguido essa gramatica
     * <VAR> -> "VAR" = INTERGER = "END" | "=" <EXPRESAO>
     * <EXPRESAO> -> String "END" | numero "END" | variavel "END"
     * 
     *
     * @return index
     */
    public static int start(Config args) {
        VetorDinamico vt = args.vt;
        int index = args.index;
        boolean ehIf = false;

        index++;
        verificarNome(vt, index);
        index+=2;
        if (vt.getElemento(index).equals("IF")) {
            index = If.start(new Config(args.tb, index, vt));
            ehIf = true;
        }
        verificarAtribuisaoOuOFinalDaVarivel(vt, index);
        
        if (vt.getElemento(index).equals("END")) {
            index++;
            return index;
        }



        if(!ehIf){
            verificarExpresao(vt, index);
            index+=2;
            if(!vt.getElemento(index).equals("END")|| vt.equals("EOF")){
                System.out.println("[ERROR] falta o end ai amigo ");
                System.exit(1);
            }
           
        }
        
        
        return index;
    }

    public static void verificarNome(VetorDinamico vt, int index) {
        if (!vt.getElemento(index).equals("IDENTIFIER") || vt.getElemento(index).equals("EOF")) {
            System.out.println("ERROR tem que colocar um nome na variavel ");
            System.exit(1);
        }
    }

    public static void verificarAtribuisaoOuOFinalDaVarivel(VetorDinamico vt, int index) {
        if (!vt.getElemento(index).equals("END") && !vt.getElemento(index).equals("IGUAL")
                || vt.getElemento(index).equals("EOF")) {
            System.out.println("Error voce precisa ou atribuir o valor a variavel ou colocar um end nela ");
            System.exit(1);
        }
    }

    public static void verificarExpresao(VetorDinamico vt, int index) {
        if (vt.getElemento(index).equals("IGUAL")) {
            index++;
            if (!vt.getElemento(index).equals("IF") && !vt.getElemento(index).equals("STRING")
                    && !vt.getElemento(index).equals("NUMBER") && !vt.getElemento(index).equals("IDENTIFIER")
                    || vt.getElemento(index).equals("EOF")) {
                System.out.println("[ERROR] voce precisa atribuir um valor a variavel");
                System.exit(1);
            }
        }
    }
}
