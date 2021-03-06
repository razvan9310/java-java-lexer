package JavaLexer;

import java.util.HashMap;
import JavaLexer.LexerAutomaton.StateSymbolPair;

import static JavaLexer.Utils.*;

public class LexerAutomatonFactory {
  /**
   * Creates a {@link LexerAutomaton} instance by defining transitions and final states. Check the
   * JavaLexer documentation for conceptual diagrams of the automaton.
   * @return New {@link LexerAutomaton} instance, equipped with transitions and final states maps.
   */
  public static LexerAutomaton lexerAutomatonInstance() {
    HashMap<StateSymbolPair, Integer> transitionsMap = new HashMap<StateSymbolPair, Integer>();

    // Numbers: starting with 0:
    transitionsMap.put(new StateSymbolPair(0, '0'), 1);
    // 0, 0l, 0L, 0f, 0F, 0d, 0D:
    transitionsMap.put(new StateSymbolPair(1, 'f'), 2);
    transitionsMap.put(new StateSymbolPair(1, 'F'), 2);
    transitionsMap.put(new StateSymbolPair(1, 'd'), 2);
    transitionsMap.put(new StateSymbolPair(1, 'D'), 2);
    transitionsMap.put(new StateSymbolPair(1, 'l'), 59);
    transitionsMap.put(new StateSymbolPair(1, 'L'), 59);

    // Hexadecimal:
    transitionsMap.put(new StateSymbolPair(1, 'x'), 3);
    transitionsMap.put(new StateSymbolPair(1, 'X'), 3);

    addHexadecimalDigitTransitionsToMap(transitionsMap, 3, 4);
    transitionsMap.put(new StateSymbolPair(3, '.'), 18);

    addHexadecimalDigitTransitionsToMap(transitionsMap, 4, 4);
    transitionsMap.put(new StateSymbolPair(4, 'p'), 12);
    transitionsMap.put(new StateSymbolPair(4, 'P'), 12);
    transitionsMap.put(new StateSymbolPair(4, '/'), 60);
    transitionsMap.put(new StateSymbolPair(4, 'l'), 7);
    transitionsMap.put(new StateSymbolPair(4, 'L'), 7);
    transitionsMap.put(new StateSymbolPair(4, '_'), 5);
    transitionsMap.put(new StateSymbolPair(4, '.'), 8);

    transitionsMap.put(new StateSymbolPair(5, '_'), 5);
    addHexadecimalDigitTransitionsToMap(transitionsMap, 5, 6);

    transitionsMap.put(new StateSymbolPair(6, '_'), 5);
    transitionsMap.put(new StateSymbolPair(6, 'l'), 7);
    transitionsMap.put(new StateSymbolPair(6, 'L'), 7);
    addHexadecimalDigitTransitionsToMap(transitionsMap, 6, 6);
    transitionsMap.put(new StateSymbolPair(6, 'p'), 12);
    transitionsMap.put(new StateSymbolPair(6, 'P'), 12);
    transitionsMap.put(new StateSymbolPair(6, '.'), 8);

    addHexadecimalDigitTransitionsToMap(transitionsMap, 8, 9);
    transitionsMap.put(new StateSymbolPair(8, 'p'), 12);
    transitionsMap.put(new StateSymbolPair(8, 'P'), 12);

    addHexadecimalDigitTransitionsToMap(transitionsMap, 9, 9);
    transitionsMap.put(new StateSymbolPair(9, '_'), 10);
    transitionsMap.put(new StateSymbolPair(9, 'p'), 12);
    transitionsMap.put(new StateSymbolPair(9, 'P'), 12);

    transitionsMap.put(new StateSymbolPair(10, '_'), 10);
    addHexadecimalDigitTransitionsToMap(transitionsMap, 10, 11);

    transitionsMap.put(new StateSymbolPair(11, '_'), 10);
    addHexadecimalDigitTransitionsToMap(transitionsMap, 11, 11);
    transitionsMap.put(new StateSymbolPair(11, 'p'), 12);
    transitionsMap.put(new StateSymbolPair(11, 'P'), 12);

    transitionsMap.put(new StateSymbolPair(12, '+'), 13);
    transitionsMap.put(new StateSymbolPair(12, '-'), 13);
    addDecimalDigitTransitionsToMap(transitionsMap, 12, 14, true);

    addDecimalDigitTransitionsToMap(transitionsMap, 13, 14, true);

    transitionsMap.put(new StateSymbolPair(14, 'f'), 15);
    transitionsMap.put(new StateSymbolPair(14, 'F'), 15);
    transitionsMap.put(new StateSymbolPair(14, 'd'), 15);
    transitionsMap.put(new StateSymbolPair(14, 'D'), 15);
    transitionsMap.put(new StateSymbolPair(14, '_'), 16);

    transitionsMap.put(new StateSymbolPair(16, '_'), 16);
    addDecimalDigitTransitionsToMap(transitionsMap, 16, 17, true);

    addDecimalDigitTransitionsToMap(transitionsMap, 17, 17, true);
    transitionsMap.put(new StateSymbolPair(17, 'f'), 15);
    transitionsMap.put(new StateSymbolPair(17, 'F'), 15);
    transitionsMap.put(new StateSymbolPair(17, 'd'), 15);
    transitionsMap.put(new StateSymbolPair(17, 'D'), 15);

    addHexadecimalDigitTransitionsToMap(transitionsMap, 18, 9);

    // Binary:
    transitionsMap.put(new StateSymbolPair(1, 'b'), 19);
    transitionsMap.put(new StateSymbolPair(1, 'B'), 19);

    addBinaryDigitTransitionsToMap(transitionsMap, 19, 20);

    addBinaryDigitTransitionsToMap(transitionsMap, 20, 20);
    transitionsMap.put(new StateSymbolPair(20, 'l'), 21);
    transitionsMap.put(new StateSymbolPair(20, 'L'), 21);
    transitionsMap.put(new StateSymbolPair(20, '_'), 22);

    transitionsMap.put(new StateSymbolPair(22, '_'), 22);
    addBinaryDigitTransitionsToMap(transitionsMap, 22, 23);

    transitionsMap.put(new StateSymbolPair(23, 'l'), 21);
    transitionsMap.put(new StateSymbolPair(23, 'L'), 21);
    transitionsMap.put(new StateSymbolPair(23, '_'), 22);

    // Octal:
    addOctalDigitTransitionsToMap(transitionsMap, 1, 24);
    addOctalDigitTransitionsToMap(transitionsMap, 24, 24);
    transitionsMap.put(new StateSymbolPair(24, 'l'), 25);
    transitionsMap.put(new StateSymbolPair(24, 'L'), 25);
    transitionsMap.put(new StateSymbolPair(24, '_'), 26);

    transitionsMap.put(new StateSymbolPair(26, '_'), 26);
    addOctalDigitTransitionsToMap(transitionsMap, 26, 27);

    transitionsMap.put(new StateSymbolPair(27, '_'), 26);
    addOctalDigitTransitionsToMap(transitionsMap, 27, 27);

    // Decimal 0.*:
    transitionsMap.put(new StateSymbolPair(1, '.'), 28);
    transitionsMap.put(new StateSymbolPair(28, 'f'), 29);
    transitionsMap.put(new StateSymbolPair(28, 'F'), 29);
    transitionsMap.put(new StateSymbolPair(28, 'd'), 29);
    transitionsMap.put(new StateSymbolPair(28, 'D'), 29);
    addDecimalDigitTransitionsToMap(transitionsMap, 28, 30, true);
    transitionsMap.put(new StateSymbolPair(28, 'e'), 33);
    transitionsMap.put(new StateSymbolPair(28, 'E'), 33);

    transitionsMap.put(new StateSymbolPair(30, 'f'), 29);
    transitionsMap.put(new StateSymbolPair(30, 'F'), 29);
    transitionsMap.put(new StateSymbolPair(30, 'd'), 29);
    transitionsMap.put(new StateSymbolPair(30, 'D'), 29);
    addDecimalDigitTransitionsToMap(transitionsMap, 30, 30, true);
    transitionsMap.put(new StateSymbolPair(30, '_'), 31);
    transitionsMap.put(new StateSymbolPair(30, 'e'), 33);
    transitionsMap.put(new StateSymbolPair(30, 'E'), 33);

    transitionsMap.put(new StateSymbolPair(31, '_'), 31);
    addDecimalDigitTransitionsToMap(transitionsMap, 31, 32, true);

    transitionsMap.put(new StateSymbolPair(32, 'f'), 29);
    transitionsMap.put(new StateSymbolPair(32, 'F'), 29);
    transitionsMap.put(new StateSymbolPair(32, 'd'), 29);
    transitionsMap.put(new StateSymbolPair(32, 'D'), 29);
    transitionsMap.put(new StateSymbolPair(32, '_'), 31);
    addDecimalDigitTransitionsToMap(transitionsMap, 32, 32, true);
    transitionsMap.put(new StateSymbolPair(32, 'e'), 33);
    transitionsMap.put(new StateSymbolPair(32, 'E'), 33);

    transitionsMap.put(new StateSymbolPair(33, '+'), 34);
    transitionsMap.put(new StateSymbolPair(33, '-'), 34);
    addDecimalDigitTransitionsToMap(transitionsMap, 33, 35, true);

    addDecimalDigitTransitionsToMap(transitionsMap, 34, 35, true);

    addDecimalDigitTransitionsToMap(transitionsMap, 35, 35, true);
    transitionsMap.put(new StateSymbolPair(35, '_'), 36);
    transitionsMap.put(new StateSymbolPair(35, 'f'), 37);
    transitionsMap.put(new StateSymbolPair(35, 'F'), 37);
    transitionsMap.put(new StateSymbolPair(35, 'd'), 37);
    transitionsMap.put(new StateSymbolPair(35, 'D'), 37);

    transitionsMap.put(new StateSymbolPair(36, '_'), 36);
    addDecimalDigitTransitionsToMap(transitionsMap, 36, 38, true);

    transitionsMap.put(new StateSymbolPair(38, '_'), 36);
    addDecimalDigitTransitionsToMap(transitionsMap, 38, 38, true);
    transitionsMap.put(new StateSymbolPair(38, 'f'), 37);
    transitionsMap.put(new StateSymbolPair(38, 'F'), 37);
    transitionsMap.put(new StateSymbolPair(38, 'd'), 37);
    transitionsMap.put(new StateSymbolPair(38, 'D'), 37);

    // Decimal numbers starting with '1-9':
    addDecimalDigitTransitionsToMap(transitionsMap, 0, 39, false);
    transitionsMap.put(new StateSymbolPair(39, '.'), 28);
    transitionsMap.put(new StateSymbolPair(39, 'e'), 33);
    transitionsMap.put(new StateSymbolPair(39, 'E'), 33);
    addDecimalDigitTransitionsToMap(transitionsMap, 39, 39, true);
    transitionsMap.put(new StateSymbolPair(39, 'f'), 40);
    transitionsMap.put(new StateSymbolPair(39, 'F'), 40);
    transitionsMap.put(new StateSymbolPair(39, 'd'), 40);
    transitionsMap.put(new StateSymbolPair(39, 'D'), 40);
    transitionsMap.put(new StateSymbolPair(39, '_'), 41);
    transitionsMap.put(new StateSymbolPair(39, 'l'), 65);
    transitionsMap.put(new StateSymbolPair(39, 'L'), 65);

    transitionsMap.put(new StateSymbolPair(41, '_'), 41);
    addDecimalDigitTransitionsToMap(transitionsMap, 41, 42, true);

    transitionsMap.put(new StateSymbolPair(42, '.'), 28);
    transitionsMap.put(new StateSymbolPair(42, 'e'), 33);
    transitionsMap.put(new StateSymbolPair(42, 'E'), 33);
    transitionsMap.put(new StateSymbolPair(42, 'f'), 40);
    transitionsMap.put(new StateSymbolPair(42, 'F'), 40);
    transitionsMap.put(new StateSymbolPair(42, 'd'), 40);
    transitionsMap.put(new StateSymbolPair(42, 'D'), 40);
    transitionsMap.put(new StateSymbolPair(42, '_'), 41);
    transitionsMap.put(new StateSymbolPair(42, 'l'), 65);
    transitionsMap.put(new StateSymbolPair(42, 'L'), 65);

    // Apostrophe:
    transitionsMap.put(new StateSymbolPair(0, '\''), 43);
    for (char c = 0; c < 256; ++c) {
      if (c == '"' || !isEscapeCharacter(c)) {
        transitionsMap.put(new StateSymbolPair(43, c), 44);
      }
    }
    transitionsMap.put(new StateSymbolPair(43, '\\'), 45);
    transitionsMap.put(new StateSymbolPair(43, '\''), 66);

    transitionsMap.put(new StateSymbolPair(44, '\''), 66);

    transitionsMap.put(new StateSymbolPair(45, 'b'), 44);
    transitionsMap.put(new StateSymbolPair(45, 't'), 44);
    transitionsMap.put(new StateSymbolPair(45, 'n'), 44);
    transitionsMap.put(new StateSymbolPair(45, 'f'), 44);
    transitionsMap.put(new StateSymbolPair(45, 'r'), 44);
    transitionsMap.put(new StateSymbolPair(45, '\''), 44);
    transitionsMap.put(new StateSymbolPair(45, '"'), 44);
    transitionsMap.put(new StateSymbolPair(45, '\\'), 44);
    for (char c = '0'; c < '8'; ++c) {
      if (c < '4') {
        transitionsMap.put(new StateSymbolPair(45, c), 46);
      } else {
        transitionsMap.put(new StateSymbolPair(45, c), 47);
      }
    }

    addOctalDigitTransitionsToMap(transitionsMap, 46, 49);
    transitionsMap.put(new StateSymbolPair(46, '\''), 66);

    addOctalDigitTransitionsToMap(transitionsMap, 47, 48);
    transitionsMap.put(new StateSymbolPair(47, '\''), 66);

    transitionsMap.put(new StateSymbolPair(48, '\''), 66);

    addOctalDigitTransitionsToMap(transitionsMap, 49, 50);
    transitionsMap.put(new StateSymbolPair(49, '\''), 66);

    transitionsMap.put(new StateSymbolPair(50, '\''), 66);

    // Quotes:
    transitionsMap.put(new StateSymbolPair(0, '"'), 51);

    for (char c = 0; c < 256; ++c) {
      if (c == '\'' || !isEscapeCharacter(c)) {
        transitionsMap.put(new StateSymbolPair(51, c), 51);
      }
    }
    transitionsMap.put(new StateSymbolPair(51, '\\'), 52);
    transitionsMap.put(new StateSymbolPair(51, '"'), 67);

    for (char c = 0; c < 256; ++c) {
      if (c == '\'' || c == '"' || c == '\\' || !isEscapeCharacter(c)) {
        transitionsMap.put(new StateSymbolPair(52, c), 51);
      }
    }

    // Identifiers / keywords / null / true / false:
    for (char c = 0; c < 256; ++c) {
      if (Character.isJavaLetter(c)) {
        transitionsMap.put(new StateSymbolPair(0, c), 57);
        transitionsMap.put(new StateSymbolPair(57, c), 57);
      }
      if (Character.isJavaLetterOrDigit(c) && !Character.isJavaLetter(c)) {
        transitionsMap.put(new StateSymbolPair(57, c), 58);
        transitionsMap.put(new StateSymbolPair(58, c), 58);
      }
    }

    // Whitespaces / comments:
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 0, 0);
    transitionsMap.put(new StateSymbolPair(0, '/'), 53);

    transitionsMap.put(new StateSymbolPair(53, '/'), 54);
    transitionsMap.put(new StateSymbolPair(53, '*'), 55);
    transitionsMap.put(new StateSymbolPair(53, '='), 80);

    for (char c = 0; c < 256; ++c) {
      if (c != '\n' && c != '\r') {
        transitionsMap.put(new StateSymbolPair(54, c), 54);
      } else {
        transitionsMap.put(new StateSymbolPair(54, c), 0);
      }
    }

    for (char c = 0; c < 256; ++c) {
      if (c != '*') {
        transitionsMap.put(new StateSymbolPair(55, c), 55);
      } else {
        transitionsMap.put(new StateSymbolPair(55, c), 56);
      }
    }

    transitionsMap.put(new StateSymbolPair(56, '/'), 0);
    for (char c = 0; c < 256; ++c) {
      if (c != '/' && c != '*') {
        transitionsMap.put(new StateSymbolPair(56, c), 55);
      }
    }
    transitionsMap.put(new StateSymbolPair(56, '*'), 56);

    // Operators:
    char[] pureUnaryOperators = {'~', '?', ':'};
    for (char c : pureUnaryOperators) {
      transitionsMap.put(new StateSymbolPair(0, c), 68);
    }
    char[] acceptsEqualSuffix = {'=', '!', '^', '*', '%'};
    for (char c : acceptsEqualSuffix) {
      transitionsMap.put(new StateSymbolPair(0, c), 69);
    }
    transitionsMap.put(new StateSymbolPair(0, '&'), 70);
    transitionsMap.put(new StateSymbolPair(0, '|'), 71);
    transitionsMap.put(new StateSymbolPair(0, '+'), 72);
    transitionsMap.put(new StateSymbolPair(0, '-'), 73);
    transitionsMap.put(new StateSymbolPair(0, '<'), 74);
    transitionsMap.put(new StateSymbolPair(0, '>'), 76);

    transitionsMap.put(new StateSymbolPair(69, '='), 68);

    transitionsMap.put(new StateSymbolPair(70, '&'), 68);
    transitionsMap.put(new StateSymbolPair(70, '='), 68);

    transitionsMap.put(new StateSymbolPair(71, '|'), 68);
    transitionsMap.put(new StateSymbolPair(71, '='), 68);

    transitionsMap.put(new StateSymbolPair(72, '='), 68);
    transitionsMap.put(new StateSymbolPair(72, '+'), 68);

    transitionsMap.put(new StateSymbolPair(73, '-'), 68);
    transitionsMap.put(new StateSymbolPair(73, '='), 68);

    transitionsMap.put(new StateSymbolPair(74, '<'), 75);

    transitionsMap.put(new StateSymbolPair(75, '='), 68);

    transitionsMap.put(new StateSymbolPair(76, '='), 68);
    transitionsMap.put(new StateSymbolPair(76, '>'), 77);

    transitionsMap.put(new StateSymbolPair(77, '='), 68);
    transitionsMap.put(new StateSymbolPair(77, '>'), 78);

    transitionsMap.put(new StateSymbolPair(78, '='), 68);

    // Separators:
    addSeparatorTransitionsToMap(transitionsMap, 0, 79);

    // Final states:
    HashMap<Integer, Integer> finalStatesToTokenTypesMap = new HashMap<Integer, Integer>();
    finalStatesToTokenTypesMap.put(0, Token.EMPTY_TOKEN);
    finalStatesToTokenTypesMap.put(1, Token.NUMBER_DECIMAL_INTEGER);
    finalStatesToTokenTypesMap.put(2, Token.NUMBER_DECIMAL_FLOATING_POINT);
    finalStatesToTokenTypesMap.put(4, Token.NUMBER_HEXADECIMAL_INTEGER);
    finalStatesToTokenTypesMap.put(6, Token.NUMBER_HEXADECIMAL_INTEGER);
    finalStatesToTokenTypesMap.put(7, Token.NUMBER_HEXADECIMAL_INTEGER);
    finalStatesToTokenTypesMap.put(14, Token.NUMBER_HEXADECIMAL_FLOATING_POINT);
    finalStatesToTokenTypesMap.put(15, Token.NUMBER_HEXADECIMAL_FLOATING_POINT);
    finalStatesToTokenTypesMap.put(17, Token.NUMBER_HEXADECIMAL_FLOATING_POINT);
    finalStatesToTokenTypesMap.put(20, Token.NUMBER_BINARY);
    finalStatesToTokenTypesMap.put(21, Token.NUMBER_BINARY);
    finalStatesToTokenTypesMap.put(23, Token.NUMBER_BINARY);
    finalStatesToTokenTypesMap.put(24, Token.NUMBER_OCTAL);
    finalStatesToTokenTypesMap.put(25, Token.NUMBER_OCTAL);
    finalStatesToTokenTypesMap.put(27, Token.NUMBER_OCTAL);
    finalStatesToTokenTypesMap.put(28, Token.NUMBER_DECIMAL_FLOATING_POINT);
    finalStatesToTokenTypesMap.put(29, Token.NUMBER_DECIMAL_FLOATING_POINT);
    finalStatesToTokenTypesMap.put(30, Token.NUMBER_DECIMAL_FLOATING_POINT);
    finalStatesToTokenTypesMap.put(32, Token.NUMBER_DECIMAL_FLOATING_POINT);
    finalStatesToTokenTypesMap.put(35, Token.NUMBER_DECIMAL_FLOATING_POINT);
    finalStatesToTokenTypesMap.put(37, Token.NUMBER_DECIMAL_FLOATING_POINT);
    finalStatesToTokenTypesMap.put(38, Token.NUMBER_DECIMAL_FLOATING_POINT);
    finalStatesToTokenTypesMap.put(39, Token.NUMBER_DECIMAL_INTEGER);
    finalStatesToTokenTypesMap.put(40, Token.NUMBER_DECIMAL_FLOATING_POINT);
    finalStatesToTokenTypesMap.put(42, Token.NUMBER_DECIMAL_INTEGER);
    finalStatesToTokenTypesMap.put(53, Token.OPERATOR);
    finalStatesToTokenTypesMap.put(57, Token.KEYWORD_IDENTIFIER_BOOLEAN_NULL);
    finalStatesToTokenTypesMap.put(58, Token.KEYWORD_IDENTIFIER_BOOLEAN_NULL);
    finalStatesToTokenTypesMap.put(59, Token.NUMBER_DECIMAL_INTEGER);
    finalStatesToTokenTypesMap.put(65, Token.NUMBER_DECIMAL_INTEGER);
    finalStatesToTokenTypesMap.put(66, Token.CHARACTER);
    finalStatesToTokenTypesMap.put(67, Token.STRING);
    for (int i = 68; i <= 78; ++i) {
      finalStatesToTokenTypesMap.put(i, Token.OPERATOR);
    }
    finalStatesToTokenTypesMap.put(79, Token.SEPARATOR);
    finalStatesToTokenTypesMap.put(80, Token.OPERATOR);

    LexerAutomaton lexerAutomaton = new LexerAutomaton(transitionsMap, finalStatesToTokenTypesMap);
    return lexerAutomaton;
  }
}
