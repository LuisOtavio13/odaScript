package src.ast;

public enum TipoInferencia {
    NUMBER("i32"),
    IDENTIFIER("i32"),
    BOOLEAN("i1"),
    DESCONHECIDO("void");

    private final String llvmType;

    TipoInferencia(String llvmType) {
        this.llvmType = llvmType;
    }

    public String getLLVMType() {
        return llvmType;
    }

    public static TipoInferencia inferirTipo(String token) {
        if (token == null) {
            return DESCONHECIDO;
        }

        try {
            Integer.parseInt(token);
            return NUMBER;
        } catch (NumberFormatException e) {
            if ("true".equalsIgnoreCase(token) || "false".equalsIgnoreCase(token)) {
                return BOOLEAN;
            }
            return IDENTIFIER;
        }
    }
}
