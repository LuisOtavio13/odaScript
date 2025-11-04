package src.analizeSemantica;

import utils.VetorDinamico;

public class If {


    /*
    TODO: add sistema de else
        number simbolo number
     */
    public static int start(Config a){
        
        int index = a.index;
        VetorDinamico  vt= a.vt;

        index++;
        if(!vt.getElemento(index).equals("NUMBER") && ! vt.getElemento(index).equals("BOLEAN")  && ! vt.getElemento(index).equals("IDENTIFIER")||  vt.getElemento(index).equals("EOF")){
            System.out.println("[ERROR] voce precisa de um numero ou um valor boleano");
            System.exit(1);
        }
        
        index = verificarExpresao(vt,index);
        index = verificarOqueRetorna(index, vt);
        index++;
        

        return index;
    }
    public static void verifiacarSeEhAlgoValido(int index, VetorDinamico vt){
        if(!vt.getElemento(index).equals("NUMBER") && ! vt.getElemento(index).equals("BOLEAN") && vt.getElemento(index).equals("IDENTIFIER") || vt.getElemento(index).equals("EOF")){
            System.out.println("[ERROR] voce precisa falar que numero esta comparando exemplo 2 == 3");
            System.exit(1);
        }
    }
    public static int verificarExpresao(VetorDinamico vt, int index){
        
        index++;
        

       
        if(! vt.getElemento(index).equals("MAIOR_QUE")){
            System.out.println("[ERROR] voce precisa informar um simbolo de comparacao ou um bolleano");
            System.out.println("É aqui msm");
            System.exit(1);
        }
        index++;
        verifiacarSeEhAlgoValido(index, vt);
        index++;
        return  index;
    }
    public static int verificarOqueRetorna(int index, VetorDinamico vt){
        index++;
        
        verifiacarSeEhAlgoValido(index, vt);

        if(!vt.getElemento(index).equals("ELSE")){
            System.out.println("[ERROR] deve colocar o else ai fi");
            System.exit(1);
        }
        index++;
        verifiacarSeEhAlgoValido(index, vt);
        return index;
    }
}
