import utils.VetorDinamico;
import src.simbolos.TabelaDeSimbolos;

%%
%type Void 

%{
    private VetorDinamico vetor = new VetorDinamico();
    private TabelaDeSimbolos tb = new TabelaDeSimbolos();
    
    public VetorDinamico getVetor() {
        return vetor;
    }
    private void addSb(String tipo, String okay){
        tb.adicionarSimbolo(tipo, okay);
    }
    
    private void addToken(String tipo) {
        vetor.adicionar(tipo);
    }
    public TabelaDeSimbolos obterTabelaDeSimbolos(){
        return tb;
    }
%}

%%

"<" {
    addSb("MENOR_QUE", yytext());
    addToken("MENOR_QUE");
}
">" {
    addSb("MAIOR_QUE", yytext());
    addToken("MAIOR_QUE");
}

if {
    addSb("IF", yytext());
    addToken("IF");
}
else {
    addSb("ELSE", yytext());
    addToken("ELSE");
}
print {
    addSb("PRINT", yytext());
    addToken("PRINT");
}
">=" {
    addSb("MAIOR_OU_IGUAL_QUE", yytext());
    addToken("MAIOR_OU_IGUAL_QUE");
}

"<=" {
    addSb("MENOR_OU_IGUAL_QUE", yytext());
    addToken("MENOR_OU_IGUAL_QUE");
}

"==" {
    addSb("IGUAL_QUE", yytext());
    addToken("IGUAL_QUE");
}





var {
    addSb("VAR", yytext());
    addToken("VAR");
}
end {
    addSb("END", yytext());
    addToken("END");
}
= {
    addSb("IGUAL", yytext());
    addToken("IGUAL");
}
[a-zA-Z]+    { 
    addSb("IDENTIFIER", yytext());
    addToken("IDENTIFIER");
}

\"[^\"]*\"   {
    String conteudo = yytext().substring(1, yytext().length() - 1);
    addSb("STRING", conteudo);
    addToken("STRING");
}

[0-9]+       { 
    addSb("NUMBER", yytext());
    addToken("NUMBER");
}

[ \t\n\r]+   {  }

<<EOF>>      { 
    addToken("EOF");
    return null; 
}