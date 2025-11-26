package utils;

import src.ast.AstPattter;

public class VetorDInamicoDeAST  {



   
    private static final int FATOR_CRESCIMENTO = 2;
    private static final int CAPACIDADE_INICIAL = 10;
    private static final int TAMANHO_MAXIMO = Integer.MAX_VALUE;
    
   
    private AstPattter[] elementosArmazenados;  
    private int tamanhoAtual;               

    
    public VetorDInamicoDeAST() {
        this.elementosArmazenados = new AstPattter[CAPACIDADE_INICIAL];
        this.tamanhoAtual = 0;
    }

    
    public VetorDInamicoDeAST(int capacidadeInicial) {
        if (capacidadeInicial <= 0) {
            throw new IllegalArgumentException("capacidade inicial deve ser maior que zero");
        }
        this.elementosArmazenados = new AstPattter[capacidadeInicial];
        this.tamanhoAtual = 0;
    }

    
    public void adicionar(AstPattter elemento) {
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
        
        
        AstPattter[] novoVetor = new AstPattter[novoTamanhoint];
        
        
        copiarElementosParaNovoVetor(novoVetor);
        
        
        elementosArmazenados = novoVetor;
    }

    
    private void copiarElementosParaNovoVetor(AstPattter[] novoVetor) {
        for (int indiceOrigem = 0; indiceOrigem < elementosArmazenados.length; indiceOrigem++) {
            novoVetor[indiceOrigem] = elementosArmazenados[indiceOrigem];
        }
    }

   
    public AstPattter obterElemento(int indice) {
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

    
    public AstPattter remover(int indice) {
        validarIndiceAcesso(indice);
        
        AstPattter elementoRemovido = elementosArmazenados[indice];
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
        elementosArmazenados = new AstPattter[CAPACIDADE_INICIAL];
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

