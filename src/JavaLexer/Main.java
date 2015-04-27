package JavaLexer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Created by razvan on 4/26/15.
 */
public class Main {
  public static void main(String[] args) {
    Lexer lexer = new Lexer();
    lexer.setLexerAutomaton(LexerAutomatonFactory.lexerAutomatonInstance());

    System.out.print("Java source file path: ");
    Scanner in = new Scanner(System.in);
    String sourceFilePath = in.next();
    Path path = FileSystems.getDefault().getPath("", sourceFilePath);
    byte [] fileData;
    try {
      fileData = Files.readAllBytes(path);
    } catch (IOException e) {
      System.out.println("Error: invalid file path");
      return;
    }
    if (fileData.length == 0) {
      System.out.println("Error: empty source file");
      return;
    }
    lexer.setInput(fileData);

    System.out.println("\nTokens:");
    Token currentToken = lexer.getToken();
    while (currentToken != null) {
      int tokenType = currentToken.getType();
      int tokenValue = currentToken.getValue();
      String tokenValueName = lexer.getTokenValue(tokenValue);
      String tokenTypeName = Utils.tokenTypeName(tokenType, tokenValueName);
      if (!"".equals(tokenTypeName)) {
        System.out.println(String.format("Type: %s, value: %s", tokenTypeName, tokenValueName));
      }
      currentToken = lexer.getToken();
    }
    if (lexer.getCurrentPosition() < fileData.length) {
      System.out.println("Error: unrecognized token at position "
          + String.valueOf(lexer.getCurrentPosition()));
    }

    System.out.println("\nTokens table description:");
    String[] description = lexer.getFormattedTableDescription();
    for (String row : description) {
      System.out.println(row);
    }
  }
}
