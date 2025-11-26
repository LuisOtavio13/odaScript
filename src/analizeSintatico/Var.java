package src.analizeSintatico;

import utils.VetorDinamico;

public class Var {

    public static int iniciarProcessamento(ContextoAnalise contextoAnalise) {
        if (contextoAnalise == null) {
            throw new IllegalArgumentException("Contexto não pode ser null");
        }
        
        VetorDinamico vetorTokens = contextoAnalise.obterVetorTokens();
        int indiceAtual = contextoAnalise.obterIndiceAtual();

        indiceAtual++;
        validarNomeVariavel(vetorTokens, indiceAtual);
        indiceAtual++;
        
        validarOperadorIgual(vetorTokens, indiceAtual);
        indiceAtual++;
        
        String tokenAtual = vetorTokens.obterElemento(indiceAtual);
        if (tokenAtual.equals(ConstantesTokens.IF)) {
            indiceAtual = If.iniciarProcessamento(
                new ContextoAnalise(
                    contextoAnalise.obterTabelaDeSimbolos(),
                    indiceAtual,
                    vetorTokens
                )
            );
        } else if (!ValidadorToken.ehOperandoValido(tokenAtual)) {
            GerenciadorErros.valorInvalido(indiceAtual);
            throw new IllegalStateException("Valor inválido");
        } else {
            indiceAtual++;
        }
        
        if (!vetorTokens.obterElemento(indiceAtual).equals(ConstantesTokens.END)) {
            GerenciadorErros.endFaltando("VAR");
            throw new IllegalStateException("END esperado");
        }
      
        
        return indiceAtual;
    }

    private static void validarNomeVariavel(VetorDinamico vetorTokens, int indice) {
        String tokenAtual = vetorTokens.obterElemento(indice);
        
        if (!tokenAtual.equals(ConstantesTokens.IDENTIFIER) || 
            tokenAtual.equals(ConstantesTokens.EOF)) {
            GerenciadorErros.identificadorEsperado(indice);
            throw new IllegalStateException("Identificador esperado");
        }
    }

    private static void validarOperadorIgual(VetorDinamico vetorTokens, int indice) {
        String tokenAtual = vetorTokens.obterElemento(indice);
        
        if (!tokenAtual.equals(ConstantesTokens.IGUAL)) {
            GerenciadorErros.tokenInesperado(tokenAtual, "=", indice);
            throw new IllegalStateException("= esperado");
        }
    }
}
