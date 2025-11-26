package src.patter;

import src.ast.Var;
import src.ast.TipoInferencia;

public class LLVMGenerator {

    private StringBuilder irCode;
    private int alocaIndice;

    public LLVMGenerator() {
        this.irCode = new StringBuilder();
        this.alocaIndice = 0;
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
        
        irCode.append("  %").append(alocaIndice).append(" = alloca ").append(tipoLLVM).append("\n");
        
        if (variavel.getValor() != null) {
            String valorLLVM = criarValor(variavel.getValor(), variavel.getTipo());
            irCode.append("  store ").append(tipoLLVM).append(" ").append(valorLLVM)
                   .append(", ").append(tipoLLVM).append("* %").append(alocaIndice).append("\n");
        }
        
        alocaIndice++;
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
