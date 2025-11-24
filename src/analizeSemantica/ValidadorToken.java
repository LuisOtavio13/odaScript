package src.analizeSemantica;

import utils.VetorDinamico;


public class ValidadorToken {

    
    public static void validarIndice(int indice, VetorDinamico vetorTokens) {
        if (indice < 0 || indice >= vetorTokens.obterTamanho()) {
            throw new IndexOutOfBoundsException(
                String.format(
                    "indice invalido: %d. intervalo valido: [0, %d)",
                    indice,
                    vetorTokens.obterTamanho()
                )
            );
        }
    }

    public static boolean ehOperandoValido(String token) {
        return token != null && (
            token.equals(ConstantesTokens.NUMBER) ||
            token.equals(ConstantesTokens.STRING) ||
            token.equals(ConstantesTokens.IDENTIFIER)
        );
    }

    
    public static boolean ehOperadorRelacional(String token) {
        return token != null && (
            token.equals(ConstantesTokens.IGUAL_QUE) ||
            token.equals(ConstantesTokens.MAIOR_QUE) ||
            token.equals(ConstantesTokens.MENOR_QUE) ||
            token.equals(ConstantesTokens.MAIOR_OU_IGUAL_QUE) ||
            token.equals(ConstantesTokens.MENOR_OU_IGUAL_QUE)
        );
    }

    
    public static boolean ehOperadorAritmetico(String token) {
        return token != null && (
            token.equals("+") ||
            token.equals("-") ||
            token.equals("*") ||
            token.equals("/") ||
            token.equals("%")
        );
    }

   
    public static boolean ehPalavraChave(String token) {
        return token != null && (
            token.equals(ConstantesTokens.VAR) ||
            token.equals(ConstantesTokens.IF) ||
            token.equals(ConstantesTokens.ELSE) ||
            token.equals(ConstantesTokens.END)
        );
    }

   
    public static boolean ehEOF(String token) {
        return token != null && token.equals(ConstantesTokens.EOF);
    }

    
    public static void validarNaoEOF(String token) {
        if (ehEOF(token)) {
            throw new IllegalStateException("fim de arquivo inesperado");
        }
    }

    
    public static void validarTipo(String token, String tipoEsperado) {
        if (!token.equals(tipoEsperado)) {
            throw new IllegalStateException(
                String.format(
                    "token esperado %s, encontrado %s",
                    tipoEsperado,
                    token
                )
            );
        }
    }

   
    public static String obterTokenComValidacao(VetorDinamico vetorTokens, int indice) {
        validarIndice(indice, vetorTokens);
        return vetorTokens.obterElemento(indice);
    }

    
}
