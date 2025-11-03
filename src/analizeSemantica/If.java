package src.analizeSemantica;

import utils.VetorDinamico;

public class If {


    /*
    TODO: add sistema de else

     */
    public  static int start(Config a){
        System.out.println("hello word");
        int index = a.index;
        VetorDinamico  vt= a.vt;

        index++;
        if(!vt.getElemento(index).equals("NUMBER") && ! vt.getElemento(index).equals("BOLEAN")  && ! vt.getElemento(index).equals("IDENTIFIER")||  vt.getElemento(index).equals("EOF")){
            System.out.println("[ERROR] voce precisa de um numero ou um valor boleano");
            System.exit(1);
        }
        index++;
        index = verificarExpresao(vt,index);


        return index;
    }
    public static void verifiacarSeEhAlgoValido(int index, VetorDinamico vt){
        if(!vt.getElemento(index).equals("NUMBER") && ! vt.getElemento(index).equals("BOLEAN") && vt.getElemento(index).equals("IDENTIFIER") || vt.getElemento(index).equals("EOF")){
            System.out.println("[ERROR] voce precisa falar que numero esta comparando exemplo 2 == 3");
            System.exit(1);
        }
    }
    public static int verificarExpresao(VetorDinamico vt, int index){
        final String[] expresao = {"MENOR_QUE", "IGUAL_QUE", "MAIOR_QUE", "MENOR_OU_IGUAL_QUE", "MAIOR_OU_IGUAL_QUE"};
        boolean foi = false;
        index++;

        for (String s : expresao) {
            if (vt.getElemento(index).equals(s)) {
                foi = true;
                break;
            }
        }
        if(!foi){
            System.out.println("[ERROR] voce precisa informar um simbolo de comparacao ou um bolleano");
            System.exit(1);
        }
        verifiacarSeEhAlgoValido(index, vt);
        return  index;
    }
}
