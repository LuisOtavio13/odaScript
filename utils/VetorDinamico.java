package utils;

public class VetorDinamico {
    /**
     * Atributos
     * 
     * FATOR_CRESCIMENTO  : Fator de crescimento do vetor dinâmico
     * vetor: Armazena os elementos do vetor dinâmico
     * tamanhoAtual: Indica qual é o proximo indexe a adicionar um novo elemento
     */
     private final  int FATOR_CRESCIMENTO = 2;
     private String[] vetor = new String[10];
     private int tamanhoAtual = 0;
    
   
    public  void adicionar(String elemento) {
        verificarCapacidade();
        if( elemento != null){
            vetor[tamanhoAtual] = elemento;
            tamanhoAtual++;
        }
        
       
    }
   
   
    public  void verificarCapacidade() {
        if(tamanhoAtual == vetor.length){
          aumentarTamanho();
        }
    }
    /**
     * aumenta o tamanho do vetor dinamico
     * criando um novo vetor com o tamanho multiplicado
     */
    public void aumentarTamanho() {
            long tamanho = (long) (vetor.length * FATOR_CRESCIMENTO);
            if(tamanho > Integer.MAX_VALUE){
                throw new OutOfMemoryError("Tamanho do vetor excede o limite maximo");
            }
            int tamanhoInt =(int) tamanho;
            String[] novoVetor = new String[tamanhoInt];
            for(int i = 0; i < vetor.length; i++){
                novoVetor[i] = vetor[i];
            }
            vetor = novoVetor;
    }
    
    public String getElemento(int index) {
        if(index < 0 || index >= tamanhoAtual){
            throw new IndexOutOfBoundsException("Index: " + index + ", Tamanho atual: " + tamanhoAtual);
        }
        return vetor[index];
    }
    
    public int buscarElemento(String elemento) {
        for(int i = 0; i < tamanhoAtual; i++){
            if(vetor[i].equals(elemento)){
                return i;
            }
        }
        return -1;
    }
    public int tamanhoVetor() {
        return tamanhoAtual;
    }
    

}
