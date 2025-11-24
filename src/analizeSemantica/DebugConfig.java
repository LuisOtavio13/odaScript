package src.analizeSemantica;


public class DebugConfig {
    
  
    private static final boolean DEBUG_GLOBAL = false;
    private static final boolean DEBUG_ANALISE_LEXICA = false;
    private static final boolean DEBUG_ANALISE_SINTATICA = false;
    private static final boolean DEBUG_ANALISE_SEMANTICA = false;
    
    
    public static boolean isDebugAtivo() {
        return DEBUG_GLOBAL;
    }
    
    
    public static boolean isDebugAnaliseLexico() {
        return DEBUG_GLOBAL || DEBUG_ANALISE_LEXICA;
    }
    
    public static boolean isDebugAnaliseSintatica() {
        return DEBUG_GLOBAL || DEBUG_ANALISE_SINTATICA;
    }
    
    
    public static boolean isDebugAnaliseSemantica() {
        return DEBUG_GLOBAL || DEBUG_ANALISE_SEMANTICA;
    }
    

}
