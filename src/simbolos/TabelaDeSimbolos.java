package src.simbolos;

import src.a.Eu;

public class TabelaDeSimbolos {
    static int tamanho = 0;
    static final int FATOR_CRESCIMENTO = 2;
    static int capacidade = 2;
    static String[][] tabela = new String[2][2];

    public static void add(String lexema, String tipo) {
        
        if(Eu.DEBUG_ALL || Eu.DEBUG_TB_SIMBOLOS){
            System.out.println("[DEBUG] adicionando no index " + tamanho + " da matriz  o lexema " + lexema + " do tipo " + tipo);
        }
        if (lexema == null || tipo == null) {
            return;
        }
        verificaCapacidade();
        tabela[tamanho][0] = lexema;
        tabela[tamanho][1] = tipo;
        tamanho++;
    }

    public static void verificaCapacidade() {
        if (tamanho >= capacidade) {
            if(Eu.DEBUG_TB_SIMBOLOS || Eu.DEBUG_ALL){
                System.out.println("[DEBUG] foi verificado a capasidade ");
            }
            capacidade += FATOR_CRESCIMENTO;
            String[][] novaTabela = new String[capacidade][2];
            novaTabela = inicializarMariz(novaTabela);
            duplicarMatriz(novaTabela);
        }
    }

    public static void duplicarMatriz(String[][] novaTabela) {
        if(Eu.DEBUG_ALL || Eu.DEBUG_TB_SIMBOLOS){
            System.out.println("[DEBUG] foi duplicado uma matriz");
        }
        for (int i = 0; i < tamanho; i++) {
            novaTabela[i][0] = tabela[i][0];
            novaTabela[i][1] = tabela[i][1];
        }
        tabela = novaTabela;
    }

    public static String[][] inicializarMariz(String[][] novaTabela) {
        if(Eu.DEBUG_TB_SIMBOLOS || Eu.DEBUG_ALL){
            System.out.println("[DEBUG] foi inicializado a matriz nova");
        }
        for (int i = 0; i < capacidade; i++) {
            novaTabela[i][0] = "";
            novaTabela[i][1] = "";
        }
        return novaTabela;
    }

    public static String getLexema(int indice) {
        return tabela[indice][0];
    }

    public static String remover(int indice) {
        if(Eu.DEBUG_ALL  || Eu.DEBUG_TB_SIMBOLOS){
            System.out.println("[DEBUG] foi removido o valor"+ tabela[indice][0]+ " - "+ tabela[indice][1]);
        }
        String lexemaRemovido = tabela[indice][0];
        for (int i = indice; i < tamanho - 1; i++) {
            tabela[i][0] = tabela[i + 1][0];
            tabela[i][1] = tabela[i + 1][1];
        }
        tamanho--;
        return lexemaRemovido;
    }

    public void percorerMatriz() {
        if(Eu.DEBUG_TB_SIMBOLOS || Eu.DEBUG_ALL){
            System.out.println("[DEBUG] estou percorendo a matriz");
        }
        for (int i = 0; i < tamanho; i++) {
           System.out.println(tabela[i][0] + " - " + tabela[i][1]);
        }
    }
}
