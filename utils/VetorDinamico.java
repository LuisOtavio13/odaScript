package utils;

public class VetorDinamico implements Colecao {
    
   
    private static final int FATOR_CRESCIMENTO = 2;
    private static final int CAPACIDADE_INICIAL = 10;
    private static final int TAMANHO_MAXIMO = Integer.MAX_VALUE;
    
   
    private String[] elementosArmazenados;  
    private int tamanhoAtual;               

    
    public VetorDinamico() {
        this.elementosArmazenados = new String[CAPACIDADE_INICIAL];
        this.tamanhoAtual = 0;
    }

    
    public VetorDinamico(int capacidadeInicial) {
        if (capacidadeInicial <= 0) {
            throw new IllegalArgumentException("capacidade inicial deve ser maior que zero");
        }
        this.elementosArmazenados = new String[capacidadeInicial];
        this.tamanhoAtual = 0;
    }

    
    public void adicionar(String elemento) {
        verificarCapacidadeESomenteRedimensionar();
        
        if (elemento != null) {
            elementosArmazenados[tamanhoAtual] = elemento;
            tamanhoAtual++;
        }
    }

    private void verificarCapacidadeESomenteRedimensionar() {
        if (tamanhoAtual >= elementosArmazenados.length) {
            aumentarCapacidade();
        }
    }

   
    private void aumentarCapacidade() {
       
        long novoTamanhoLongo = (long) elementosArmazenados.length * FATOR_CRESCIMENTO;
        
       
        if (novoTamanhoLongo > TAMANHO_MAXIMO) {
            throw new OutOfMemoryError(
                "tamanho do vetor dinamico excede o limite maximo: " + TAMANHO_MAXIMO
            );
        }

      
        int novoTamanhoint = (int) novoTamanhoLongo;
        
        
        String[] novoVetor = new String[novoTamanhoint];
        
        
        copiarElementosParaNovoVetor(novoVetor);
        
        
        elementosArmazenados = novoVetor;
    }

    
    private void copiarElementosParaNovoVetor(String[] novoVetor) {
        for (int indiceOrigem = 0; indiceOrigem < elementosArmazenados.length; indiceOrigem++) {
            novoVetor[indiceOrigem] = elementosArmazenados[indiceOrigem];
        }
    }

   
    public String obterElemento(int indice) {
        validarIndiceAcesso(indice);
        return elementosArmazenados[indice];
    }

    
    private void validarIndiceAcesso(int indice) {
        if (indice < 0 || indice >= tamanhoAtual) {
            throw new IndexOutOfBoundsException(
                String.format("Índice inválido: %d. Intervalo válido: [0, %d)", indice, tamanhoAtual)
            );
        }
    }

    
    public int buscarElemento(String elementoBuscado) {
        if (elementoBuscado == null) {
            return -1;
        }

        for (int indiceAtual = 0; indiceAtual < tamanhoAtual; indiceAtual++) {
            if (elementoBuscado.equals(elementosArmazenados[indiceAtual])) {
                return indiceAtual;
            }
        }
        return -1;
    }

    
    public boolean contem(String elemento) {
        return buscarElemento(elemento) >= 0;
    }

    
    public String remover(int indice) {
        validarIndiceAcesso(indice);
        
        String elementoRemovido = elementosArmazenados[indice];
        deslocarElementosAposPosicao(indice);
        tamanhoAtual--;
        
        return elementoRemovido;
    }

    
    private void deslocarElementosAposPosicao(int posicaoInicial) {
        for (int indiceAtual = posicaoInicial; indiceAtual < tamanhoAtual - 1; indiceAtual++) {
            elementosArmazenados[indiceAtual] = elementosArmazenados[indiceAtual + 1];
        }
    }

    
    public int obterTamanho() {
        return tamanhoAtual;
    }

   
    public int obterCapacidade() {
        return elementosArmazenados.length;
    }

  
    public void limpar() {
        elementosArmazenados = new String[CAPACIDADE_INICIAL];
        tamanhoAtual = 0;
    }

    
    @Override
    public String toString() {
        StringBuilder construtorString = new StringBuilder("[");
        
        for (int indiceAtual = 0; indiceAtual < tamanhoAtual; indiceAtual++) {
            construtorString.append(elementosArmazenados[indiceAtual]);
            
            if (indiceAtual < tamanhoAtual - 1) {
                construtorString.append(", ");
            }
        }
        
        construtorString.append("]");
        return construtorString.toString();
    }
}
