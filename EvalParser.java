import java.util.*;
import java.lang.String;
import java.util.LinkedList; 

public class EvalParser {
  Scanner scan = new Scanner();

  int tempID = 0;
  String threeAddressResult = "";

  /***************** Three Address Translator ***********************/
  // TODO #2 Continued: Write the functions for E/E', T/T', and F. Return the temporary ID associated with each subexpression and
  //                    build the threeAddressResult string with your three address translation 
  /****************************************/
  public int threeAddrE(LinkedList<Token> tokens) {
    int leftID = threeAddrT(tokens);
    int currID = -1;
    while (true) {
      if (tokens.peek() != null && tokens.peek().tokenType == Token.TokenType.PLUS){
        String op = tokens.peek().tokenVal;
        tokens.remove();
        int rightID = threeAddrT(tokens);
        operationThreeAddr(leftID, op, rightID);
        currID = tempID;
        tempID++;
      }
      else if (tokens.peek() != null && tokens.peek().tokenType == Token.TokenType.MINUS) {
        String op = tokens.peek().tokenVal;
        tokens.remove();
        int rightID = threeAddrT(tokens);
        operationThreeAddr(leftID, op, rightID);
        currID = tempID;
        tempID++;
      }
      else {
        currID = leftID;
        break;
      }
    }
    return currID;
  }

  public int threeAddrT(LinkedList<Token> tokens) {
    int leftID = threeAddrF(tokens);
    int currID = -1;
    while (true) {
      if (tokens.peek() != null && tokens.peek().tokenType == Token.TokenType.MUL) {
        String op = tokens.peek().tokenVal;
        tokens.remove();
        int rightID = threeAddrF(tokens);
        operationThreeAddr(leftID, op, rightID);
        currID = tempID;
        tempID++;
      }
      else if (tokens.peek() != null && tokens.peek().tokenType == Token.TokenType.DIV) {
        String op = tokens.peek().tokenVal;
        tokens.remove();
        int rightID = threeAddrF(tokens);
        operationThreeAddr(leftID, op, rightID);
        currID = tempID;
        tempID++;
      }
      else {
        currID = leftID;
        break;
      }
    }
    return currID;
  }

  public int threeAddrF(LinkedList<Token> tokens) {
    int currID = -1;
    if (tokens.peek() != null && tokens.peek().tokenType == Token.TokenType.OP) {
      tokens.remove();
      threeAddrE(tokens);
      if (tokens.peek() != null && tokens.peek().tokenType == Token.TokenType.CP) {
        tokens.remove();
      }
      else {
        System.out.println("ERROR: Expression not supported by grammar");
        System.exit(1);
      }
    }
    else if (tokens.peek().tokenType == Token.TokenType.NUM) {
      constantThreeAddr(tokens.peek().tokenVal);
      currID = tempID;
      this.tempID++;
      tokens.remove();
    }
    else {
      System.out.println("ERROR: Expression not supported by grammar");
      System.exit(1);
    }
    return currID;
  }
  
  public void constantThreeAddr(String val) {
    this.threeAddressResult += "temp" + this.tempID + " = " + val + "\n";
  }

  public void operationThreeAddr(int val1, String op, int val2) {
    this.threeAddressResult += "temp" + this.tempID + " = temp" + val1 + " " + op + " temp" + val2 + "\n";
  }
  /***************** Simple Expression Evaluator ***********************/
  // TODO #1 Continued: Write the functions for E/E', T/T', and F. Return the expression's value
  /****************************************/

  /* TODO #1: Write a parser that can evaluate expressions */
  public int evaluateExpression(String eval){
    LinkedList<Token> tokens = scan.extractTokenList(eval);
    //todo
    return 0;
  }

  /* TODO #2: Now add three address translation to your parser*/
  public String getThreeAddr(String eval){
    this.threeAddressResult = "";
    this.tempID = 0;
    LinkedList<Token> tokens = scan.extractTokenList(eval);
    threeAddrE(tokens);
    return this.threeAddressResult;
  } 

}
