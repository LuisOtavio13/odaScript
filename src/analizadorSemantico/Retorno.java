package src.analizadorSemantico;

import src.ast.AstPattter;

import utils.VetorDinamico;

public class Retorno {
    private VetorDinamico listaRetorno;
    private AstPattter listaRetornoAST;

    public VetorDinamico getListaRetorno() {
        return listaRetorno;
    }
    public void setListaRetorno(VetorDinamico listaRetorno) {
        this.listaRetorno = listaRetorno;
    }
    public AstPattter getListaRetornoAST() {
        return listaRetornoAST;
    }
    public void setListaRetornoAST(AstPattter listaRetornoAST) {
        this.listaRetornoAST = listaRetornoAST;
    }
    
}
