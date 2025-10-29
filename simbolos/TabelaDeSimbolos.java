package simbolos;

public class TabelaDeSimbolos {
    static int tamanho = 0;
    static int capa = 2;
    static String[][] tabela = new String[2][2];

    public static void add(String lexema, String tipo) {
        if(lexema == null || tipo == null){
            return;
        }
         verificaCapacidade();
         tabela[tamanho][0] = lexema;
         tabela[tamanho][1] = tipo;
         tamanho++;
    }

    public static void verificaCapacidade() {
        if(tamanho >= capa) {
            capa += 2;
            String[][] novaTabela = new String[capa][2];
            for(int i = 0; i < tamanho; i++) {
                novaTabela[i][0] = tabela[i][0];
                novaTabela[i][1] = tabela[i][1];
            }
            tabela = novaTabela;
          
        }
    }
    public static String getLexema(int indice) {
        return tabela[indice][0];
    }
    public static String remover(int indice) {
        String lexemaRemovido = tabela[indice][0];
        for(int i = indice; i < tamanho - 1; i++) {
            tabela[i][0] = tabela[i + 1][0];
            tabela[i][1] = tabela[i + 1][1];
        }
        tamanho--;
        return lexemaRemovido;
    }
    public void percorerMatriz(){
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tabela[i].length; j++) {
                System.out.print(tabela[i][j] + " ");
            }
            System.out.println();
        }
    }
}
