package src.ast;

import src.patter.VisitedImpl;

public abstract class AstPattter {
    public abstract String accept(VisitedImpl visitd);
}
