public class ScannerTest{

  /* Quick test of token extraction*/
  public static void testTokenExtraction(){
    System.out.println("*******************************************");
    System.out.println("Testing Token Extraction");
    Scanner test = new Scanner();

    String result = test.extractTokens("123");
    String expected = "|NUM: 123|";
    assert(result.equals(expected));

    result = test.extractTokens("+ 3 3");
    expected = "|PLUS: +||NUM: 3||NUM: 3|";
    assert(result.equals(expected));

    result = test.extractTokens("+ - * / < >");
    expected = "|PLUS: +||MINUS: -||MUL: *||DIV: /||LT: <||GT: >|";
    assert(result.equals(expected));

    result = test.extractTokens("<= - >=");
    expected = "|LTE: <=||MINUS: -||GTE: >=|";
    assert(result.equals(expected));

    System.out.println("Congrats: preliminary token extraction tests passed! Now make your own test cases "+
                       "(this is only a subset of what we will test your code on)");
    System.out.println("*******************************************");
    System.out.println();
  }

  /* Additional test cases */
  public static void testTokenExtraction2() {
    System.out.println("*******************************************");
    System.out.println("Testing More Token Extraction");
    Scanner test = new Scanner();
    
    // Logan: Test extraction with no whitespace
    String result = test.extractTokens("+-+");
    String expected = "|PLUS: +||MINUS: -||PLUS: +|";
    assert(result.equals(expected));

    // Logan: Test extraction of LTE and GTE with no whitespace
    result = test.extractTokens("<=>=");
    expected = "|LTE: <=||GTE: >=|";
    assert(result.equals(expected));

    // Logan: Test extraction of a long number
    result = test.extractTokens("1234567890");
    expected = "|NUM: 1234567890|";
    assert(result.equals(expected));

    // Logan: Test extraction of multi-digit numbers and symbols with no whitespace
    result = test.extractTokens("123+321<=456");
    expected = "|NUM: 123||PLUS: +||NUM: 321||LTE: <=||NUM: 456|";
    assert(result.equals(expected));
    
    // Carly: Test extraction with space, tab, and newline
    result = test.extractTokens("1 +\t2\n");
    expected = "|NUM: 1||PLUS: +||NUM: 2|";
    assert(result.equals(expected));
    
    // Carly: Test extraction with whitespace at end
    result = test.extractTokens("1 + 2 ");
    expected = "|NUM: 1||PLUS: +||NUM: 2|";
    assert(result.equals(expected));

    // Carly: Test parenthesis
    result = test.extractTokens(" ()))(()(");
    expected = "|OP: (||CP: )||CP: )||CP: )||OP: (||OP: (||CP: )||OP: (|";
    assert(result.equals(expected));

    System.out.println("Congrats: additional tests passed!");
    System.out.println("*******************************************");
    System.out.println();
  }

  public static void main(String[] args){
    testTokenExtraction();
    testTokenExtraction2();
  }

}