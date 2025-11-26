package src.analizadorSemantico;


import src.analizeSintatico.ConstantesTokens;
import src.ast.AstPattter;
import src.ast.Var;
import src.ast.PrintCmd;
import src.ast.StringCmd;
import src.ast.IfExpression;
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
                        // if value is an IfExpression, set variable type to NUMBER by inference
                        if (valorExtraido instanceof IfExpression) {
                            varNode.setTipo(src.ast.TipoInferencia.NUMBER);
                        }
                    }
                }
                
                return astVar;
            }

            case ConstantesTokens.IDENTIFIER: {

                String nome = tabelaDeSimbolos.obterLexema(indiceAtual);

                VariavelAnalizadorSemantico.verificarDeclaracao(variaveisDeclaradas, nome);

                return null; 
            }

            case ConstantesTokens.PRINT: {
                // Próximo token pode ser identificador (variável) ou STRING (string literal)
                if (indiceAtual + 1 < vetorTokens.obterTamanho()) {
                    String proximoTokenType = vetorTokens.obterElemento(indiceAtual + 1);
                    
                    if (proximoTokenType.equals(ConstantesTokens.STRING)) {
                        // Print de string literal
                        String conteudo = tabelaDeSimbolos.obterLexema(indiceAtual + 1);
                        return new StringCmd(conteudo);
                    } else if (proximoTokenType.equals(ConstantesTokens.IDENTIFIER)) {
                        // Print de variável
                        String nomeVariavel = tabelaDeSimbolos.obterLexema(indiceAtual + 1);
                        VariavelAnalizadorSemantico.verificarDeclaracao(variaveisDeclaradas, nomeVariavel);
                        return new PrintCmd(nomeVariavel);
                    }
                }
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
                        // Valor é uma expressão IF — extrai partes: IF <left> <op> <right> <then> ELSE <else> END
                        int idx = indiceVAR + 3; // aponta para IF
                        // left
                        Object left = null;
                        if (idx + 1 < vetorTokens.obterTamanho()) {
                            String leftType = vetorTokens.obterElemento(idx + 1);
                            String leftLex = tabelaDeSimbolos.obterLexema(idx + 1);
                            if (ConstantesTokens.NUMBER.equals(leftType)) {
                                try { left = Integer.parseInt(leftLex); } catch (NumberFormatException ex) { left = leftLex; }
                            } else {
                                left = leftLex;
                            }
                        }
                        // operator
                        String operator = tabelaDeSimbolos.obterLexema(idx + 2);
                        // right
                        Object right = null;
                        if (idx + 3 < vetorTokens.obterTamanho()) {
                            String rightType = vetorTokens.obterElemento(idx + 3);
                            String rightLex = tabelaDeSimbolos.obterLexema(idx + 3);
                            if (ConstantesTokens.NUMBER.equals(rightType)) {
                                try { right = Integer.parseInt(rightLex); } catch (NumberFormatException ex) { right = rightLex; }
                            } else {
                                right = rightLex;
                            }
                        }
                        // then value (next)
                        Object thenVal = null;
                        if (idx + 4 < vetorTokens.obterTamanho()) {
                            String thenType = vetorTokens.obterElemento(idx + 4);
                            String thenLex = tabelaDeSimbolos.obterLexema(idx + 4);
                            if (ConstantesTokens.NUMBER.equals(thenType)) {
                                try { thenVal = Integer.parseInt(thenLex); } catch (NumberFormatException ex) { thenVal = thenLex; }
                            } else {
                                thenVal = thenLex;
                            }
                        }
                        // else value (after ELSE)
                        Object elseVal = null;
                        int elseTokenPos = idx + 5; // expected ELSE at +5
                        if (elseTokenPos < vetorTokens.obterTamanho() && ConstantesTokens.ELSE.equals(vetorTokens.obterElemento(idx + 5))) {
                            if (idx + 6 < vetorTokens.obterTamanho()) {
                                String elseType = vetorTokens.obterElemento(idx + 6);
                                String elseLex = tabelaDeSimbolos.obterLexema(idx + 6);
                                if (ConstantesTokens.NUMBER.equals(elseType)) {
                                    try { elseVal = Integer.parseInt(elseLex); } catch (NumberFormatException ex) { elseVal = elseLex; }
                                } else {
                                    elseVal = elseLex;
                                }
                            }
                        }

                        return new IfExpression(left, operator, right, thenVal, elseVal);
                    }
                }
            }
        }
        
        return null;
    }
}
