package src.ast;

import src.patter.VisitedImpl;

public class PrintCmd extends AstPattter {
    
    private String nomeVariavel;

    public PrintCmd(String nomeVariavel) {
        this.nomeVariavel = nomeVariavel;
    }

    public String getNomeVariavel() {
        return nomeVariavel;
    }

    public void setNomeVariavel(String nomeVariavel) {
        this.nomeVariavel = nomeVariavel;
    }

    @Override
    public String accept(VisitedImpl visitd) {
        return visitd.Print(this);
    }
}
