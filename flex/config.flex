import utils.VetorDinamico;
import simbolos.TabelaDeSimbolos;

%%
%type Void 

%{
    private VetorDinamico vetor = new VetorDinamico();
    private TabelaDeSimbolos tb = new TabelaDeSimbolos();
    
    public VetorDinamico getVetor() {
        return vetor;
    }
    private void addSb(String tipo, String okay){
        tb.add(tipo, okay);
    }
    
    private void addToken(String tipo) {
        vetor.adicionar(tipo);
    }
    public TabelaDeSimbolos aaa(){
        return tb;
    }
%}

%%

[a-zA-Z]+    { 
    addSb("IDENTIFIER", yytext());
    addToken("IDENTIFIER");
}

[0-9]+       { 
    addSb("NUMBER", yytext());
    addToken("NUMBER");
}

[ \t\n\r]+   { /* ignora */ }

<<EOF>>      { 
    return null; // ou não retorne nada
}