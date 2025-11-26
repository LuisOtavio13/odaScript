
import java.io.FileReader;
import java.io.IOException;

import src.analizadorSemantico.MainAnalizadorSemantico;
import src.analizeSintatico.OKAY;
import src.simbolos.TabelaDeSimbolos;
import src.patter.LLVMGenerator;
import src.ast.AstPattter;
import src.ast.Var;
import src.ast.PrintCmd;
import src.ast.StringCmd;
import utils.VetorDInamicoDeAST;
import utils.VetorDinamico;


public class Main {

    private static final String ARQUIVO_ENTRADA = "teste.oda";
    private static final String SEPARADOR_LINHA = "-".repeat(50);
    private static final String TITULO_TABELA_SIMBOLOS = "TABELA DE SÍMBOLOS LÉXICOS";
    private static final String TITULO_TOKENS = "TOKENS IDENTIFICADOS";
    private static final String TITULO_LLVM_IR = "LLVM IR GERADO (via JavaCPP)";

    public static void main(String[] args) {
        VetorDInamicoDeAST vetorDInamicoDeAST = new VetorDInamicoDeAST();
        String nomeArquivo = (args.length > 0) ? args[0] : ARQUIVO_ENTRADA;
        try {
            validarArquivoEntrada(nomeArquivo);

            Object[] resultadosLexicos = executarAnaliseLexicaCompleta(nomeArquivo);
            VetorDinamico tokenVector = (VetorDinamico) resultadosLexicos[0];
            TabelaDeSimbolos symbolTable = (TabelaDeSimbolos) resultadosLexicos[1];

            exibirResultadosAnaliseLexica(symbolTable, tokenVector);

            OKAY.main(symbolTable, tokenVector);
            vetorDInamicoDeAST = MainAnalizadorSemantico.main(symbolTable, tokenVector);

            exibirLLVMIR(vetorDInamicoDeAST);

        } catch (IOException ioException) {
            exibirErroArquivo(ioException);
        } catch (Exception exception) {
            exibirErroPrincipal(exception);
        }
    }

    private static void validarArquivoEntrada(String caminhoArquivo) throws IOException {
        java.io.File arquivo = new java.io.File(caminhoArquivo);
        if (!arquivo.exists() || !arquivo.isFile()) {
            throw new IOException("arquivo de entrada nao encontrado: " + caminhoArquivo);
        }
        if (!arquivo.canRead()) {
            throw new IOException("permissao negada para leitura do arquivo: " + caminhoArquivo);
        }
    }

    private static Object[] executarAnaliseLexicaCompleta(String caminhoArquivo) throws IOException {
        Yylex analisadorLexico = new Yylex(new FileReader(caminhoArquivo));
        analisadorLexico.yylex();
        
        return new Object[]{
            analisadorLexico.getVetor(),
            analisadorLexico.obterTabelaDeSimbolos()
        };
    }

    private static void exibirResultadosAnaliseLexica(
            TabelaDeSimbolos tabelaSimbolos,
            VetorDinamico vetorTokens) {
        exibirTabelaDeSimbolos(tabelaSimbolos);
        exibirTokens(vetorTokens);
    }

    private static void exibirTabelaDeSimbolos(TabelaDeSimbolos tabelaSimbolos) {
        System.out.println("\n" + SEPARADOR_LINHA);
        System.out.println(TITULO_TABELA_SIMBOLOS);
        System.out.println(SEPARADOR_LINHA);
        tabelaSimbolos.exibirTabela();
    }

    private static void exibirTokens(VetorDinamico vetorTokens) {
        System.out.println("\n" + SEPARADOR_LINHA);
        System.out.println(TITULO_TOKENS);
        System.out.println(SEPARADOR_LINHA);
        
        for (int indiceToken = 0; indiceToken < vetorTokens.obterTamanho(); indiceToken++) {
            String token = vetorTokens.obterElemento(indiceToken);
            System.out.println(String.format("  [%d] %s", indiceToken + 1, token));
        }
    }

    private static void exibirLLVMIR(VetorDInamicoDeAST ast) {
        System.out.println("\n" + SEPARADOR_LINHA);
        System.out.println(TITULO_LLVM_IR);
        System.out.println(SEPARADOR_LINHA);

        LLVMGenerator gerador = new LLVMGenerator();
        gerador.criarMain();

        for (int i = 0; i < ast.obterTamanho(); i++) {
            AstPattter elemento = ast.obterElemento(i);
            if (elemento instanceof Var) {
                gerador.declararVariavel((Var) elemento);
            } else if (elemento instanceof PrintCmd) {
                PrintCmd printCmd = (PrintCmd) elemento;
                gerador.imprimirVariavel(printCmd.getNomeVariavel());
            } else if (elemento instanceof StringCmd) {
                StringCmd stringCmd = (StringCmd) elemento;
                gerador.imprimirString(stringCmd.getConteudo());
            }
        }

        gerador.retornarZero();
        gerador.gerarIR();
        gerador.compilarParaObjeto("program");
        gerador.limpar();
    }

    private static void exibirErroArquivo(IOException ioException) {
        System.err.println("\n[ERRO DE ARQUIVO] " + ioException.getMessage());
        ioException.printStackTrace(System.err);
    }

    private static void exibirErroPrincipal(Exception exception) {
        System.err.println("\n[ERRO] falha na execucao da aplicacao:");
        System.err.println("tipo: " + exception.getClass().getSimpleName());
        System.err.println("mensagem: " + exception.getMessage());
        exception.printStackTrace(System.err);
    }
}


