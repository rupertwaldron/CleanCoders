package com.ruppyrup.episode30.fsm;


import com.ruppyrup.episode30.fsm.generators.implementers.JavaNestedSwitchCaseImplementer;
import com.ruppyrup.episode30.fsm.generators.nestedSwitchCaseGenerator.NSCGenerator;
import com.ruppyrup.episode30.fsm.lexer.Lexer;
import com.ruppyrup.episode30.fsm.optimizer.Optimizer;
import com.ruppyrup.episode30.fsm.parser.FsmSyntax;
import com.ruppyrup.episode30.fsm.parser.Parser;
import com.ruppyrup.episode30.fsm.parser.SyntaxBuilder;
import com.ruppyrup.episode30.fsm.semanticAnalyzer.AbstractSyntaxTree;
import com.ruppyrup.episode30.fsm.semanticAnalyzer.SemanticAnalyzer;

import java.io.IOException;
import java.nio.file.*;

import static com.ruppyrup.episode30.fsm.parser.ParserEvent.EOF;


public class SMC {
  public static void main(String[] args) throws IOException {
      new SmcCompiler().run();

  }

  private static class SmcCompiler {
    private String javaPackage = null;
    private String outputDirectory = null;

    public void run() throws IOException {
      javaPackage = "com.ruppyrup.episode30.fsm.generated";
      outputDirectory = "src/com/ruppyrup/episode30/fsm/generated";

      String fileName = "src/com/ruppyrup/episode30/fsm/OneCoinTurnstile.txt";
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

      if (syntaxErrorCount == 0) {
        AbstractSyntaxTree ast = analyzer.analyze(fsm);
        StateMachine stateMachine = optimizer.optimize(ast);

        JavaNestedSwitchCaseImplementer implementer = new JavaNestedSwitchCaseImplementer(javaPackage);
        generator.generate(stateMachine).accept(implementer);

        String outputFileName = stateMachine.header.fsm + ".java";

        Path outputPath;
        if (outputDirectory == null)
          outputPath = FileSystems.getDefault().getPath(outputFileName);
        else
          outputPath = FileSystems.getDefault().getPath(outputDirectory, outputFileName);

        Files.write(outputPath, implementer.getOutput().getBytes());
      }
    }
  }
}
