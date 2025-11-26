package src.patter;

import src.ast.Var;
import src.ast.TipoInferencia;
import src.ast.IfExpression;
import java.util.HashMap;
import java.util.Map;

public class LLVMGenerator {

    private StringBuilder irCode;
    private int alocaIndice;
    private int tempCounter;
    private int labelCounter;
    private Map<String, Integer> allocMap;

    public LLVMGenerator() {
        this.irCode = new StringBuilder();
        this.alocaIndice = 0;
        this.tempCounter = 0;
        this.labelCounter = 0;
        this.allocMap = new HashMap<>();
    }

    public void criarMain() {
        irCode.append("; Modulo LLVM - odaScript\n");
        irCode.append("target triple = \"x86_64-pc-linux-gnu\"\n\n");
        irCode.append("define i32 @main() {\n");
        irCode.append("entry:\n");
    }

    public void declararVariavel(Var variavel) {
        if (variavel == null || variavel.getNome() == null) {
            return;
        }

        String tipoLLVM = mapearTipo(variavel.getTipo());

        int myIndex = alocaIndice;
        irCode.append("  %").append(myIndex).append(" = alloca ").append(tipoLLVM).append("\n");

        if (variavel.getNome() != null) {
            allocMap.put(variavel.getNome(), myIndex);
        }

        if (variavel.getValor() != null) {

            if (variavel.getValor() instanceof IfExpression) {
                IfExpression ie = (IfExpression) variavel.getValor();
                emitIfExpressionStore(ie, tipoLLVM, myIndex);
            } else {
                String valorLLVM = criarValor(variavel.getValor(), variavel.getTipo());
                irCode.append("  store ").append(tipoLLVM).append(" ").append(valorLLVM)
                        .append(", ").append(tipoLLVM).append("* %").append(myIndex).append("\n");
            }
        }

        alocaIndice++;
    }

    private void emitIfExpressionStore(IfExpression ie, String tipoLLVM, int slotIndex) {

        // Prepara os operandos lado esquerdo e direito da comparacao
        String leftOp = prepareOperandForExpr(ie.getLeft(), "i32");
        String rightOp = prepareOperandForExpr(ie.getRight(), "i32");

        // Cria um registrador temporario para guardar o resultado da comparacao
        String condReg = "%t" + (tempCounter++);
        String cmpOp = "icmp sgt"; // default: maior que (signed greater than)

        // Decide o operador de comparação com base no IfExpression
        if (">".equals(ie.getOperator()) || "MAIOR_QUE".equals(ie.getOperator())) {
            cmpOp = "icmp sgt";
        } else if ("<".equals(ie.getOperator()) || "MENOR_QUE".equals(ie.getOperator())) {
            cmpOp = "icmp slt";
        } else if ("==".equals(ie.getOperator()) || "EQ".equals(ie.getOperator())) {
            cmpOp = "icmp eq";
        }

        // Gera a instrucao de comparacao
        irCode.append("  ").append(condReg).append(" = ").append(cmpOp).append(" i32 ")
                .append(leftOp).append(", ").append(rightOp).append("\n");

        // cria labels unicos para os blocos then, else e end
        String thenLbl = "then" + (labelCounter++);
        String elseLbl = "else" + (labelCounter++);
        String endLbl = "end" + (labelCounter++);

        // Branch condicional: se condReg for true → then, senão → else
        irCode.append("  br i1 ").append(condReg).append(", label %").append(thenLbl)
                .append(", label %").append(elseLbl).append("\n");

        // Bloco THEN
        irCode.append(thenLbl).append(":\n");
        String thenVal = (ie.getThenValue() == null) ? "0" : criarValor(ie.getThenValue(), TipoInferencia.NUMBER);
        irCode.append("  store i32 ").append(thenVal).append(", i32* %").append(slotIndex).append("\n");
        irCode.append("  br label %").append(endLbl).append("\n");

        // Bloco ELSE
        irCode.append(elseLbl).append(":\n");
        String elseVal = (ie.getElseValue() == null) ? "0" : criarValor(ie.getElseValue(), TipoInferencia.NUMBER);
        irCode.append("  store i32 ").append(elseVal).append(", i32* %").append(slotIndex).append("\n");
        irCode.append("  br label %").append(endLbl).append("\n");

        // Bloco END (fim da expressão condicional)
        irCode.append(endLbl).append(":\n");
    }

    private String prepareOperandForExpr(Object operand, String tipo) {
        if (operand == null)
            return "0";
        if (operand instanceof Integer) {
            return String.valueOf(operand);
        }

        String name = operand.toString();
        if (allocMap.containsKey(name)) {
            int idx = allocMap.get(name);
            String reg = "%t" + (tempCounter++);
            irCode.append("  ").append(reg).append(" = load ").append(tipo).append(", ")
                    .append(tipo).append("* %").append(idx).append("\n");
            return reg;
        }

        try {
            Integer.parseInt(name);
            return name;
        } catch (NumberFormatException ex) {
            return "0";
        }
    }

    private String mapearTipo(TipoInferencia tipo) {
        switch (tipo) {
            case NUMBER:
                return "i32";
            case BOOLEAN:
                return "i1";
            case IDENTIFIER:
                return "i32";
            default:
                return "void";
        }
    }

    private String criarValor(Object valor, TipoInferencia tipo) {
        if (valor == null) {
            return "0";
        }

        try {
            int intValue = Integer.parseInt(valor.toString());
            return String.valueOf(intValue);
        } catch (NumberFormatException e) {
            if ("true".equalsIgnoreCase(valor.toString())) {
                return "1";
            } else if ("false".equalsIgnoreCase(valor.toString())) {
                return "0";
            }
        }

        return "0";
    }

    public void retornarZero() {
        irCode.append("  ret i32 0\n");
    }

    public void gerarIR() {
        irCode.append("}\n");
        System.out.println(irCode.toString());
    }

    public void compilarParaObjeto(String nomeArquivo) {
        // Placeholder para futura compilacao
    }

    public void limpar() {
        irCode.setLength(0);
        alocaIndice = 0;
    }
}
