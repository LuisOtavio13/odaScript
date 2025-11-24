package src.analizeSemantica;


public class ConstantesTokens {
    
    
    public static final String VAR = "VAR";
    public static final String IF = "IF";
    public static final String ELSE = "ELSE";
    public static final String END = "END";
    
   
    public static final String NUMBER = "NUMBER";
    public static final String STRING = "STRING";
    public static final String IDENTIFIER = "IDENTIFIER";
    
    
    public static final String IGUAL = "IGUAL";
    public static final String IGUAL_QUE = "IGUAL_QUE";
    public static final String MAIOR_QUE = "MAIOR_QUE";
    public static final String MENOR_QUE = "MENOR_QUE";
    public static final String MAIOR_OU_IGUAL_QUE = "MAIOR_OU_IGUAL_QUE";
    public static final String MENOR_OU_IGUAL_QUE = "MENOR_OU_IGUAL_QUE";
    
   
    public static final String EOF = "EOF";
    
    
    private ConstantesTokens() {
        throw new UnsupportedOperationException("Classe utilitária - instanciação não permitida");
    }
}
