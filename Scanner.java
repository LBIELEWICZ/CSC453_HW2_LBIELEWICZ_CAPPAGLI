public class Scanner{
  enum TokenType{
      NUM, PLUS, MINUS, MUL, DIV, LT, LTE, GT, GTE, LPAR, RPAR;
  }

  class Token{
    TokenType tokenType;
    String tokenVal;
    public Token(TokenType tokenType, String tokenVal){
      this.tokenType = tokenType;
      this.tokenVal = tokenVal;
    }
    public String toString(){
      return "|" + this.tokenType + ": " + this.tokenVal + "|";
    }
  }

  public Token extractToken(StringBuilder stream){
    int itr = 0;
    char tokChar = stream.charAt(itr);
    Token ret = null;
    
    // Handling whitespace
    while(tokChar == ' ' || tokChar == '\t' || tokChar == '\n'){
      stream.deleteCharAt(0);
      if (stream.length() == 0)
        return null;
      tokChar = stream.charAt(0);
    }
    
    // Handling a NUM token
    if (tokChar >= '0' && tokChar <= '9') {
      String num = "";
      num += tokChar;
      itr++;

      while (itr < stream.length() && stream.charAt(itr) >= '0' && stream.charAt(itr) <= '9') {
        num += stream.charAt(itr);
        itr++;
        if (itr == stream.length())
          break;
        tokChar = stream.charAt(itr);
      }
      
      ret = new Token(TokenType.NUM, num);
    }

    // Handling a PLUS token
    else if (tokChar == '+') {
      ret = new Token(TokenType.PLUS, "+");
      itr++;
    }

    // Handling a MINUS token
    else if (tokChar == '-') {
      ret = new Token(TokenType.MINUS, "-");
      itr++;
    }

    // Handling a MUL token
    else if (tokChar == '*') {
      ret = new Token(TokenType.MUL, "*");
      itr++;
    }

    // Handling a DIV token
    else if (tokChar == '/') {
      ret = new Token(TokenType.DIV, "/");
      itr++;
    }

    // Handling LTE and LT tokens
    else if (tokChar == '<') {
      if (stream.length() > 1 && stream.charAt(itr + 1) == '=') {
        itr++;
        ret = new Token(TokenType.LTE, "<=");
      }
      else
        ret = new Token(TokenType.LT, "<");
      itr++;
    }

    // Handling GTE and GT tokens
    else if (tokChar == '>') {
      if (stream.length() > 1 && stream.charAt(itr + 1) == '=') {
        itr++;
        ret = new Token(TokenType.GTE, ">=");
      }
      else
        ret = new Token(TokenType.GT, ">");
      itr++;
    }
    
    // Handling a LPAR token
    else if (tokChar == '(') {
      ret = new Token(TokenType.LPAR, "(");
      itr++;
    }
    
    // Handling an RPAR token
    else if (tokChar == ')') {
      ret = new Token(TokenType.RPAR, ")");
      itr++;
    }
    
    // Handling invalid characters
    else {
      System.out.println("ERROR: Invalid character '" + tokChar + "'in " + stream + ". Exiting program.");
      System.exit(1);
    }    
    
    stream.delete(0, itr); // Removing read chars from StringBuilder
    return ret;
  }

  public String extractTokens(String arg){
    String result= "";
  	StringBuilder stream = new StringBuilder(arg);

  	while(stream.length() > 0){
  		Token nextToken = extractToken(stream);
                if (nextToken != null)
  		  result += nextToken.toString();
  	}

    return result;
  }

}
