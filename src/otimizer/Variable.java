package src.otimizer;

import src.ast.AstPattter;
import src.ast.Var;
import utils.VetorDInamicoDeAST;

public class Variable {

    public static void otimizer(VetorDInamicoDeAST vetorDInamicoDeAST) {
        for (int i = 0; i < vetorDInamicoDeAST.obterTamanho(); i++) {
            AstPattter elemento = vetorDInamicoDeAST.obterElemento(i);
            if (elemento instanceof Var) {
                Var variavel = (Var) elemento;
                String nomeVariavel = variavel.getNome();
                removeVariableIfNotUsed(vetorDInamicoDeAST, nomeVariavel, i);
            }
        }
    }

    private static void removeVariableIfNotUsed(VetorDInamicoDeAST vetorDInamicoDeAST, String nomeVariavel, int index) {
        if (vetorDInamicoDeAST.buscarElemento(nomeVariavel) == -1) {
            vetorDInamicoDeAST.remover(index);
        }
    }
}
