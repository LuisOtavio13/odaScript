package src.simbolos;


public class TabelaDeSimbolos {
    
   
    private static final int FATOR_CRESCIMENTO_MATRIZ = 2;
    private static final int CAPACIDADE_INICIAL = 2;
    private static final int COLUNA_LEXEMA = 0;
    private static final int COLUNA_TIPO = 1;
    private static final int QUANTIDADE_COLUNAS = 2;
    
    
    private int quantidadeRegistros;        // quantidade de registros armazenados
    private int capacidadeAtual;            // capacidade máxima atual
    private String[][] tabelaSimbolo;       // matriz de simbolos [lexema, tipo]

    
    public TabelaDeSimbolos() {
        this.quantidadeRegistros = 0;
        this.capacidadeAtual = CAPACIDADE_INICIAL;
        this.tabelaSimbolo = new String[CAPACIDADE_INICIAL][QUANTIDADE_COLUNAS];
        inicializarMatriz();
    }

    
    private void inicializarMatriz() {
        for (int linhaAtual = 0; linhaAtual < capacidadeAtual; linhaAtual++) {
            tabelaSimbolo[linhaAtual][COLUNA_LEXEMA] = "";
            tabelaSimbolo[linhaAtual][COLUNA_TIPO] = "";
        }
    }

    
    public void adicionarSimbolo(String tipoSimbolo, String lexema) {
       
        validarParametrosAdicionar(tipoSimbolo, lexema);
        
        
        verificarCapacidadeERedimensionar();
        
        
        tabelaSimbolo[quantidadeRegistros][COLUNA_TIPO] = tipoSimbolo;
        tabelaSimbolo[quantidadeRegistros][COLUNA_LEXEMA] = lexema;
        quantidadeRegistros++;
    }

   
    private void validarParametrosAdicionar(String tipoSimbolo, String lexema) {
        if (tipoSimbolo == null || lexema == null) {
            throw new IllegalArgumentException("Tipo e léxema não podem ser null");
        }
    }

    
    private void verificarCapacidadeERedimensionar() {
        if (quantidadeRegistros >= capacidadeAtual) {
            redimensionarMatriz();
        }
    }

   
    private void redimensionarMatriz() {
        
        int novaCapacidade = capacidadeAtual * FATOR_CRESCIMENTO_MATRIZ;
        
        
        String[][] novaMatriz = new String[novaCapacidade][QUANTIDADE_COLUNAS];
        inicializarMatrizEspecifica(novaMatriz, novaCapacidade);
        
        
        copiarDadosParaNovaMatriz(novaMatriz);
        
        
        tabelaSimbolo = novaMatriz;
        capacidadeAtual = novaCapacidade;
    }

    
    private void inicializarMatrizEspecifica(String[][] matrizInicializar, int tamanho) {
        for (int linhaAtual = 0; linhaAtual < tamanho; linhaAtual++) {
            matrizInicializar[linhaAtual][COLUNA_LEXEMA] = "";
            matrizInicializar[linhaAtual][COLUNA_TIPO] = "";
        }
    }

    
    private void copiarDadosParaNovaMatriz(String[][] novaMatriz) {
        for (int linhaAtual = 0; linhaAtual < quantidadeRegistros; linhaAtual++) {
            novaMatriz[linhaAtual][COLUNA_LEXEMA] = tabelaSimbolo[linhaAtual][COLUNA_LEXEMA];
            novaMatriz[linhaAtual][COLUNA_TIPO] = tabelaSimbolo[linhaAtual][COLUNA_TIPO];
        }
    }

    
    public String obterLexema(int indice) {
        validarIndiceAcesso(indice);
        return tabelaSimbolo[indice][COLUNA_LEXEMA];
    }

    
    public String obterTipo(int indice) {
        validarIndiceAcesso(indice);
        return tabelaSimbolo[indice][COLUNA_TIPO];
    }

    
    private void validarIndiceAcesso(int indice) {
        if (indice < 0 || indice >= quantidadeRegistros) {
            throw new IndexOutOfBoundsException(
                String.format("Índice inválido: %d. Intervalo válido: [0, %d)", indice, quantidadeRegistros)
            );
        }
    }

    
    public String removerPeloIndice(int indice) {
        validarIndiceAcesso(indice);
        
        String lexemaRemovido = tabelaSimbolo[indice][COLUNA_LEXEMA];
        deslocarRegistrosAposPosicao(indice);
        quantidadeRegistros--;
        
        return lexemaRemovido;
    }

    
    private void deslocarRegistrosAposPosicao(int posicaoInicial) {
        for (int linhaAtual = posicaoInicial; linhaAtual < quantidadeRegistros - 1; linhaAtual++) {
            tabelaSimbolo[linhaAtual][COLUNA_LEXEMA] = tabelaSimbolo[linhaAtual + 1][COLUNA_LEXEMA];
            tabelaSimbolo[linhaAtual][COLUNA_TIPO] = tabelaSimbolo[linhaAtual + 1][COLUNA_TIPO];
        }
    }

    
    public int buscarLexema(String lexemaBuscado) {
        if (lexemaBuscado == null) {
            return -1;
        }

        for (int linhaAtual = 0; linhaAtual < quantidadeRegistros; linhaAtual++) {
            if (lexemaBuscado.equals(tabelaSimbolo[linhaAtual][COLUNA_LEXEMA])) {
                return linhaAtual;
            }
        }
        return -1;
    }

    
    public boolean contem(String lexema) {
        return buscarLexema(lexema) >= 0;
    }

    
    public int obterQuantidadeSimbolos() {
        return quantidadeRegistros;
    }

    
    public int obterCapacidade() {
        return capacidadeAtual;
    }

    
    public void exibirTabela() {
        System.out.println(construirStringFormatada());
    }

    
    private String construirStringFormatada() {
        StringBuilder construtorString = new StringBuilder();
        
       
        construtorString.append(String.format("%-40s | %-20s\n", "LÉXEMA", "TIPO"));
        construtorString.append("-".repeat(62)).append("\n");
        
        
        for (int linhaAtual = 0; linhaAtual < quantidadeRegistros; linhaAtual++) {
            String lexema = tabelaSimbolo[linhaAtual][COLUNA_LEXEMA];
            String tipo = tabelaSimbolo[linhaAtual][COLUNA_TIPO];
            construtorString.append(
                String.format("%-40s | %-20s\n", lexema, tipo)
            );
        }
        
        return construtorString.toString();
    }

    
    public void limpar() {
        quantidadeRegistros = 0;
        capacidadeAtual = CAPACIDADE_INICIAL;
        tabelaSimbolo = new String[CAPACIDADE_INICIAL][QUANTIDADE_COLUNAS];
        inicializarMatriz();
    }

    
    @Override
    public String toString() {
        return String.format(
            "TabelaDeSimbolos{quantidade=%d, capacidade=%d}",
            quantidadeRegistros,
            capacidadeAtual
        );
    }
}
