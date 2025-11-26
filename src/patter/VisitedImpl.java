package src.patter;

import src.ast.Var;

public class VisitedImpl implements Visitd {

    private static final String LLVM_HEADER = "target triple = \"x86_64-unknown-linux-gnu\"\n";
    private static final String LLVM_DEFINE_START = "define i32 @main() {\n";
    private static final String LLVM_DEFINE_END = "  ret i32 0\n}\n";

    private StringBuilder llvmCode;
    private int registroCounter;

    public VisitedImpl() {
        this.llvmCode = new StringBuilder();
        this.registroCounter = 0;
    }

    @Override
    public String Var(Var var) {
        String registro = generateLLVMVar(var);
        llvmCode.append(registro);
        return registro;
    }

    private String generateLLVMVar(Var var) {
        String tipoLLVM = var.getTipo().getLLVMType();
        String nomeVariavel = var.getNome();

        if (var.getValor() != null) {
            return String.format("  %%v%d = alloca %s\n  store %s %s, %s* %%v%d\n",
                registroCounter,
                tipoLLVM,
                tipoLLVM,
                var.getValor().toString(),
                tipoLLVM,
                registroCounter++);
        } else {
            return String.format("  %%%s = alloca %s\n",
                nomeVariavel,
                tipoLLVM);
        }
    }

    public String gerarLLVMCompleto(String corpo) {
        StringBuilder completo = new StringBuilder();
        completo.append(LLVM_HEADER);
        completo.append("\n");
        completo.append(LLVM_DEFINE_START);
        completo.append(corpo);
        completo.append(LLVM_DEFINE_END);
        return completo.toString();
    }

    public String obterCodigoLLVM() {
        return llvmCode.toString();
    }

    public void limpar() {
        llvmCode = new StringBuilder();
        registroCounter = 0;
    }
}
