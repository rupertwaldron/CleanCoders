package com.ruppyrup.episode30.fsm;


import com.ruppyrup.episode30.fsm.generators.implementers.JavaNestedSwitchCaseImplementer;
import com.ruppyrup.episode30.fsm.generators.implementers.KotlinNestedSwitchCaseImplementer;
import com.ruppyrup.episode30.fsm.generators.nestedSwitchCaseGenerator.NSCGenerator;
import com.ruppyrup.episode30.fsm.generators.nestedSwitchCaseGenerator.NSCNode;
import com.ruppyrup.episode30.fsm.generators.nestedSwitchCaseGenerator.NSCNodeVisitor;
import com.ruppyrup.episode30.fsm.lexer.Lexer;
import com.ruppyrup.episode30.fsm.optimizer.Optimizer;
import com.ruppyrup.episode30.fsm.parser.FsmSyntax;
import com.ruppyrup.episode30.fsm.parser.Parser;
import com.ruppyrup.episode30.fsm.parser.SyntaxBuilder;
import com.ruppyrup.episode30.fsm.semanticAnalyzer.AbstractSyntaxTree;
import com.ruppyrup.episode30.fsm.semanticAnalyzer.SemanticAnalyzer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.ruppyrup.episode30.fsm.parser.ParserEvent.EOF;


public class GumBallSMC {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) throw new RuntimeException("You must choose Java or Kotlin as a command line argument");
        new SmcCompiler(args[0]).run();
    }

    private static class SmcCompiler {
        private String javaPackage = null;
        private String outputDirectory = null;

        private final String compileType;

        private SmcCompiler(String compileType) {
            this.compileType = compileType;
        }

        public void run() throws IOException {
            javaPackage = "com.ruppyrup.episode30.fsm.generated";
            outputDirectory = "src/com/ruppyrup/episode30/fsm/generated";

            String fileName = "src/com/ruppyrup/episode30/fsm/GumBall.txt";
            String smContent = new String(Files.readAllBytes(Paths.get(fileName)));

            SyntaxBuilder syntaxBuilder = new SyntaxBuilder();
            Parser parser = new Parser(syntaxBuilder);
            Lexer lexer = new Lexer(parser);
            SemanticAnalyzer analyzer = new SemanticAnalyzer();
            Optimizer optimizer = new Optimizer();
            NSCGenerator generator = new NSCGenerator();

            lexer.lex(smContent);
            parser.handleEvent(EOF, -1, -1);

            FsmSyntax fsm = syntaxBuilder.getFsm();
            int syntaxErrorCount = fsm.errors.size();

            System.out.println(String.format("Compiled with %d syntax error%s.", syntaxErrorCount, (syntaxErrorCount == 1 ? "" : "s")));

            for (FsmSyntax.SyntaxError error : fsm.errors)
                System.out.println(error.toString());

            if (syntaxErrorCount > 0) return; // Early return if errors found

            AbstractSyntaxTree ast = analyzer.analyze(fsm);
            StateMachine stateMachine = optimizer.optimize(ast); // changed optimizer to converter for simplicity

            NSCNodeVisitor implementer = getCompileTypeImplementor(); // return a java or kotlin implementor

            NSCNode generatedNode = generator.generate(stateMachine);
            generatedNode.accept(implementer);

            String outputFileName = stateMachine.header.fsm + compileType + "." + implementer.getCodeType();

            Path outputPath;
            if (outputDirectory == null)
                outputPath = FileSystems.getDefault().getPath(outputFileName);
            else
                outputPath = FileSystems.getDefault().getPath(outputDirectory, outputFileName);

            Files.write(outputPath, implementer.getOutput().getBytes());
        }

        @NotNull
        private NSCNodeVisitor getCompileTypeImplementor() {
            NSCNodeVisitor implementer;
            if ("java".equalsIgnoreCase(compileType))
                implementer = new JavaNestedSwitchCaseImplementer(javaPackage);
            else if ("kotlin".equalsIgnoreCase(compileType))
                implementer = new KotlinNestedSwitchCaseImplementer(javaPackage);
            else
                throw new RuntimeException("Unrecognised compile type");
            return implementer;
        }
    }
}
