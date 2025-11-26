package src.analizeSintatico;

import utils.VetorDinamico;

public class Atribuicao {

    private static final int INCREMENTO_AVANCO = 1;

    public static int iniciarProcessamento(ContextoAnalise contextoAnalise) {
        if (contextoAnalise == null) {
            throw new IllegalArgumentException("Contexto não pode ser null");
        }

        VetorDinamico vetorTokens = contextoAnalise.obterVetorTokens();
        int indiceAtual = contextoAnalise.obterIndiceAtual();

        validarIdentificador(vetorTokens, indiceAtual);
        indiceAtual += INCREMENTO_AVANCO;

        validarOperadorIgual(vetorTokens, indiceAtual);
        indiceAtual += INCREMENTO_AVANCO;

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
            indiceAtual += INCREMENTO_AVANCO;
        }

        if (!vetorTokens.obterElemento(indiceAtual).equals(ConstantesTokens.END)) {
            GerenciadorErros.endFaltando("Atribuição");
            throw new IllegalStateException("END esperado");
        }
        indiceAtual += INCREMENTO_AVANCO;

        return indiceAtual;
    }

    private static void validarIdentificador(VetorDinamico vetorTokens, int indice) {
        String tokenAtual = vetorTokens.obterElemento(indice);

        if (tokenAtual.equals(ConstantesTokens.EOF) || !tokenAtual.equals(ConstantesTokens.IDENTIFIER)) {
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
