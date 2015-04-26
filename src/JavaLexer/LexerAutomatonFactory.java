package JavaLexer;

import java.util.HashMap;
import JavaLexer.LexerAutomaton.StateSymbolPair;

import static JavaLexer.Utils.*;

/**
 * Created by razvan on 4/26/15.
 */
public class LexerAutomatonFactory {
  public static LexerAutomaton lexerAutomatonInstance() {
    HashMap<StateSymbolPair, Integer> transitionsMap = new HashMap<StateSymbolPair, Integer>();

    // Numbers: starting with 0:
    transitionsMap.put(new StateSymbolPair(0, '0'), 1);
    // 0, 0l, 0L, 0f, 0F, 0d, 0D:
    transitionsMap.put(new StateSymbolPair(1, 'l'), 2);
    transitionsMap.put(new StateSymbolPair(1, 'L'), 2);
    transitionsMap.put(new StateSymbolPair(1, 'f'), 2);
    transitionsMap.put(new StateSymbolPair(1, 'F'), 2);
    transitionsMap.put(new StateSymbolPair(1, 'd'), 2);
    transitionsMap.put(new StateSymbolPair(1, 'D'), 2);
    addOperatorTransitionsToMap(transitionsMap, 1, 59);
    addSeparatorTransitionsToMap(transitionsMap, 1, 59);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 1, 59);
    transitionsMap.put(new StateSymbolPair(1, '/'), 59);

    addOperatorTransitionsToMap(transitionsMap, 2, 59);
    addSeparatorTransitionsToMap(transitionsMap, 2, 59);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 2, 59);
    transitionsMap.put(new StateSymbolPair(2, '/'), 59);

    // Hexadecimal:
    transitionsMap.put(new StateSymbolPair(1, 'x'), 3);
    transitionsMap.put(new StateSymbolPair(1, 'X'), 3);

    addHexadecimalDigitTransitionsToMap(transitionsMap, 3, 4);
    transitionsMap.put(new StateSymbolPair(3, '.'), 18);

    addHexadecimalDigitTransitionsToMap(transitionsMap, 4, 4);
    transitionsMap.put(new StateSymbolPair(4, 'p'), 12);
    transitionsMap.put(new StateSymbolPair(4, 'P'), 12);
    addOperatorTransitionsToMap(transitionsMap, 4, 60);
    addSeparatorTransitionsToMap(transitionsMap, 4, 60);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 4, 60);
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
    addOperatorTransitionsToMap(transitionsMap, 6, 60);
    addSeparatorTransitionsToMap(transitionsMap, 6, 60);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 6, 60);
    transitionsMap.put(new StateSymbolPair(6, '/'), 60);
    addHexadecimalDigitTransitionsToMap(transitionsMap, 6, 6);
    transitionsMap.put(new StateSymbolPair(6, 'p'), 12);
    transitionsMap.put(new StateSymbolPair(6, 'P'), 12);
    transitionsMap.put(new StateSymbolPair(6, '.'), 8);

    addOperatorTransitionsToMap(transitionsMap, 7, 60);
    addSeparatorTransitionsToMap(transitionsMap, 7, 60);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 7, 60);
    transitionsMap.put(new StateSymbolPair(7, '/'), 60);

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
    addDecimalDigitTransitionsToMap(transitionsMap, 12, 14);

    addDecimalDigitTransitionsToMap(transitionsMap, 13, 14);

    transitionsMap.put(new StateSymbolPair(14, 'f'), 15);
    transitionsMap.put(new StateSymbolPair(14, 'F'), 15);
    transitionsMap.put(new StateSymbolPair(14, 'd'), 15);
    transitionsMap.put(new StateSymbolPair(14, 'D'), 15);
    addOperatorTransitionsToMap(transitionsMap, 14, 61);
    addSeparatorTransitionsToMap(transitionsMap, 14, 61);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 14, 61);
    transitionsMap.put(new StateSymbolPair(14, '/'), 61);
    transitionsMap.put(new StateSymbolPair(14, '_'), 16);

    addOperatorTransitionsToMap(transitionsMap, 15, 61);
    addSeparatorTransitionsToMap(transitionsMap, 15, 61);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 15, 61);
    transitionsMap.put(new StateSymbolPair(15, '/'), 61);

    transitionsMap.put(new StateSymbolPair(16, '_'), 16);
    addDecimalDigitTransitionsToMap(transitionsMap, 16, 17);

    addDecimalDigitTransitionsToMap(transitionsMap, 17, 17);
    addOperatorTransitionsToMap(transitionsMap, 17, 61);
    addSeparatorTransitionsToMap(transitionsMap, 17, 61);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 17, 61);
    transitionsMap.put(new StateSymbolPair(17, '/'), 61);
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
    addOperatorTransitionsToMap(transitionsMap, 20, 62);
    addSeparatorTransitionsToMap(transitionsMap, 20, 62);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 20, 62);
    transitionsMap.put(new StateSymbolPair(20, '/'), 62);

    addOperatorTransitionsToMap(transitionsMap, 21, 62);
    addSeparatorTransitionsToMap(transitionsMap, 21, 62);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 21, 62);
    transitionsMap.put(new StateSymbolPair(21, '/'), 61);

    transitionsMap.put(new StateSymbolPair(22, '_'), 22);
    addBinaryDigitTransitionsToMap(transitionsMap, 22, 23);

    transitionsMap.put(new StateSymbolPair(23, 'l'), 21);
    transitionsMap.put(new StateSymbolPair(23, 'L'), 21);
    transitionsMap.put(new StateSymbolPair(23, '_'), 22);
    addBinaryDigitTransitionsToMap(transitionsMap, 23, 23);
    addOperatorTransitionsToMap(transitionsMap, 23, 62);
    addSeparatorTransitionsToMap(transitionsMap, 23, 62);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 23, 62);
    transitionsMap.put(new StateSymbolPair(23, '/'), 62);

    // Octal:
    addOctalDigitTransitionsToMap(transitionsMap, 1, 24);
    addOctalDigitTransitionsToMap(transitionsMap, 24, 24);
    transitionsMap.put(new StateSymbolPair(24, 'l'), 25);
    transitionsMap.put(new StateSymbolPair(24, 'L'), 25);
    transitionsMap.put(new StateSymbolPair(24, '_'), 26);
    addOperatorTransitionsToMap(transitionsMap, 24, 63);
    addSeparatorTransitionsToMap(transitionsMap, 24, 63);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 24, 63);
    transitionsMap.put(new StateSymbolPair(24, '/'), 63);

    addOperatorTransitionsToMap(transitionsMap, 25, 63);
    addSeparatorTransitionsToMap(transitionsMap, 25, 63);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 25, 63);
    transitionsMap.put(new StateSymbolPair(25, '/'), 63);

    transitionsMap.put(new StateSymbolPair(26, '_'), 26);
    addOctalDigitTransitionsToMap(transitionsMap, 26, 27);

    transitionsMap.put(new StateSymbolPair(27, '_'), 26);
    addOctalDigitTransitionsToMap(transitionsMap, 27, 27);
    addOperatorTransitionsToMap(transitionsMap, 27, 63);
    addSeparatorTransitionsToMap(transitionsMap, 27, 63);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 27, 63);
    transitionsMap.put(new StateSymbolPair(27, '/'), 63);

    // Decimal 0.*:
    transitionsMap.put(new StateSymbolPair(1, '.'), 28);
    transitionsMap.put(new StateSymbolPair(28, 'f'), 29);
    transitionsMap.put(new StateSymbolPair(28, 'F'), 29);
    transitionsMap.put(new StateSymbolPair(28, 'd'), 29);
    transitionsMap.put(new StateSymbolPair(28, 'D'), 29);
    addDecimalDigitTransitionsToMap(transitionsMap, 28, 30);
    transitionsMap.put(new StateSymbolPair(28, 'e'), 33);
    transitionsMap.put(new StateSymbolPair(28, 'E'), 33);
    addOperatorTransitionsToMap(transitionsMap, 28, 64);
    addSeparatorTransitionsToMap(transitionsMap, 28, 64);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 28, 64);
    transitionsMap.put(new StateSymbolPair(28, '/'), 64);

    addOperatorTransitionsToMap(transitionsMap, 29, 64);
    addSeparatorTransitionsToMap(transitionsMap, 29, 64);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 29, 64);
    transitionsMap.put(new StateSymbolPair(29, '/'), 64);

    transitionsMap.put(new StateSymbolPair(30, 'f'), 29);
    transitionsMap.put(new StateSymbolPair(30, 'F'), 29);
    transitionsMap.put(new StateSymbolPair(30, 'd'), 29);
    transitionsMap.put(new StateSymbolPair(30, 'D'), 29);
    addDecimalDigitTransitionsToMap(transitionsMap, 30, 30);
    transitionsMap.put(new StateSymbolPair(30, '_'), 31);
    transitionsMap.put(new StateSymbolPair(30, 'e'), 33);
    transitionsMap.put(new StateSymbolPair(30, 'E'), 33);

    transitionsMap.put(new StateSymbolPair(31, '_'), 31);
    addDecimalDigitTransitionsToMap(transitionsMap, 31, 32);

    transitionsMap.put(new StateSymbolPair(32, 'f'), 29);
    transitionsMap.put(new StateSymbolPair(32, 'F'), 29);
    transitionsMap.put(new StateSymbolPair(32, 'd'), 29);
    transitionsMap.put(new StateSymbolPair(32, 'D'), 29);
    transitionsMap.put(new StateSymbolPair(32, '_'), 31);
    addDecimalDigitTransitionsToMap(transitionsMap, 32, 32);
    transitionsMap.put(new StateSymbolPair(32, 'e'), 33);
    transitionsMap.put(new StateSymbolPair(32, 'E'), 33);
    addOperatorTransitionsToMap(transitionsMap, 32, 64);
    addSeparatorTransitionsToMap(transitionsMap, 32, 64);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 32, 64);
    transitionsMap.put(new StateSymbolPair(32, '/'), 64);

    transitionsMap.put(new StateSymbolPair(33, '+'), 34);
    transitionsMap.put(new StateSymbolPair(33, '-'), 34);
    addDecimalDigitTransitionsToMap(transitionsMap, 33, 35);

    addDecimalDigitTransitionsToMap(transitionsMap, 34, 35);

    addDecimalDigitTransitionsToMap(transitionsMap, 35, 35);
    transitionsMap.put(new StateSymbolPair(35, '_'), 36);
    transitionsMap.put(new StateSymbolPair(35, 'f'), 37);
    transitionsMap.put(new StateSymbolPair(35, 'F'), 37);
    transitionsMap.put(new StateSymbolPair(35, 'd'), 37);
    transitionsMap.put(new StateSymbolPair(35, 'D'), 37);
    addOperatorTransitionsToMap(transitionsMap, 35, 64);
    addSeparatorTransitionsToMap(transitionsMap, 35, 64);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 35, 64);
    transitionsMap.put(new StateSymbolPair(35, '/'), 64);

    transitionsMap.put(new StateSymbolPair(36, '_'), 36);
    addDecimalDigitTransitionsToMap(transitionsMap, 36, 38);

    addOperatorTransitionsToMap(transitionsMap, 37, 64);
    addSeparatorTransitionsToMap(transitionsMap, 37, 64);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 37, 64);
    transitionsMap.put(new StateSymbolPair(37, '/'), 64);

    transitionsMap.put(new StateSymbolPair(38, '_'), 36);
    addDecimalDigitTransitionsToMap(transitionsMap, 38, 38);
    transitionsMap.put(new StateSymbolPair(38, 'f'), 37);
    transitionsMap.put(new StateSymbolPair(38, 'F'), 37);
    transitionsMap.put(new StateSymbolPair(38, 'd'), 37);
    transitionsMap.put(new StateSymbolPair(38, 'D'), 37);
    addOperatorTransitionsToMap(transitionsMap, 38, 64);
    addSeparatorTransitionsToMap(transitionsMap, 38, 64);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 38, 64);
    transitionsMap.put(new StateSymbolPair(38, '/'), 64);

    // Decimal numbers starting with '1-9':
    addDecimalDigitTransitionsToMap(transitionsMap, 0, 39);
    transitionsMap.put(new StateSymbolPair(39, '.'), 28);
    transitionsMap.put(new StateSymbolPair(39, 'e'), 33);
    transitionsMap.put(new StateSymbolPair(39, 'E'), 33);
    addDecimalDigitTransitionsToMap(transitionsMap, 39, 39);
    transitionsMap.put(new StateSymbolPair(39, 'l'), 40);
    transitionsMap.put(new StateSymbolPair(39, 'L'), 40);
    transitionsMap.put(new StateSymbolPair(39, 'f'), 40);
    transitionsMap.put(new StateSymbolPair(39, 'F'), 40);
    transitionsMap.put(new StateSymbolPair(39, 'd'), 40);
    transitionsMap.put(new StateSymbolPair(39, 'D'), 40);
    transitionsMap.put(new StateSymbolPair(39, '_'), 41);
    addOperatorTransitionsToMap(transitionsMap, 39, 65);
    addSeparatorTransitionsToMap(transitionsMap, 39, 65);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 39, 65);
    transitionsMap.put(new StateSymbolPair(39, '/'), 65);

    addOperatorTransitionsToMap(transitionsMap, 40, 65);
    addSeparatorTransitionsToMap(transitionsMap, 40, 65);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 40, 65);
    transitionsMap.put(new StateSymbolPair(40, '/'), 65);

    transitionsMap.put(new StateSymbolPair(41, '_'), 41);
    addDecimalDigitTransitionsToMap(transitionsMap, 41, 42);

    transitionsMap.put(new StateSymbolPair(42, '.'), 28);
    transitionsMap.put(new StateSymbolPair(42, 'e'), 33);
    transitionsMap.put(new StateSymbolPair(42, 'E'), 33);
    transitionsMap.put(new StateSymbolPair(42, 'l'), 40);
    transitionsMap.put(new StateSymbolPair(42, 'L'), 40);
    transitionsMap.put(new StateSymbolPair(42, 'f'), 40);
    transitionsMap.put(new StateSymbolPair(42, 'F'), 40);
    transitionsMap.put(new StateSymbolPair(42, 'd'), 40);
    transitionsMap.put(new StateSymbolPair(42, 'D'), 40);
    transitionsMap.put(new StateSymbolPair(42, '_'), 41);
    addDecimalDigitTransitionsToMap(transitionsMap, 42, 42);
    addOperatorTransitionsToMap(transitionsMap, 42, 65);
    addSeparatorTransitionsToMap(transitionsMap, 42, 65);
    addWhitespaceLiteralTransitionsToMap(transitionsMap, 42, 65);
    transitionsMap.put(new StateSymbolPair(42, '/'), 65);

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

    for (char c = 0; c < 256; ++c) {
      if (c != '/') {
        transitionsMap.put(new StateSymbolPair(56, c), 55);
      } else {
        transitionsMap.put(new StateSymbolPair(56, c), 0);
      }
    }

    // Operators:
    char[] pureUnaryOperators = {'~', '?', ':'};
    for (char c : pureUnaryOperators) {
      transitionsMap.put(new StateSymbolPair(0, c), 68);
    }
    char[] acceptsEqualSuffix = {'=', '!', '^', '*', '/', '%'};
    for (char c : acceptsEqualSuffix) {
      transitionsMap.put(new StateSymbolPair(0, c), 69);
    }
    transitionsMap.put(new StateSymbolPair(0, '&'), 70);
    transitionsMap.put(new StateSymbolPair(0, '|'), 71);
    transitionsMap.put(new StateSymbolPair(0, '+'), 72);
    transitionsMap.put(new StateSymbolPair(0, '-'), 73);
    transitionsMap.put(new StateSymbolPair(0, '<'), 74);

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

    transitionsMap.put(new StateSymbolPair(77, '='), 68);
    transitionsMap.put(new StateSymbolPair(77, '>'), 78);

    transitionsMap.put(new StateSymbolPair(78, '='), 68);

    // Separators:
    addSeparatorTransitionsToMap(transitionsMap, 0, 79);

    // Final states:
    HashMap<Integer, Integer> finalStatesToTokenTypesMap = new HashMap<Integer, Integer>();
    finalStatesToTokenTypesMap.put(57, KEYWORD_IDENTIFIER_BOOLEAN_NULL);
    finalStatesToTokenTypesMap.put(58, KEYWORD_IDENTIFIER_BOOLEAN_NULL);
    finalStatesToTokenTypesMap.put(59, NUMBER_DECIMAL);
    finalStatesToTokenTypesMap.put(60, NUMBER_HEXADECIMAL);
    finalStatesToTokenTypesMap.put(61, NUMBER_HEXADECIMAL);
    finalStatesToTokenTypesMap.put(62, NUMBER_BINARY);
    finalStatesToTokenTypesMap.put(63, NUMBER_OCTAL);
    finalStatesToTokenTypesMap.put(64, NUMBER_DECIMAL);
    finalStatesToTokenTypesMap.put(65, NUMBER_DECIMAL);
    finalStatesToTokenTypesMap.put(66, CHARACTER);
    finalStatesToTokenTypesMap.put(67, STRING);
    for (int i = 68; i <= 78; ++i) {
      finalStatesToTokenTypesMap.put(i, OPERATOR);
    }
    finalStatesToTokenTypesMap.put(79, SEPARATOR);

    return new LexerAutomaton(transitionsMap, finalStatesToTokenTypesMap);
  }
}
