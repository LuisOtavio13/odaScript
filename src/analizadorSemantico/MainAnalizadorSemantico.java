package src.analizadorSemantico;


import src.analizeSintatico.ConstantesTokens;
import src.ast.AstPattter;
import src.ast.Var;
import src.simbolos.TabelaDeSimbolos;
import utils.VetorDInamicoDeAST;
import utils.VetorDinamico;

public class MainAnalizadorSemantico {
    
    private static VetorDinamico variaveisDeclaradas = new VetorDinamico();

    public static VetorDInamicoDeAST main(TabelaDeSimbolos tabelaDeSimbolos, VetorDinamico vetorTokens) {

        VetorDInamicoDeAST vetor = new VetorDInamicoDeAST();

        for (int i = 0; i < vetorTokens.obterTamanho(); i++) {

            AstPattter ast = processarTokenPorTipo(tabelaDeSimbolos, vetorTokens, i);

            if (ast != null) {
                vetor.adicionar(ast);
            }
        }

        return vetor;
    }

    private static AstPattter processarTokenPorTipo(
            TabelaDeSimbolos tabelaDeSimbolos,
            VetorDinamico vetorTokens,
            int indiceAtual) {
        
        String tipoToken = vetorTokens.obterElemento(indiceAtual);

        switch (tipoToken) {

            case ConstantesTokens.VAR: {

                String nomeVariavel = tabelaDeSimbolos.obterLexema(indiceAtual +1);
                Retorno retorno = VariavelAnalizadorSemantico
                                        .declararVariavel(variaveisDeclaradas, nomeVariavel);
                variaveisDeclaradas = retorno.getListaRetorno();
                
                AstPattter astVar = retorno.getListaRetornoAST();
                if (astVar instanceof Var) {
                    Var varNode = (Var) astVar;
                    Object valorExtraido = extrairValorDaDeclaracao(tabelaDeSimbolos, vetorTokens, indiceAtual);
                    if (valorExtraido != null) {
                        varNode.setValor(valorExtraido);
                    }
                }
                
                return astVar;
            }

            case ConstantesTokens.IDENTIFIER: {

                String nome = tabelaDeSimbolos.obterLexema(indiceAtual);

                VariavelAnalizadorSemantico.verificarDeclaracao(variaveisDeclaradas, nome);

                return null; 
            }

            case ConstantesTokens.EOF:
                return null;

            default:
                return null;
        }
    }

    private static Object extrairValorDaDeclaracao(
            TabelaDeSimbolos tabelaDeSimbolos,
            VetorDinamico vetorTokens,
            int indiceVAR) {
        
        // Formato esperado: VAR identifier = value END
        // indiceVAR aponta para VAR
        // indiceVAR+1 é IDENTIFIER
        // indiceVAR+2 deveria ser IGUAL
        
        if (indiceVAR + 2 < vetorTokens.obterTamanho()) {
            String tipoToken = vetorTokens.obterElemento(indiceVAR + 2);
            if (ConstantesTokens.IGUAL.equals(tipoToken)) {
                if (indiceVAR + 3 < vetorTokens.obterTamanho()) {
                    String tipoValor = vetorTokens.obterElemento(indiceVAR + 3);
                    String lexemaValor = tabelaDeSimbolos.obterLexema(indiceVAR + 3);
                    
                    if (ConstantesTokens.NUMBER.equals(tipoValor)) {
                        try {
                            return Integer.parseInt(lexemaValor);
                        } catch (NumberFormatException e) {
                            return lexemaValor;
                        }
                    } else if (ConstantesTokens.IDENTIFIER.equals(tipoValor)) {
                        return lexemaValor;
                    } else if (ConstantesTokens.IF.equals(tipoValor)) {
                        // Valor é uma expressão IF; retorna um placeholder ou o tipo inferido
                        // Por enquanto, retorna um valor que implica em NUMBER
                        return 0;
                    }
                }
            }
        }
        
        return null;
    }
}
