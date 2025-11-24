package src.analizeSemantica;

import utils.VetorDinamico;


public class If {
    
    
    private static final int INCREMENTO_AVANCO = 1;

  
    public static int iniciarProcessamento(ContextoAnalise contextoAnalise) {
        if (contextoAnalise == null) {
            throw new IllegalArgumentException("contexto de analise nao pode ser null");
        }
        
        VetorDinamico vetorTokens = contextoAnalise.obterVetorTokens();
        int indiceAtual = contextoAnalise.obterIndiceAtual();

        
        indiceAtual++;
        
       
        validarPrimeiroValorCondicao(vetorTokens, indiceAtual);
        
        
        indiceAtual = processarExpressaoCondicional(vetorTokens, indiceAtual);
        
       
        indiceAtual = processarBlocoRetorno(vetorTokens, indiceAtual);
        
        return indiceAtual;
    }

    
    private static void validarPrimeiroValorCondicao(VetorDinamico vetorTokens, int indice) {
        String tokenAtual = vetorTokens.obterElemento(indice);
        
        if (!ehValorValido(tokenAtual) || tokenAtual.equals(ConstantesTokens.EOF)) {
            exibirErroPrimeiroValor();
            throw new IllegalStateException("primeiro valor da condição inválido na posição " + indice);
        }
    }

   
    private static int processarExpressaoCondicional(VetorDinamico vetorTokens, int indice) {
    
        indice += INCREMENTO_AVANCO;
        
       
        if (!ehOperadorRelacionalValido(vetorTokens.obterElemento(indice))) {
            exibirErroOperadorRelacional();
            throw new IllegalStateException("Operador relacional inválido na posição " + indice);
        }
        
        
        indice += INCREMENTO_AVANCO;
        
       
        validarSegundoValorCondicao(vetorTokens, indice);
        
       
        indice += INCREMENTO_AVANCO;
        
        return indice;
    }

    
    private static void validarSegundoValorCondicao(VetorDinamico vetorTokens, int indice) {
        String tokenAtual = vetorTokens.obterElemento(indice);
        
        if (!ehValorValido(tokenAtual) || tokenAtual.equals(ConstantesTokens.EOF)) {
            exibirErroSegundoValor();
            throw new IllegalStateException("Segundo valor da condição inválido na posição " + indice);
        }
    }

    
    private static int processarBlocoRetorno(VetorDinamico vetorTokens, int indice) {
        
        validarValorRetorno(vetorTokens, indice);
        indice += INCREMENTO_AVANCO;
        
        
        if (!vetorTokens.obterElemento(indice).equals(ConstantesTokens.ELSE)) {
            exibirErroElseFaltando();
            throw new IllegalStateException("ELSE esperado na posição " + indice);
        }
        
        
        indice += INCREMENTO_AVANCO;
        
        
        validarValorRetorno(vetorTokens, indice);
        indice += INCREMENTO_AVANCO;
        
        
        if (!vetorTokens.obterElemento(indice).equals(ConstantesTokens.END)) {
            GerenciadorErros.endFaltando("IF");
            throw new IllegalStateException("END esperado na posição " + indice);
        }
        
        return indice;
    }

    
    private static void validarValorRetorno(VetorDinamico vetorTokens, int indice) {
        String tokenAtual = vetorTokens.obterElemento(indice);
        
        if (!ehValorValido(tokenAtual) || tokenAtual.equals(ConstantesTokens.EOF)) {
            exibirErroValorRetorno();
            throw new IllegalStateException("Valor de retorno inválido na posição " + indice);
        }
    }

    
    private static boolean ehValorValido(String token) {
        return token.equals(ConstantesTokens.NUMBER) ||
               token.equals(ConstantesTokens.IDENTIFIER) ||
               token.equals("BOOLEAN");
    }

   
    private static boolean ehOperadorRelacionalValido(String token) {
        return token.equals(ConstantesTokens.MAIOR_QUE) ||
               token.equals(ConstantesTokens.MENOR_QUE) ||
               token.equals(ConstantesTokens.IGUAL_QUE) ||
               token.equals(ConstantesTokens.MAIOR_OU_IGUAL_QUE) ||
               token.equals(ConstantesTokens.MENOR_OU_IGUAL_QUE) ||
               token.equals("DIFERENTE");
    }

    
    private static void exibirErroPrimeiroValor() {
        System.err.println("[ERRO SINTÁTICO] Primeiro valor da condição deve ser: NUMBER, IDENTIFIER ou BOOLEAN");
    }

    
    private static void exibirErroOperadorRelacional() {
        System.err.println("[ERRO SINTÁTICO] Operador relacional inválido. Válidos: ==, >, <, >=, <=, !=");
    }

   
    private static void exibirErroSegundoValor() {
        System.err.println("[ERRO SINTÁTICO] Segundo valor da condição deve ser: NUMBER, IDENTIFIER ou BOOLEAN");
    }

    
    private static void exibirErroElseFaltando() {
        System.err.println("[ERRO SINTÁTICO] ELSE obrigatório após condição IF");
    }

   
    
    private static void exibirErroValorRetorno() {
        System.err.println("[ERRO SINTÁTICO] Valor de retorno deve ser: NUMBER, IDENTIFIER ou BOOLEAN");
    }
}
