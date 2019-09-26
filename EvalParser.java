import java.util.*;
import java.lang.String;

public class EvalParser {
  Scanner scan = new Scanner();

  int tempID = 0;
  String threeAddressResult = "";

  /***************** Three Address Translator ***********************/
  // TODO #2 Continued: Write the functions for E/E', T/T', and F. Return the temporary ID associated with each subexpression and
  //                    build the threeAddressResult string with your three address translation 
  /****************************************/

  /***************** Simple Expression Evaluator ***********************/
  // TODO #1 Continued: Write the functions for E/E', T/T', and F. Return the expression's value
  /****************************************/

  /* TODO #1: Write a parser that can evaluate expressions */
  public int evaluateExpression(String eval){
    LinkedList<Token> tokens = scan.extractTokenList(eval);
    //todo
    return 0;
  }

  // Parsing E/E'
  private void E(LinkedList<Token> tokens){
    T();
    while(true){ // E'
      if(tokens.peek().tokenType == TokenType.PLUS){
        tokens.remove(); //match('+');
        T();
      }else if(tokens.peek().tokenType == TokenType.MINUS){
        tokens.remove(); //match('-');
        T();
      }else{
        break;
      }
    }
  }

  // Parsing T/T'
  private void T(LinkedList<Token> tokens){
    F();
    while(true){ // T'
      if(tokens.peek().tokenType == TokenType.MUL){
        tokens.remove(); //match('*');
        F();
      }else if(tokens.peek().tokenType == TokenType.DIV){
        tokens.remove(); //match('/');
        F();
      }else{
        break;
      }
    }
  }

  // Parsing F
  private void F(LinkedList<Token> tokens){
    if(tokens.peek().tokenType == TokenType.OP){
      tokens.remove(); //match('(');
      E();
      if(tokens.peek().tokenType == TokenType.CP){
        tokens.remove(); //match(')');
      }else{
        System.out.println("ERROR: Not in the grammer.");
        System.exit(1);
      }
    }else if(tokens.peek().tokenType == TokenType.NUM){
      tokens.remove(); //match(number);
    }else{
       System.out.println("ERROR: Not in the grammer.");
       System.exit(1);
    }
  }

  /* TODO #2: Now add three address translation to your parser*/
  public String getThreeAddr(String eval){
    this.threeAddressResult = "";
    return this.threeAddressResult;
  } 

}
