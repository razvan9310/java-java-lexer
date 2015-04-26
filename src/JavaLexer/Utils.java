package JavaLexer;

import java.util.HashMap;

import static JavaLexer.LexerAutomaton.StateSymbolPair;

/**
 * Created by razvan on 4/26/15.
 */
public class Utils {
  public static char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'A', 'b',
          'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F'};
  public static char[] ESCAPE_CHARACTERS = {'\'', '\"', '\\', '\n', '\r', '\f', '\b', '\013'};
  public static String[] OPERATORS = {"=", ">", "<", "!", "~", "?", ":", "==", "<=", ">=", "!=",
      "&&", "||", "++", "--", "+", "-", "*", "/", "&", "|", "^", "%", "<<", ">>", ">>>", "+=", "-=",
      "*=", "/=", "&=", "|=", "^=", "%=", "<<=", ">>=", ">>>="};
  public static char[] SEPARATORS = {'(', ')', '{', '}', '[', ']', ';', ',', '.'};
  public static char[] WHITESPACE_LITERALS = {' ', '\t', '\r', '\n', '\f', '\013'};

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

  public static boolean isBinaryDigit(char d) {
    return d == '0' || d == '1';
  }

  public static void addOperatorTransitionsToMap(HashMap<StateSymbolPair, Integer> map, int source,
      int destination) {
    for (String operator : OPERATORS) {
      map.put(new StateSymbolPair(source, operator.charAt(0)), destination);
    }
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
      int source, int destination) {
    for (char digit : DIGITS) {
      if (isDecimalDigit(digit)) {
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
