package utils;
//\"       ::= casa uma aspas dupla literal precisa escapar com barra
// - \"       ::= casa uma aspas dupla literal precisa escapar com barra
// - [^\"\\]  ::= casa qualquer caractere que nao seja aspas ou barra invertida
// - \\.)     ::= permite escapes como \" ou \\ dentro da string
// - (...)    ::= agrupa os dois casos acima caractere normal ou escape
// - *        ::= permite zero ou mais desses caracteres dentro da string
// - \"       ::= fecha a aspas dupla
public class Regex {
   public final static String[] REGEX = {
    "VAR",
    "END",
    "IF",
    ":",
    "PRINT",
    "\\\"([^\\\"\\\\]|\\\\.)*\\\"",
    "[0-9]+"
   };
   public final static int VAR = 0;
   public final static int END = 1; 
   public final static int IF = 2;
   public final static int COLON = 3;
   public final static int PRINT = 4;
   public final static int STRING = 5;
   public final static int NUMBER = 6;
}