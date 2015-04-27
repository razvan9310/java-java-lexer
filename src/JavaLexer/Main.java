package JavaLexer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Runnable class.
 * Asks for the path to the source file to be fed to the lexer and reads the given
 * file in binary mode.
 * Tokens will be printed to stdout, formatted as "Type: %s, value: %s". A complete list of token
 * values and their corresponding source file positions will also be printed to stdout. See
 * {@link Lexer#getFormattedTableDescription()} for a description of the latter list.
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
