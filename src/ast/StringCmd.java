package src.ast;

import src.patter.VisitedImpl;

public class StringCmd extends AstPattter {
    
    private String conteudo;

    public StringCmd(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String accept(VisitedImpl visitd) {
        return visitd.String(this);
    }
}
