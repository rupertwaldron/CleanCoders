package com.ruppyrup.episode34.interpreter;

import org.junit.jupiter.api.Test;

import java.util.Stack;

public class CalculatorContext {
  Stack<Expression> stack=new Stack<>();

  public int parse(String str){
    String[] tokenList = str.split(" ");
    for (String symbol : tokenList) {
      if (!ParserUtil.isOperator(symbol)) { // push a number on to the stack
        Expression numberExpression = new NumberExpression(symbol);
        stack.push(numberExpression);
        System.out.printf("Pushed to stack: %d%n", numberExpression.interpret());
      } else if (ParserUtil.isOperator(symbol)) { // pop the last two numbers off the stack and combine them into an expression
        Expression firstExpression = stack.pop();
        Expression secondExpression = stack.pop();
        System.out.printf("Popped operands %d and %d%n",
            firstExpression.interpret(), secondExpression.interpret());
        Expression operator = ParserUtil.getExpressionObject(firstExpression, secondExpression, symbol); // retrieve the combined expression
        System.out.printf("Applying Operator: %s%n", operator);
        stack.push(operator); // push the combined expression back onto the stack
      }
    }
    return stack.pop().interpret(); // The final expression will contain multiple nested expressions
  }
}

class CalculatorContextTest {
  @Test
  public void testParse() throws Exception {
    String input="3 5 +";
    CalculatorContext calculatorContext = new CalculatorContext();
    int result = calculatorContext.parse(input);
    System.out.println("Final result should be 8 : " + result);

    input="2 1 5 + *";
    calculatorContext = new CalculatorContext();
    result = calculatorContext.parse(input);
    System.out.println("Final result should be 12 : " + result);

    input="7 10 2 1 5 + * - +";
    result = calculatorContext.parse(input);
    System.out.println("Final result should be 9 : " + result);
  }
}
