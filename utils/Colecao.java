package utils;

/**
 * interface colecao define contrato para estruturas de dados coleção 
 * @author Projeto OdaScript
 */
public interface Colecao {
    
    /**
     * adiciona um elemento a coleção
     * 
     * @param elemento - elemento a ser adicionado pode ser null
     */
    void adicionar(String elemento);
    
    /**
     * remove um elemento em uma posicao especifica
     * 
     * @param indice - posicao do elemento a remover
     * @return elemento removido
     * @throws IndexOutOfBoundsException - se indice for invalido
     */
    String remover(int indice);
    
    /**
     * obtem um elemento em uma posicao especifica
     * 
     * @param indice - posicao do elemento
     * @return elemento na posicao
     * @throws IndexOutOfBoundsException - se indice for invalido
     */
    String obterElemento(int indice);
    
    /**
     * busca sequencialmente um elemento
     * 
     * @param elemento - elemento a buscar
     * @return indice do elemento, ou -1 se nao encontrado
     */
    int buscarElemento(String elemento);
    
    /**
     * verifica se um elemento existe na colecao
     * 
     * @param elemento - elemento a verificar
     * @return true se existe, false caso contrario
     */
    boolean contem(String elemento);
    
    /**
     * obtem o tamanho atual da colecao
     * 
     * @return quantidade de elementos
     */
    int obterTamanho();
    
    /**
     * limpa todos os elementos da colecao
     */
    void limpar();
}
