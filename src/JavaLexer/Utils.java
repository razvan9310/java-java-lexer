package JavaLexer;

import java.util.HashMap;

import static JavaLexer.LexerAutomaton.StateSymbolPair;

/**
 * Created by razvan on 4/26/15.
 */
public class Utils {
  public static final int KEYWORD_IDENTIFIER_BOOLEAN_NULL = 0;
  public static final int NUMBER_DECIMAL_FLOATING_POINT = 1;
  public static final int NUMBER_DECIMAL_INTEGER = 2;
  public static final int NUMBER_HEXADECIMAL_FLOATING_POINT = 3;
  public static final int NUMBER_HEXADECIMAL_INTEGER = 4;
  public static final int NUMBER_OCTAL = 5;
  public static final int NUMBER_BINARY = 6;
  public static final int OPERATOR = 7;
  public static final int SEPARATOR = 8;
  public static final int CHARACTER = 9;
  public static final int STRING = 10;

  public static final String[] BOOLEAN_LITERALS = {"false", "true"};
  public static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'A', 'b',
          'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F'};
  public static final char[] ESCAPE_CHARACTERS = {'\'', '\"', '\\', '\n', '\r', '\f', '\b', '\013'};
  public static final String[] KEYWORDS = {"abstract", "assert", "boolean", "break", "byte", "case",
          "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum",
          "extends", "final", "finally", "float", "for", "if", "goto", "implements", "import",
          "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected",
          "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
          "throw", "throws", "transient", "try", "void", "volatile", "while"};
  public static final String NULL_LITERAL = "null";
  public static final String[] OPERATORS = {"=", ">", "<", "!", "~", "?", ":", "==", "<=", ">=", "!=",
      "&&", "||", "++", "--", "+", "-", "*", "/", "&", "|", "^", "%", "<<", ">>", ">>>", "+=", "-=",
      "*=", "/=", "&=", "|=", "^=", "%=", "<<=", ">>=", ">>>="};
  public static final char[] SEPARATORS = {'(', ')', '{', '}', '[', ']', ';', ',', '.'};
  public static final char[] WHITESPACE_LITERALS = {' ', '\t', '\r', '\n', '\f', '\013'};

  public static String tokenTypeName(int tokenType, String tokenValue) {
    switch (tokenType) {
      case KEYWORD_IDENTIFIER_BOOLEAN_NULL:
        if (BOOLEAN_LITERALS[0].equals(tokenValue) || BOOLEAN_LITERALS[1].equals(tokenValue)) {
          return "boolean literal";
        } else if (NULL_LITERAL.equals(tokenValue)) {
          return "null literal";
        } else {
          for (String keyword: KEYWORDS) {
            if (keyword.equals(tokenValue)) {
              return "keyword";
            }
          }
          return "identifier";
        }
      case NUMBER_DECIMAL_FLOATING_POINT:
        return "decimal floating point literal";
      case NUMBER_DECIMAL_INTEGER:
        return "decimal integer literal";
      case NUMBER_HEXADECIMAL_FLOATING_POINT:
        return "hexadecimal floating point literal";
      case NUMBER_HEXADECIMAL_INTEGER:
        return "hexadecimal integer literal";
      case NUMBER_OCTAL:
        return "octal literal";
      case NUMBER_BINARY:
        return "binary literal";
      case OPERATOR:
        return "operator";
      case SEPARATOR:
        return "separator";
      case CHARACTER:
        return "character literal";
      case STRING:
        return "string literal";
      default:
        return null;
    }
  }

  public static boolean isHexadecimalDigit(char d) {
    for(char digit : DIGITS) {
      if (digit == d) {
        return true;
      }
    }
    return false;
  }

  public static boolean isDecimalDigit(char d) {
    return isHexadecimalDigit(d) && d <= '9';
  }

  public static boolean isOctalDigit(char d) {
    return isDecimalDigit(d) && d <= '7';
  }

  public static void addSeparatorTransitionsToMap(HashMap<StateSymbolPair, Integer> map, int source,
      int destination) {
    for (char separator : SEPARATORS) {
      map.put(new StateSymbolPair(source, separator), destination);
    }
  }

  public static void addWhitespaceLiteralTransitionsToMap(HashMap<StateSymbolPair, Integer> map,
      int source, int destination) {
    for (char whitespaceLiteral : WHITESPACE_LITERALS) {
      map.put(new StateSymbolPair(source, whitespaceLiteral), destination);
    }
  }

  public static void addHexadecimalDigitTransitionsToMap(HashMap<StateSymbolPair, Integer> map,
      int source, int destination) {
    for (char digit : DIGITS) {
      map.put(new StateSymbolPair(source, digit), destination);
    }
  }

  public static void addDecimalDigitTransitionsToMap(HashMap<StateSymbolPair, Integer> map,
      int source, int destination, boolean addZero) {
    for (char digit : DIGITS) {
      if (isDecimalDigit(digit)) {
        if (digit == '0' && !addZero) {
          continue;
        }
        map.put(new StateSymbolPair(source, digit), destination);
      }
    }
  }

  public static void addOctalDigitTransitionsToMap(HashMap<StateSymbolPair, Integer> map,
      int source, int destination) {
    for (char digit : DIGITS) {
      if (isOctalDigit(digit)) {
        map.put(new StateSymbolPair(source, digit), destination);
      }
    }
  }

  public static void addBinaryDigitTransitionsToMap(HashMap<StateSymbolPair, Integer> map,
      int source, int destination) {
    map.put(new StateSymbolPair(source, '0'), destination);
    map.put(new StateSymbolPair(source, '1'), destination);
  }

  public static boolean isEscapeCharacter(char c) {
    for (char escape : ESCAPE_CHARACTERS) {
      if (c == escape) {
        return true;
      }
    }
    return false;
  }
}
