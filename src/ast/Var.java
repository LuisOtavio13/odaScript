package src.ast;

import src.patter.VisitedImpl;

public class Var extends AstPattter {
    
    private String nome;
    private Object valor;
    private TipoInferencia tipo;

    public Var(String nome) {
        this.nome = nome;
        this.tipo = TipoInferencia.DESCONHECIDO;
    }

    public Var(String nome, Object valor) {
        this.nome = nome;
        this.valor = valor;
        this.tipo = TipoInferencia.inferirTipo(valor != null ? valor.toString() : null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
        this.tipo = TipoInferencia.inferirTipo(valor != null ? valor.toString() : null);
    }

    public TipoInferencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoInferencia tipo) {
        this.tipo = tipo;
    }

    @Override
    public String accept(VisitedImpl visitd) {
        return visitd.Var(this);
    }
}

