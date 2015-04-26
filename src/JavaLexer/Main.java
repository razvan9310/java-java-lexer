package JavaLexer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import JavaLexer.Lexer.CurrentTokenNextPosition;

/**
 * Created by razvan on 4/26/15.
 */
public class Main {
  public static void main(String[] args) {
    Lexer lexer = new Lexer();
    lexer.setLexerAutomaton(LexerAutomatonFactory.lexerAutomatonInstance());

    System.out.print("java source file path: ");
    Scanner in = new Scanner(System.in);
    String sourceFilePath = in.next();
    Path path = FileSystems.getDefault().getPath("", sourceFilePath);
    byte [] fileData;
    try {
      fileData = Files.readAllBytes(path);
    } catch (IOException e) {
      System.out.println("error: invalid file path");
      return;
    }
    if (fileData.length == 0) {
      System.out.println("error: empty source file");
      return;
    }
    lexer.setInput(fileData);

    int pos = 0;
    CurrentTokenNextPosition currentTokenNextPosition = lexer.getTokenAndNextPosition(pos);
    while (currentTokenNextPosition.mCurrentToken != null) {
      int tokenType = currentTokenNextPosition.mCurrentToken.getType();
      int tokenValue = currentTokenNextPosition.mCurrentToken.getValue();
      System.out.println(String.format("type: %d, value: %d", tokenType, tokenValue));
      currentTokenNextPosition = lexer.getTokenAndNextPosition(
          currentTokenNextPosition.mNextPosition);
    }
    if (currentTokenNextPosition.mNextPosition < fileData.length) {
      System.out.println("error: unrecognized token:");
      for (int i = pos; i < currentTokenNextPosition.mNextPosition; ++i) {
        System.out.print((char) fileData[i]);
      }
    }
  }
}
