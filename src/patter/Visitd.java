package src.patter;

import src.ast.Var;
import src.ast.PrintCmd;
import src.ast.StringCmd;

public interface Visitd {
    String Var(Var var);
    String Print(PrintCmd printCmd);
    String String(StringCmd stringCmd);
}
