package src.ast;

public class IfExpression {
    private Object left;
    private String operator;
    private Object right;
    private Object thenValue;
    private Object elseValue;

    public IfExpression(Object left, String operator, Object right, Object thenValue, Object elseValue) {
        this.left = left;
        this.operator = operator;
        this.right = right;
        this.thenValue = thenValue;
        this.elseValue = elseValue;
    }

    public Object getLeft() { return left; }
    public String getOperator() { return operator; }
    public Object getRight() { return right; }
    public Object getThenValue() { return thenValue; }
    public Object getElseValue() { return elseValue; }
}
