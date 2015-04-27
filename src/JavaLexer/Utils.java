package JavaLexer;

import java.util.HashMap;

import static JavaLexer.LexerAutomaton.StateSymbolPair;

/**
 * Static utilities class.
 */
public class Utils {
  /**
   * Token types, according to the Java spec.
   */
  public static final int EMPTY_TOKEN = 0;
  public static final int KEYWORD_IDENTIFIER_BOOLEAN_NULL = 1;
  public static final int NUMBER_DECIMAL_FLOATING_POINT = 2;
  public static final int NUMBER_DECIMAL_INTEGER = 3;
  public static final int NUMBER_HEXADECIMAL_FLOATING_POINT = 4;
  public static final int NUMBER_HEXADECIMAL_INTEGER = 5;
  public static final int NUMBER_OCTAL = 6;
  public static final int NUMBER_BINARY = 7;
  public static final int OPERATOR = 8;
  public static final int SEPARATOR = 9;
  public static final int CHARACTER = 10;
  public static final int STRING = 11;

  public static final String EMPTY_TOKEN_STRING = "";
  public static final String BOOLEAN_LITERAL_STRING = "boolen literal";
  public static final String CHARACTER_LITERAL_STRING = "character literal";
  public static final String IDENTIFIER_STRING = "identifier";
  public static final String KEYWORD_STRING = "keyword";
  public static final String NUMBER_BINARY_INTEGER_STRING = "binary integer literal";
  public static final String NUMBER_DECIMAL_FLOATING_POINT_STRING =
      "decimal floating point literal";
  public static final String NUMBER_DECIMAL_INTEGER_STRING = "decimal integer literal";
  public static final String NUMBER_HEXADECIMAL_FLOATING_POINT_STRING =
          "hexadecimal floating point literal";
  public static final String NUMBER_HEXADECIMAL_INTEGER_STRING = "hexadecimal integer literal";
  public static final String NUMBER_OCTAL_INTEGER_STRING = "octal integer literal";
  public static final String NULL_LITERAL_STRING = "null literal";
  public static final String OPERATOR_STRING = "operator";
  public static final String SEPARATOR_STRING = "separator";
  public static final String STRING_LITERAL_STRING = "string literal";

  /**
   * Various token values, according to the Java spec.
   */
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

  /**
   * Literal name of the given token.
   * @param tokenType One of the declared token types.
   * @param tokenValue String value.
   * @return One of the declared "*_STRING" values.
   */
  public static String tokenTypeName(int tokenType, String tokenValue) {
    switch (tokenType) {
      case EMPTY_TOKEN:
        return EMPTY_TOKEN_STRING;
      case KEYWORD_IDENTIFIER_BOOLEAN_NULL:
        if (BOOLEAN_LITERALS[0].equals(tokenValue) || BOOLEAN_LITERALS[1].equals(tokenValue)) {
          return BOOLEAN_LITERAL_STRING;
        } else if (NULL_LITERAL.equals(tokenValue)) {
          return NULL_LITERAL_STRING;
        } else {
          for (String keyword: KEYWORDS) {
            if (keyword.equals(tokenValue)) {
              return KEYWORD_STRING;
            }
          }
          return IDENTIFIER_STRING;
        }
      case NUMBER_DECIMAL_FLOATING_POINT:
        return NUMBER_DECIMAL_FLOATING_POINT_STRING;
      case NUMBER_DECIMAL_INTEGER:
        return NUMBER_DECIMAL_INTEGER_STRING;
      case NUMBER_HEXADECIMAL_FLOATING_POINT:
        return NUMBER_HEXADECIMAL_FLOATING_POINT_STRING;
      case NUMBER_HEXADECIMAL_INTEGER:
        return NUMBER_HEXADECIMAL_INTEGER_STRING;
      case NUMBER_OCTAL:
        return NUMBER_OCTAL_INTEGER_STRING;
      case NUMBER_BINARY:
        return NUMBER_BINARY_INTEGER_STRING;
      case OPERATOR:
        return OPERATOR_STRING;
      case SEPARATOR:
        return SEPARATOR_STRING;
      case CHARACTER:
        return CHARACTER_LITERAL_STRING;
      case STRING:
        return STRING_LITERAL_STRING;
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
