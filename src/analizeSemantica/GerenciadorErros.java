package src.analizeSemantica;


public class GerenciadorErros {

   
    private static final String PREFIXO_ERRO_SINTAXICO = "[ERRO SINTATICO] ";
    private static final String PREFIXO_ERRO_SEMANTICO = "[ERRO SEMANTICO] ";
    private static final String PREFIXO_ERRO_LEXICO = "[ERRO LEXICO] ";
    private static final String PREFIXO_DEBUG = "[DEBUG] ";

   
  
    public static void operandoInvalido(int posicao) {
        System.err.println(
            PREFIXO_ERRO_SINTAXICO +
            "operando invalido na posicao " + posicao +
            " esperado NUMBER, STRING ou IDENTIFIER"
        );
    }

    
    public static void operadorInvalido(int posicao) {
        System.err.println(
            PREFIXO_ERRO_SINTAXICO +
            "operador invalido na posicao " + posicao +
            " validos: ==, >, <, >=, <=, !=, =, +, -, *, /, %"
        );
    }

    
    public static void identificadorEsperado(int posicao) {
        System.err.println(
            PREFIXO_ERRO_SINTAXICO +
            "identificador esperado na posicao " + posicao
        );
    }

    
    public static void endFaltando(String contexto) {
        System.err.println(
            PREFIXO_ERRO_SINTAXICO +
            "END obrigatorio para fechar " + contexto
        );
    }

    
    public static void tokenInesperado(String tokenEncontrado, String tokenEsperado, int posicao) {
        System.err.println(
            PREFIXO_ERRO_SINTAXICO +
            "na posicao " + posicao + ": esperado '" + tokenEsperado +
            "', encontrado '" + tokenEncontrado + "'"
        );
    }

   
    public static void fimArquivoPrematuro() {
        System.err.println(
            PREFIXO_ERRO_SINTAXICO +
            "fim de arquivo (EOF)"
        );
    }

    
    public static void valorInvalido(int posicao) {
        System.err.println(
            PREFIXO_ERRO_SINTAXICO +
            "valor invalido na posicao " + posicao +
            ". esperado: NUMBER, STRING ou IDENTIFIER"
        );
    }


    public static void variavelNaoDeclarada(String nomeVariavel) {
        System.err.println(
            PREFIXO_ERRO_SEMANTICO +
            "variavel '" + nomeVariavel + "' nao foi declarada"
        );
    }

    
    public static void variavelJaDeclarada(String nomeVariavel) {
        System.err.println(
            PREFIXO_ERRO_SEMANTICO +
            "variavel '" + nomeVariavel + "' ja foi declarada"
        );
    }

   
    public static void tipoIncompativel(String tipoEsperado, String tipoEncontrado) {
        System.err.println(
            PREFIXO_ERRO_SEMANTICO +
            "tipo incompativel esperado: " + tipoEsperado +
            " encontrado: " + tipoEncontrado
        );
    }

   
    public static void tokenDesconhecido(String token, int posicao) {
        System.err.println(
            PREFIXO_ERRO_LEXICO +
            "token desconhecido '" + token + "' na posicao " + posicao
        );
    }

   
    public static void caractereInvalido(char caractere, int linha, int coluna) {
        System.err.println(
            PREFIXO_ERRO_LEXICO +
            "Caractere inválido '" + caractere +
            "' na linha " + linha + ", coluna " + coluna
        );
    }

   

    public static void arquivoNaoEncontrado(String caminho) {
        System.err.println(
            "[ERRO] arquivo nao encontrado: " + caminho
        );
    }

    
    public static void erroIO(String mensagem) {
        System.err.println(
            "[ERRO DE I/O] " + mensagem
        );
    }

    
    public static void excecaoGenerica(Exception excecao) {
        System.err.println("[ERRO] falha na execucao da aplicacao:");
        System.err.println("tipo: " + excecao.getClass().getSimpleName());
        System.err.println("mensagem: " + excecao.getMessage());
    }

   
    public static void debug(String mensagem) {
        if (DebugConfig.isDebugAtivo()) {
            System.out.println(PREFIXO_DEBUG + mensagem);
        }
    }

  
    public static void debugPosicao(String mensagem, int posicao) {
        if (DebugConfig.isDebugAtivo()) {
            System.out.println(PREFIXO_DEBUG + mensagem + " (posicao: " + posicao + ")");
        }
    }


}
