public class EvalParserTest{

  public static void TestExpressionEval(){
    System.out.println("*******************************************");
    System.out.println("Testing Expression evaluation");
    EvalParser parser = new EvalParser();

    String eval = "9+(2*2)";
    assert(parser.evaluateExpression(eval) == 13);

    eval = "10-9";
    assert(parser.evaluateExpression(eval) == 1);

    eval = "3-5*17";
    assert(parser.evaluateExpression(eval) == -82);

    System.out.println("Congrats: expression evaluation tests passed! Now make your own test cases "+
                       "(this is only a subset of what we will test your code on)");
    System.out.println("*******************************************");
    
    System.out.println("Testing More Expression evaluation");
    
    // Logan: Test the precedence of terms in parentheses
    eval = "(9+2)*2";
    assert(parser.evaluateExpression(eval) == 22);

    // Logan: Test an expression entirely contained in parentheses
    eval = "(3-5*17)";
    assert(parser.evaluateExpression(eval) == -82);
    
    // Logan: Test a very long expression
    eval = "1+1+1+1+1+1+1+1+1+1";
    assert(parser.evaluateExpression(eval) == 10);

    // Logan: Test nested parentheses
    eval = "(((1+1)*2)-3)";
    assert(parser.evaluateExpression(eval) == 1);

    // Carly: Test parentheses with only one number inside
    eval = "(5)";
    assert(parser.evaluateExpression(eval) == 5);

    // Carly: Test division
    eval = "6 / 2";
    assert(parser.evaluateExpression(eval) == 3);
    
   System.out.println("Additional Expression Evaluation Tests Passed");
  }

  public static void TestThreeAddrGen(){
    System.out.println("*******************************************");
    System.out.println("Testing Three Address Generation");
    EvalParser parser = new EvalParser();

    String eval = "9+(2*2)";
    String result = "temp0 = 9\n"+
                    "temp1 = 2\n"+
                    "temp2 = 2\n"+
                    "temp3 = temp1 * temp2\n"+
                    "temp4 = temp0 + temp3\n";
    assert(parser.getThreeAddr(eval).equals(result));

    System.out.println("Congrats: three address generation tests passed! Now make your own test cases "+
                       "(this is only a subset of what we will test your code on)");
    System.out.println("*******************************************");

    System.out.println("Testing More Three Address Generation");
    
    // Logan: Test expression with no parentheses
    eval = "3-5*17";
    result = "temp0 = 3\n"+
             "temp1 = 5\n"+
             "temp2 = 17\n"+
             "temp3 = temp1 * temp2\n"+
             "temp4 = temp0 - temp3\n";
    assert(parser.getThreeAddr(eval).equals(result));

    // Logan: Test a long expression
    eval = "1+1+1+1+1+1";
    result = "temp0 = 1\n"+
             "temp1 = 1\n"+
             "temp2 = temp0 + temp1\n"+
             "temp3 = 1\n"+
             "temp4 = temp2 + temp3\n"+
             "temp5 = 1\n"+
             "temp6 = temp4 + temp5\n"+
             "temp7 = 1\n"+
             "temp8 = temp6 + temp7\n"+
             "temp9 = 1\n"+
             "temp10 = temp8 + temp9\n";
    assert(parser.getThreeAddr(eval).equals(result));

    // Logan: Test nested parentheses
    eval = "(1+(2*(3+3)))";
    result = "temp0 = 1\n"+
             "temp1 = 2\n"+
             "temp2 = 3\n"+
             "temp3 = 3\n"+
             "temp4 = temp2 + temp3\n"+
             "temp5 = temp1 * temp4\n"+
             "temp6 = temp0 + temp5\n";
    assert(parser.getThreeAddr(eval).equals(result));

    // Carly: Test multipule groups of parentheses
    eval = "(1 + 2) * (3 + 3) - ((7 - 1) / (3 * 2))";
    result = "temp0 = 1\n"+
             "temp1 = 2\n"+
             "temp2 = temp0 + temp1\n"+
             "temp3 = 3\n"+
             "temp4 = 3\n"+
             "temp5 = temp3 + temp4\n"+
             "temp6 = temp2 * temp5\n"+
             "temp7 = 7\n"+
             "temp8 = 1\n"+
             "temp9 = temp7 - temp8\n"+
             "temp10 = 3\n"+
             "temp11 = 2\n"+
             "temp12 = temp10 * temp11\n"+
             "temp13 = temp9 / temp12\n"+
             "temp14 = temp6 - temp13\n";
    assert(parser.getThreeAddr(eval).equals(result));
  }

  public static void main(String[] args){
    TestExpressionEval();
    TestThreeAddrGen();
  }

}
