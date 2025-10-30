import java.io.File;
import java.io.FileReader;

import src.analizeSemantica.OKAY;

import src.simbolos.TabelaDeSimbolos;
import utils.VetorDinamico;

public class Main {
    public static void main(String[] args) {
        try{
            Yylex a = new Yylex(new FileReader("teste.oda"));
            a.yylex();
            VetorDinamico b = a.getVetor();
            TabelaDeSimbolos tb = a.aaa();
            System.out.println("-----------------");
            tb.percorerMatriz();
            System.out.println("-------------------");
            for (int i = 0; i < b.tamanhoVetor(); i++) {
                System.out.println(b.getElemento(i));
            }
            System.out.println("AQUI É O ANALIZADOR SINTATICO");
            OKAY.main(tb, b);
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
}
