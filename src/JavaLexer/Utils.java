package JavaLexer;

import java.util.HashMap;

import static JavaLexer.LexerAutomaton.StateSymbolPair;

/**
 * Static utilities class. Holds methods for manipulating transitions and token types.
 */
public class Utils {
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
   * @param tokenType One of the {@link Token} types.
   * @param tokenValue String value.
   * @return One of the declared "*_STRING" values.
   */
  public static String tokenTypeName(int tokenType, String tokenValue) {
    switch (tokenType) {
      case Token.EMPTY_TOKEN:
        return EMPTY_TOKEN_STRING;
      case Token.KEYWORD_IDENTIFIER_BOOLEAN_NULL:
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
      case Token.NUMBER_DECIMAL_FLOATING_POINT:
        return NUMBER_DECIMAL_FLOATING_POINT_STRING;
      case Token.NUMBER_DECIMAL_INTEGER:
        return NUMBER_DECIMAL_INTEGER_STRING;
      case Token.NUMBER_HEXADECIMAL_FLOATING_POINT:
        return NUMBER_HEXADECIMAL_FLOATING_POINT_STRING;
      case Token.NUMBER_HEXADECIMAL_INTEGER:
        return NUMBER_HEXADECIMAL_INTEGER_STRING;
      case Token.NUMBER_OCTAL:
        return NUMBER_OCTAL_INTEGER_STRING;
      case Token.NUMBER_BINARY:
        return NUMBER_BINARY_INTEGER_STRING;
      case Token.OPERATOR:
        return OPERATOR_STRING;
      case Token.SEPARATOR:
        return SEPARATOR_STRING;
      case Token.CHARACTER:
        return CHARACTER_LITERAL_STRING;
      case Token.STRING:
        return STRING_LITERAL_STRING;
      default:
        return null;
    }
  }

  /**
   * Tests whether the given character is a hexadecimal digit, i.e. 0-9, a-f, A-F.
   * @param d {@code char} to test.
   * @return {@code true} if the given {@code char} is hexadecimal, {@code false} otherwise.
   */
  public static boolean isHexadecimalDigit(char d) {
    for(char digit : DIGITS) {
      if (digit == d) {
        return true;
      }
    }
    return false;
  }

  /**
   * Tests whether the given character is a decimal digit, i.e. 0-9.
   * @param d {@code char} to test.
   * @return {@code true} if the given {@code char} is hexadecimal, {@code false} otherwise.
   */
  public static boolean isDecimalDigit(char d) {
    return isHexadecimalDigit(d) && d <= '9';
  }

  /**
   * Tests whether the given character is an octal digit, i.e. 0-7,
   * @param d {@code char} to test.
   * @return {@code true} if the given {@code char} is hexadecimal, {@code false} otherwise.
   */
  public static boolean isOctalDigit(char d) {
    return isDecimalDigit(d) && d <= '7';
  }

  /**
   * Adds transitions with all {@link Utils#SEPARATORS}, from the given source, to the given
   * destination.
   * @param map Transitions {@code HashMap} to add to.
   * @param source {@code int} denoting source node.
   * @param destination {@code int} denoting destination node.
   */
  public static void addSeparatorTransitionsToMap(HashMap<StateSymbolPair, Integer> map, int source,
      int destination) {
    for (char separator : SEPARATORS) {
      map.put(new StateSymbolPair(source, separator), destination);
    }
  }

  /**
   * Adds transitions with all {@link Utils#WHITESPACE_LITERALS}, from the given source, to the
   * given destination.
   * @param map Transitions {@code HashMap} to add to.
   * @param source {@code int} denoting source node.
   * @param destination {@code int} denoting destination node.
   */
  public static void addWhitespaceLiteralTransitionsToMap(HashMap<StateSymbolPair, Integer> map,
      int source, int destination) {
    for (char whitespaceLiteral : WHITESPACE_LITERALS) {
      map.put(new StateSymbolPair(source, whitespaceLiteral), destination);
    }
  }

  /**
   * Adds transitions with all hexadecimal digits (i.e. 0-9, a-f, A-F), from the given source, to
   * the given destination.
   * @param map Transitions {@code HashMap} to add to.
   * @param source {@code int} denoting source node.
   * @param destination {@code int} denoting destination node.
   */
  public static void addHexadecimalDigitTransitionsToMap(HashMap<StateSymbolPair, Integer> map,
      int source, int destination) {
    for (char digit : DIGITS) {
      map.put(new StateSymbolPair(source, digit), destination);
    }
  }

  /**
   * Adds transitions with all decimal digits (i.e. 0-9), from the given source, to the given
   * destination.
   * @param map Transitions {@code HashMap} to add to.
   * @param source {@code int} denoting source node.
   * @param destination {@code int} denoting destination node.
   * @param addZero {@code boolean} flag indicating whether to include transitions with 0.
   */
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

  /**
   * Adds transitions with all octal digits (i.e. 0-7), from the given source, to the given
   * destination.
   * @param map Transitions {@code HashMap} to add to.
   * @param source {@code int} denoting source node.
   * @param destination {@code int} denoting destination node.
   */
  public static void addOctalDigitTransitionsToMap(HashMap<StateSymbolPair, Integer> map,
      int source, int destination) {
    for (char digit : DIGITS) {
      if (isOctalDigit(digit)) {
        map.put(new StateSymbolPair(source, digit), destination);
      }
    }
  }

  /**
   * Adds transitions with 0 and 1, from the given source, to the given destination.
   * @param map Transitions {@code HashMap} to add to.
   * @param source {@code int} denoting source node.
   * @param destination {@code int} denoting destination node.
   */
  public static void addBinaryDigitTransitionsToMap(HashMap<StateSymbolPair, Integer> map,
      int source, int destination) {
    map.put(new StateSymbolPair(source, '0'), destination);
    map.put(new StateSymbolPair(source, '1'), destination);
  }

  /**
   * Tests whether the given {@code char} is an escape character.
   * @param c {@code char} to test.
   * @return {@code true} if the given {@code char} is in {@link Utils#ESCAPE_CHARACTERS},
   *     {@code false} otherwise.
   */
  public static boolean isEscapeCharacter(char c) {
    for (char escape : ESCAPE_CHARACTERS) {
      if (c == escape) {
        return true;
      }
    }
    return false;
  }
}
